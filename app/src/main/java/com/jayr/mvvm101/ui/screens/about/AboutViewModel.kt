package com.jayr.mvvm101.ui.screens.about

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AboutViewModel: ViewModel() {

//    state (this is basically the data),
    private var _count = MutableStateFlow(0)

//    backing property (reference the state and make sure it is not updated
    val count = _count.asStateFlow()

    // methods
    fun addOne(){
        _count.value ++
    }

}