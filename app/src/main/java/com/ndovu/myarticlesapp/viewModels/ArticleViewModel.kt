package com.ndovu.myarticlesapp.viewModels

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ndovu.myarticlesapp.models.ArticleResponse
import com.ndovu.myarticlesapp.services.RetrofitInstance
import com.ndovu.myarticlesapp.services.RetrofitServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.material.textfield.TextInputLayout
import com.ndovu.myarticlesapp.utils.ErrorHandler

class ArticleViewModel internal constructor(
    val errorDisplay: TextInputLayout,
    val progressBar: ProgressBar
) : ViewModel() {
    private val retrofitServiceApi: RetrofitServiceApi = RetrofitInstance().getRetrofitServiceApi()

    private val allArticlesResponse: MutableLiveData<ArticleResponse?> = MutableLiveData()


    fun fetchArticles(period: Int, apiKey: String): LiveData<ArticleResponse?> {
        retrofitServiceApi.returnAllArticles(period, apiKey)
            .enqueue(object : Callback<ArticleResponse?> {
                override fun onFailure(call: Call<ArticleResponse?>, throwable: Throwable) {
                    progressBar.visibility = View.GONE
                    errorDisplay.error = throwable.message
                }

                override fun onResponse(
                    call: Call<ArticleResponse?>, response
                    : Response<ArticleResponse?>
                ) {
                    progressBar.visibility = View.GONE
                    if (response.code() == 200) {
                        allArticlesResponse.postValue(response.body())
                    } else {
                        ErrorHandler.handleErrorMessage(response, errorDisplay)
                    }
                }
            })
        return allArticlesResponse
    }


}