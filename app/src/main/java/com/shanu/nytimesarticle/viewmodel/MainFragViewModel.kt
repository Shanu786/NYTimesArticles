package com.shanu.nytimesarticle.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shanu.nytimesarticle.model.NYTimesArticle
import com.shanu.nytimesarticle.repository.MainFragRepository

class MainFragViewModel (application: Application, var mainFragRepository: MainFragRepository) : ViewModel() {

    fun getNYTMostPopularArticle(articleModel:String, period:String, articlesApiKey:String): LiveData<NYTimesArticle?> {
        return liveData {
            var lyricsDetails = mainFragRepository.getLyricsRepository(articleModel, period, articlesApiKey)
            try {
                emit(lyricsDetails)
            } catch (e: Exception) {
            }
        }
    }
}