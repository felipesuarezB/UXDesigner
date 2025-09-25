package com.example.plantimeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantimeapp.R
import com.example.plantimeapp.ui.theme.GreenDark
import com.example.plantimeapp.ui.theme.GreenPrimary

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onGoToRegister: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo gráfico
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo PlanTime",
                modifier = Modifier
                    .height(100.dp)
                    .padding(bottom = 24.dp),
                contentScale = ContentScale.Fit
            )

            var user by remember { mutableStateOf("") }
            var pass by remember { mutableStateOf("") }

            OutlinedTextField(
                value = user,
                onValueChange = { user = it },
                label = { Text("Usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(0.9f)
            )

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = onLogin,
                colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp)
            ) { Text("Ingresar") }

            Spacer(Modifier.height(10.dp))
            Button(
                onClick = onGoToRegister,
                colors = ButtonDefaults.buttonColors(containerColor = GreenDark),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp)
            ) { Text("Registrarse") }
            Spacer(Modifier.height(32.dp))
            Image(
                painter = painterResource(id = R.drawable.inicio),
                contentDescription = "Imagen de inicio",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun BoxScope.DecorativePlants() {
    // Try to render drawables if present; otherwise render subtle gradients
    val leftPainter = null // Drawable 'plantleft' no existe, usar null para mostrar Box placeholder
    val rightPainter = null // Drawable 'plantright' no existe, usar null para mostrar Box placeholder

    if (leftPainter != null) {
        Image(
            painter = leftPainter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .width(160.dp)
                .align(Alignment.BottomStart),
            contentScale = ContentScale.Fit,
            alpha = 0.95f
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .width(120.dp)
                .align(Alignment.BottomStart)
                .background(Color(0x1166BF45), RoundedCornerShape(topEnd = 24.dp))
        )
    }

    if (rightPainter != null) {
        Image(
            painter = rightPainter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .width(160.dp)
                .align(Alignment.BottomEnd),
            contentScale = ContentScale.Fit,
            alpha = 0.95f
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .width(120.dp)
                .align(Alignment.BottomEnd)
                .background(Color(0x1166BF45), RoundedCornerShape(topStart = 24.dp))
        )
    }
}
