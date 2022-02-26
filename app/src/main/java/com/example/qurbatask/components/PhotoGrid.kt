package com.example.qurbatask

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun PhotoGrid(modifier: Modifier = Modifier, photos: List<Int>) {
    when (photos.size) {
        1 -> {
            Image(
                painter = painterResource(photos[0]),
                contentDescription = null,
                modifier = modifier.fillMaxWidth(),
            )
        }
        2 -> {
            Row(modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(photos[0]),
                    contentDescription = null,
                )
                Image(
                    painter = painterResource(photos[1]),
                    contentDescription = null,
                )
            }
        }
        3 -> {
            ConstraintLayout(modifier.fillMaxWidth()) {

                val (img1, img2, img3) = createRefs()
                val guideline = createGuidelineFromStart(0.5f)
                Image(
                    painter = painterResource(photos[0]),
                    contentDescription = null,
                    modifier.constrainAs(img1) {
                        start.linkTo(parent.start)
                        end.linkTo(guideline, 2.dp)
                    }
                )

                Image(
                    painter = painterResource(photos[1]),
                    contentDescription = null,
                    modifier.constrainAs(img2) {
                        start.linkTo(guideline, 2.dp)
                        end.linkTo(parent.end)
                        top.linkTo(img1.top)
                    }
                )
                Image(
                    painter = painterResource(photos[2]),
                    contentDescription = null,
                    modifier.constrainAs(img3) {
                        start.linkTo(guideline, 2.5.dp)
                        end.linkTo(parent.end)
                        bottom.linkTo(img1.bottom)
                    }
                )
            }
        }
        4 -> {
            Column(modifier) {
                Row {
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(photos[0]),
                        contentDescription = null,
                    )
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(photos[1]),
                        contentDescription = null,
                    )
                }
                Row {
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(photos[2]),
                        contentDescription = null,
                    )
                    Image(
                        modifier = Modifier.weight(1f),
                        painter = painterResource(photos[3]),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}