package com.example.week9.navigation

sealed interface NavigationDestination {
    val title : String
    val route : String

    data object H
}