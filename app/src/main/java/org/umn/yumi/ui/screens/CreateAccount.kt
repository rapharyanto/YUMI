package org.umn.yumi.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import org.umn.yumi.ui.theme.YumiTheme

// --- PALET WARNA DIKUNCI SELALU CERAH & TEKS HITAM ---
private val BgSurface = Color(0xFFFDF8FF)
private val SurfaceContainerLowest = Color(0xFFFFFFFF)
private val SurfaceContainerLow = Color(0xFFF7F1FF)
private val SurfaceContainer = Color(0xFFF1EBFF)

private val TextDark = Color(0xFF1C1A28)      // Warna Hitam Pekat untuk Teks Utama
private val TextVariant = Color(0xFF59413C)   // Warna Gelap untuk Teks Sekunder
private val OutlineVariant = Color(0xFF8C736C) // Warna Placeholder / Line

private val PrimaryOrange = Color(0xFFFF6B4A)
private val PrimaryDeep = Color(0xFFAE3115)
private val SecondaryGreen = Color(0xFF006C49)

@Composable
fun CreateAccountScreen(
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    var agreeTerms by remember { mutableStateOf(true) }

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

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
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // --- TOP NAVIGATION & BRAND ACCENT ---
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onLoginClick,
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
            }

            // --- WARM CULINARY HEADER CARD ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(SurfaceContainerLowest, SurfaceContainerLow)
                        )
                    )
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = PrimaryDeep,
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = "MULAI PETUALANGAN KULINERMU",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryDeep,
                            letterSpacing = 0.5.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Buat Akun Baru ✨",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Daftar sekarang untuk temukan kuliner hits, resto terdekat, dan rekomendasi Voice AI yang pas di lidahmu!",
                        fontSize = 14.sp,
                        color = TextVariant,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // --- ERROR MESSAGE DISPLAY ---
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = PrimaryDeep,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            // --- MAIN REGISTRATION FORM ---
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                // Field 1: Full Name
                FormLabel(title = "Nama Lengkap")
                InputField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = "Misal: Sarah Anandita",
                    icon = Icons.Default.Person
                )

                // Field 2: Email
                FormLabel(title = "Alamat Email")
                InputField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = "nama@email.com",
                    icon = Icons.Default.ContactMail,
                    keyboardType = KeyboardType.Email
                )

                // Field 3: Password
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FormLabel(title = "Kata Sandi")
                    Text(
                        text = "Min. 8 Karakter",
                        fontSize = 12.sp,
                        color = TextVariant
                    )
                }
                PasswordField(
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Kombinasi huruf, angka & simbol",
                    icon = Icons.Default.Lock,
                    isVisible = isPasswordVisible,
                    onToggleVisibility = { isPasswordVisible = !isPasswordVisible }
                )

                // Password Strength Indicator
                if (password.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val isLengthValid = password.length >= 8
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(4.dp)
                                .clip(CircleShape)
                                .background(SecondaryGreen)
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(4.dp)
                                .clip(CircleShape)
                                .background(if (isLengthValid) SecondaryGreen else Color.LightGray)
                        )
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(4.dp)
                                .clip(CircleShape)
                                .background(if (isLengthValid && password.any { it.isDigit() }) SecondaryGreen else Color.LightGray)
                        )
                        Text(
                            text = if (isLengthValid) "Kuat" else "Sedang",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = SecondaryGreen,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }

                // Field 4: Confirm Password
                FormLabel(title = "Konfirmasi Kata Sandi")
                PasswordField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    placeholder = "Ketik ulang kata sandi",
                    icon = Icons.Default.VerifiedUser,
                    isVisible = isConfirmPasswordVisible,
                    onToggleVisibility = { isConfirmPasswordVisible = !isConfirmPasswordVisible }
                )

                // Terms Agreement Checkbox
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { agreeTerms = !agreeTerms },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (agreeTerms) SecondaryGreen else SurfaceContainer)
                            .border(
                                1.dp,
                                if (agreeTerms) SecondaryGreen else OutlineVariant,
                                RoundedCornerShape(6.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (agreeTerms) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Saya menyetujui Syarat & Ketentuan serta Kebijakan Privasi NyamYuk.",
                        fontSize = 12.sp,
                        color = TextVariant,
                        lineHeight = 16.sp
                    )
                }

                // Submit Button
                Button(
                    onClick = {
                        if (fullName.isBlank() || email.isBlank() || password.isBlank()) {
                            errorMessage = "Mohon lengkapi seluruh data."
                            return@Button
                        }
                        if (password != confirmPassword) {
                            errorMessage = "Konfirmasi kata sandi tidak cocok."
                            return@Button
                        }
                        if (password.length < 8) {
                            errorMessage = "Kata sandi minimal 8 karakter."
                            return@Button
                        }
                        if (!agreeTerms) {
                            errorMessage = "Anda harus menyetujui Syarat & Ketentuan."
                            return@Button
                        }

                        isLoading = true
                        errorMessage = ""

                        val auth = FirebaseAuth.getInstance()
                        val emailInput = email.trim()

                        auth.createUserWithEmailAndPassword(emailInput, password)
                            .addOnSuccessListener { authResult ->
                                val user = authResult.user
                                val profileUpdates = UserProfileChangeRequest.Builder()
                                    .setDisplayName(fullName.trim())
                                    .build()

                                user?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
                                    isLoading = false
                                    if (task.isSuccessful) {
                                        onRegisterSuccess()
                                    } else {
                                        errorMessage = "Gagal memperbarui profil pengguna."
                                    }
                                }
                            }
                            .addOnFailureListener { e ->
                                isLoading = false
                                errorMessage = e.localizedMessage ?: "Pendaftaran gagal."
                            }
                    },
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryOrange)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.White)
                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("Daftar Akun Sekarang", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // --- FOOTER REDIRECTION LINK ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Sudah punya akun kulineran? ", fontSize = 14.sp, color = TextVariant)
                Text(
                    text = "Masuk Sekarang",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryDeep,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }
        }
    }
}

// --- HELPER COMPOSABLES ---
@Composable
private fun FormLabel(title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = TextDark)
        Text(text = " *", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryDeep)
    }
}

@Composable
private fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: ImageVector,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = SurfaceContainerLowest
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = TextVariant, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(10.dp))
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text(text = placeholder, fontSize = 13.sp, color = OutlineVariant) },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 14.sp,
                    color = TextDark
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = TextDark,
                    unfocusedTextColor = TextDark
                ),
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Next),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: ImageVector,
    isVisible: Boolean,
    onToggleVisibility: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = SurfaceContainerLowest
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = TextVariant, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(10.dp))
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text(text = placeholder, fontSize = 13.sp, color = OutlineVariant) },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 14.sp,
                    color = TextDark
                ),
                visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = TextDark,
                    unfocusedTextColor = TextDark
                ),
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = onToggleVisibility,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    imageVector = if (isVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = null,
                    tint = TextVariant,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateAccountScreenPreview() {
    YumiTheme {
        CreateAccountScreen(
            onRegisterSuccess = {},
            onLoginClick = {}
        )
    }
}