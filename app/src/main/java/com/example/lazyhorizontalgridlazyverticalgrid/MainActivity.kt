package com.example.lazyhorizontalgridlazyverticalgrid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isClick by remember {
                mutableStateOf(false)
            }
            Column {
                Text(
                    text = "Обновить",
                    fontSize = 32.sp,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                        .fillMaxWidth()
                        .border(width = 1.dp, Color.Black)
                        .clickable {
                            isClick = !isClick
                        },
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(4.dp))
                if (isClick) {
                    PicturesGrid()
                }
            }
        }
    }
}

@Composable
fun PicturesGrid() {
    val itemList = mutableListOf(
        R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
        R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight
    )
    val randomItemList = List(99) {
        itemList[Random.nextInt(itemList.size)]
    }
    var winningMessage by remember { mutableStateOf("") }

    winningMessage = checkWinningCombinations(randomItemList)

    if (winningMessage.isNotEmpty()) {
        Text(
            text = winningMessage,
            fontSize = 24.sp,
            color = Color.Green,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(randomItemList) { item ->
            Image(
                painter = painterResource(id = item),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .padding(5.dp)
            )
        }
    }
}

fun checkWinningCombinations(list: List<Int>): String {
    for (i in list.indices step 3) {
        if (i + 2 < list.size) {
            if (list[i] == list[i + 1] && list[i] == list[i + 2]) {
                return "Поздравляем! Найдено три одинаковых изображения!"
            }
        }
    }
    return ""
}

