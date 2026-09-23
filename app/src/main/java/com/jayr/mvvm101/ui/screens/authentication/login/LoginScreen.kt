package com.jayr.mvvm101.ui.screens.authentication.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jayr.mvvm101.R
import com.jayr.mvvm101.ui.Navigation.Home
import com.jayr.mvvm101.ui.Navigation.Login
import com.jayr.mvvm101.ui.Navigation.PasswordReset
import com.jayr.mvvm101.ui.screens.authentication.AuthUIstate
import com.jayr.mvvm101.ui.screens.authentication.AuthenticationViewModel
import com.jayr.mvvm101.ui.screens.authentication.registration.LottieAnimationWidget
import com.jayr.mvvm101.ui.screens.authentication.registration.ShowToastMessage

@Composable
fun LoginScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    authenticationViewModel: AuthenticationViewModel = viewModel()
){
    val emailInput = remember { TextFieldState("") }
    val passwordInput = remember { TextFieldState("") }
    val elementVerticalGap = 12.dp
    val seePassword = remember { mutableStateOf(false) }
    val uIstate = authenticationViewModel.uiState.collectAsState()
    val responseMessage = authenticationViewModel.responseMessage.collectAsState()


    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        LottieAnimationWidget(R.raw.authentication)
        Spacer(modifier = Modifier.height(elementVerticalGap))
        Text("Login to your account")
        Spacer(modifier = Modifier.height(elementVerticalGap))

        OutlinedTextField(
            state = emailInput,
            label = { Text("Email ")},
            shape = RoundedCornerShape(30.dp),
            placeholder = {Text("eg jd@example.com")},
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.mail),
                    contentDescription = stringResource(R.string.email_description)
                )
            }
        )
        Spacer(modifier = Modifier.height(elementVerticalGap))
        OutlinedTextField(
            state = passwordInput,
            label = { Text("Password")},
            shape = RoundedCornerShape(30.dp),
            placeholder = {Text("**********")},
//            use ouput transformation to obsucre the input
            outputTransformation = if(!seePassword.value){OutputTransformation{
                replace(0, length, "•".repeat(length))
            }}else{
                OutputTransformation{}
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.passkey),
                    contentDescription = stringResource(R.string.password_description)
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        seePassword.value = !seePassword.value
                    }
                ) {
                    if (seePassword.value){
                        Icon(
                            painter = painterResource(R.drawable.visibility_off),
                            contentDescription = stringResource(R.string.password_description)
                        )
                    }else{
                        Icon(
                            painter = painterResource(R.drawable.visibility_on),
                            contentDescription = stringResource(R.string.password_description)
                        )
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(elementVerticalGap))
        Row() {
            TextButton(
                onClick = {navHostController.navigate(Login)}
            ) {
                Text("Back to Login?")
            }
            TextButton(
                onClick = {navHostController.navigate(PasswordReset)}
            ) {
                Text("Oops! Forgot Password?")
            }

        }
        if (uIstate.value == AuthUIstate.isLoading){
            CircularProgressIndicator()
        }else{
            Button(
                onClick = {
                    authenticationViewModel.loginUser(email = emailInput.text as String, password = passwordInput.text as String)
                    if (uIstate.value == AuthUIstate.isSuccess) {
                        navHostController.navigate(Home)
                    }
                }
            ) {
                Text("Login")
            }
            ShowToastMessage(responseMessage.value)
        }


    }
}
