package com.shanu.nytimesarticle.retrofit.base

data class Error(
    var ErrorCode: String,
    var ErrorDescription: String,
    var MoreInfo: MoreInfo,
    var httpCode: Int
)