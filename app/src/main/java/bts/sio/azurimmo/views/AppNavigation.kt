package bts.sio.azurimmo.views

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodels.AppartementViewModel
import bts.sio.azurimmo.viewmodels.BatimentViewModel

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    val batimentViewModel: BatimentViewModel = viewModel()
    val appartementViewModel: AppartementViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "batiments_list",
        modifier = modifier
    ) {
        // Bâtiments
        composable("batiments_list") {
            BatimentList(navController = navController)
        }

        // Appartements
        composable("appartements_list") {
            AppartementList(navController = navController)
        }

        // Un Appartement
        composable("appartement_details/{appartementId}") { backStackEntry ->
            val appartementId = backStackEntry.arguments?.getString("appartementId")?.toIntOrNull()
            if (appartementId != null) {
                AppartementConsulter(appartementId = appartementId)
            }
        }

        // Appartements d'un bâtiment
        // Liste des appartements pour un bâtiment spécifique
        composable("appartements_list/{batimentId}") { backStackEntry ->
            val batimentId = backStackEntry.arguments?.getString("batimentId")?.toIntOrNull()
            if (batimentId != null) {
                appartementViewModel.getAppartementsByBatiment(batimentId)
                batimentViewModel.getBatiment(batimentId)
            } else {
                // Gestion de l'erreur si batimentId est nul
                Text("Erreur : ID du bâtiment invalide.")
            }
            AppartementList(navController = navController, batimentId = batimentId)
        }

        // Locataires
        composable("locataires_list") {
            LocataireList() // Affiche la liste des locataires
        }

        // Réparations
        composable("reparations_list") {
            ReparationList() // Affiche la liste des réparations
        }
    }
}
