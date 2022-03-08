package com.android.nytime.data.repository

import com.android.nytime.di.module.NetworkModule
import com.android.nytime.presentation.news.MostViewModel
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

class KoinModuleTest : AutoCloseKoinTest() {

    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(Level.DEBUG)
            modules(listOf(MostViewModel, NetworkModule))
        }.checkModules()
    }

}