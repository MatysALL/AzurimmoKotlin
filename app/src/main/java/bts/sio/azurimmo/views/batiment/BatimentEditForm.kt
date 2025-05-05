package bts.sio.azurimmo.views.batiment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import bts.sio.azurimmo.model.Batiment
import bts.sio.azurimmo.viewmodels.BatimentViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BatimentEditForm(
    batimentId: Int,
    navController: NavController,
    viewModel: BatimentViewModel = viewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val batiment by viewModel.batiment.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var ville by remember { mutableStateOf("") }
    var adresse by remember { mutableStateOf("") }

    // Charger les données du bâtiment à modifier
    LaunchedEffect(batimentId) {
        viewModel.getBatiment(batimentId)
    }

    // Mettre à jour les champs une fois les données chargées
    LaunchedEffect(batiment) {
        batiment?.let {
            ville = it.ville.toString()
            adresse = it.adresse.toString()
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    errorMessage?.let {
        Text(text = it, color = MaterialTheme.colorScheme.error)
    }

    batiment?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Modifier Bâtiment", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = ville,
                onValueChange = { ville = it },
                label = { Text("ville") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = adresse,
                onValueChange = { adresse = it },
                label = { Text("Adresse") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val updatedBatiment = Batiment(id = batimentId, ville = ville, adresse = adresse)
                    coroutineScope.launch {
                        try {
                            viewModel.addBatiment(updatedBatiment) {
                                if (it != null) {
                                    navController.popBackStack()
                                }
                            }
                        } catch (e: Exception) {
                            // Already handled by viewModel
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enregistrer")
            }
        }
    }
}