package com.seasoned.havecravings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import android.annotation.SuppressLint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                // Set the overall background color here
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFEBEFF1)), // Ensure the background is correctly applied
                    color = Color(0xFFEBEFF1) // Confirm setting the background color
                ) {
                    HaveCravingsApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class) // Opt-in to experimental API usage
@Composable
fun HaveCravingsApp() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("What should I do?") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF61727A),
                    titleContentColor = Color.White
                )
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent, // Ensure the Scaffold doesn't override the background color
        contentColor = Color.Black,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Adjust padding to respect the app bar and other components
                    .padding(16.dp)
            ) {
                var text by remember { mutableStateOf("\"Sup?\"") } // Initialize text with quotes
                Box(modifier = Modifier
                    .weight(1f) // Allocate most vertical space to text
                    .fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart) {
                    Text(
                        text = text,
                        style = TextStyle(fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold, fontSize = 48.sp),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(horizontal = 20.dp) // Horizontal padding for text
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.thinking_person),
                        contentDescription = "Thinking Person",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(start = 16.dp)  // Left padding for the image
                    )
                    Button(
                        onClick = {
                            text = if (Random.nextInt(100) < 90) {
                                listOf(
                                    "\"No, you have come this far. Just a bit more!\"",
                                    "\"No, don't fall!\"",
                                    "\"No, just a little longer\""
                                ).random() // Randomly select negative response
                            } else {
                                listOf(
                                    "\"Okay, but just this once!\"",
                                    "\"Yeah okay, cheating is fine sometimes!\""
                                ).random() // Randomly select positive response
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B0000)),
                        modifier = Modifier
                            .padding(end = 16.dp)  // Right padding for the button
                    ) {
                        Text("I HAVE A CRAVING", color = Color.White)
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        HaveCravingsApp()
    }
}