package org.kotlin.springboot

import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

@SpringBootApplication
class Application {

    @Bean
    fun transactionManager(dataSource: DataSource) = SpringTransactionManager(dataSource)
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}