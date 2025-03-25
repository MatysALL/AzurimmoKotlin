package bts.sio.azurimmo.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import bts.sio.azurimmo.model.Appartement  // Mise à jour de l'import

@Composable
fun AppartementCard(appartement: Appartement) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = appartement.description, style = MaterialTheme.typography.bodyLarge)
            Text(text = "surface : "+appartement.surface, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Description de l'appartement : ${appartement.batiment?.ville ?: "Non disponible"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
