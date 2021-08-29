package org.badchat.http.server

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres
import org.badchat.messages.ctrl.getLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import javax.annotation.PreDestroy
import javax.sql.DataSource

/**
 * Configuration active during dev profile that starts and stop postgres with the server.
 */
@Configuration
@Profile("dev")
class EmbeddedPostgresConfig {
    val pg: EmbeddedPostgres = EmbeddedPostgres.start()

    @Bean
    // choose embedded postgres as datasource
    fun dataSource(): DataSource = pg.postgresDatabase

    @PreDestroy
    fun shutdownPostgres() {
        log.info("Shutting down embedded Postgres")
        pg.close()
    }

    companion object {
        private val log = getLogger()
    }
}