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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.project.namu.CartManager
import com.project.namu.R
import com.project.namu.data.model.CartItemModel
import com.project.namu.ui.component.Pay_BottomBar
import com.project.namu.ui.theme.BackGround
import com.project.namu.ui.theme.Main100

@Composable
fun PaymentScreen(navController: NavController) {
    val cartItems = CartManager.cartItems  // mutableStateListOf이므로 상태 변경 시 재구성됨
    val totalPrice = CartManager.getTotalPrice()  // 총 주문 금액

    Surface(color = BackGround, modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp), // vertical padding만 적용
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    TopBar()
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    // 각 항목을 forEach로 나열할 때에도 개별적으로 감싸줍니다.
                    Column {
                        cartItems.forEach { item ->
                            CartItem(cartItem = item)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    RequestSection()
                }
            }
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    PaymentMethods()
                }
            }
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    CouponSection()
                }
            }
            item {
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    PaymentSummary(navController = navController, totalPrice = totalPrice)
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            // 마지막 Pay_BottomBar에는 horizontal padding을 적용하지 않음 → 화면 전체 너비 사용
            item {
                Pay_BottomBar(navController)
            }
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
                    .background(Color.LightGray),
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
                        lineHeight = 16.sp
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
                        .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
                        .padding(horizontal = 4.dp, vertical = 8.dp)
                ) {
                    // 감소 버튼: decreaseQuantity() 호출
                    Icon(
                        painter = painterResource(id = R.drawable.minus),
                        contentDescription = "Decrease",
                        modifier = Modifier
                            .size(14.dp)
                            .clickable {
                                cartItem.decreaseQuantity()
                            }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // **중요**: 표시할 때는 quantityState를 사용해야 함
                    Text(
                        text = "${cartItem.quantityState}",
                        modifier = Modifier.padding(horizontal = 4.dp),
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // 증가 버튼: increaseQuantity() 호출
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = "Increase",
                        modifier = Modifier
                            .size(14.dp)
                            .clickable {
                                cartItem.increaseQuantity()
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
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("요청사항", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("견과류 알레르기가 있어요.")
        }
    }
}
@Composable
fun PaymentMethods() {
    var selectedMethod by remember { mutableStateOf("신용/체크카드") }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("결제 수단", fontWeight = FontWeight.Bold, fontSize = 16.sp)

            PaymentOptionRow("신용/체크카드", selectedMethod) { selectedMethod = it }
            PaymentOptionRow("카카오페이", selectedMethod, R.drawable.kakao) { selectedMethod = it }
            PaymentOptionRow("현장결제", selectedMethod) { selectedMethod = it }
        }
    }
}

@Composable
fun PaymentOptionRow(
    method: String,
    selectedMethod: String,
    iconRes: Int? = null, // 아이콘이 있을 경우만 표시
    onSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelected(method) }, // Row 클릭 시 선택 변경
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            iconRes?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = method,
                    modifier = Modifier.size(24.dp) // 아이콘 크기 조정
                )
                Spacer(modifier = Modifier.width(8.dp)) // 아이콘과 텍스트 간격
            }
            Text(method)
        }
        RadioButton(
            selected = selectedMethod == method,
            onClick = { onSelected(method) } // 클릭하면 선택 변경
        )
    }
}


@Composable
fun CouponSection() {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // "할인 쿠폰" 제목
            Text("할인 쿠폰", fontWeight = FontWeight.Bold, fontSize = 16.sp)

            Spacer(modifier = Modifier.height(8.dp))

            // 쿠폰 선택 박스
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("쿠폰 1", fontSize = 14.sp, color = Color.Black)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("1,000원 할인", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Main100)
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = "Go to Coupons",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun PaymentSummary(navController: NavController, totalPrice: Int) {
    // 할인 금액을 상수로 지정 (예: 1000원)
    val discount = 1000
    val finalPrice = totalPrice - discount

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // 주문 금액
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("주문 금액", fontSize = 16.sp)
                Text("${totalPrice}원", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 쿠폰 할인 금액
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("쿠폰", fontSize = 16.sp)
                Text("-${discount}원", fontWeight = FontWeight.Bold, color = Main100)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))

            // 최종 결제 금액 (할인 적용)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("최종 결제 금액", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text("${finalPrice}원", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}
