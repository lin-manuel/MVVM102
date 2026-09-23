package com.jayr.mvvm101.ui.screens.home

import androidx.lifecycle.ViewModel
import com.jayr.mvvm101.data.models.StudentModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {

//     state => data
    private var _student  = MutableStateFlow(StudentModel(name = "John Doe", year = 2000))

//    backing property
    val student = _student.asStateFlow()

//    methods (things to do to update the states)
fun baptizeStudent(name:String, year:Int){
    val newStudent = _student.value.copy(
        name = name,
        year= year
    )

    _student.value = newStudent
}
}