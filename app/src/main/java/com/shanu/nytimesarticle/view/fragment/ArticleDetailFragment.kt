package com.shanu.nytimesarticle.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.shanu.nytimesarticle.R
import com.shanu.nytimesarticle.databinding.FragmentArticleDetailsBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

class ArticleDetailFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private lateinit var fragmentArticleDetailsBinding: FragmentArticleDetailsBinding

    private lateinit var nyAbstract: String
    private lateinit var nyByline: String
    private lateinit var nyPublishedDate: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentArticleDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_article_details, container, false)

        nyAbstract = arguments?.getString("nyAbstract").toString()
        nyByline = arguments?.getString("nyByline").toString()
        nyPublishedDate = arguments?.getString("nyPublishedDate").toString()

        setUI()

        fragmentArticleDetailsBinding.lifecycleOwner = viewLifecycleOwner
        return fragmentArticleDetailsBinding.root
    }

    private fun setUI() {
        fragmentArticleDetailsBinding.detailsArticle.text = nyAbstract
        fragmentArticleDetailsBinding.detailsArticleBy.text = "Article By: $nyByline"
        fragmentArticleDetailsBinding.detailsPostedDate.text = "Article Date: $nyPublishedDate"
    }

}