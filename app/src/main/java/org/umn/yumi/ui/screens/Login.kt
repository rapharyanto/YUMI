package org.umn.yumi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import org.umn.yumi.ui.auth.GoogleAuthUiClient
import org.umn.yumi.ui.theme.YumiTheme

// --- PALET WARNA LOGIN SCREEN ---
private val LoginBg = Color(0xFFFDF8FF)
private val PrimaryOrange = Color(0xFFFF6B4A)
private val DarkRed = Color(0xFFAE3115)
private val TextDark = Color(0xFF1C1A28)
private val TextVariant = Color(0xFF59413C)
private val InputBg = Color(0xFFF7F1FF)
private val ErrorContainer = Color(0xFFFFDAD6)
private val OnErrorContainer = Color(0xFF93000A)
private val SecondaryGreen = Color(0xFF006C49)
private val DividerColor = Color(0xFFE5E0F3)

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onGuestClick: () -> Unit = {}
) {
    var identifier by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isRememberMeChecked by remember { mutableStateOf(true) }
    var showErrorNotice by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("Periksa kembali email dan kata sandi Anda.") }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val googleAuthUiClient = remember { GoogleAuthUiClient(context) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LoginBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            // ================= 1. AREA FORM UTAMA (AKAN MENAMBUNG SISA SPASI DI ATAS) =================
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                // Header Text
                Text(
                    text = "Selamat Datang\nKembali! 👋",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = TextDark,
                    lineHeight = 36.sp,
                    letterSpacing = (-0.5).sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Masuk dan temukan kuliner lezat favoritmu hari ini di sekitarmu.",
                    fontSize = 14.sp,
                    color = TextVariant,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Error Notice Toast
                if (showErrorNotice) {
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
                                contentDescription = "Error",
                                tint = OnErrorContainer,
                                modifier = Modifier.size(20.dp)
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Akses Belum Berhasil",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = OnErrorContainer
                                )
                                Text(
                                    text = errorMessage,
                                    fontSize = 11.sp,
                                    color = OnErrorContainer.copy(alpha = 0.9f)
                                )
                            }
                        }
                    }
                }

                // Email Input
                Text(
                    text = "Email",
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
                            value = identifier,
                            onValueChange = {
                                identifier = it
                                if (showErrorNotice) showErrorNotice = false
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
                                imeAction = ImeAction.Next
                            ),
                            modifier = Modifier.weight(1f)
                        )

                        if (identifier.isNotEmpty()) {
                            IconButton(
                                onClick = { identifier = "" },
                                modifier = Modifier.size(26.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Clear input",
                                    tint = TextVariant,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Password Input
                Text(
                    text = "Kata Sandi",
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
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = TextVariant,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        TextField(
                            value = password,
                            onValueChange = {
                                password = it
                                if (showErrorNotice) showErrorNotice = false
                            },
                            placeholder = {
                                Text(
                                    text = "Minimal 8 karakter",
                                    fontSize = 13.sp,
                                    color = TextVariant.copy(alpha = 0.5f)
                                )
                            },
                            singleLine = true,
                            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
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
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(
                            onClick = { isPasswordVisible = !isPasswordVisible },
                            modifier = Modifier.size(26.dp)
                        ) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle password visibility",
                                tint = TextVariant,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Utilities Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { isRememberMeChecked = !isRememberMeChecked }
                            .padding(vertical = 4.dp, horizontal = 2.dp)
                    ) {
                        Checkbox(
                            checked = isRememberMeChecked,
                            onCheckedChange = { isRememberMeChecked = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = SecondaryGreen,
                                uncheckedColor = TextVariant.copy(alpha = 0.5f)
                            ),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Ingat Saya",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextDark
                        )
                    }

                    Text(
                        text = "Lupa Kata Sandi?",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkRed,
                        modifier = Modifier
                            .clickable { onForgotPasswordClick() }
                            .padding(vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Login Button
                Button(
                    onClick = {
                        if (identifier.isBlank() || password.isBlank()) {
                            errorMessage = "Mohon isi email dan kata sandi Anda."
                            showErrorNotice = true
                        } else {
                            isLoading = true
                            showErrorNotice = false
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(identifier.trim(), password)
                                .addOnSuccessListener {
                                    isLoading = false
                                    onLoginSuccess()
                                }
                                .addOnFailureListener { exception ->
                                    isLoading = false
                                    errorMessage = exception.localizedMessage ?: "Gagal masuk. Periksa kembali akun Anda."
                                    showErrorNotice = true
                                }
                        }
                    },
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(999.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryOrange,
                        disabledContainerColor = PrimaryOrange.copy(alpha = 0.6f)
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Memverifikasi...",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Masuk Sekarang",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Icon(
                                imageVector = Icons.Outlined.ArrowForward,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Divider
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = DividerColor
                    )
                    Text(
                        text = "atau masuk dengan",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextVariant,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = DividerColor
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Google Button
                Surface(
                    onClick = {
                        coroutineScope.launch {
                            isLoading = true
                            showErrorNotice = false

                            val resId = context.resources.getIdentifier(
                                "default_web_client_id",
                                "string",
                                context.packageName
                            )
                            val webClientId = if (resId != 0) context.getString(resId) else ""

                            if (webClientId.isEmpty()) {
                                isLoading = false
                                errorMessage = "Web Client ID tidak ditemukan. Pastikan google-services.json sudah terpasang."
                                showErrorNotice = true
                                return@launch
                            }

                            val result = googleAuthUiClient.signInWithGoogle(webClientId)
                            isLoading = false

                            if (result.isSuccess) {
                                onLoginSuccess()
                            } else {
                                errorMessage = result.exceptionOrNull()?.localizedMessage
                                    ?: "Gagal melakukan Google Sign-In."
                                showErrorNotice = true
                            }
                        }
                    },
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    shadowElevation = 1.dp
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = GoogleIconVector,
                            contentDescription = "Google Logo",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Masuk dengan Google",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextDark
                        )
                    }
                }
            } // <--- SELESAI COLUMN WEIGHT(1f) DI SINI

            // ================= 2. FOOTER (DI BIKIN DI LUAR, OTOMATIS TERTANAM DI BAWAH) =================
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Belum punya akun kulineran? ",
                    fontSize = 13.sp,
                    color = TextVariant
                )
                Text(
                    text = "Daftar Sekarang",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkRed,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .clickable { onRegisterClick() }
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                )
            }
        }
    }
}

// ================= INLINE GOOGLE VECTOR LOGO =================
private val GoogleIconVector: ImageVector
    @Composable
    get() = remember {
        ImageVector.Builder(
            name = "GoogleLogo",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(fill = SolidColor(Color(0xFF4285F4))) {
                moveTo(22.56f, 12.25f)
                curveTo(22.56f, 11.47f, 22.49f, 10.72f, 22.36f, 10f)
                horizontalLineTo(12f)
                verticalLineTo(14.26f)
                horizontalLineTo(17.92f)
                curveTo(17.66f, 15.63f, 16.88f, 16.79f, 15.71f, 17.57f)
                verticalLineTo(20.34f)
                horizontalLineTo(19.28f)
                curveTo(21.36f, 18.42f, 22.56f, 15.6f, 22.56f, 12.25f)
                close()
            }
            path(fill = SolidColor(Color(0xFF34A853))) {
                moveTo(12f, 23f)
                curveTo(14.97f, 23f, 17.46f, 22.02f, 19.28f, 20.34f)
                lineTo(15.71f, 17.57f)
                curveTo(14.73f, 18.23f, 13.48f, 18.63f, 12f, 18.63f)
                curveTo(9.14f, 18.63f, 6.71f, 16.7f, 5.84f, 14.1f)
                horizontalLineTo(2.18f)
                verticalLineTo(16.94f)
                curveTo(3.99f, 20.53f, 7.7f, 23f, 12f, 23f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFBBC05))) {
                moveTo(5.84f, 14.1f)
                curveTo(5.62f, 13.44f, 5.49f, 12.74f, 5.49f, 12f)
                curveTo(5.49f, 11.26f, 5.62f, 10.56f, 5.84f, 9.9f)
                verticalLineTo(7.06f)
                horizontalLineTo(2.18f)
                curveTo(1.43f, 8.55f, 1f, 10.22f, 1f, 12f)
                curveTo(1f, 13.78f, 1.43f, 15.45f, 2.18f, 16.94f)
                lineTo(5.84f, 14.1f)
                close()
            }
            path(fill = SolidColor(Color(0xFFEA4335))) {
                moveTo(12f, 5.38f)
                curveTo(13.62f, 5.38f, 15.06f, 5.94f, 16.21f, 7.02f)
                lineTo(19.36f, 3.87f)
                curveTo(17.45f, 2.09f, 14.97f, 1f, 12f, 1f)
                curveTo(7.7f, 1f, 3.99f, 3.47f, 2.18f, 7.06f)
                lineTo(5.84f, 9.9f)
                curveTo(6.71f, 7.3f, 9.14f, 5.38f, 12f, 5.38f)
                close()
            }
        }.build()
    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    YumiTheme {
        LoginScreen()
    }
}