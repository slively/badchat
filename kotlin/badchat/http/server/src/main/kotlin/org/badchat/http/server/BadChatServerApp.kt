package org.badchat.http.server

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres
import org.badchat.messages.ctrl.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.undertow.UndertowReactiveWebServerFactory
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import javax.annotation.PreDestroy
import javax.sql.DataSource

@SpringBootApplication(
    // this makes scanning annotations in other packages work
    scanBasePackages = ["org.badchat"]
)
@EnableR2dbcRepositories(basePackages = ["org.badchat"])
class BadChatServerApp {

    @Bean
    // choose undertow as embedded web server
    fun embeddedServer(): ReactiveWebServerFactory = UndertowReactiveWebServerFactory()
}

fun main(args: Array<String>) {
    runApplication<BadChatServerApp>(*args)
}
