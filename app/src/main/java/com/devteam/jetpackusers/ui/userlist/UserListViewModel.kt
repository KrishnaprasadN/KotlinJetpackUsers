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
    private val dataRepository = DataRepository()

    // page number which is used to get the list of users
    private var pageNo = 1

    // get the list of users for the given page id.
    // this uses a live data scope with suspended data repository method
    val users = liveData {
        Logger.d("**** ViewModel - Calling get user for $pageNo")
        try {
            val page = dataRepository.getUserFor(pageNo)
            Logger.d("**** ViewModel - received the page response - $page")
            emit(page.data)
        } catch (e: Exception) {
            Logger.d("**** Exception $e")
            emit(listOf())
        }
    }
}