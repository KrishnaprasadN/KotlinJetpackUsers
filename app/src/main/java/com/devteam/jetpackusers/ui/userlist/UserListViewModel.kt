package com.devteam.jetpackusers.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devteam.jetpackusers.io.model.User

class UserListViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val  users: LiveData<List<User>> = MutableLiveData<List<User>>()
}