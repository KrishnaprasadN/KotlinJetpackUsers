package com.devteam.jetpackusers.ui.userlist

import android.view.View
import com.devteam.jetpackusers.io.model.User

interface UserListClickListner {
    fun onUserClicked(view : View, userId : Integer)
}