package com.nicolascommandeur.wikicat

sealed class Destination(val route: String) {
    object Home: Destination("home_screen_destination")
    object Details: Destination("details_screen_destination")
}