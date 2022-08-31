package com.victorbrndls.pfs

import android.app.Application
import com.victorbrndls.pfs.infrastructure.importer.CsvExpenseImporter
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@HiltAndroidApp
class PfsApplication : Application() {

    private val scope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var csvExpenseImporter: Provider<CsvExpenseImporter>

    override fun onCreate() {
        super.onCreate()

        scope.launch { csvExpenseImporter.get().import() }
    }

}