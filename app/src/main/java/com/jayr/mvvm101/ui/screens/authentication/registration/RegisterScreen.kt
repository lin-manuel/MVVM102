package com.jayr.mvvm101.ui.screens.authentication.registration
 
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jayr.mvvm101.R
import com.jayr.mvvm101.ui.Navigation.Home
import com.jayr.mvvm101.ui.Navigation.Login
import com.jayr.mvvm101.ui.Navigation.PasswordReset
import com.jayr.mvvm101.ui.screens.authentication.AuthUIstate
import com.jayr.mvvm101.ui.screens.authentication.AuthenticationViewModel

@Composable
fun RegisterScreen(
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
        Text("Create account ")
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
                    authenticationViewModel.registerUser(email = emailInput.text as String, password = passwordInput.text as String)
                    if (uIstate.value == AuthUIstate.isSuccess) {
                        navHostController.navigate(Login)
                    }
                }
            ) {
                Text("Register ")
            }
            ShowToastMessage(responseMessage.value)
        }


    }
}

@Composable
fun ShowToastMessage(message:String){
    val duration = Toast.LENGTH_SHORT
    val context = LocalContext.current
    val toast = Toast.makeText(context, message, duration) // in Activity
    toast.show()
}

@Composable
fun LottieAnimationWidget(lottieAnimation:Int) {
    val configuration = LocalWindowInfo.current

    // Width and height available to your layout in Dp
    val screenWidthDp = configuration.containerSize.width.dp
    val screenHeightDp = configuration.containerSize.height.dp
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieAnimation))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        modifier = Modifier
            .height(screenHeightDp * 0.1f)
            .width(screenWidthDp * 0.3f),
        composition = composition,
        progress = { progress },
    )
}