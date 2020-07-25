package com.cv.demo.kotapi.config

import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@EnableSwagger2
@SpringBootConfiguration
class Swagger {

    @Bean
    fun api(): Docket {

        val docket = Docket(DocumentationType.SWAGGER_2)
                .groupName("MyDemo")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cv.demo.kotapi.controller"))
                .paths(PathSelectors.any())
                .build()

        docket.directModelSubstitute(GregorianCalendar::class.java, Date::class.java)

        return docket
    }
}