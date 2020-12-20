package com.shanu.nytimesarticle.viewmodel

import android.content.Context
import com.shanu.nytimesarticle.model.ArticleResult
import java.util.*

class ArticleViewModel(
    private var getDBValues: ArticleResult, private var context: Context
) : Observable() {

    val articleName: String?
        get() = getDBValues.abstract

    val articleBy: String?
        get() = getDBValues.byline

    val articlePublishedDate: String?
        get() = getDBValues.publishedDate
}