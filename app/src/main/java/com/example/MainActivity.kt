package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.BibleViewModel
import com.example.ui.components.BibleBottomBar
import com.example.ui.components.BibleScreen
import com.example.ui.screens.DailyPlansScreen
import com.example.ui.screens.DiscoverScreen
import com.example.ui.screens.JournalScreen
import com.example.ui.screens.ReaderScreen
import com.example.ui.screens.SearchScreen
import com.example.ui.theme.HolyBibleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HolyBibleTheme {
                HolyBibleApp()
            }
        }
    }
}

@Composable
fun HolyBibleApp(
    viewModel: BibleViewModel = viewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: BibleScreen.READER.route

    val currentScreen = BibleScreen.entries.find { it.route == currentRoute } ?: BibleScreen.READER

    val isReader = currentRoute == BibleScreen.READER.route

    Scaffold(
        bottomBar = {
            if (!isReader) {
                BibleBottomBar(
                    currentScreen = currentScreen,
                    onNavigate = { screen ->
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BibleScreen.READER.route,
            modifier = Modifier.padding(if (isReader) androidx.compose.foundation.layout.PaddingValues() else innerPadding)
        ) {
            composable(BibleScreen.READER.route) {
                ReaderScreen(
                    viewModel = viewModel,
                    onNavigateToSearch = { navController.navigate(BibleScreen.SEARCH.route) },
                    onNavigateToPlans = { navController.navigate(BibleScreen.PLANS.route) },
                    onNavigateToJournal = { navController.navigate(BibleScreen.JOURNAL.route) }
                )
            }

            composable(BibleScreen.PLANS.route) {
                DailyPlansScreen(
                    viewModel = viewModel,
                    onNavigateToReader = { bookId, chapter ->
                        viewModel.selectBookAndChapter(bookId, chapter)
                        navController.navigate(BibleScreen.READER.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(BibleScreen.DISCOVER.route) {
                DiscoverScreen(
                    viewModel = viewModel,
                    onNavigateToReader = { bookId, chapter, targetVerse ->
                        viewModel.selectBookAndChapter(bookId, chapter, targetVerse)
                        navController.navigate(BibleScreen.READER.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(BibleScreen.SEARCH.route) {
                SearchScreen(
                    viewModel = viewModel,
                    onNavigateToReader = { bookId, chapter, targetVerse ->
                        viewModel.selectBookAndChapter(bookId, chapter, targetVerse)
                        navController.navigate(BibleScreen.READER.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    },
                    onBack = { navController.popBackStack() }
                )
            }

            composable(BibleScreen.JOURNAL.route) {
                JournalScreen(
                    viewModel = viewModel,
                    onNavigateToReader = { bookId, chapter, targetVerse ->
                        viewModel.selectBookAndChapter(bookId, chapter, targetVerse)
                        navController.navigate(BibleScreen.READER.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
