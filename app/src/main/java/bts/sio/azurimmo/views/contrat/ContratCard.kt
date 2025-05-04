package bts.sio.azurimmo.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.sp
import bts.sio.azurimmo.model.Contrat

@Composable
fun ContratCard(contrat: Contrat) {
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
            contrat.appartement?.numero?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium
                )
            } ?: Text(text = "Numéro inconnue", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            val debut = contrat.datedebut ?: "Date début inconnue"
            val fin = contrat.datefin ?: "Date fin inconnue"

            Text(
                text = "$debut - $fin",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp)
            )
        }
    }
}
