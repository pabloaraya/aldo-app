package com.qubits.fw3.corecommon.testing

import java.util.*

import kotlin.random.Random

object RandomFactory {

    fun randomInt(): Int = Random.nextInt(0, 1001)

    fun randomString(): String = UUID.randomUUID().toString()
}