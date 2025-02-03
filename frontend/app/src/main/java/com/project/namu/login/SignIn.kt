package com.project.namu.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.namu.R
import com.project.namu.model.EmailSignInRequest
import com.project.namu.model.LogInViewModel
import com.project.namu.model.SignInViewModel

@Composable
fun SignIn(
    popUpViewModel: PopUpViewModel,
    signInViewModel: SignInViewModel,
    navController: NavController,
    logInViewModel: LogInViewModel
){
    val isDialogVisible by popUpViewModel.isDialogVisible.collectAsState()
    val signInMessage by signInViewModel.signInMessage.collectAsState()

    // ✅ ViewModel에서 값 가져오기 (자동 업데이트됨)
    val name by signInViewModel.name.collectAsState()
    val email by signInViewModel.email.collectAsState()
    val phoneNumber by signInViewModel.phoneNumber.collectAsState()
    val password by signInViewModel.password.collectAsState()
    val passwordDone by signInViewModel.passwordDone.collectAsState()

    if(isDialogVisible){
        PopUp( text = signInMessage, viewModel = popUpViewModel, navController)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ){
        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = R.drawable.namulogo),
            contentDescription = "로그인 로고 이미지",
            modifier = Modifier
                .width(166.dp)
                .height(66.dp)
                .align(Alignment.CenterHorizontally)
        )


        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "작은 배달로, 나무 키우기",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF1F9F37),
                textAlign = TextAlign.Center,
                letterSpacing = 0.02.sp,
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier= Modifier.height(50.dp))



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {



            TextFieldName(text = "  이름")


            InformationTextField(
                textfield = "Name",
                text = name,
                textUpdate = { newText -> signInViewModel.updateName(newText)  }
                )

            TextFieldName(text = "  전화번호")

            PhoneNumberTextField(
                text = phoneNumber,
                textUpdate = { newText ->
                    // 최대 글자 수는 숫자 기준 11자리 (하이픈 제외)

                   signInViewModel.updatePhoneNumber(newText)
                    val digits = newText.filter { it.isDigit() }
                    if (digits.length <= 11) {
                        signInViewModel.updatePhoneNumber(newText)
 }}
            )

            TextFieldName(text = "  이메일(아이디)")

            InformationTextField(
                textfield = "E-mail",
                text = email,
                textUpdate= { newText -> signInViewModel.updateEmail(newText) }

            )

            PasswordTextFields(
                signInViewModel = signInViewModel
            )
        }
        Spacer(modifier = Modifier.height(30.dp))


        LogSignButton(type = "signin", color = "green", popUpViewModel = popUpViewModel, LogInViewModel = logInViewModel, signInViewModel = signInViewModel, navController = navController)}

        Spacer(modifier = Modifier.height(30.dp))




    }


@Composable
fun TextFieldName(
    text: String
){
    Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
            letterSpacing = 0.02.sp,
        )
    )

}

class PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // 입력된 텍스트를 숫자만 남기고 필터링
        val digits = text.text.filter { it.isDigit() }

        // 최대 11자리까지만 허용 (예: 010-1234-5678)
        val trimmed = if (digits.length > 11) digits.substring(0, 11) else digits

        // 하이픈을 추가하여 포맷팅
        val out = StringBuilder()
        for (i in trimmed.indices) {
            out.append(trimmed[i])
            when (i) {
                2, 6 -> out.append('-') // 3번째, 7번째 문자 뒤에 하이픈 추가
            }
        }

        // 포맷팅된 텍스트 생성
        val formattedText = out.toString()

        // OffsetMapping 구현
        val phoneNumberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // 하이픈 위치에 따라 원본 오프셋을 변환된 오프셋으로 매핑
                return when {
                    offset <= 2 -> offset
                    offset <= 6 -> offset + 1
                    offset <= 11 -> offset + 2
                    else -> formattedText.length
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                // 변환된 오프셋을 원본 오프셋으로 매핑
                return when {
                    offset <= 3 -> offset
                    offset <= 8 -> offset - 1
                    offset <= 13 -> offset - 2
                    else -> trimmed.length
                }
            }
        }

        return TransformedText(AnnotatedString(formattedText), phoneNumberOffsetTranslator)
    }
}

@Composable
fun PhoneNumberTextField(
    text : String,
    textUpdate : (String) -> Unit
){
    TextField(
        value = text,
        onValueChange = textUpdate,
        label = { Text(
            text = "Phone Number",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFAAAAAA),
                textAlign = TextAlign.Center,
                letterSpacing = 0.02.sp,
            )
        ) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White

            ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color(0xFFE7E7E7), // 포커스 상태에 따른 색상 변경
                shape = RoundedCornerShape(16.dp)
            )
        ,singleLine = true,
        visualTransformation = PhoneNumberVisualTransformation()
    )
}

@Composable
fun InformationTextField(
    textfield: String,
    text : String,
    textUpdate : (String) -> Unit

){
    TextField(
        value = text,
        onValueChange = textUpdate,
        label = { Text(
            text = textfield,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFAAAAAA),
                textAlign = TextAlign.Center,
                letterSpacing = 0.02.sp,
            )
        ) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent


            ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color(0xFFE7E7E7), // 포커스 상태에 따른 색상 변경
                shape = RoundedCornerShape(16.dp)
            )
        ,singleLine = true
    )



}

@Composable
fun PasswordTextFields(
    signInViewModel: SignInViewModel
){
    val password by signInViewModel.password.collectAsState()
    val passwordDone by signInViewModel.passwordDone.collectAsState()

    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }



    TextFieldName(text = "  비밀번호")


    TextField(
        value = password,
        onValueChange = { newPassword ->
            signInViewModel.updatePassword(newPassword) },
        label = { Text(
            text = "Password",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFAAAAAA),
                textAlign = TextAlign.Center,
                letterSpacing = 0.02.sp,
            )
        ) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) {
                Icons.Default.Clear
            } else {
                Icons.Default.Lock
            }
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = null)
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White

            ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color(0xFFE7E7E7), // 포커스 상태에 따른 색상 변경
                shape = RoundedCornerShape(16.dp)
            )
        , singleLine = true
    )

    var isError by remember { mutableStateOf(false) }
    Row() {
        TextFieldName(text = "  비밀번호 확인")

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "비밀번호가 일치하지 않아요",
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(400),
                color = if (isError == true) Color.Red else Color.Transparent,
                textAlign = TextAlign.Center,
                letterSpacing = 0.02.sp,
            ),
            modifier = Modifier.align(Alignment.CenterVertically)


            )
    }

    var isFocused by remember { mutableStateOf(false) }

    TextField(
        value = confirmPassword,
        onValueChange = { confirmPassword = it  },
        label = { Text(
            text = "Password",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFFAAAAAA),
                textAlign = TextAlign.Center,
                letterSpacing = 0.02.sp,
            )
        ) },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) {
                Icons.Default.Clear
            } else {
                Icons.Default.Lock
            }
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = null)
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White

            ),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color(0xFFE7E7E7), // 포커스 상태에 따른 색상 변경
                shape = RoundedCornerShape(16.dp)
            )
            .onFocusChanged { focusState -> isFocused = focusState.isFocused }
        , singleLine = true
    )


    if(isFocused == false && confirmPassword != "") {
        var passwordsMatch = password == confirmPassword
        if (passwordsMatch == false){
            confirmPassword = ""
            isError = true
        }
        else {
            signInViewModel.setPasswordDone(true)
        }
    }



    if(isFocused == true && isError == true){
        isError = false
    }
}

