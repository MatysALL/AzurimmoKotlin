package bts.sio.azurimmo.views

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Build
import androidx.navigation.NavController

@Composable
fun AppBottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Bâtiment", "batiments_list", Icons.Filled.Business),
        // Utiliser la route complète même sans paramètre pour la clarté
        BottomNavItem("Appart", "appartements_list", Icons.Filled.Home),
        BottomNavItem("Contrat", "contrats_list", Icons.Filled.Description),
        BottomNavItem("Locataire", "locataires_list", Icons.Filled.Person),
        BottomNavItem("Reparation", "reparations_list", Icons.Filled.Build)
    )

    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = false // Assurez-vous de ne pas supprimer la page initiale
                        }
                        launchSingleTop = true // Évite de recréer plusieurs fois la même destination
                        restoreState = true // Restaurer l'état si possible
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val label: String, val route: String, val icon: ImageVector)
