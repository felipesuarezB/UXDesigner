package com.example.plantimeapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import com.example.plantimeapp.navigation.NavRoute
import kotlinx.coroutines.launch

@Composable
fun AppScaffold(
    currentRoute: String,
    onNavigate: (NavRoute) -> Unit,
    onLogout: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "PlanTime",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Dashboard") },
                    selected = currentRoute == NavRoute.Dashboard.route,
                    onClick = { onNavigate(NavRoute.Dashboard); scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text("Mis plantas") },
                    selected = currentRoute == NavRoute.MisPlantas.route,
                    onClick = { onNavigate(NavRoute.MisPlantas); scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text("Comunidad") },
                    selected = currentRoute == NavRoute.Comunidad.route,
                    onClick = { onNavigate(NavRoute.Comunidad); scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text("Historial") },
                    selected = currentRoute == NavRoute.Historial.route,
                    onClick = { onNavigate(NavRoute.Historial); scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text("Panel de control") },
                    selected = currentRoute == NavRoute.PanelControl.route,
                    onClick = { onNavigate(NavRoute.PanelControl); scope.launch { drawerState.close() } }
                )
                if (onLogout != null) {
                    NavigationDrawerItem(
                        label = { Text("Cerrar sesión") },
                        selected = false,
                        onClick = { onLogout(); scope.launch { drawerState.close() } }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("PlanTime") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
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
}
