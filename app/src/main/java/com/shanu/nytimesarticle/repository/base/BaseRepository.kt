package com.shanu.nytimesarticle.repository.base

import android.util.Log
import com.shanu.nytimesarticle.retrofit.api.Results
import com.shanu.nytimesarticle.retrofit.model.APIStatus
import com.shanu.nytimesarticle.retrofit.model.ProgressStatus
import com.shanu.nytimesarticle.retrofit.model.Status
import com.shanu.nytimesarticle.view.activity.NYArticlesActivity.Companion.displayErrorMsg
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

open class BaseRepository {

    private var apiStatus: APIStatus = APIStatus()

    suspend fun <T : Any> getSafeApiCall(
        call: suspend () -> Response<T>,
        errorMessage: String,
        progress: ProgressStatus = ProgressStatus.NORMAL
    ): T? {
        if (progress == ProgressStatus.SHOW || progress == ProgressStatus.NORMAL) {
            apiStatus.status = Status.LOADING
            displayErrorMsg.postValue(apiStatus)
        }
        val result: Results<T> = getSafeApiResult(call, errorMessage)
        var data: T? = null
        when (result) {
            is Results.Success -> {
                data = result.data

                if (progress == ProgressStatus.HIDE || progress == ProgressStatus.NORMAL) {
                    apiStatus.status = Status.SUCCESS
                    displayErrorMsg.postValue(apiStatus)
                }
            }

            is Results.Error -> {
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
                apiStatus.status = Status.FAILED
                apiStatus.message = result.exception.message
                displayErrorMsg.postValue(apiStatus)
            }
        }
        return data
    }

    private suspend fun <T : Any> getSafeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): Results<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                if (!response.body()!!.equals("")) {
                    Results.Success(response.body()!!)
                } else {
                    Results.Error(IOException("Response not received. Please try again"))
                }
            } else {
                Results.Error(IOException("An issue occurred while processing your request"))
            }
        } catch (ex: SocketTimeoutException) {
            return Results.Error(IOException("Timeout. Please try again later"))
        } catch (ex: IOException) {
            return Results.Error(IOException("API Error. Please try again later"))
        } catch (ex: Exception) {
            Results.Error(IOException("An issue occurred while processing your request, Custom ERROR - $errorMessage"))
        }
    }

}