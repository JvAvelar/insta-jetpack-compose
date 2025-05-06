package com.jvitor.jpcpratice.ui.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jvitor.jpcpratice.data.model.FeatureModel
import com.jvitor.jpcpratice.viewmodel.ScreenViewModel

@Composable
fun Featured(
    viewModel: ScreenViewModel,
    modifier: Modifier = Modifier
) {
    val listFeatured = viewModel.featured.collectAsState().value

    LazyRow(modifier.testTag("lazy_row_featured")) {
        items(listFeatured) { feat ->
            Box {
                ProfileFeatured(feat)
            }
        }
    }
}

@Composable
private fun ProfileFeatured(feat: FeatureModel) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .testTag("column_featured_items"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(feat.profilePhoto),
            contentDescription = "profile photo",
            modifier = Modifier
                .testTag("img_profile_photo_featured")
                .size(56.dp)
                .background(Color.LightGray, CircleShape)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = feat.name,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .padding(4.dp)
                .testTag("txt_user_name_featured")
        )
    }
}
