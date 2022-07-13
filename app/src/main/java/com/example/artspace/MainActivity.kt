package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainPreview()
                }
            }
        }
    }
}

// Col is vertical
// Row is horizontal

@Composable
fun PrevNextButtons(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {},
            modifier = modifier.width(100.dp)
        ) {
            Text(text = "Previous")
        }

        Button(
            onClick = {},
            modifier = modifier.width(100.dp)
        ) {
            Text(text = "Next")
        }
    }
}

// create composable for art
@Composable
fun ArtView(
    painter: Painter = painterResource(id = R.drawable.art2),
    title:String = "title"
) {
    Surface(
        modifier = Modifier.padding(40.dp),
        elevation = 12.dp,
        border = BorderStroke(width = 3.dp, color = Color.Gray)
    ) {
        Image(
            painter = painter,
            contentDescription = title,
            modifier = Modifier.padding(20.dp)
        )
    }
}

/**
 * Composable to create images
 */
@Composable
fun CaptionView(
    title: String = "Title",
    author: String = "Author",
    year: String = "Year"
) {
    Surface(
        elevation = 12.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(11.dp),
        ) {
            // Art title
            Text(
                text = title,
                fontSize = 25.sp,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                buildAnnotatedString {
                    // Author
                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Monospace)
                    ) {
                        append(author)
                    }

                    // year
                    withStyle(style = SpanStyle(
                        fontStyle = FontStyle.Italic,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.Serif)
                    ) {
                        append(" ($year)")
                    }
                }
            )
        }
    }
}

// Create a list of images & string

@Composable
fun MainPreview() {
    // change the index

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtView()
        CaptionView()
        PrevNextButtons()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        MainPreview()
    }
}