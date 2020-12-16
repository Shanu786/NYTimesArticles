package com.shanu.nytimesarticle.retrofit.model

data class APIStatus(
    var status : Status? = Status.LOADING,
    var message : String? = ""
)

enum class Status{
    LOADING,
    SUCCESS,
    FAILED
}

enum class ProgressStatus{
    HIDE,
    SHOW,
    NORMAL,
    NO_LOADER
}