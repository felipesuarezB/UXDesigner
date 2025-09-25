package com.example.plantimeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import com.example.plantimeapp.ui.theme.GreenDark
import com.example.plantimeapp.ui.theme.GreenPrimary

@Composable
fun RegisterScreen(
    onRegistered: () -> Unit,
    onBackToLogin: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo gráfico
        Image(
            painter = painterResource(id = com.example.plantimeapp.R.drawable.logo),
            contentDescription = "Logo PlanTime",
            modifier = Modifier
                .height(100.dp)
                .padding(bottom = 24.dp),
            contentScale = ContentScale.Fit
        )
        Text("Crear cuenta", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var pass by remember { mutableStateOf("") }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") },
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
            onClick = onRegistered,
            colors = ButtonDefaults.buttonColors(containerColor = GreenPrimary),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(48.dp),
            shape = RoundedCornerShape(10.dp)
        ) { Text("Registrarme") }

        Spacer(Modifier.height(10.dp))
        OutlinedButton(
            onClick = onBackToLogin,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(48.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = GreenDark)
        ) { Text("Volver al login") }
        Spacer(Modifier.height(32.dp))
        Image(
            painter = painterResource(id = com.example.plantimeapp.R.drawable.inicio),
            contentDescription = "Imagen de inicio",
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
    }
}
