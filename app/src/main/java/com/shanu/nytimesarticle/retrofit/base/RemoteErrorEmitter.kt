package com.shanu.nytimesarticle.retrofit.base

interface RemoteErrorEmitter {
    fun onError(errorType: ErrorType, msg: String)
}