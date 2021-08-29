package org.badchat.http.server

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.postgresql.api.PostgresqlConnection
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryMetadata
import org.postgresql.ds.common.BaseDataSource
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.sql.SQLException
import javax.sql.DataSource

/**
 * From https://github.com/zonkyio/embedded-database-spring-test/issues/121
 */
@Component
class EmbeddedPostgresConnectionFactory(@Autowired dataSource: DataSource) : ConnectionFactory {
    private val dataSource: DataSource // an AOP proxy with a changing target database

    @Volatile
    private var config: BaseDataSource? = null // the last instance of the target database

    @Volatile
    private var factory: PostgresqlConnectionFactory? = null

    @Suppress("deprecation")
    private fun connectionFactory(): PostgresqlConnectionFactory {
        val freshConfig: BaseDataSource = try {
            dataSource.unwrap(BaseDataSource::class.java)
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }

        if (factory == null || config !== freshConfig) { // checks if the target database has changed or not
            config = freshConfig
            factory = PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                    .host(freshConfig.serverName)
                    .port(freshConfig.portNumber)
                    .username(freshConfig.user!!)
                    .password(freshConfig.password)
                    .database(freshConfig.databaseName)
                    .build()
            )
        }
        return factory as PostgresqlConnectionFactory
    }

    override fun create(): Publisher<PostgresqlConnection> = connectionFactory().create()

    override fun getMetadata(): ConnectionFactoryMetadata = connectionFactory().metadata

    init {
        this.dataSource = dataSource
    }
}
