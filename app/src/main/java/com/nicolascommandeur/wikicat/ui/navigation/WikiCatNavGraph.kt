package com.nicolascommandeur.wikicat.ui.navigation

import android.util.Log
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nicolascommandeur.wikicat.ui.screens.BreedDetailsScreen
import com.nicolascommandeur.wikicat.ui.screens.HomeScreen
import com.nicolascommandeur.wikicat.ui.viewmodels.BreedDetailsScreenViewModel
import com.nicolascommandeur.wikicat.ui.viewmodels.HomeScreenViewModel

fun NavGraphBuilder.wikiCatNavGraph(
    onNavigateToDetails: (String) -> Unit,
    onBackClick: () -> Unit
) {
    // HOME
    composable(route = Destination.Home.route) {
        val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
        HomeScreen(
            viewModel = homeScreenViewModel,
            onNavigateToDetails = onNavigateToDetails
        )
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
        val breedDetailsScreenViewModel = hiltViewModel<BreedDetailsScreenViewModel>()
        val catBreedId: String? = backStackEntry.arguments?.getString("catBreedId")
        Log.d("MainActivity", "catBreedId = $catBreedId (${catBreedId?.javaClass})")
        BreedDetailsScreen(
            viewModel = breedDetailsScreenViewModel,
            catBreedId = catBreedId,
            onBackClick = onBackClick
        )
    }
}