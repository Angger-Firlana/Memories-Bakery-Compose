package com.example.kenanganbakery.presentation.ui.screen.auth.component

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.domain.models.auth.LoginRequest
import com.example.kenanganbakery.presentation.ui.component.button.ModernButton
import com.example.kenanganbakery.presentation.ui.component.text.ModernText
import com.example.kenanganbakery.presentation.ui.component.textfield.ModernOutlinedTextField
import com.example.kenanganbakery.presentation.viewmodel.AuthViewModel

@Composable
fun Login(modifier: Modifier = Modifier, viewModel:AuthViewModel) {
    val context: Context = LocalContext.current
    var input by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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
            input,
            onValueChange = {
                input = it
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
            modifier = Modifier.fillMaxWidth()
        )
        ModernText(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),

            text = "forgot password?",
            textAlign = TextAlign.End
        )

        ModernButton(
            modifier = Modifier.padding(top = 20.dp).fillMaxWidth(),
            text = "Login",
            onClick = {
                viewModel.login(
                    LoginRequest(
                        login = input,
                        password = password
                    ),
                    context = context
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            )
        )
    }
}