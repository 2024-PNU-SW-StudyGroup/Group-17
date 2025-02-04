import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.project.namu.CartManager
import com.project.namu.R
import com.project.namu.data.model.CartItemModel
import com.project.namu.ui.theme.BackGround
import com.project.namu.ui.theme.Main100

@Composable
fun PaymentScreen(navController: NavController) {
    val cartItems = CartManager.cartItems  // mutableStateListOf이므로 상태 변경 시 재구성됨

    Surface(color = BackGround, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp) // 전체 contentPadding 통일
        ) {
            TopBar()
            Spacer(modifier = Modifier.height(16.dp))

            // 장바구니 아이템들을 LazyColumn으로 표시
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(cartItems) { item ->
                    CartItem(cartItem = item)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            RequestSection()
            Spacer(modifier = Modifier.height(16.dp))
            PaymentMethods()
            Spacer(modifier = Modifier.height(16.dp))
            CouponSection()
            Spacer(modifier = Modifier.height(16.dp))
            PaymentSummary(navController)
        }
    }
}
    @Composable
    fun CartItem(cartItem: CartItemModel) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // 상품 이미지
            Image(
                painter = rememberAsyncImagePainter(cartItem.imageUrl),
                contentDescription = "Food Image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))

            // 상품 정보 및 수량 조절
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                // 상품명
                Text(
                    text = cartItem.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                // 상품 설명 (있을 경우)
                cartItem.description?.let { description ->
                    Text(
                        text = description,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        lineHeight = 16.sp // 원하는 줄 간격 값으로 조절
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                // 가격 정보: 할인 가격이 있으면 할인 가격과 원래 가격(취소선) 표시
                if (cartItem.discountPrice != null && cartItem.discountPrice < cartItem.price) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${cartItem.discountPrice}원",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Main100
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${cartItem.price}원",
                            fontSize = 14.sp,
                            color = Main100,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                } else {
                    Text(
                        text = "${cartItem.price}원",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                // 수량 조절 버튼
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp)) // 테두리 추가
                        .padding(horizontal = 4.dp, vertical = 8.dp)
                ) {
                    // 감소 버튼
                    Icon(
                        painter = painterResource(id = R.drawable.minus),
                        contentDescription = "Decrease",
                        modifier = Modifier
                            .size(14.dp)
                            .clickable {
                                if (cartItem.quantity > 1) {
                                    cartItem.quantity--
                                }
                            }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${cartItem.quantity}",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // 증가 버튼
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = "Increase",
                        modifier = Modifier
                            .size(14.dp)
                            .clickable {
                                cartItem.quantity++
                            }
                    )
                }
            }
        }
    }
}


@Composable
fun TopBar() {
    Surface(color = Color.White,  modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text("카페인중독 부산대점", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("픽업 19:00 ~ 19:30  도보 3분", fontSize = 14.sp, color = Color.Gray)
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow Right",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}





@Composable
fun RequestSection() {
    Card(shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),   colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("요청사항", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = AnnotatedString("견과류 알레르기가 있어요."))
        }
    }
}

@Composable
fun PaymentMethods() {
    Column {
        Text("결제 수단", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = false, onClick = {})
            Text("신용/체크카드")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = false, onClick = {})
            Text("카카오페이")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = false, onClick = {})
            Text("현장결제")
        }
    }
}

@Composable
fun CouponSection() {
    Card(shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("할인 쿠폰", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text("쿠폰 1", fontSize = 14.sp, color = Color.Blue)
            Spacer(modifier = Modifier.width(8.dp))
            Text("1,000원 할인", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun PaymentSummary(navController: NavController) {
    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("주문 금액", fontSize = 16.sp)
            Text("14,140원", fontWeight = FontWeight.Bold)
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("쿠폰", fontSize = 16.sp)
            Text("-1,000원", fontWeight = FontWeight.Bold, color = Color.Red)
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("최종 결제 금액", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("13,140원", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Green)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
