package com.devteam.jetpackusers.io

import com.devteam.jetpackusers.io.model.Page
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroService {

  @GET("users")
  suspend fun getUsersForPage(@Query("page")pageId:Int): Page
  //suspend fun getUsersForPage(): Page
  //fun getUsersForPage(): Call<Page>

}