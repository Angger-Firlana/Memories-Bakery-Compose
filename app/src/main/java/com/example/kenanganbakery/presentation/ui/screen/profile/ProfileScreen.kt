package com.example.kenanganbakery.presentation.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kenanganbakery.R
import com.example.kenanganbakery.domain.models.tab.SettingTabItem
import com.example.kenanganbakery.presentation.ui.component.text.ModernText

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val settingItems = listOf(
        SettingTabItem(
            title = "Edit Profile",
            route = "edit_profile",
            icon = Icons.Default.PersonPin
        ),
        SettingTabItem(
            title = "Settings",
            route = "edit_profile",
            icon = Icons.Default.Settings
        )
    )
    Box(
        Modifier.fillMaxSize()
    ){
        Column(
            Modifier.fillMaxSize()
        ) {
            Card(
                Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.pp),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(120.dp).clip(CircleShape)
                    )

                    ModernText(
                        text = "Profile",
                        size = 22
                    )
                }
            }

            Card(
                onClick = {

                },
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                ),
                border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.primary)
            ) {
                settingItems.forEach{ item ->
                    Row(
                        Modifier.fillMaxWidth().padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            item.icon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(40.dp)
                        )

                        Spacer(Modifier.width(16.dp))

                        ModernText(
                            text = item.title,
                            size = 16
                        )
                    }

                    HorizontalDivider()
                }
            }


        }
    }
}