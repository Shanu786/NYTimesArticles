package com.shanu.nytimesarticle.retrofit.base

enum class ErrorType {
    NETWORK, // IO
    TIMEOUT, // Socket
    UNKNOWN //Anything else
}