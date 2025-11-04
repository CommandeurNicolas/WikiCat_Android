package com.nicolascommandeur.wikicat

import kotlin.math.max

object VersionUtil {
    fun isApiVersionGreater(apiVersion: String, localVersion: String): Boolean {
        val apiComponents = apiVersion.split(".").map { it.toInt() }
        val localComponents = localVersion.split(".").map { it.toInt() }

        // Compare each component : major, minor, patch
        for (i in 0..< max(apiComponents.size, localComponents.size)) {
            val apiValue = if (i < apiComponents.size) apiComponents[i] else 0
            val localValue = if (i < localComponents.size) localComponents[i] else 0

            if (apiValue > localValue) return true // IS GREATER
            else if (localValue > apiValue) return false // IS LOWER
        }
        return false // IS EQUAL
    }
}