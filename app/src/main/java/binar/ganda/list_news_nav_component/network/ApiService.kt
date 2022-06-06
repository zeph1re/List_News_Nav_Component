package binar.ganda.list_news_nav_component.network

import binar.ganda.list_news_nav_component.model.ResponsNewsItem
import binar.ganda.list_news_nav_component.model.ResponseUserItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("news")
    fun getAllNews() : Call<List<ResponsNewsItem>>

    @GET("user")
    fun getAllUser(
        @Query("username") username : String
    ) : Call<List<ResponseUserItem>>
}