package com.example.Auzaar.view.login

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.Auzaar.R
import com.example.Auzaar.model.Response
import com.example.Auzaar.navigation.Screen
import com.example.Auzaar.ui.theme.oswald
import com.example.Auzaar.view.components.ProgressBar
import kotlinx.coroutines.launch


@Composable
fun LoginPage(modifier: Modifier = Modifier, viewModel: loginViewModel, navController: NavController) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isEmailValid by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier
            .padding(all = 5.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.padding(vertical = 40.dp))


        Image(
            painter = painterResource(id = R.drawable.hamsplash), contentDescription = "",
            Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )


        Spacer(modifier = Modifier.padding(vertical = 20.dp, horizontal = 5.dp))

        Text(text = "Hi there! Please Login" ,modifier.align(Alignment.CenterHorizontally),style = MaterialTheme.typography.h1.copy(fontFamily = oswald, fontSize = 30.sp))

        Card(
            modifier
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colors.primary
                ), elevation = 5.dp
        ) {
            Column(Modifier.padding(5.dp)) {

                Email(email = email, onEmailChange = { newValue ->
                    email = newValue
                    isEmailValid = email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(newValue).matches()
                }, isEmailValid = isEmailValid)

                Password(password = password, onPasswordChange = { password = it }, imeAction = ImeAction.Done)

                LoginButton(onButtonClicked = {
                    viewModel.signIn(email, password) })

            }
        }

        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "'OR'",modifier.align(Alignment.CenterHorizontally), style = MaterialTheme.typography.h1.copy(fontFamily = oswald, fontSize = 30.sp))

        Spacer(modifier = Modifier.padding(10.dp))
        CreateAccountButton(navController = navController)
        when(val response = viewModel.signInState.value) {
            is Response.Loading -> ProgressBar()
            is Response.Success -> if (response.data) {
                LaunchedEffect(response.data) {
                    navController.navigate(Screen.HomeScreen.route)
                }
            }
            is Error -> LaunchedEffect(Unit) {
                Log.d("tag", "suck it up")
            }
        }

    }
}


@Composable
fun Email(
    modifier: Modifier = Modifier, email: String, onEmailChange: (String) -> Unit, isEmailValid: Boolean
) {


    OutlinedTextField(
        value = email, onValueChange = onEmailChange,
        modifier
            .padding(5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),

        maxLines = 1,
        isError = !isEmailValid,
        label = { Text(text = "Your Email") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )

    )
    if (!isEmailValid) {
        Text(
            text = "Invalid Email",
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun Password(
    modifier: Modifier = Modifier, password: String, onPasswordChange: (String) -> Unit, imeAction: ImeAction
) {

    OutlinedTextField(
        value = password, onValueChange = onPasswordChange,
        modifier
            .padding(5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),

        maxLines = 1,
        label = { Text(text = "Your password") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction
        )

    )
}

@Composable
fun LoginButton(modifier: Modifier = Modifier, onButtonClicked: () -> Unit) {
    OutlinedButton(
        onClick = onButtonClicked,
        modifier
            .padding(5.dp)
            .fillMaxWidth()
            .border(width = 2.dp, color = MaterialTheme.colors.primary, shape = RoundedCornerShape(20.dp))
            .height(50.dp)
    ) {
        Text(text = "Login")
    }

  
}

@Composable
fun CreateAccountButton(modifier: Modifier = Modifier, navController: NavController) {
    val scope = rememberCoroutineScope()
    Button(onClick = {
                     scope.launch {
                         navController.navigate(Screen.SignUpPage.route)
                     }
    },
        modifier
            .padding(5.dp)
            .fillMaxWidth()
            .shadow(3.dp, shape = RoundedCornerShape(20.dp))
            .height(50.dp),
        content = { Text(text = "Create Account") })
}

