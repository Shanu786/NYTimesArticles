package com.shanu.nytimesarticle.repository

import com.shanu.nytimesarticle.model.NYTimesArticle
import com.shanu.nytimesarticle.repository.base.BaseRepository
import com.shanu.nytimesarticle.retrofit.api.ApiInterface

class MainFragRepository(var apiInterface: ApiInterface) : BaseRepository() {

    suspend fun getLyricsRepository(
        sections: String,
        period: String,
        articlesApiKey: String
    ): NYTimesArticle? {
        return getSafeApiCall(
            call = {
                apiInterface.getNYTimesMostPopularArticlesResponse(
                    sections,
                    period,
                    articlesApiKey
                )
            },
            errorMessage = "Error while fetching the details..."
        )
    }
}