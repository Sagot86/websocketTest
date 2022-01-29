package com.ws.client.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun Any.getLogger(): Logger = this::class.run {
    val clazz = if (isCompanion) java.declaringClass else java
    LoggerFactory.getLogger(clazz)
}