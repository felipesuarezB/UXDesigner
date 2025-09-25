package com.example.plantimeapp.navigation

sealed class NavRoute(val route: String) {
    data object Login : NavRoute("login")
    data object Register : NavRoute("register")
    // Main sections
    data object Dashboard : NavRoute("dashboard")
    data object MisPlantas : NavRoute("mis-plantas")
    data object Comunidad : NavRoute("comunidad")
    data object Historial : NavRoute("historial")
    data object PanelControl : NavRoute("panel-control")
    data object CrearPlanta : NavRoute("crear-planta")
    data object ConfigAlarmas : NavRoute("config-alarmas")
}
