package bts.sio.azurimmo.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.Text

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "batiments_list",
        modifier = modifier
    ) {
        // Batiments
        composable("batiments_list") {
            BatimentList(navController = navController)
        }

        // Appartements
        composable("appartements_list") {
            AppartementList()
        }

        // Appartements d'un Batiment
        composable("appartements_list?batimentId={batimentId}") { backStackEntry ->
            val batimentId = backStackEntry.arguments?.getString("batimentId")?.toIntOrNull()
            AppartementList(batimentId = batimentId)
        }

        // Locataires
        composable("locataires_list") {
            LocataireList()
        }

        // Reparations
        composable("reparations_list") {
            ReparationList()
        }
    }
}
