package com.mra.newsappcompose.ui.search

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mra.newsappcompose.R
import com.mra.newsappcompose.data.models.CategoryModel
import com.mra.newsappcompose.global.objects.StateItems
import com.mra.newsappcompose.ui.resultsearch.openResultSearch
import org.koin.androidx.compose.getViewModel

/**
 * Create by Mohammadreza Allahgholi
 *  Site: https://seniorandroid.ir
 */

@Composable
fun Search(
    navController: NavController,
    mViewModel: SearchViewModel = getViewModel(),
    mLoadingListener: (Boolean) -> Unit?,
) {

    var selectedCategoryItem by rememberSaveable {
        StateItems.homeSearchCategory
    }

    LaunchedEffect(
        key1 = Unit
    ) {
        mViewModel.getCategories()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Header()

            SearchBox {
                navController.openResultSearch(
                    it,
                    selectedCategoryItem
                )
            }

            if (mViewModel.categories.isNotEmpty())
                Categories(
                    selectedCategoryItem = selectedCategoryItem,
                    categories = mViewModel.categories,
                    onClick = {
                        StateItems.homeSearchCategory.value = it
                        selectedCategoryItem = it
                    }
                )

        }

    }

}

@Composable
fun Categories(
    selectedCategoryItem: String,
    categories: MutableList<CategoryModel>,
    onClick: (String) -> Unit
) {

    LazyVerticalGrid(
        userScrollEnabled = false,
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(16.dp)
    ) {

        items(categories) { item ->
            CategoryItem(
                selectedCategoryItem = selectedCategoryItem,
                category = item,
                onClick = onClick
            )
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryItem(selectedCategoryItem: String, category: CategoryModel, onClick: (String) -> Unit) {

    val isSelected = selectedCategoryItem == category.title
    val backgroundColor = if (isSelected) MaterialTheme.colors.primary else Color.White
    val textColor = if (isSelected) Color.White else Color.Black

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = backgroundColor,
        onClick = {
            onClick(category.title)
        }
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = category.name,
                style = TextStyle(color = textColor)
            )
        }

    }

}

@Composable
fun SearchBox(
    doSearch: (String) -> Unit,
) {

    val focusManager = LocalFocusManager.current

    var text: String by remember {
        StateItems.homeSearchField
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(33.dp),
        elevation = 0.dp
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(
                modifier = Modifier
                    .height(55.dp)
                    .width(55.dp),
                onClick = {
                    if (text.isNotEmpty())
                        doSearch(text)
                    focusManager.clearFocus()
                }) {
                Icon(
                    painter = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.ic_search)),
                    contentDescription = null,
                    tint = Color.Gray
                )
            }

            TextField(
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(
                        text = "Search news ...",
                        style = TextStyle(
                            color = Color.Gray,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                },
                modifier = Modifier.fillMaxSize(),
                value = text,
                shape = RoundedCornerShape(10.dp),
                onValueChange = {
                    StateItems.homeSearchField.value = it
                    text = it
                },
                keyboardActions = KeyboardActions(onDone = {
                    if (text.isNotEmpty())
                        doSearch(text)
                    focusManager.clearFocus()
                })
            )

        }

    }

}

@Composable
private fun Header() {
    ToolbarView()

    Spacer(modifier = Modifier.width(12.dp))

    Text(
        modifier = Modifier.padding(start = 20.dp),
        text = "Search",
        style = TextStyle(
            fontWeight = FontWeight.ExtraBold,
            fontSize = 35.sp,
            color = Color.Black
        )
    )
}

@Composable
private fun ToolbarView() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 18.dp,
                    bottom = 12.dp,
                    top = 22.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .clip(CircleShape),
                model = R.drawable.ic_avatar,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "10 Jan 2022",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    fontSize = 18.sp
                )
            )
        }
    }
}
