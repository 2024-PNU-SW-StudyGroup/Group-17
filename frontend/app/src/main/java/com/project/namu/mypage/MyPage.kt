package com.project.namu.mypage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.project.namu.R
import com.project.namu.model.MyPageViewModel

@Composable
fun MyPage(
    MyPageViewModel : MyPageViewModel
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFAFD3A6)),
        contentAlignment = Alignment.TopCenter

    ){
        MyPageViewModel.fetchMyPageData(1)
        val isOrderMessage by MyPageViewModel.isOrderMessage.collectAsState()
        val profile_url by MyPageViewModel.profile_url.collectAsState()
        val user_name by MyPageViewModel.user_name.collectAsState()
        val total_discount by MyPageViewModel.total_discount.collectAsState()

        Text(
            text = "마이페이지",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF),
            ),
            modifier = Modifier.padding(top=30.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 128.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF4F4F4)
            ),
            shape = RoundedCornerShape(topStart = 40.dp, topEnd=40.dp, bottomStart = 0.dp, bottomEnd = 0.dp)
        ){
            Spacer(modifier = Modifier.height(50.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(0.dp),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .width(80.dp)
                            .height(27.dp)

                    ) {
                        Text(
                            text = user_name,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF121212),
                            )
                        )
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    }
                }


            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFFFFF)
                ),
                border = BorderStroke(1.dp, Color(0xFFE7E7E7)),
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(horizontal = 76.dp, vertical = 16.dp),

                ){
                Text(
                text = "$user_name 님은 현재 이만큼 절약했어요!",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF121212),
                )
            )

                    Spacer(modifier = Modifier.height(20.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "$total_discount",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF1F9F37),
                )
            )

            Text(
                text = "원",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF1F9F37),
                )
            )
        }


                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (isOrderMessage) {
                ReservePopUp()
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ){
                Card(

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFFFFF)
                    ),
                    border = BorderStroke(1.dp, Color(0xFFE7E7E7)),
                    modifier = Modifier

                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(horizontal=10.dp, vertical = 11.dp)
                    ) {
                        Column {
                            Text(
                                text = "$user_name 님 덕분에\n 줄어든 이산화탄소",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF121212),
                                    textAlign = TextAlign.Center,
                                )
                            )

                            Spacer(modifier = Modifier.height(3.dp))
                            Row {
                            Text(
                                text = "12.3",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF1F9F37),
                                )
                            )
                                Spacer(modifier = Modifier.width(2.dp))

                            Text(
                                text = "KG",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF1F9F37),
                                )
                            )
                                }
                        }
                        Image(
                            painter = painterResource(id = R.drawable.leaves),
                            contentDescription = "나뭇잎",
                            modifier = Modifier.size (45.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFFFFF)
                    ),
                    border = BorderStroke(1.dp, Color(0xFFE7E7E7)),
                ){

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(horizontal=10.dp, vertical = 11.dp)
                    ) {
                        Column() {
                            Text(
                                text = "$user_name 님 덕분에\n 지킨 나무의 수",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF121212),
                                    textAlign = TextAlign.Center,
                                )
                            )
                            Spacer(modifier = Modifier.height(3.dp))

                            Row (
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ){
                                Text(
                                    text = "53",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF1F9F37),
                                    )
                                )

                                Spacer(modifier = Modifier.width(2.dp))

                                Text(
                                    text = "그루",
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF1F9F37),
                                    )
                                )

                            }
                        }

                        Image(
                            painter = painterResource(id = R.drawable.tree),
                            contentDescription = "나무",
                            modifier = Modifier
                                .width(45.dp)
                                .height(60.dp)
                        )
                    }
                }

            }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 34.dp)
                ) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(size = 12.dp),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier

                            .width(136.dp)
                            .height(32.dp)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(size = 12.dp),
                                color = Color(0xFF1F9F37)
                            )
                            .padding(0.dp)


                    )

                    {
                        Row (


                        ){
                            Image(
                                painter = painterResource(id = R.drawable.message),
                                contentDescription = "리뷰",
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "리뷰 관리",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF121212),
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(size = 12.dp),
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier

                            .width(136.dp)
                            .height(32.dp)
                            .border(
                                width = 1.dp,
                                shape = RoundedCornerShape(size = 12.dp),
                                color = Color(0xFF1F9F37)
                            )
                            .padding(0.dp)


                    )

                    {
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.coupon),
                                contentDescription = "쿠폰",
                                modifier = Modifier.size(20.dp)
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = "쿠폰 등록",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF121212),
                                )
                            )
                        }
                    }

                }
            Spacer(modifier = Modifier.height(20.dp))

            SettingButton(text = "주문 내역", icon = R.drawable.orderhistory )

            Spacer(modifier = Modifier.height(10.dp))

            SettingButton(text ="계정 관리", icon = R.drawable.usermanage )

            Spacer(modifier = Modifier.height(10.dp))

            SettingButton(text = "주소지 관리", icon = R.drawable.map)

            Spacer(modifier = Modifier.height(10.dp))

            SettingButton(text = "고객 센터", icon = R.drawable.headset)


        }


        ProfileImage(imageUrl = profile_url)

    }
}

@Composable
fun ProfileImage(imageUrl: String?) {
    if (imageUrl != null) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true) // 부드러운 전환 효과
                .build(),
            contentDescription = "프로필 사진",
            modifier = Modifier
                .padding(top = 88.dp)
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
    } else {
        // 이미지 URL이 아직 로드되지 않았을 때 보여줄 UI (예: 로딩 스피너)
        CircularProgressIndicator(modifier = Modifier.size(80.dp))
    }
}

@Composable
fun SettingButton(
    text: String,
    icon : Int
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(color = Color.White, shape = RoundedCornerShape(size = 12.dp))
            .border(
                width = 1.dp,
                color = Color(0xFFE7E7E7),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .padding(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically,

    ){
        Image(
            painter = painterResource(id = icon),
            contentDescription = "주문 내역 아이콘 ",
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF121212),
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(0.dp),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .size(24.dp)
        ){
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp),
                tint = Color.Black
            )
        }

    }
}

