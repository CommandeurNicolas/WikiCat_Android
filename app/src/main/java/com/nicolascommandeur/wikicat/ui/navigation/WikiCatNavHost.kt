package com.nicolascommandeur.wikicat.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun WikiCatNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
    ) {
        wikiCatNavGraph(
            onNavigateToDetails = { catBreedId ->
                navController.navigate(route = Destination.Details.route + "?catBreedId=$catBreedId")
            },
            onBackClick = { navController.popBackStack() }
        )
    }
}