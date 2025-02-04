import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.namu.ui.theme.BackGround

@Composable
fun PaymentScreen() {
    Surface(color = BackGround, modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(bottom = 16.dp)) {
            TopBar()
            Spacer(modifier = Modifier.height(16.dp))

            CartItem()
            CartItem()
            Spacer(modifier = Modifier.height(16.dp))

            RequestSection()
            Spacer(modifier = Modifier.height(16.dp))

            PaymentMethods()
            Spacer(modifier = Modifier.height(16.dp))

            CouponSection()
            Spacer(modifier = Modifier.height(16.dp))

            PaymentSummary()
        }
    }
}


@Composable
fun TopBar() {
    Surface(color = Color.White,  modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text("카페인중독 부산대점", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("픽업 19:00 ~ 19:30  도보 3분  거리 257m", fontSize = 14.sp, color = Color.Gray)
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
fun CartItem() {
    Card(shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                contentDescription = "Food Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text("세트A", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("애플 와플(1), 콘치폭 핫도그 (1)", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("7,070원", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Green)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("10,100원", fontSize = 14.sp, color = Color.Gray, textDecoration = TextDecoration.LineThrough)
                }
                // 오른쪽에 정렬하도록 align(Alignment.End) 추가
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    IconButton(onClick = { /* 감소 로직 */ }, modifier = Modifier.size(24.dp)) {
                        Text("-")
                    }
                    Text("1", modifier = Modifier.padding(horizontal = 4.dp), fontSize = 16.sp)
                    IconButton(onClick = { /* 증가 로직 */ }, modifier = Modifier.size(24.dp)) {
                        Text("+")
                    }
                }
            }
        }
    }
}

@Composable
fun RequestSection() {
    Card(shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Text("요청사항", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.weight(1f))
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
fun PaymentSummary() {
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
        Button(onClick = { /* 결제 로직 */ }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp)) {
            Text("결제하기", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPaymentScreen() {
    PaymentScreen()
}
