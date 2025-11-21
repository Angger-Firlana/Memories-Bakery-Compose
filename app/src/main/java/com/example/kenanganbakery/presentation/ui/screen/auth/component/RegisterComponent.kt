package com.example.kenanganbakery.presentation.ui.screen.auth.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.presentation.ui.component.button.ModernButton
import com.example.kenanganbakery.presentation.ui.component.text.ModernText
import com.example.kenanganbakery.presentation.ui.component.textfield.ModernOutlinedTextField
import com.example.kenanganbakery.presentation.viewmodel.AuthViewModel

@Composable
fun RegisterComponent(modifier: Modifier = Modifier, viewModel:AuthViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.65f)

            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ModernText(
            modifier = Modifier.fillMaxWidth(),
            text = "Email / Username",
            color = MaterialTheme.colorScheme.onBackground
        )

        ModernOutlinedTextField(
            username,
            onValueChange = {
                username = it
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        ModernText(
            modifier = Modifier.fillMaxWidth(),
            text = "Email",
            color = MaterialTheme.colorScheme.onBackground
        )

        ModernOutlinedTextField(
            email,
            onValueChange = {
                email = it
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        ModernText(
            modifier = Modifier.fillMaxWidth(),
            text = "Password",
            color = MaterialTheme.colorScheme.onBackground
        )

        ModernOutlinedTextField(
            password,
            onValueChange = {
                password = it
            },
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        ModernText(
            modifier = Modifier.fillMaxWidth(),
            text = "Confirm Password",
            color = MaterialTheme.colorScheme.onBackground
        )

        ModernOutlinedTextField(
            confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )
        ModernText(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),

            text = "forgot password?",
            textAlign = TextAlign.End
        )

        ModernButton(
            modifier = Modifier.padding(top = 20.dp).fillMaxWidth(),
            text = "Register",
            onClick = {

            }
        )
    }
}