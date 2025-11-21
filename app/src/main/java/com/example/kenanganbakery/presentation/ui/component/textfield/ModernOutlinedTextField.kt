package com.example.kenanganbakery.presentation.ui.component.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun ModernOutlinedTextField(
    value:String,
    onValueChange:(String)->Unit,
    placeholderText:String? =null,
    trailingIcon: ImageVector? = null,
    leadingIcon:ImageVector? =  null,
    isPassword:Boolean = false,
    modifier:Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            placeholderText?.let{
                Text(
                    placeholderText
                )
            }

        },
        shape = RoundedCornerShape(16.dp),
        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    leadingIcon,
                    null
                )
            }
        },
        trailingIcon = {
            trailingIcon?.let {
                Icon(
                    trailingIcon,
                    null
                )
            }
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.Gray.copy(0.1f),
            focusedContainerColor = Color.Gray.copy(0.3f)
        ),

        modifier = modifier
    )
}