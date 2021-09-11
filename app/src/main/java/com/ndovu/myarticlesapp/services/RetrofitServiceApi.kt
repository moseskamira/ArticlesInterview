package com.ndovu.myarticlesapp.services

import com.ndovu.myarticlesapp.models.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceApi {
    @Headers("Content-Type: application/json")
    @GET("svc/mostpopular/v2/viewed/{period}.json")
    fun returnAllArticles(@Path("period") period: Int, @Query("api-key") apiKey: String):
            Call<ArticleResponse>
}