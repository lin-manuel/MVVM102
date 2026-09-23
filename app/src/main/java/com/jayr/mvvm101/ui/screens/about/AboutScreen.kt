package com.jayr.mvvm101.ui.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController


@Composable
fun AboutScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    aboutViewModel: AboutViewModel = viewModel()
){
//    get the state
    val count = aboutViewModel.count.collectAsState().value

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("COUNT: $count")
        Button(
            onClick = {
                aboutViewModel.addOne()
            }
        ) {
            Text("Add One")
        }
    }

}