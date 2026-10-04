package com.example.contactos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contactos.ui.theme.ContactosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactosTheme {
                ContactosApp()
            }
        }
    }
}

data class Contacto(
    val nombre: String,
    val telefono: String,
    val descripcion: String,
    val iniciales: String,
    val colorAvatar: Color
)

private val contactos = listOf(
    Contacto("Ana Martinez", "+503 7123-4567", "Diseno UX", "AM", Color(0xFF006D77)),
    Contacto("Carlos Hernandez", "+503 7234-5678", "Desarrollador Android", "CH", Color(0xFF8B5E34)),
    Contacto("Sofia Ramirez", "+503 7345-6789", "Marketing digital", "SR", Color(0xFFB23A48)),
    Contacto("Diego Lopez", "+503 7456-7890", "Soporte tecnico", "DL", Color(0xFF3A5A40)),
    Contacto("Valeria Renderos", "+503 7567-8901", "Estudiante DSM", "VR", Color(0xFF5B5F97)),
    Contacto("Miguel Torres", "+503 7678-9012", "Base de datos", "MT", Color(0xFF9A031E)),
    Contacto("Lucia Flores", "+503 7789-0123", "QA tester", "LF", Color(0xFF0A9396)),
    Contacto("Fernando Castro", "+503 7890-1234", "Redes y seguridad", "FC", Color(0xFF6D597A))
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactosApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lista de contactos",
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { innerPadding ->
        ContactosScreen(
            listaContactos = contactos,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContactosScreen(
    listaContactos: List<Contacto>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Contactos guardados",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )
        }

        items(listaContactos) { contacto ->
            ContactoCard(contacto = contacto)
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun ContactoCard(contacto: Contacto, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AvatarContacto(contacto = contacto)

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = contacto.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = contacto.telefono,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = contacto.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun AvatarContacto(contacto: Contacto, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(58.dp)
            .clip(CircleShape)
            .background(contacto.colorAvatar),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = contacto.iniciales,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactosPreview() {
    ContactosTheme {
        ContactosApp()
    }
}
