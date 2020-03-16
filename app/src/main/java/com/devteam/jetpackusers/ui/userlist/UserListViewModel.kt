package com.devteam.jetpackusers.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devteam.jetpackusers.io.model.User
import com.devteam.jetpackusers.repository.DataRepository
import androidx.lifecycle.liveData

class UserListViewModel : ViewModel() {
    val dataRepository = DataRepository()

    /*val users = liveData {
        val page = dataRepository.getUserFor(5)
        emit(page.data)
    }*/

    var users: LiveData<List<User>> = MutableLiveData()

    fun getUsersOfPage(pageId: Int): LiveData<List<User>> {
        users = dataRepository.getUsersOf(pageId)
        return users
    }
}