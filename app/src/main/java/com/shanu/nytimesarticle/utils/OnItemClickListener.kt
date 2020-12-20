package com.shanu.nytimesarticle.utils

import com.shanu.nytimesarticle.model.ArticleResult

interface OnItemClickListener {
    fun onItemClick(
        position: Int,
        articleList: ArticleResult
    )

}