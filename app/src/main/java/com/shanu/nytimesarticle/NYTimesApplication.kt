package com.shanu.nytimesarticle

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.shanu.findLyrics.viewmodel.common.ViewModelFactory
import com.shanu.nytimesarticle.repository.MainFragRepository
import com.shanu.nytimesarticle.retrofit.api.ApiInterface
import com.shanu.nytimesarticle.retrofit.base.RetrofitNetwork
import com.shanu.nytimesarticle.retrofit.interceptor.NetworkConnectionInterceptor
import com.shanu.nytimesarticle.viewmodel.MainFragViewModel
import com.shanu.nytimesarticle.viewmodel.common.bindViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class NYTimesApplication : Application(), KodeinAware {

    companion object {
        lateinit var instance: NYTimesApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@NYTimesApplication))

        // Common Components
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind<ApiInterface>() with singleton { RetrofitNetwork.getApiInterface(instance()) }
        bind<ViewModelProvider.Factory>() with singleton {
            ViewModelFactory(
                kodein.direct
            )
        }

        // For MainFragment ViewModel & Repository
        bindViewModel<MainFragViewModel>() with provider {
            MainFragViewModel(
                instance(),
                instance()
            )
        }
        bind() from singleton { MainFragRepository(instance()) }
    }

}