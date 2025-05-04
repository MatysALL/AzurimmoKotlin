package bts.sio.azurimmo.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.sp
import bts.sio.azurimmo.model.Appartement

@Composable
fun AppartementCard(appartement: Appartement) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Afficher le numéro de l'appartement
            appartement.numero?.let {
                Text(
                    text = "$it",  // Affichage du numéro
                    style = MaterialTheme.typography.titleMedium
                )
            } ?: Text(
                text = "Numéro inconnu",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))  // Espace entre le numéro et la description

            // Afficher la description de l'appartement
            appartement.description?.let {
                Text(
                    text = "$it",  // Affichage de la description
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp // Taille de la police pour la description
                    )
                )
            } ?: Text(
                text = "Description non disponible",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
