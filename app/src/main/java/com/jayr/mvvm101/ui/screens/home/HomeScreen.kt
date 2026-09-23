package com.jayr.mvvm101.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jayr.mvvm101.ui.Navigation.About

@Composable
fun HomeScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = viewModel()
){

    val student = homeViewModel.student.collectAsState()


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("WELCOME HOME!!")
        Text("It has been a while ${student.value.name}, you started in ${student.value.year},right?")

        Button(
            onClick = {
                homeViewModel.baptizeStudent(student.value.name + " Kamau", 2026)
            }
        ) {
            Text("Baptize to Kamau")
        }
        Button(
            onClick = {
                navHostController.navigate(About)
            }
        ) {
            Text("Go to about page")
        }
    }
}