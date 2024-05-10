package com.example.ejercicio2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ejercicio2.R
import com.example.ejercicio2.model.AvatarDetailDto

@Composable
fun Detail(
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier,
    characterDetail: AvatarDetailDto
){
    val scrollState= rememberLazyListState()
    val element:String
    var color: Color
    var borderColor:Color
    if (characterDetail.weapon?.lowercase()?.contains("fire") == true){
        color=Color(215,75,62)
        borderColor=Color(161,11,13)
        element="fire"
    }else if (characterDetail.weapon?.lowercase()?.contains("air") == true) {
        color=Color(241,195,110)
        borderColor=Color(196,139,62)
        element="air"
    }else if (characterDetail.weapon?.lowercase()?.contains("water") == true){
        color=Color(119,153,251)
        borderColor=Color(44,99,225)
        element="water"
    }else if (characterDetail.weapon?.lowercase()?.contains("earth") == true){
        color=Color(69,134,50)
        borderColor=Color(27,100,10)
        element="earth"
    }else if(characterDetail.weapon?.lowercase()?.contains("the elements") == true){
        color=Color(42,167,215)
        borderColor=Color(0,128,192)
        element="all"
    }
    else{
        color=Color(74,161,107)
        borderColor=Color(0,128,128)
        element="none"
    }
    LazyColumn(
        state = scrollState,
        modifier = Modifier.background(color)
    ){
        item{
            Row(
                modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(10.dp)
                        .aspectRatio(1f)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(
                            context = LocalContext.current
                        ).data(characterDetail.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = stringResource(R.string.avatar_image),
                        modifier = modifier
                            .aspectRatio(1f)
                            .clip(CircleShape)
                            .border(4.dp, color = borderColor, shape = CircleShape),
                        error = painterResource(id = R.drawable.no_image),
                        placeholder = painterResource(id = R.drawable.carga),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = characterDetail.name?:"",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier=Modifier.padding(start=10.dp)
                )
            }
            Column(modifier.padding(horizontal=20.dp)){
                Text(
                    text= stringResource(R.string.affiliation),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = characterDetail.affiliation?: stringResource(R.string.no_affiliation),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    modifier=Modifier.padding(bottom=10.dp)
                )
                Text(
                    text= stringResource(R.string.gender),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = characterDetail.gender?: stringResource(R.string.no_gender),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    modifier=Modifier.padding(bottom=10.dp)
                )
                Text(
                    text= stringResource(R.string.profession),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = characterDetail.profession?: stringResource(R.string.no_profession),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    modifier=Modifier.padding(bottom=10.dp)
                )
                Text(
                    text= stringResource(R.string.position),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = characterDetail.position?: stringResource(R.string.no_position),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    modifier=Modifier.padding(bottom=10.dp)
                )
                Text(
                    text= stringResource(R.string.allies),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    //Corregir, sólo muestra un elemento
                    text = characterDetail.allies.joinToString().removePrefix("[").removeSuffix("]").ifEmpty {
                        stringResource(
                            R.string.no_allies
                        ) },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    modifier=Modifier.padding(bottom=10.dp)
                )
                Text(
                    text= stringResource(R.string.enemies),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = characterDetail.enemies.joinToString().removePrefix("[").removeSuffix("]").ifEmpty {
                        stringResource(
                            R.string.no_enemies
                        ) },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    modifier=Modifier.padding(bottom=10.dp)
                )
                Text(
                    text= stringResource(R.string.weapons),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = characterDetail.weapon?: stringResource(R.string.no_weapons),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ){
                val context= LocalContext.current
                if(element!="none" && element !="all") {
                    val resourceId = context.resources.getIdentifier(element, "drawable", context.packageName)
                    Image(
                        painter = painterResource(id = resourceId), contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(end = 10.dp)
                            .clip(CircleShape)
                            .aspectRatio(1f)
                    )
                }else if(element=="all"){
                    var elements= listOf("fire","water","earth","air")
                    for (el in elements) {
                        val resourceId = context.resources.getIdentifier(
                            el,
                            "drawable",
                            context.packageName
                        )
                        Image(
                            painter = painterResource(id = resourceId), contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(end = 10.dp)
                                .clip(CircleShape)
                                .aspectRatio(1f)
                        )
                    }
                }
            }
        }
    }
}
