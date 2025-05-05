package bts.sio.azurimmo.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodels.BatimentViewModel
import bts.sio.azurimmo.viewmodels.AppartementViewModel
import bts.sio.azurimmo.viewmodels.ContratViewModel
import bts.sio.azurimmo.viewmodels.LocataireViewModel
import bts.sio.azurimmo.viewmodels.ReparationViewModel
import bts.sio.azurimmo.views.batiment.BatimentEditForm
import bts.sio.azurimmo.views.batiment.BatimentForm

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    // Initialisation du ViewModel pour les bâtiments
    val batimentViewModel: BatimentViewModel = viewModel()
    val appartementViewModel: AppartementViewModel = viewModel()
    val contratViewModel: ContratViewModel = viewModel()
    val locataireViewModel: LocataireViewModel = viewModel()
    val reparationViewModel: ReparationViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "batiments_list", // Lancement direct sur la liste des bâtiments
        modifier = modifier
    ) {
        // Navigation vers la liste des bâtiments
        composable("batiments_list") {
            BatimentList(navController = navController) // Utilisation du ViewModel pour charger la liste des batiments
        }

        composable("edit_batiment/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (id != null) {
                // Laissez BatimentEditForm obtenir son ViewModel
                BatimentEditForm(batimentId = id, navController = navController)
            } else {
                Text("ID invalide")
            }
        }

        composable("appartements_list?batimentId={batimentId}") { backStackEntry ->
            val batimentId = backStackEntry.arguments?.getString("batimentId")?.toIntOrNull()
            AppartementList(batimentId = batimentId)
        }

        composable("add_batiment") {
            BatimentForm(navController)
        }

        composable("contrats_list") {
            ContratList(viewModel = contratViewModel) // Affiche la liste des contrats
        }

        composable("locataires_list") {
            LocataireList(viewModel = locataireViewModel) // Affiche la liste des contrats
        }

        composable("reparations_list") {
            ReparationList(viewModel = reparationViewModel) // Affiche la liste des contrats
        }
    }
}
