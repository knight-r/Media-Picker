package com.example.media_picker

import androidx.fragment.app.FragmentActivity

class MediaPickerBuilder {
    companion object {
        fun with(activity: FragmentActivity): MediaPicker {
            return MediaPicker(activity)
        }
    }
}