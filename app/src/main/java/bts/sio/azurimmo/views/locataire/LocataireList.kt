package bts.sio.azurimmo.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import bts.sio.azurimmo.viewmodels.LocataireViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LocataireList(viewModel: LocataireViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getLocataires()
    }

    // Observer les données de manière réactive
    val locataires by viewModel.locataires.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Titre en violet
        Text(
            text = "Liste des Locataires",
            style = TextStyle(
                color = Color(0xFF7B1FA2), // Couleur violette
                fontSize = 24.sp,           // Taille de la police
                fontWeight = FontWeight.Bold // Gras
            ),
            modifier = Modifier
                .padding(16.dp) // Espacement autour du titre
        )

        // Affichage de l'interface utilisateur en fonction de l'état
        if (isLoading) {
            // Utilisation d'un Box pour centrer le CircularProgressIndicator
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (errorMessage != null) {
            Text(text = errorMessage ?: "Une erreur est survenue", color = Color.Red)
        } else {
            LazyColumn {
                items(locataires) { locataire ->
                    LocataireCard(locataire = locataire) // Utilisation de la carte pour chaque bâtiment
                }
            }
        }
    }
}
