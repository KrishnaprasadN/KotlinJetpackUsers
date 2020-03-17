package com.devteam.jetpackusers.repository

import com.devteam.jetpackusers.io.RetroService
import com.devteam.jetpackusers.io.RetrofitInstance
import com.devteam.jetpackusers.io.model.Page


class DataRepository {
    var retroService: RetroService = RetrofitInstance.retroService

    suspend fun getUserFor(pageId: Int): Page = retroService.getUsersForPage(pageId)
}