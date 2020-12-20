package com.shanu.nytimesarticle.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shanu.nytimesarticle.R
import com.shanu.nytimesarticle.databinding.FragmentMainBinding
import com.shanu.nytimesarticle.model.ArticleResult
import com.shanu.nytimesarticle.utils.AppConstants
import com.shanu.nytimesarticle.utils.OnItemClickListener
import com.shanu.nytimesarticle.view.adapter.NYArticleAdapter
import com.shanu.nytimesarticle.viewmodel.MainFragViewModel
import com.shanu.nytimesarticle.viewmodel.common.kodeinViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein

class MainFragment() : Fragment(), KodeinAware, OnItemClickListener {

    override val kodein by kodein()
    private lateinit var fragMainBinding: FragmentMainBinding
    private val mainFragViewModel: MainFragViewModel by kodeinViewModel()

    private lateinit var nyArticleAdapter: NYArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragMainBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        setupArticleRecyclerView(fragMainBinding.listPopularArticles)

        activity?.let {
            mainFragViewModel.getNYTMostPopularArticle(
                "all-sections",
                "7",
                AppConstants.NY_POPULAR_ARTICLES_API_KEY
            )
                .observe(viewLifecycleOwner, Observer {
                    it.let {
                        var nyArticleAdapter =
                            fragMainBinding.listPopularArticles.adapter as NYArticleAdapter
                        if (it != null) {
                            nyArticleAdapter.setArticleItems(it.results as MutableList<ArticleResult>)
                        }
                        nyArticleAdapter.setListener(this)
                    }
                })
        }

        fragMainBinding.lifecycleOwner = viewLifecycleOwner
        return fragMainBinding.root
    }

    private fun setupArticleRecyclerView(listPopularArticles: RecyclerView) {
        nyArticleAdapter = NYArticleAdapter()
        listPopularArticles.layoutManager = LinearLayoutManager(requireContext())
        listPopularArticles.adapter = nyArticleAdapter
        nyArticleAdapter.setListener(this)
    }

    override fun onItemClick(position: Int, articleList: ArticleResult) {
        var articleDetailsBundle = Bundle()
        articleDetailsBundle.putString("nyAbstract", articleList.abstract)
        articleDetailsBundle.putString("nyByline", articleList.byline)
        articleDetailsBundle.putString("nyPublishedDate", articleList.publishedDate)
        findNavController().navigate(R.id.articleDetailFragment, articleDetailsBundle)
    }

}