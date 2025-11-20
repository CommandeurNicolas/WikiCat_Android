package com.nicolascommandeur.wikicat

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nicolascommandeur.wikicat.ui.screens.BreedDetailsScreen
import com.nicolascommandeur.wikicat.ui.screens.HomeScreen
import com.nicolascommandeur.wikicat.ui.viewmodels.HomeScreenViewModel

@Composable
fun WikiCatNavHost(
    navController: NavHostController,
    homeScreenViewModel: HomeScreenViewModel,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
        modifier = modifier
    ) {
        // HOME
        composable(route = Destination.Home.route) {
            HomeScreen(viewModel = homeScreenViewModel) { catBreedId ->
                navController.navigate(route = Destination.Details.route + "?catBreedId=$catBreedId")
            }
        }

        // BREED DETAILS
        composable(
            route = Destination.Details.route + "?catBreedId={catBreedId}",
            arguments = listOf(
                navArgument("catBreedId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val catBreedId: String? = backStackEntry.arguments?.getString("catBreedId")
            Log.d("MainActivity", "catBreedId = $catBreedId (${catBreedId?.javaClass})")
            BreedDetailsScreen(
                catBreedId = catBreedId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}