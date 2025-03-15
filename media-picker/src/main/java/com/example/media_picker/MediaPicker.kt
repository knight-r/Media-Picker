package com.example.media_picker

import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.PickVisualMediaRequest

class MediaPicker(
    private val activity: ComponentActivity,
    private val maxItems: Int = 5
) {

    private var singleMediaPicker: ActivityResultLauncher<PickVisualMediaRequest>
    private var multipleMediaPicker: ActivityResultLauncher<PickVisualMediaRequest>

    init {
        // Register for single media selection
        singleMediaPicker = activity.registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            onSingleMediaPicked?.invoke(uri)
        }

        // Register for multiple media selection
        multipleMediaPicker = activity.registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(maxItems)) { uris ->
            onMultipleMediaPicked?.invoke(uris)
        }
    }

    var onSingleMediaPicked: ((Uri?) -> Unit)? = null
    var onMultipleMediaPicked: ((List<Uri>) -> Unit)? = null

    /**
     * Picks a single media item (image or video).
     *
     * @param mediaType The type of media to pick (e.g., ImageOnly, VideoOnly, ImageAndVideo).
     */
    fun pickSingleMedia(mediaType: ActivityResultContracts.PickVisualMedia.VisualMediaType) {
        singleMediaPicker.launch(PickVisualMediaRequest(mediaType))
    }

    /**
     * Picks multiple media items (images or videos).
     *
     * @param mediaType The type of media to pick (e.g., ImageOnly, VideoOnly, ImageAndVideo).
     * @param maxItems The maximum number of items to select.
     */
    fun pickMultipleMedia(mediaType: ActivityResultContracts.PickVisualMedia.VisualMediaType, maxItems: Int = 5) {
        multipleMediaPicker.launch(PickVisualMediaRequest(mediaType))
    }
}