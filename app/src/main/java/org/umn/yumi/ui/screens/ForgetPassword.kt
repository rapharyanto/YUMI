package org.umn.yumi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import org.umn.yumi.ui.theme.YumiTheme

private val BgSurface = Color(0xFFFDF8FF)
private val PrimaryOrange = Color(0xFFFF6B4A)
private val PrimaryDeep = Color(0xFFAE3115)
private val TextDark = Color(0xFF1C1A28)
private val TextVariant = Color(0xFF59413C)
private val InputBg = Color(0xFFF7F1FF)
private val SurfaceContainerLow = Color(0xFFF7F1FF)
private val ErrorContainer = Color(0xFFFFDAD6)
private val OnErrorContainer = Color(0xFF93000A)
private val SecondaryGreen = Color(0xFF006C49)
private val SuccessContainer = Color(0xFFD2E8D4)

@Composable
fun ForgotPasswordScreen(
    onBackToLoginClick: () -> Unit,
    isDark: Boolean = isSystemInDarkTheme()
) {
    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isSuccess by remember { mutableStateOf(false) }
    val bgSurface = if (isDark) Color(0xFF121212) else Color(0xFFFDF8FF)
    val textDark = if (isDark) Color(0xFFE6E1E5) else Color(0xFF1C1A28)
    val textVariant = if (isDark) Color(0xFFCAC4D0) else Color(0xFF59413C)
    val inputBg = if (isDark) Color(0xFF2B2930) else Color(0xFFF7F1FF)
    val surfaceContainerLow = if (isDark) Color(0xFF1E1B24) else Color(0xFFF7F1FF)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgSurface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Tombol Kembali
            IconButton(
                onClick = onBackToLoginClick,
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainerLow)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    tint = TextDark,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Header Text
            Text(
                text = "Lupa Kata Sandi? 🔑",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = TextDark,
                lineHeight = 36.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Masukkan email akun NyamYuk kamu. Kami akan mengirimkan tautan untuk mengatur ulang kata sandi.",
                fontSize = 14.sp,
                color = TextVariant,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Error Banner
            if (errorMessage.isNotEmpty()) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = ErrorContainer
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = null,
                            tint = OnErrorContainer,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = errorMessage,
                            fontSize = 12.sp,
                            color = OnErrorContainer,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Success Banner
            if (isSuccess) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = SuccessContainer
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = SecondaryGreen,
                            modifier = Modifier.size(22.dp)
                        )
                        Text(
                            text = "Email reset password telah dikirim! Silakan periksa inbox atau folder spam email kamu.",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = SecondaryGreen,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Input Email
            Text(
                text = "Alamat Email",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = TextVariant
            )

            Spacer(modifier = Modifier.height(6.dp))

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = InputBg
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .padding(horizontal = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Mail,
                        contentDescription = null,
                        tint = TextVariant,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    TextField(
                        value = email,
                        onValueChange = {
                            email = it
                            errorMessage = ""
                        },
                        placeholder = {
                            Text(
                                text = "nama@email.com",
                                fontSize = 13.sp,
                                color = TextVariant.copy(alpha = 0.5f)
                            )
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = TextDark,
                            unfocusedTextColor = TextDark
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Tombol Kirim Email Reset
            Button(
                onClick = {
                    if (email.isBlank()) {
                        errorMessage = "Mohon masukkan alamat email kamu."
                        return@Button
                    }

                    isLoading = true
                    errorMessage = ""
                    isSuccess = false

                    // PERINTAH UTAMA FIREBASE UNTUK RESET PASSWORD
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email.trim())
                        .addOnSuccessListener {
                            isLoading = false
                            isSuccess = true
                        }
                        .addOnFailureListener { exception ->
                            isLoading = false
                            errorMessage = exception.localizedMessage ?: "Gagal mengirim email reset password."
                        }
                },
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(999.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Kirim Link Reset",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Footer Link
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ingat kata sandi? ",
                    fontSize = 13.sp,
                    color = TextVariant
                )
                Text(
                    text = "Masuk Kembali",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryDeep,
                    modifier = Modifier.clickable { onBackToLoginClick() }
                )
            }
        }
    }
}

// ================= PREVIEW SECTION =================

@Preview(
    name = "Light Mode",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ForgotPasswordScreenPreview() {
    YumiTheme(darkTheme = false) {
        ForgotPasswordScreen(
            onBackToLoginClick = {},
            isDark = false
        )
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ForgotPasswordScreenDarkPreview() {
    YumiTheme(darkTheme = true) {
        ForgotPasswordScreen(
            onBackToLoginClick = {},
            isDark = true // <--- PAKSA IS_DARK JADI TRUE DI PREVIEW
        )
    }
}