package com.defaultxyz.issnow.utils

import android.os.AsyncTask

class DbExecutor(private val action: () -> Unit): AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg p0: Void?): Void? {
        action.invoke()
        return null
    }
}