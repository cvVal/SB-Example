package com.cv.demo.kotapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class MyDemoMain

fun main(args: Array<String>) {
    runApplication<MyDemoMain>(*args)
}