package com.devteam.jetpackusers.ui.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devteam.jetpackusers.repository.DataRepository
import com.devteam.jetpackusers.utils.Logger

/**
 * View Model class corresponding to User list fragment
 */
class UserListViewModel : ViewModel() {
    // data repository instance
    val dataRepository = DataRepository()

    // page number which is used to get the list of users
    var pageNo = 1

    // user id which is used to fetch the user details
    var userId = 1

    // get the list of users for the given page id.
    // this uses a live data scope with suspended data repository method
    val users = liveData {
        val page = dataRepository.getUserFor(pageNo)
        Logger.d("**** ViewModel - received the page response - $page")
        emit(page.data)
    }

    // get the user details for the given user id
    // this uses a live data scope with suspended data repository method
    val userDetails = liveData {
        val userDataResponse = dataRepository.getUserDetail(userId)
        Logger.d("**** ViewModel - received the user details response - $userDataResponse")
        emit(userDataResponse.data)
    }
}