package com.minthanhtike.jetbizcard

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.minthanhtike.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize()) {
        Card(
            modifier = modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .height(350.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Profile()
                Divider(
                    thickness = 10.dp,
                    color = Color.Cyan,
                )
                ProfileText()
                Button(onClick = { }) {
                    Text(
                        text = "Portfolio",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

            }
        }
    }
}

@Composable
private fun ProfileText() {
    Text(
        text = "Miles P.",
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 5.dp)
    )
    Text(
        text = "Android composable Programmer",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(vertical = 5.dp)
    )
    Text(
        text = "@themilesCompose",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 5.dp)
    )
}

@Composable
private fun Profile() {
    var imageUri: Uri? by remember {
        mutableStateOf(null)
    }
    val photoPicker = rememberLauncherForActivityResult(contract = PickVisualMedia()) {
        if (it != null) {
            imageUri = it
        }
    }
    Surface(
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp)
    ) {

        if (imageUri != null) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = imageUri)
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .size(135.dp)
                    .clickable {
                        photoPicker.launch(
                            PickVisualMediaRequest(
                                mediaType = PickVisualMedia.ImageAndVideo
                            )
                        )
                    },
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.people),
                contentDescription = "",
                modifier = Modifier
                    .size(135.dp)
                    .clickable {
                        photoPicker.launch(
                            PickVisualMediaRequest(
                                mediaType = PickVisualMedia.ImageAndVideo
                            )
                        )
                    },
                contentScale = ContentScale.Crop
            )
        }

    }
}

@Preview
@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Surface {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BizzCardPre() {
    JetBizCardTheme {
        CreateBizCard()
    }
}