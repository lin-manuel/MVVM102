package com.jayr.mvvm101.ui.screens.authentication.passwordReset

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
import com.jayr.mvvm101.ui.Navigation.Login
import com.jayr.mvvm101.ui.Navigation.PasswordReset
import com.jayr.mvvm101.ui.Navigation.Register
import com.jayr.mvvm101.ui.screens.authentication.AuthUIstate
import com.jayr.mvvm101.ui.screens.authentication.AuthenticationViewModel
import com.jayr.mvvm101.ui.screens.authentication.registration.LottieAnimationWidget
import com.jayr.mvvm101.ui.screens.authentication.registration.ShowToastMessage

@Composable
fun PasswordResetScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    authenticationViewModel: AuthenticationViewModel = viewModel()
){

    val emailInput = remember { TextFieldState("") }
    val elementVerticalGap = 12.dp
    val uIstate = authenticationViewModel.uiState.collectAsState()
    val responseMessage = authenticationViewModel.responseMessage.collectAsState()


    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        LottieAnimationWidget(R.raw.authentication)
        Spacer(modifier = Modifier.height(elementVerticalGap))
        Text("Get password reset link ")
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

        Spacer(modifier = Modifier.height(elementVerticalGap))
        Row() {
            TextButton(
                onClick = {navHostController.navigate(Login)}
            ) {
                Text("Back to Login?")
            }
            TextButton(
                onClick = {navHostController.navigate(Register)}
            ) {
                Text("Oops! No account?")
            }

        }
        if (uIstate.value == AuthUIstate.isLoading){
            CircularProgressIndicator()
        }else{
            Button(
                onClick = {
                    authenticationViewModel.sendPasswordResetToUser(email = emailInput.text as String)
                    if (uIstate.value == AuthUIstate.isSuccess) {
                        navHostController.navigate(Login)
                    }
                }
            ) {
                Text("Get Password reset ")
            }
            ShowToastMessage(responseMessage.value)
        }


    }
}