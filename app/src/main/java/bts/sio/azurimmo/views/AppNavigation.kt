package bts.sio.azurimmo.views

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
            AppartementList()
        }

        // Appartements d'un bâtiment
        composable("appartements_list?batimentId={batimentId}") { backStackEntry ->
            val batimentId = backStackEntry.arguments?.getString("batimentId")?.toIntOrNull()
            if (batimentId != null) {
                appartementViewModel.getAppartementsByBatiment(batimentId) // Charge les appartements spécifiques
                batimentViewModel.getBatiment(batimentId) // Charge les infos du bâtiment sélectionné
            }
            AppartementList(batimentId = batimentId)
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
