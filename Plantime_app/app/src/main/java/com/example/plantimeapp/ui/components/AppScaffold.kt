package com.example.plantimeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import com.example.plantimeapp.navigation.NavRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    currentRoute: String,
    onNavigate: (NavRoute) -> Unit,
    onLogout: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("PlanTime") },
                navigationIcon = {
                    // Puedes agregar aquí un ícono si lo necesitas
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute == NavRoute.MisPlantas.route,
                    onClick = { onNavigate(NavRoute.MisPlantas) },
                    icon = { Icon(Icons.Filled.Menu, contentDescription = "Mis plantas") },
                    label = { Text("Mis plantas") }
                )
                NavigationBarItem(
                    selected = currentRoute == NavRoute.Comunidad.route,
                    onClick = { onNavigate(NavRoute.Comunidad) },
                    icon = { Icon(Icons.Filled.Menu, contentDescription = "Comunidad") },
                    label = { Text("Comunidad") }
                )
                NavigationBarItem(
                    selected = currentRoute == NavRoute.Historial.route,
                    onClick = { onNavigate(NavRoute.Historial) },
                    icon = { Icon(Icons.Filled.Menu, contentDescription = "Historial") },
                    label = { Text("Historial") }
                )
                NavigationBarItem(
                    selected = currentRoute == NavRoute.PanelControl.route,
                    onClick = { onNavigate(NavRoute.PanelControl) },
                    icon = { Icon(Icons.Filled.Menu, contentDescription = "Configuración") },
                    label = { Text("Configuración") }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            content()
        }
    }
}

