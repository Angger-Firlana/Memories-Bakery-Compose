package com.example.kenanganbakery.presentation.ui.screen.auth

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kenanganbakery.domain.models.auth.LoginRequest
import com.example.kenanganbakery.presentation.ui.component.button.ModernButton
import com.example.kenanganbakery.presentation.ui.component.header.LogoKenanganBakery
import com.example.kenanganbakery.presentation.ui.component.text.ModernText
import com.example.kenanganbakery.presentation.ui.component.textfield.ModernOutlinedTextField
import com.example.kenanganbakery.presentation.ui.screen.auth.component.Login
import com.example.kenanganbakery.presentation.ui.screen.auth.component.RegisterComponent
import com.example.kenanganbakery.presentation.viewmodel.AuthViewModel

@Composable
fun AuthScreen(modifier: Modifier = Modifier, viewModel:AuthViewModel, onLogin:()-> Unit) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp * 0.55

    var isLoginMode by remember { mutableStateOf(true) }

    val state by viewModel.state.collectAsState()

    LaunchedEffect(state) {
        state?.let {
            if (it){
                onLogin()
                Toast.makeText(context, "Berhasil Login", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Gagal Login", Toast.LENGTH_SHORT).show()
            }
            viewModel.clearState()
        }
    }
    Box(
        modifier = modifier.fillMaxSize()
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .align(Alignment.TopCenter),
            shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                LogoKenanganBakery(
                    modifier = Modifier.fillMaxWidth(),
                    size = 20
                )
            }
        }

        if(isLoginMode){
            Login(
                modifier = Modifier.align(Alignment.BottomCenter),
                viewModel = viewModel
            )
        }else{
            RegisterComponent(
                modifier = Modifier.align(Alignment.BottomCenter),
                viewModel = viewModel
            )
        }




        Card(
            modifier
                .height(65.dp)
                .fillMaxWidth(0.8f)
                .align(Alignment.TopCenter)
                .offset(y = screenWidth.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            )
        ){
            Row(
                Modifier.fillMaxWidth()
            ) {
                ModernButton(
                    modifier = Modifier.weight(if(isLoginMode) 2f else 1f).animateContentSize(),
                    text = "Login",
                    onClick = {
                        isLoginMode = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(isLoginMode)MaterialTheme.colorScheme.secondary else Color.LightGray,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                )
                ModernButton(
                    modifier = Modifier.weight(if(!isLoginMode) 2f else 1f).animateContentSize(),
                    text = "Register",
                    onClick = {
                        isLoginMode = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(!isLoginMode)MaterialTheme.colorScheme.secondary else Color.LightGray,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}