package com.cv.demo.kotapi.config.security

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

class MvcWebApplicationInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {

    override fun getServletMappings(): Array<String> = arrayOf("/")
    override fun getServletConfigClasses(): Array<Class<*>>? = arrayOf(WebSecurityConfig::class.java)
    override fun getRootConfigClasses(): Array<Class<*>>? = arrayOf(WebSecurityConfig::class.java)
}