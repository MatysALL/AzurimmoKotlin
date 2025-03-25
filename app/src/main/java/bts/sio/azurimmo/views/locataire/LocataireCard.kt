package bts.sio.azurimmo.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import bts.sio.azurimmo.model.Locataire  // Mise à jour de l'import

@Composable
fun LocataireCard(locataire: Locataire) {
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
            Text(text = locataire.prenom, style = MaterialTheme.typography.bodyLarge)
            Text(text = locataire.nom, style = MaterialTheme.typography.bodyMedium)
            Text(text = locataire.datenaiss, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
