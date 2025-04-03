package com.example.movies.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movies.presentation.ui.screen.MovieDetailsScreen
import com.example.movies.presentation.ui.screen.MovieListScreen
import com.example.movies.presentation.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
               App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "movie_list"){
        composable(route = "movie_list"){
            MovieListScreen { id ->
                if(id != null)
                    navController.navigate("movie_details/${id}")
                else
                    Toast.makeText(navController.context, "Invalid Request Id", Toast.LENGTH_SHORT).show()
            }
        }
        composable(
            route = "movie_details/{id}", arguments =
            listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            MovieDetailsScreen()
        }
    }

}

