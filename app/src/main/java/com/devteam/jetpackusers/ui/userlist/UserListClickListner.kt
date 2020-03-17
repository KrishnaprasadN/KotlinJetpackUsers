package com.devteam.jetpackusers.ui.userlist

import com.devteam.jetpackusers.io.model.User

interface UserListClickListner {
    fun onUserClicked(user : User)
}