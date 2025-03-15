# MediaPicker Library

The `MediaPicker` library is a lightweight and easy-to-use Android library that simplifies the process of picking single or multiple media items (images and videos) from the device's gallery using the modern **Photo Picker API**. It supports customization for selecting specific types of media and limiting the number of items that can be selected.

---

## Features

- **Pick Single Media**: Select a single image or video.
- **Pick Multiple Media**: Select multiple images or videos with a customizable limit.
- **Custom Media Types**: Choose between images, videos, or both.
- **No Permissions Required**: Uses the Photo Picker API, so no runtime permissions are needed.
- **Jetpack Compose Support**: Fully compatible with Jetpack Compose.

---

## Installation

Add the following dependency to your `build.gradle` file:

```gradle
dependencies {
    implementation "com.github.knight-r:Media-Picker:1.0.0" 
}
