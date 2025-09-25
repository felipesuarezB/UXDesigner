package com.example.plantimeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.plantimeapp.ui.screens.DashboardScreen
import com.example.plantimeapp.ui.screens.LoginScreen
import com.example.plantimeapp.ui.screens.RegisterScreen
import com.example.plantimeapp.ui.screens.MisPlantasScreen
import com.example.plantimeapp.ui.screens.ComunidadScreen
import com.example.plantimeapp.ui.screens.HistorialScreen
import com.example.plantimeapp.ui.screens.PanelControlScreen
import com.example.plantimeapp.ui.screens.CrearPlantaScreen
import com.example.plantimeapp.ui.screens.EstadisticasScreen
import com.example.plantimeapp.ui.screens.CalendarioScreen
import com.example.plantimeapp.ui.components.AppScaffold
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue

@Composable
fun AppNavGraph(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: NavRoute.Login.route

    NavHost(navController = navController, startDestination = NavRoute.Login.route) {
        // Auth
        composable(NavRoute.Login.route) {
            LoginScreen(
                onLogin = {
                    navController.navigate(NavRoute.Dashboard.route) {
                        popUpTo(NavRoute.Login.route) { inclusive = true }
                    }
                },
                onGoToRegister = { navController.navigate(NavRoute.Register.route) }
            )
        }
        composable(NavRoute.Register.route) {
            RegisterScreen(
                onRegistered = {
                    navController.navigate(NavRoute.Dashboard.route) {
                        popUpTo(NavRoute.Login.route) { inclusive = true }
                    }
                },
                onBackToLogin = { navController.popBackStack() }
            )
        }

        // Main sections (wrapped in AppScaffold)
        composable(NavRoute.Dashboard.route) {
            AppScaffold(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    if (route.route != currentRoute) navController.navigate(route.route)
                },
                onLogout = {
                    navController.navigate(NavRoute.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            ) {
                DashboardScreen(
                    onLogout = { /* handled from drawer */ },
                    onCreatePlant = { navController.navigate(NavRoute.CrearPlanta.route) }
                )
            }
        }

        composable(NavRoute.MisPlantas.route) {
            AppScaffold(
                currentRoute = currentRoute,
                onNavigate = { route -> if (route.route != currentRoute) navController.navigate(route.route) }
            ) {
                MisPlantasScreen(
                    onGoCalendario = { navController.navigate(NavRoute.Calendario.route) },
                    onGoEstadisticas = { navController.navigate(NavRoute.Estadisticas.route) },
                    onGoCrearPlanta = { navController.navigate(NavRoute.CrearPlanta.route) }
                )
            }
        }
        composable(NavRoute.Estadisticas.route) {
            AppScaffold(
                currentRoute = currentRoute,
                onNavigate = { route -> if (route.route != currentRoute) navController.navigate(route.route) }
            ) { EstadisticasScreen() }
        }
        composable(NavRoute.Comunidad.route) {
            AppScaffold(
                currentRoute = currentRoute,
                onNavigate = { route -> if (route.route != currentRoute) navController.navigate(route.route) }
            ) { ComunidadScreen() }
        }
        composable(NavRoute.Historial.route) {
            AppScaffold(
                currentRoute = currentRoute,
                onNavigate = { route -> if (route.route != currentRoute) navController.navigate(route.route) }
            ) { HistorialScreen() }
        }
        composable(NavRoute.PanelControl.route) {
            AppScaffold(
                currentRoute = currentRoute,
                onNavigate = { route -> if (route.route != currentRoute) navController.navigate(route.route) }
            ) {
                PanelControlScreen(
                    onConfigAlarmas = { navController.navigate(NavRoute.ConfigAlarmas.route) },
                    onCrearPlanta = { navController.navigate(NavRoute.CrearPlanta.route) },
                    onConfigSensores = { /* Aquí puedes navegar a la pantalla de sensores si existe */ }
                )
            }
        }
        composable(NavRoute.ConfigAlarmas.route) {
            AppScaffold(
                currentRoute = currentRoute,
                onNavigate = { route -> if (route.route != currentRoute) navController.navigate(route.route) }
            ) { com.example.plantimeapp.ui.screens.ConfigAlarmasScreen() }
        }
        composable(NavRoute.CrearPlanta.route) {
            AppScaffold(
                currentRoute = currentRoute,
                onNavigate = { route -> if (route.route != currentRoute) navController.navigate(route.route) }
            ) { CrearPlantaScreen(onDone = { navController.popBackStack() }) }
        }
    }
}
