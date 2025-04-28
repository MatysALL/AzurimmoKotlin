package bts.sio.azurimmo.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import bts.sio.azurimmo.model.Appartement  // Mise à jour de l'import
import bts.sio.azurimmo.model.Batiment


@Composable
fun AppartementCard(
    appartement: Appartement,
    onClick: () -> Unit // Ajout d'un callback pour la navigation
    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row {
                Text(
                    text = "Numéro : ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = ""+appartement.numero,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row {
                Text(
                    text = "Description : ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text =appartement.description,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Row {
                Text(
                    text = "Surface : ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = String.format("%.2f", appartement.surface),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = " m²",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

/*
@Composable
fun AppartementCard(appartement: Appartement, navController: NavController) {
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
            Text(text = appartement.numero+appartement.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "surface : "+appartement.surface+"m²", style = MaterialTheme.typography.bodyMedium)
            Text(text = "pièces : "+appartement.nbPiece, style = MaterialTheme.typography.bodyMedium)
        }
    }
}*/