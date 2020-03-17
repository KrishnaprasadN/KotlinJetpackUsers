package com.devteam.jetpackusers.ui.userdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.devteam.jetpackusers.repository.DataRepository
import com.devteam.jetpackusers.utils.Logger

/**
 * View Model class corresponding to User list fragment
 */
class UserDetailViewModel : ViewModel() {
    // data repository instance
    val dataRepository = DataRepository()


}