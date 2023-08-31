package com.ab.brewbuddy.composables.notification

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel


//canvas in Jetpack compose

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HandwritingCanvas(
    canvasViewModel: CanvasViewModel // A ViewModel to manage the canvas state
) {
    val canvasSize = remember { Size(400f, 400f) } // Set your desired canvas size

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Handle touch down event to start drawing
                    }
                    MotionEvent.ACTION_MOVE -> {
                        // Handle touch move event to draw on the canvas
                    }
                    MotionEvent.ACTION_UP -> {
                        // Handle touch up event to finish drawing
                    }
                }
                true
            }
    ) {
        // Draw the content of the canvas here using canvasViewModel.state
    }
}

@Composable
fun HandwritingEditorScreen(
    canvasViewModel: CanvasViewModel // A ViewModel to manage the canvas state
) {
    Column(modifier = Modifier.fillMaxSize()) {
        HandwritingCanvas(canvasViewModel)
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Eraser button
            IconButton(onClick = { /* Handle eraser button click */ }) {
                Icon(Icons.Filled.Share, contentDescription = "Eraser")
            }

            // Undo button
            IconButton(onClick = { /* Handle undo button click */ }) {
                Icon(Icons.Filled.Search, contentDescription = "Undo")
            }

            // Redo button
            IconButton(onClick = { /* Handle redo button click */ }) {
                Icon(Icons.Filled.List, contentDescription = "Redo")
            }

            // Pen color picker
            // Implement a color picker that updates the canvasViewModel with the selected color

            // Pen type selector
            // Implement a dropdown or segmented control to switch between pen types

            // Export button
            IconButton(onClick = { /* Handle export button click */ }) {
                Icon(Icons.Filled.Share, contentDescription = "Export")
            }

            // Save button
            IconButton(onClick = { /* Handle save button click */ }) {
                Icon(Icons.Filled.Notifications, contentDescription = "Save")
            }
        }
    }
}







































class CanvasViewModel : ViewModel() {
    // Add state variables for pen color, pen type, and drawings on the canvas

    // Implement methods to handle user interactions, such as drawing, erasing, undo, redo, etc.
}
