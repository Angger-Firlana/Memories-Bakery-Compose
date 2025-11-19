package com.example.kenanganbakery.presentation.ui.screen.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.domain.models.auth.LoginRequest
import com.example.kenanganbakery.presentation.ui.component.button.ModernButton
import com.example.kenanganbakery.presentation.ui.component.header.LogoKenanganBakery
import com.example.kenanganbakery.presentation.ui.component.text.ModernText
import com.example.kenanganbakery.presentation.ui.component.textfield.ModernOutlinedTextField
import com.example.kenanganbakery.presentation.viewmodel.AuthViewModel

@Composable
fun LoginScreen(modifier: Modifier = Modifier, viewModel:AuthViewModel) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp * 0.55
    var input by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state by viewModel.state.collectAsState()

    LaunchedEffect(state) {
        state?.let {
            if (it){
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
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
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
                text = "Login"
            ) {
                viewModel.login(
                    LoginRequest(
                        login = input,
                        password = password
                    )
                )
            }
        }



        Card(
            modifier.height(80.dp).fillMaxWidth(0.8f).align(Alignment.TopCenter).offset(y = screenWidth.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            content = {

            }
        )
    }
}
