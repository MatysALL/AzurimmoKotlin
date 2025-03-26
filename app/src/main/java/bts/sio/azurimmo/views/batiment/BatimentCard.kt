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
            .clickable {navController.navigate("appartements_list?batimentId=${batiment.id}")},
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            batiment.adresse?.let { Text(text = it, style = MaterialTheme.typography.bodyLarge) }
            batiment.ville?.let { Text(text = it, style = MaterialTheme.typography.bodyMedium) }
        }
    }
}
