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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
fun PrevNextButtons(
    modifier: Modifier = Modifier,
    value: Int,
    onValueChangeIncrease: (Int) -> Unit,
    onValueChangeDecrease: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {onValueChangeDecrease.invoke(value)},
            modifier = modifier.width(100.dp)
        ) {
            Text(text = "Previous")
        }

        Button(
            onClick = {onValueChangeIncrease.invoke(value)},
            modifier = modifier.width(100.dp)
        ) {
            Text(text = "Next")
        }
    }
}

// create composable for art
@Composable
fun ArtView(
    painter: Painter = painterResource(id = R.drawable.art3),
    title:String = "title"
) {
    Surface(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(500.dp),
        elevation = 12.dp,
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(2.dp, Color.LightGray)
    ) {
        Image(
            alignment = Alignment.Center,
            painter = painter,
            contentDescription = title,
            modifier = Modifier
                .padding(20.dp),
            contentScale = ContentScale.Fit
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
                .padding(11.dp)
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
    var idx by remember { mutableStateOf(1) }

    val image: Painter = when (idx) {
        1 -> painterResource(R.drawable.art1)
        2 -> painterResource(R.drawable.art2)
        3 -> painterResource(R.drawable.art3)
        else -> painterResource(R.drawable.art1)
    }

    val title: String = when (idx) {
        1 -> stringResource(id = R.string.art1_title)
        2 -> stringResource(id = R.string.art2_title)
        3 -> stringResource(id = R.string.art3_title)
        else -> "Title"
    }

    val author: String = when (idx) {
        1 -> stringResource(id = R.string.art1_author)
        2 -> stringResource(id = R.string.art2_author)
        3 -> stringResource(id = R.string.art3_author)
        else -> "author"
    }

    val year: String = when (idx) {
        1 -> stringResource(id = R.string.art1_year)
        2 -> stringResource(id = R.string.art2_year)
        3 -> stringResource(id = R.string.art3_year)
        else -> "Year"
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ArtView(painter = image, title = title)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(Alignment.Bottom),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CaptionView(title = title, author = author, year = year)
            PrevNextButtons(value = idx, onValueChangeIncrease = {
                idx = it + 1
                if (idx == 4) {
                    idx = 1
                }
            }) {
                idx = it - 1
                if (idx == 0) {
                    idx = 3
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        MainPreview()
    }
}