package com.shihab.kotlintoday.feature.mvvm.util

import androidx.lifecycle.LiveData

interface NetworkListener {
    fun onSuccess(api_code: Int, string: LiveData<String>)
    fun onFailure(api_code: Int, string: String)
    fun onStart(api_code: Int, string: String)
}