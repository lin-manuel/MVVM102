package com.jayr.mvvm101.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jayr.mvvm101.ui.screens.about.AboutScreen
import com.jayr.mvvm101.ui.screens.authentication.login.LoginScreen
import com.jayr.mvvm101.ui.screens.authentication.passwordReset.PasswordResetScreen
import com.jayr.mvvm101.ui.screens.authentication.registration.RegisterScreen
import com.jayr.mvvm101.ui.screens.home.HomeScreen

@Composable
fun Navigation(
    modifier: Modifier,
    navHostController: NavHostController
){

    NavHost(
        navController = navHostController,
        startDestination = Register
    ){
        composable<Home> {
            HomeScreen(
            modifier = modifier,
            navHostController = navHostController
        ) }
        composable<About> {
            AboutScreen(
                modifier = modifier,
                navHostController = navHostController
            )
        }
        composable <Register>{
            RegisterScreen(
                modifier = modifier,
                navHostController = navHostController
            )
        }
        composable <Login>{
            LoginScreen(
                modifier = modifier,
                navHostController = navHostController
            )
        }
        composable <PasswordReset>{
            PasswordResetScreen(
                modifier = modifier,
                navHostController = navHostController
            )
        }
    }
}