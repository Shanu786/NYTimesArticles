package com.shanu.nytimesarticle.view.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.shanu.nytimesarticle.retrofit.model.APIStatus
import com.shanu.nytimesarticle.retrofit.model.Status

class NYArticlesActivity : AppCompatActivity() {
    companion object {
        var displayErrorMsg: MutableLiveData<APIStatus> = MutableLiveData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayErrorMsg.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.FAILED -> {
                    displayErrorDialog(it.message!!)
                }
                Status.SUCCESS -> {
                }
            }
        })
    }

    fun getContext(): Context {
        return this
    }

    private fun displayErrorDialog(msg: String) {
    }

}