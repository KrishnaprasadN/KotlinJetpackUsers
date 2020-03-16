package com.devteam.jetpackusers.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devteam.jetpackusers.io.RetroService
import com.devteam.jetpackusers.io.RetrofitInstance
import com.devteam.jetpackusers.io.model.Page
import com.devteam.jetpackusers.io.model.User
import com.devteam.jetpackusers.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DataRepository {

    var retroService: RetroService = RetrofitInstance.retroService

    suspend fun getUserFor(pageId: Int): Page = retroService.getUsersForPage(pageId)

    /*fun getUsersOf(pageId: Int): LiveData<List<User>> {
        Logger.d("DataManager - > getAllEmployees");
        var data: MutableLiveData<List<User>> = MutableLiveData()

        val url = retroService.getUsersForPage().request().url().toString()
        Logger.d("DataManager - > getUsersForPage Url = $url")
        retroService.getUsersForPage().enqueue(object : Callback<Page> {
            override fun onResponse(call: Call<Page>, response: Response<Page>) {
                Logger.d("DataManager - > getUsersForPage - > Call ${call.toString()}");
                Logger.d("DataManager - > getUsersForPage - > Response ${response}");
                data.value = response.body()?.data
            }

            override fun onFailure(call: Call<Page>, t: Throwable) {
                // set empty list
                data.value = listOf()
            }
        })

        return data
    }*/
}