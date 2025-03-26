package bts.sio.azurimmo.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import bts.sio.azurimmo.model.Appartement  // Mise à jour de l'import

@Composable
fun AppartementCard(appartement: Appartement) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row {
                Text(
                    text = "Numero : ",
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
            }
        }
    }
}
