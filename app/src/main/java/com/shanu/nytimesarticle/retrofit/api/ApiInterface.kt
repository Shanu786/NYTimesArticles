package com.shanu.nytimesarticle.retrofit.api

import com.shanu.nytimesarticle.model.NYTimesArticle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @Headers("Accept:application/json")
    @GET("/svc/mostpopular/v2/mostviewed/{section}/{period}.json?")
    suspend fun getNYTimesMostPopularArticlesResponse(
        @Path("section") artist: String,
        @Path("period") title: String,
        @Query("api-key") apiKey: String
    ): Response<NYTimesArticle>
}