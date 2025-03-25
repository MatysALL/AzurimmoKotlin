package bts.sio.azurimmo.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import bts.sio.azurimmo.model.Reparation  // Mise à jour de l'import

@Composable
fun ReparationCard(reparation: Reparation) {
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
            // Affiche la description de la réparation
            Text(text = reparation.description, style = MaterialTheme.typography.bodyLarge)

            // Affiche la description de l'appartement, si disponible
            Text(
                text = "Description de l'appartement : ${reparation.appartement?.description ?: "Non disponible"}",
                style = MaterialTheme.typography.bodyMedium
            )

            // Affiche le type de réparation, si disponible
            Text(
                text = "Type : ${reparation.type?.libelle ?: "Non disponible"}",
                style = MaterialTheme.typography.bodyMedium
            )

            // Affiche le nom de la société, si disponible
            Text(
                text = "Société : ${reparation.societe?.libelle ?: "Non disponible"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
