package com.shanu.nytimesarticle.retrofit.api

import com.shanu.nytimesarticle.model.NYTimesArticle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiInterface {

    @Headers("Content-Type: application/json")

    @GET("/mostviewed/{section}/{period}.json?api-key={samplekey}")
    suspend fun getNYTimesMostPopularArticlesResponse(
        @Path("section") artist: String,
        @Path("period") title: String,
        @Path("samplekey") sampleKey: String
    ): Response<NYTimesArticle>
}