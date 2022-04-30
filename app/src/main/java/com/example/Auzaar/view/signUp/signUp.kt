package com.example.Auzaar.view.signUp

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.Auzaar.ui.theme.aksar
import com.example.Auzaar.ui.theme.oswald
import com.example.Auzaar.view.components.ProgressBar
import com.example.Auzaar.view.login.Email
import com.example.Auzaar.view.login.Password
import kotlinx.coroutines.launch

@Composable
fun SignUpPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: signUpViewModel
) {

    // all  the necessary assignments and initializations
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var fullName by rememberSaveable { mutableStateOf("") }
    var cnic by rememberSaveable { mutableStateOf("") }
    var isEmailValid by rememberSaveable { mutableStateOf(true) }
    var isFullNameValid by rememberSaveable { mutableStateOf(true) }
    var isCnicValid by rememberSaveable { mutableStateOf(true) }
    var isPhoneValid by rememberSaveable { mutableStateOf(true) }


    // Main column start
    Column(
        modifier
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "FILL THE",
            modifier.wrapContentHeight(),
            style = MaterialTheme.typography.h1.copy(
                fontFamily = oswald,
                color = Color.DarkGray,
                fontSize = 35.sp
            ),
            textAlign = TextAlign.Left
        )
        Text(
            text = "following details to create account!",
            modifier.wrapContentHeight(),
            style = MaterialTheme.typography.h3.copy(
                fontFamily = aksar,
                color = Color.Black,
                fontSize = 30.sp
            ),
            textAlign = TextAlign.Left
        )

        //  authentication check

        when (val response = viewModel.signUpState.value) {
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
        Divider(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 20.dp),
            thickness = 3.dp,
            color = Color.Black
        )

        // email Block

        Email(email = email, onEmailChange = {
            email = it
            isEmailValid = email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(it).matches()
        }, isEmailValid = isEmailValid)

        //Password Block

        Password(
            password = password,
            onPasswordChange = { password = it },
            imeAction = ImeAction.Next
        )

        // FULLname block

        FullName(fullName = fullName, onFullNameChange = {
            fullName = it
            isFullNameValid = it.length < 20
        }, isFullNameValid = isFullNameValid)

        // CNIC block
        Cnic(cnic = cnic, onCnicChange = {
            cnic = it
            isCnicValid = it.length == 13 || it.isEmpty()
        }, isCnicValid = isCnicValid)

        // Phone Block
        Phone(phone = phone, onPhoneChange = {
            phone = it
            isPhoneValid = it.length == 11 || it.isEmpty()
        }, isPhoneValid = isPhoneValid)

        Spacer(modifier.height(20.dp))

        // Button that invokes sign up only if entered data is valid
        CreateAccountButton(onButtonClick = {
            if (dataIsValid(
                    phone = phone,
                    cnic,
                    fullName,
                    isCnicValid,
                    isPhoneValid,
                    isFullNameValid
                )
            ) {
            viewModel.signUp(email, password)
            }
        })

        Divider(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 20.dp),
            thickness = 3.dp,
            color = Color.Black
        )
        Column(modifier.padding(horizontal = 20.dp, vertical = 5.dp), Arrangement.Center) {
            Text(
                text = "Already have an account? ",
                style = MaterialTheme.typography.caption
            )
            loginTextButton(navController = navController)

        }

    }
}

// Phone composable
@Composable
fun Phone(
    modifier: Modifier = Modifier,
    phone: String,
    onPhoneChange: (String) -> Unit,
    isPhoneValid: Boolean
) {

    OutlinedTextField(
        value = phone, onValueChange = onPhoneChange,
        modifier
            .padding(5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        isError = !isPhoneValid,
        maxLines = 1,
        label = { Text(text = "Your phone number") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        )

    )
    if (!isPhoneValid) {
        Text(
            text = "Invalid phone number",
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption
        )
    }
}


// SIgn up button composable
@Composable
fun CreateAccountButton(modifier: Modifier = Modifier, onButtonClick: () -> Unit) {
    Button(
        onClick = onButtonClick,
        modifier
            .fillMaxWidth()
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(20.dp))
            .height(50.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.loginicon),
            contentDescription = "",
            modifier.padding(start = 4.dp)
        )
        Text(
            text = "Create Account",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.button.copy(fontFamily = oswald, fontSize = 15.sp)
        )
    }
}


// login button to redirect back to login page
@Composable
fun loginTextButton(navController: NavController) {
    val scope = rememberCoroutineScope()
    TextButton(onClick = {
        scope.launch {
            navController.navigate(Screen.LoginPage.route)
        }
    }) {
        Text(text = "LOGIN")

    }
}


//  full name editText component
@Composable
fun FullName(
    modifier: Modifier = Modifier,
    fullName: String,
    onFullNameChange: (String) -> Unit,
    isFullNameValid: Boolean
) {

    OutlinedTextField(
        value = fullName, onValueChange = onFullNameChange,
        modifier
            .padding(5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        isError = !isFullNameValid,
        maxLines = 1,
        label = { Text(text = "Your full name") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )

    )
    if (!isFullNameValid) {
        Text(
            text = "Invalid Name",
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption
        )
    }
}


//cnic component editText
@Composable
fun Cnic(
    modifier: Modifier = Modifier,
    cnic: String,
    onCnicChange: (String) -> Unit,
    isCnicValid: Boolean
) {

    OutlinedTextField(
        value = cnic, onValueChange = onCnicChange,
        modifier
            .padding(5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        isError = !isCnicValid,
        maxLines = 1,
        label = { Text(text = "Your CNIC number") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        )

    )
    if (!isCnicValid) {
        Text(
            text = "Invalid CNIC",
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption
        )
    }
}

fun dataIsValid(
    phone: String,
    cnic: String,
    name: String,
    isCnicValid: Boolean,
    isPhoneValid: Boolean,
    isNameValid: Boolean
): Boolean {
    return phone.isNotBlank() && cnic.isNotBlank() && name.isNotBlank() && isCnicValid && isNameValid && isPhoneValid
}
