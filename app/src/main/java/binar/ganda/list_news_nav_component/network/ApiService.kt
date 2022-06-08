package binar.ganda.list_news_nav_component.network

import binar.ganda.list_news_nav_component.model.ResponsNewsItem
import binar.ganda.list_news_nav_component.model.ResponseUserItem
import binar.ganda.list_news_nav_component.model.ResponseUserPost
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("news")
    fun getAllNews() : Call<List<ResponsNewsItem>>

    @GET("user")
    fun getAllUser(
        @Query("username") username : String
    ) : Call<List<ResponseUserItem>>

    @POST("user")
    fun registerUser(
        @Body user: ResponseUserPost
    ) : Call<ResponseUserItem>
}