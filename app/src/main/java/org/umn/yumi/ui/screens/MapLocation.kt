package org.umn.yumi.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import org.umn.yumi.R

// ==========================================
// PALET WARNA TEMA YUMI
// ==========================================
private val PrimaryColor = Color(0xFFAE3115)
private val PrimaryContainer = Color(0xFFFF6B4A)
private val SecondaryColor = Color(0xFF006C49)
private val SecondaryContainer = Color(0xFF6CF8BB)
private val OnSecondaryContainer = Color(0xFF00714D)
private val SurfaceBackground = Color(0xFFFDF8FF)
private val SurfaceContainerLow = Color(0xFFF7F1FF)
private val SurfaceContainerHigh = Color(0xFFEBE5F9)
private val OnSurfaceVariant = Color(0xFF59413C)
private val OnSurfaceText = Color(0xFF1C1A28)
private val TertiaryContainer = Color(0xFFD78900)

enum class TransportMode { WALK, MOTOR, CAR }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapLocationScreen(
    onNavigateBack: () -> Unit = {},
    onOpenGoogleMaps: () -> Unit = {}
) {
    var selectedMode by remember { mutableStateOf(TransportMode.WALK) }
    var isNavigating by remember { mutableStateOf(false) }
    var isSoundOn by remember { mutableStateOf(true) }
    var showArrivalModal by remember { mutableStateOf(false) }

    val etaDisplay = when (selectedMode) {
        TransportMode.WALK -> "450 meter • 5 menit"
        TransportMode.MOTOR -> "850 meter • 2 menit"
        TransportMode.CAR -> "1.1 km • 4 menit"
    }

    val navStepText = when (selectedMode) {
        TransportMode.WALK -> "Belok Kanan di Jl. Suryo no. 12"
        TransportMode.MOTOR -> "Putar Balik di U-Turn Senopati"
        TransportMode.CAR -> "Ambil Lajur Kanan ke Jl. Senopati"
    }

    val navStepDist = when (selectedMode) {
        TransportMode.WALK -> "120 meter lagi • lalu lurus 250m"
        TransportMode.MOTOR -> "200 meter lagi • Estimasi 2 menit"
        TransportMode.CAR -> "400 meter lagi • Parkir valas tersedia"
    }

    Scaffold(
        topBar = {
            MapTopAppBar(
                locationName = "Jakarta...",
                onBackClick = onNavigateBack,
                onNotificationClick = { },
                onProfileClick = { }
            )
        },
        containerColor = SurfaceBackground
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // 1. Interactive Top Destination & Mode Switcher Bar
                DestinationAndModeCard(
                    selectedMode = selectedMode,
                    etaDisplay = etaDisplay,
                    isSoundOn = isSoundOn,
                    onModeSelected = { selectedMode = it },
                    onToggleSound = { isSoundOn = !isSoundOn }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // 2. Map View Wrapper with Overlays
                SimulatedMapView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 3. Bottom Guidance Sheet
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Turn-by-Turn Maneuver Card
                    ManeuverCard(
                        navStepText = navStepText,
                        navStepDist = navStepDist,
                        isSoundOn = isSoundOn
                    )

                    // Resto Quick Status & Timeline Guidance
                    RestoDetailsCard(
                        isNavigating = isNavigating,
                        onToggleNavigation = { isNavigating = !isNavigating },
                        onOpenGoogleMaps = onOpenGoogleMaps,
                        onFinishTrip = { showArrivalModal = true }
                    )
                }

                // SPACER AGAR KONTEN TIDAK TERTUTUP BOTTOM NAVIGATION BAR DARI MAINACTIVITY
                Spacer(modifier = Modifier.height(88.dp))
            }

            // Arrival Modal Dialog
            if (showArrivalModal) {
                ArrivalModalDialog(
                    onDismiss = { showArrivalModal = false }
                )
            }
        }
    }
}

// ==========================================
// SUB-COMPONENTS
// ==========================================

