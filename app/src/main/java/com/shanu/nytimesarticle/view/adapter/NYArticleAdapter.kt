package com.shanu.nytimesarticle.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shanu.nytimesarticle.R
import com.shanu.nytimesarticle.databinding.LayoutArticleRowBinding
import com.shanu.nytimesarticle.model.ArticleResult
import com.shanu.nytimesarticle.utils.OnItemClickListener
import com.shanu.nytimesarticle.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.layout_article_row.view.*

class NYArticleAdapter() : RecyclerView.Adapter<NYArticleAdapter.ArticleViewHolder>() {

    private var articleResult: MutableList<ArticleResult>? = ArrayList()
    private lateinit var listener: OnItemClickListener
    private var selectedItemPosition = 0
    lateinit var layoutArticleRowBinding: LayoutArticleRowBinding

    init {
        this.articleResult = mutableListOf()
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setArticleItems(articleList: MutableList<ArticleResult>) {
        this.articleResult = articleList
        selectedItemPosition = articleList.size - 1
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        layoutArticleRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.layout_article_row,
            parent,
            false
        )
        return ArticleViewHolder(layoutArticleRowBinding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindArticleDetails(articleResult!![position])
    }

    override fun getItemCount(): Int {
        return articleResult!!.size
    }

    inner class ArticleViewHolder(private var layoutArticleRowBinding: LayoutArticleRowBinding) :
        RecyclerView.ViewHolder(layoutArticleRowBinding.root) {

        fun bindArticleDetails(articleList: ArticleResult) {

            if (layoutArticleRowBinding.articleViewModel == null) {
                layoutArticleRowBinding.articleViewModel =
                    ArticleViewModel(articleList, itemView.context)
            } else {
                layoutArticleRowBinding.articleName.text = articleList.abstract
                layoutArticleRowBinding.articleBy.text = articleList.byline
                layoutArticleRowBinding.articleDate.text = articleList.publishedDate
                layoutArticleRowBinding.executePendingBindings()
            }

            layoutArticleRowBinding.root.articleHolder.setOnClickListener {
                selectedItemPosition = layoutPosition
                listener.onItemClick(layoutPosition, articleList)
                notifyDataSetChanged()
            }
        }

    }

}