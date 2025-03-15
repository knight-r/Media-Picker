package com.example.mediapicker

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.media_picker.MediaPicker

class MainActivity : ComponentActivity() {

    private lateinit var mediaPicker: MediaPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the MediaPicker
        mediaPicker = MediaPicker(this, 10)

        setContent {
            MediaPickerScreen(mediaPicker)
        }
    }
}

@Composable
fun MediaPickerScreen(mediaPicker: MediaPicker) {
    var selectedMediaUri by remember { mutableStateOf<Uri?>(null) }
    var selectedMediaUris by remember { mutableStateOf<List<Uri>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Button to pick a single image
        Button(
            onClick = {
                mediaPicker.pickSingleMedia(ActivityResultContracts.PickVisualMedia.ImageOnly)
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Pick Single Image")
        }

        // Button to pick multiple images
        Button(
            onClick = {
                mediaPicker.pickMultipleMedia(ActivityResultContracts.PickVisualMedia.ImageAndVideo, 10)
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Pick Multiple Images")
        }

        // Display the selected single media URI
        if (selectedMediaUri != null) {
            Text(
                text = "Selected Media URI: ${selectedMediaUri.toString()}",
                modifier = Modifier.padding(8.dp)
            )
        }

        // Display the selected multiple media URIs
        if (selectedMediaUris.isNotEmpty()) {
            Text(
                text = "Selected Media URIs: ${selectedMediaUris.joinToString { it.toString() }}",
                modifier = Modifier.padding(8.dp)
            )
        }
    }

    // Handle single media selection
    mediaPicker.onSingleMediaPicked = { uri ->
        selectedMediaUri = uri
    }

    // Handle multiple media selection
    mediaPicker.onMultipleMediaPicked = { uris ->
        selectedMediaUris = uris
    }
}