@Composable
private fun MapTopAppBar(
    locationName: String,
    onBackClick: () -> Unit,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Surface(
        color = SurfaceBackground,
        shadowElevation = 1.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Kembali",
                        tint = OnSurfaceText
                    )
                }

                Text(
                    text = "NyamYuk",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryColor
                )

                Surface(
                    shape = CircleShape,
                    color = SurfaceContainerHigh,
                    modifier = Modifier.clickable { }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = PrimaryColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = locationName,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = OnSurfaceText
                        )
                        Icon(
                            imageVector = Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = OnSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(onClick = onNotificationClick) {
                    Box {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notifikasi",
                            tint = OnSurfaceVariant
                        )
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(PrimaryColor, CircleShape)
                                .align(Alignment.TopEnd)
                        )
                    }
                }

                Surface(
                    shape = CircleShape,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { onProfileClick() }
                ) {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuBfhm9rylJJs9uD_64qrF93jDzs-qCEmivyrxjb_PfXg-_DLJmm1FhWQcb0K-qiwoEguPdJynEc6-lktlWqAREdbwni-WD4lMAwi9ye8xusGCE7Sdre__KnCVM1tZFIdltbn47JcoDice6JHpAGBtCdtrnfi7249dqDcmnvJpMheccyu43uqYsO2etjrmysGFPl6XnTPFjiGpNNW_TD8mO0zaBRH2ysRjhvNhL18NGYlm4Usiysw9z5",
                        contentDescription = "Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun DestinationAndModeCard(
    selectedMode: TransportMode,
    etaDisplay: String,
    isSoundOn: Boolean,
    onModeSelected: (TransportMode) -> Unit,
    onToggleSound: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .background(PrimaryContainer.copy(alpha = 0.25f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Navigation,
                            contentDescription = null,
                            tint = PrimaryColor,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(SecondaryColor, CircleShape)
                            )
                            Text(
                                text = "RUTE AKTIF TERPILIH",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = SecondaryColor
                            )
                        }
                        Text(
                            text = "Menuju Bakso Solo Samrat",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurfaceText,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                IconButton(
                    onClick = onToggleSound,
                    modifier = Modifier
                        .size(36.dp)
                        .background(SurfaceContainerLow, CircleShape)
                ) {
                    Icon(
                        imageVector = if (isSoundOn) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                        contentDescription = "Sound Toggle",
                        tint = OnSurfaceVariant,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerLow, RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Straighten,
                        contentDescription = null,
                        tint = PrimaryColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = etaDisplay,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSurfaceText
                    )
                }

                Surface(
                    color = SecondaryContainer,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Icon(Icons.Default.Bolt, contentDescription = null, tint = OnSecondaryContainer, modifier = Modifier.size(12.dp))
                        Text("Jalur Cepat", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = OnSecondaryContainer)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerHigh.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                ModeTabButton(
                    title = "Jalan Kaki",
                    time = "5 mnt",
                    icon = Icons.Default.DirectionsWalk,
                    isSelected = selectedMode == TransportMode.WALK,
                    modifier = Modifier.weight(1f),
                    onClick = { onModeSelected(TransportMode.WALK) }
                )
                ModeTabButton(
                    title = "Motor",
                    time = "2 mnt",
                    icon = Icons.Default.TwoWheeler,
                    isSelected = selectedMode == TransportMode.MOTOR,
                    modifier = Modifier.weight(1f),
                    onClick = { onModeSelected(TransportMode.MOTOR) }
                )
                ModeTabButton(
                    title = "Mobil",
                    time = "4 mnt",
                    icon = Icons.Default.DirectionsCar,
                    isSelected = selectedMode == TransportMode.CAR,
                    modifier = Modifier.weight(1f),
                    onClick = { onModeSelected(TransportMode.CAR) }
                )
            }
        }
    }
}

@Composable
private fun ModeTabButton(
    title: String,
    time: String,
    icon: ImageVector,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = if (isSelected) Color.White else Color.Transparent,
        shadowElevation = if (isSelected) 2.dp else 0.dp
    ) {
        Column(
            modifier = Modifier.padding(vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isSelected) PrimaryColor else OnSurfaceVariant,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = title,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSelected) PrimaryColor else OnSurfaceVariant
                )
            }
            Text(
                text = time,
                fontSize = 10.sp,
                color = if (isSelected) PrimaryColor else OnSurfaceVariant.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
private fun SimulatedMapView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(SurfaceContainerLow)
    ) {
        // --- GAMBAR PETA LOKAL DARI RES/DRAWABLE ---
        Image(
            painter = painterResource(id = R.drawable.map_location_image), // Pastikan nama file di res/drawable adalah map_location_image.png (huruf kecil semua)
            contentDescription = "Map Backdrop",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // --- OVERLAY PIN LOKASI DI ATAS PETA ---

        // Landmark Marker Tag (Middle)
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-20).dp)
        ) {
            Surface(
                color = Color.White.copy(alpha = 0.95f),
                shape = RoundedCornerShape(6.dp),
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(Icons.Default.TurnSlightRight, contentDescription = null, tint = PrimaryColor, modifier = Modifier.size(14.dp))
                    Text("Lewat Jl. Cikajang", fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = OnSurfaceText)
                }
            }
        }

        // Start Location Pin (Bottom-Left)
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 30.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    color = Color(0xFF312F3D),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(modifier = Modifier.size(6.dp).background(SecondaryContainer, CircleShape))
                        Text("Kamu (Senopati Suites)", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(SecondaryColor.copy(alpha = 0.3f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(SecondaryColor, CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )
                }
            }
        }

        // Destination Pin (Top-Right)
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 36.dp, top = 24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    color = PrimaryColor,
                    shape = RoundedCornerShape(18.dp),
                    shadowElevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(Icons.Default.RamenDining, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                        Text("Bakso Samrat", fontSize = 11.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                        Surface(
                            color = Color.White.copy(alpha = 0.2f),
                            shape = CircleShape
                        ) {
                            Text("⭐ 4.9", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White, modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp))
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .background(PrimaryColor, CircleShape)
                        .border(2.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
        }

        // Floating Compass & Recenter Widgets
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FloatingActionButton(
                onClick = { },
                containerColor = Color.White,
                contentColor = PrimaryColor,
                modifier = Modifier.size(38.dp)
            ) {
                Icon(Icons.Default.Explore, contentDescription = "Kompas", modifier = Modifier.size(20.dp))
            }
            FloatingActionButton(
                onClick = { },
                containerColor = Color.White,
                contentColor = OnSurfaceVariant,
                modifier = Modifier.size(38.dp)
            ) {
                Icon(Icons.Default.MyLocation, contentDescription = "Pusatkan Lokasi", modifier = Modifier.size(20.dp))
            }
        }
    }
}

