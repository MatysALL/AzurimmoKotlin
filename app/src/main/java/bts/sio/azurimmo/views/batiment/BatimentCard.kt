package bts.sio.azurimmo.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.sp
import bts.sio.azurimmo.model.Batiment

@Composable
fun BatimentCard(
    batiment: Batiment,
    modifier: Modifier = Modifier, // Ajoutez un modificateur pour plus de flexibilité
    onClick: () -> Unit, // Événement pour le clic sur la carte entière
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp) // Ajustez le padding vertical
            .clickable(onClick = onClick), // Gère le clic sur la carte ici
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val ville = batiment.ville ?: "Ville Inconnue"
            val adresse = batiment.adresse ?: "Adresse Inconnue"

            Text(
                text = "$ville : $adresse",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
            )

            Spacer(modifier = Modifier.height(8.dp)) // Espacement entre le texte et les boutons

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End, // Aligner les boutons à droite
                verticalAlignment = Alignment.CenterVertically // Centrer verticalement les éléments de la rangée
            ) {
                // Bouton Modifier
                Button(
                    onClick = onEditClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.padding(end = 8.dp) // Espacement entre les boutons
                ) {
                    Text("Modifier", color = MaterialTheme.colorScheme.onPrimary)
                }

                //Bouton Supprimer
                Button(
                    onClick = onDeleteClick,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Supprimer", color = MaterialTheme.colorScheme.onError)
                }
            }
        }
    }
}