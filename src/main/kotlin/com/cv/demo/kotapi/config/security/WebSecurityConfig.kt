package com.cv.demo.kotapi.config.security

import org.springframework.boot.SpringBootConfiguration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Order(value = 1)
@EnableWebSecurity
@SpringBootConfiguration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
    }
}