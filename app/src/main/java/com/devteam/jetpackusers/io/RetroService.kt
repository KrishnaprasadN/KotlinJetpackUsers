package com.devteam.jetpackusers.io

import com.devteam.jetpackusers.io.model.Page
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

  @GET("users")
  suspend fun getUsersForPage(@Query("page")pageId:Int): Page

}