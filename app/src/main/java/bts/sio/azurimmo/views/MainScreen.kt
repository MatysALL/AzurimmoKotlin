package bts.sio.azurimmo.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.navigation.compose.rememberNavController
import bts.sio.azurimmo.views.AppHeader
import bts.sio.azurimmo.views.AppBottomBar
import bts.sio.azurimmo.views.AppNavigation

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            AppHeader() // Affiche l'en-tête
        },
        bottomBar = {
            AppBottomBar(navController = navController) // Affiche la barre de navigation en bas
        }
    ) { innerPadding ->
        // Contenu principal de l'écran
        AppNavigation(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}
