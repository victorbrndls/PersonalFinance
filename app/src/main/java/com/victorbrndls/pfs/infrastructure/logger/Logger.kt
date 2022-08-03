package com.victorbrndls.pfs.infrastructure.logger

import android.util.Log

class Logger {

    companion object {
        fun d(message: String) {
            Log.d("PFS", message)
        }

        fun e(message: String, throwable: Throwable) {
            Log.e("PFS", message, throwable)
        }
    }

}