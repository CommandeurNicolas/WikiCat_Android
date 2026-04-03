package com.nicolascommandeur.wikicat.ui.navigation

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
        route = Destination.Details.route + "/{catBreedId}",
        arguments = listOf(
            navArgument("catBreedId") {
                type = NavType.StringType
            }
        )
    ) {
        val breedDetailsScreenViewModel = hiltViewModel<BreedDetailsScreenViewModel>()
        BreedDetailsScreen(
            viewModel = breedDetailsScreenViewModel,
            onBackClick = onBackClick
        )
    }
}