@Composable
private fun ManeuverCard(
    navStepText: String,
    navStepDist: String,
    isSoundOn: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(Color.White, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.TurnRight,
                        contentDescription = null,
                        tint = PrimaryColor,
                        modifier = Modifier.size(26.dp)
                    )
                }
                Column {
                    Text("LANGKAH SELANJUTNYA", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White.copy(alpha = 0.8f))
                    Text(navStepText, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(navStepDist, fontSize = 11.sp, color = Color.White.copy(alpha = 0.9f))
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = if (isSoundOn) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                    contentDescription = null,
                    tint = Color(0xFFFFDDB8),
                    modifier = Modifier.size(22.dp)
                )
                Text(
                    text = if (isSoundOn) "Suara ON" else "Mute",
                    fontSize = 9.sp,
                    color = Color(0xFFFFDDB8),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun RestoDetailsCard(
    isNavigating: Boolean,
    onToggleNavigation: () -> Unit,
    onOpenGoogleMaps: () -> Unit,
    onFinishTrip: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(Icons.Default.Storefront, contentDescription = null, tint = TertiaryContainer, modifier = Modifier.size(18.dp))
                        Text("Bakso Solo Samrat - Se...", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = OnSurfaceText)
                    }
                    Text("Jl. Suryo No. 40, Senopati, Kebayor...", fontSize = 11.sp, color = OnSurfaceVariant)
                }

                Surface(
                    color = SecondaryContainer,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(modifier = Modifier.size(6.dp).background(SecondaryColor, CircleShape))
                        Text("Buka s/d 22:00", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = OnSecondaryContainer)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SurfaceContainerLow, RoundedCornerShape(10.dp))
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(Icons.Default.Groups, contentDescription = null, tint = SecondaryColor, modifier = Modifier.size(18.dp))
                Text(
                    text = "Kondisi Resto: Sedang Ramai Santai (Meja tersedia ~4 spot)",
                    fontSize = 11.sp,
                    color = OnSurfaceVariant
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(SurfaceContainerHigh, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("1", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = OnSurfaceText)
                    }
                    Text("Jl. Cikajang", fontSize = 11.sp, color = OnSurfaceVariant)
                }

                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = OnSurfaceVariant, modifier = Modifier.size(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .background(PrimaryContainer.copy(alpha = 0.3f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("2", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryColor)
                    }
                    Text("Jl. Suryo", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = OnSurfaceText)
                }

                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = OnSurfaceVariant, modifier = Modifier.size(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(Icons.Default.Flag, contentDescription = null, tint = PrimaryColor, modifier = Modifier.size(16.dp))
                    Text("Samrat", fontSize = 11.sp, color = OnSurfaceVariant)
                }
            }

            Button(
                onClick = onToggleNavigation,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isNavigating) TertiaryContainer else PrimaryColor
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.Explore, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isNavigating) "Navigasi Aktif ⏸️" else "Mulai Navigasi Langsung 🧭",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Surface(
                    onClick = onOpenGoogleMaps,
                    modifier = Modifier.weight(1f),
                    color = SurfaceContainerHigh,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.OpenInNew, contentDescription = null, tint = SecondaryColor, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Buka Google Maps", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = OnSurfaceText)
                    }
                }

                Surface(
                    onClick = onFinishTrip,
                    modifier = Modifier.weight(1f),
                    color = SecondaryContainer,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = OnSecondaryContainer, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Tiba di Lokasi", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = OnSecondaryContainer)
                    }
                }
            }
        }
    }
}

@Composable
private fun ArrivalModalDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(SecondaryContainer, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.Celebration, contentDescription = null, tint = OnSecondaryContainer, modifier = Modifier.size(32.dp))
                }

                Text("Kamu Sudah Sampai!", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = OnSurfaceText)
                Text(
                    "Selamat menikmati semangkuk hangat Bakso Solo Samrat Senopati. Mau lihat daftar menu?",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = OnSurfaceVariant
                )

                Button(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
                ) {
                    Text("Lihat Menu Meja 🍲", fontWeight = FontWeight.Bold)
                }

                TextButton(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
                    Text("Tutup", color = OnSurfaceVariant)
                }
            }
        }
    }
}

@Preview(
    name = "Map Location Screen Preview",
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_6"
)
@Composable
fun MapLocationScreenPreview() {
    MaterialTheme {
        MapLocationScreen()
    }
}