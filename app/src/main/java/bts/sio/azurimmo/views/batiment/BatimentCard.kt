package bts.sio.azurimmo.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.NavController
import bts.sio.azurimmo.model.Batiment  // Mise à jour de l'import

@Composable
fun BatimentCard(batiment: Batiment, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("appartements_list/${batiment.id}") // Naviguer en utilisant l'ID du bâtiment
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        // Contenu de la carte ici (e.g. Titre, description)
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            batiment.ville?.let { Text(text = it, style = MaterialTheme.typography.titleMedium) }
            // Ajoutez d'autres informations si nécessaire
        }
    }
}
