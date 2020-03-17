package com.devteam.jetpackusers.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devteam.jetpackusers.io.model.User
import com.devteam.jetpackusers.repository.DataRepository
import com.devteam.jetpackusers.utils.Logger


class UserListViewModel : ViewModel() {
    val dataRepository = DataRepository()
    var pageNo = 1

    val users = liveData {
        val page = dataRepository.getUserFor(pageNo)
        Logger.d("**** ViewModel - received the response - $page")
        emit(page.data)
    }

    /*var users: LiveData<List<User>> = MutableLiveData()

    fun getUsersOfPage(pageId: Int): LiveData<List<User>> {
        users = dataRepository.getUsersOf(pageId)
        return users
    }*/
}