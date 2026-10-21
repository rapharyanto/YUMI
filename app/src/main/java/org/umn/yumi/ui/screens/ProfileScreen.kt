package org.umn.yumi.ui.screens

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Close
import androidx.activity.compose.BackHandler
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ---------- Warna (palet Yumi) ----------
private val PfMerah = Color(0xFFA51C1C)         // warna utama (tombol aktif, bookmark, Voice AI)
private val PfOranyeMerah = Color(0xFFFF4500)   // aksen kuat (Lihat Semua, notif, info buka)
private val PfOranye = Color(0xFFFF8C00)        // aksen oranye
private val PfEmas = Color(0xFFFFD700)          // aksen emas
private val PfRating = Color(0xFFF99C10)        // khusus bintang rating
private val PfKrim = Color(0xFFFAEBDB)          // latar lembut (chip, tag, ikon)
private val PfBayangan = Color(0xFFEDE6D3)      // garis tepi / bayangan lembut
private val PfBg = Color(0xFFFFFBEA)            // latar halaman
private val PfInk = Color(0xFF222222)
private val PfMuted = Color(0xFF777777)
private val PfCard = Color.White

// ---------- Data ----------
enum class Kategori { KOPI_WFH, MAKAN_MALAM }

data class Resto(
    val nama: String,
    val lokasi: String,
    val kategori: Kategori,
    val tag: String,
    val rating: String,
    val info: String,
    val aksi: String,
    val aksiIcon: ImageVector,
    val foto: String? = null    // nama file di res/drawable (tanpa .jpg). Kosong = otomatis dari nama resto
)

data class Kunjungan(
    val nama: String,
    val tanggal: String,
    val catatan: String,
    val bintang: Int,
    val sudahReview: Boolean
)

// Ganti dengan data aslimu / dari API nanti
// Parameter terakhir "foto" = nama file di res/drawable (huruf kecil, tanpa .jpeg/.webp)
private val daftarTersimpan = listOf(
    Resto("Bakso Solo Samrat", "Senopati, Jakarta Selatan", Kategori.MAKAN_MALAM, "Tersimpan 2 hari lalu",
        "4.8", "Buka sekarang • 2.1 km", "Buka Maps", Icons.Outlined.Explore,
        foto = "foto_bakso_solo_samrat"),

    Resto("Kopi Kenangan Bolsena", "Ruko Bolsena, Blok B No. 18 & 19, Gading Serpong", Kategori.KOPI_WFH, "Tempat WFH Favorit ☕",
        "4.8", "Banyak colokan & Wi-Fi cepat", "Menu", Icons.Outlined.MenuBook,
        foto = "kopi_kenangan"),

    Resto("Sei Sapi Aroma Asap", "Gandaria, Kebayoran Baru", Kategori.MAKAN_MALAM, "Must try weekend 🔥",
        "4.9", "Diskon 20% NyamPay", "Koleksi", Icons.Outlined.BookmarkAdd,
        foto = "sei_sapi"),

    Resto("Burger Beef Chese Cremini", "Ruko Melody, Blok B No.030", Kategori.KOPI_WFH, "Manual brew juara",
        "4.9", "Buka sampai 21.00", "Menu", Icons.Outlined.MenuBook,
        foto = "cremini"),

    Resto("Lekker dan Kopi Bojong Nangka", "Ruko Dasana Indah Blok RT.1 No. 10, Bojong Nangka, Kab. Tangerang", Kategori.MAKAN_MALAM, "Sambalnya nampol 🌶️",
        "4.8", "Buka sekarang • 4.3 km", "Buka Maps", Icons.Outlined.Explore,
        foto = "nasigoreng"),

    Resto("Fore Coffee", "Eastvara Mall, Jl. BSD Boulevard Utara, Cijantra", Kategori.KOPI_WFH, "Sepi & nyaman",
        "4.9", "Colokan banyak, AC dingin", "Menu", Icons.Outlined.MenuBook,
        foto = "fore"),

    Resto("STUCAF (Student Cafe & Billiard)", "Ruko Alloggio Timur Jl. Alloggio Raya No. 1 & 2, Medang", Kategori.MAKAN_MALAM, "Cocok buat larut malam",
        "4.5", "Buka 09.00 – 02.00", "Buka Maps", Icons.Outlined.Explore,
        foto = "stucaf")
)

private val daftarRiwayat = listOf(
    Kunjungan("Bakso Solo Samrat", "18 Okt 2026", "Kuahnya gurih, baksonya besar. Wajib balik lagi!", 5, true),
    Kunjungan("Lekker dan kopi Bojong Nangka", "19 Okt 2026", "Ngerjain tugas 3 jam, wifinya stabil.", 4, true),
    Kunjungan("Ayam bu Yudhi", "11 Okt 2026", "Sambal terasinya juara, pedasnya pas.", 5, true),
    Kunjungan("Kopi Kenangan Bolsena", "7 Okt 2026", "Ramai saat jam makan siang.", 4, false),
    Kunjungan("Sei Sapi Aroma Asap", "2 Okt 2026", "Dagingnya empuk, porsi lumayan.", 5, true)
)

private val namaFilter = listOf("Semua", "Kopi & WFH", "Makan Malam Enak")

// ---------- Layar utama ----------
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    fotoProfil: String? = "foto_profil"
) {
    // Menyimpan pilihan tab & filter, bahkan setelah layar diputar
    var tabAktif by rememberSaveable { mutableStateOf(0) }      // 0 = Tersimpan, 1 = Riwayat
    var filterAktif by rememberSaveable { mutableStateOf(0) }   // 0 = Semua, 1 = Kopi, 2 = Makan Malam

    var tampilPencapaian by rememberSaveable { mutableStateOf(false) }
    var tampilNotif by rememberSaveable { mutableStateOf(false) }
    var tampilSetelan by rememberSaveable { mutableStateOf(false) }
    var adaNotifBaru by rememberSaveable { mutableStateOf(true) }

    // Tombol Share: membuka menu bagikan bawaan Android (WhatsApp, Instagram, dll)
    val context = LocalContext.current
    val bagikanProfil = {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Lihat profil kulinerku di Yumi! 48 resto dikunjungi, 19 review ditulis.")
        }
        context.startActivity(Intent.createChooser(intent, "Bagikan profil"))
    }

    val tersimpanTampil = when (filterAktif) {
        1 -> daftarTersimpan.filter { it.kategori == Kategori.KOPI_WFH }
        2 -> daftarTersimpan.filter { it.kategori == Kategori.MAKAN_MALAM }
        else -> daftarTersimpan
    }
    val jumlahFilter = listOf(
        daftarTersimpan.size,
        daftarTersimpan.count { it.kategori == Kategori.KOPI_WFH },
        daftarTersimpan.count { it.kategori == Kategori.MAKAN_MALAM }
    )

    Box(modifier.fillMaxSize()) {
        Scaffold(
            containerColor = PfBg,
            contentWindowInsets = WindowInsets(0),
            topBar = {
                TopBar(fotoProfil, adaNotifBaru) {
                    tampilNotif = true
                    adaNotifBaru = false
                }
            }
        ) { padding ->
            // LazyColumn = daftar yang BISA DI-SCROLL ke bawah. Semua isi halaman ada di sini.
            LazyColumn(
                modifier = Modifier.padding(padding).fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item { HeaderRow(onShare = bagikanProfil, onSetelan = { tampilSetelan = true }) }
                item { ProfilInfo(fotoProfil) }
                item { AchievementCard(onLihatSemua = { tampilPencapaian = true }) }
                item {
                    TabUtama(
                        jumlahTersimpan = daftarTersimpan.size,
                        tabAktif = tabAktif,
                        onPilih = { tabAktif = it }
                    )
                }

                if (tabAktif == 0) {
                    // ---- Isi tab Tersimpan ----
                    item {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(namaFilter.size) { i ->
                                ChipFilter(
                                    teks = "${namaFilter[i]} (${jumlahFilter[i]})",
                                    aktif = filterAktif == i,
                                    onClick = { filterAktif = i }
                                )
                            }
                        }
                    }
                    items(tersimpanTampil, key = { it.nama }) { RestoCard(it) }
                } else {
                    // ---- Isi tab Riwayat Kunjungan ----
                    items(daftarRiwayat, key = { it.nama + it.tanggal }) { RiwayatCard(it) }
                }

                item { Spacer(Modifier.height(16.dp)) }   // jarak bawah daftar
            }
        }

        // Pop-up digambar di atas layar yang sama (tanpa jendela terpisah)
        if (tampilPencapaian) SheetPencapaian(onTutup = { tampilPencapaian = false })
        if (tampilNotif) SheetNotifikasi(onTutup = { tampilNotif = false })
        if (tampilSetelan) SheetSetelan(onTutup = { tampilSetelan = false })
    }
}

// ---------- Foto dari res/drawable (gradasi jika file belum ada) ----------
// Nama file otomatis dari nama resto: "Bakso Solo Samrat" -> foto_bakso_solo_samrat
fun namaFoto(nama: String): String =
    "foto_" + nama.lowercase().replace(Regex("[^a-z0-9]+"), "_").trim('_')

@Composable
fun FotoAtauGradasi(
    foto: String?,
    modifier: Modifier,
    warna: List<Color> = listOf(Color(0xFFE9B49A), Color(0xFFB5701A)),
    konten: @Composable BoxScope.() -> Unit = {}
) {
    val context = LocalContext.current
    val idFoto = remember(foto) {
        if (foto == null) 0 else context.resources.getIdentifier(foto, "drawable", context.packageName)
    }
    Box(modifier) {
        if (idFoto != 0) {
            Image(painterResource(idFoto), null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
        } else {
            Box(Modifier.fillMaxSize().background(Brush.linearGradient(warna)), contentAlignment = Alignment.Center) {
                Icon(Icons.Filled.Person, null, tint = Color.White.copy(alpha = 0.7f), modifier = Modifier.size(36.dp))
            }
        }
        konten()
    }
}

// ---------- Top bar (tetap di atas saat scroll) ----------
@Composable
fun TopBar(fotoProfil: String?, adaNotifBaru: Boolean, onNotif: () -> Unit) {
    Row(
        Modifier.fillMaxWidth().background(PfBg).statusBarsPadding().padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Outlined.LocationOn, null, tint = PfMerah, modifier = Modifier.size(16.dp))
        Spacer(Modifier.width(4.dp))
        Text("Jakarta Selatan", fontSize = 13.sp, fontWeight = FontWeight.SemiBold, color = PfInk)
        Icon(Icons.Filled.KeyboardArrowDown, null, tint = PfInk, modifier = Modifier.size(16.dp))
        Spacer(Modifier.weight(1f))
        Box(Modifier.clip(CircleShape).clickable(onClick = onNotif).padding(6.dp)) {
            Icon(Icons.Outlined.Notifications, "Notifikasi", tint = PfInk, modifier = Modifier.size(22.dp))
            if (adaNotifBaru) {
                Box(Modifier.size(8.dp).clip(CircleShape).background(PfOranyeMerah).align(Alignment.TopEnd))
            }
        }
        Spacer(Modifier.width(12.dp))
        FotoAtauGradasi(fotoProfil, Modifier.size(30.dp).clip(CircleShape))
    }
}

@Composable
fun HeaderRow(onShare: () -> Unit, onSetelan: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Profil Kuliner", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = PfInk)
        Spacer(Modifier.weight(1f))
        IconButton(onClick = onShare) { Icon(Icons.Outlined.Share, "Bagikan", tint = PfInk, modifier = Modifier.size(20.dp)) }
        IconButton(onClick = onSetelan) { Icon(Icons.Outlined.Settings, "Pengaturan", tint = PfInk, modifier = Modifier.size(20.dp)) }
    }
}

// ---------- Foto bulat, bio, statistik ----------
@Composable
fun ProfilInfo(fotoProfil: String?) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.size(170.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.55f)), contentAlignment = Alignment.Center) {
            FotoAtauGradasi(fotoProfil, Modifier.size(150.dp).clip(CircleShape))
        }
        Spacer(Modifier.height(12.dp))
        Text(
            "Pencinta sambal terasi & manual brew coffee ☕ | 48 resto dikunjungi | 19 review ditulis",
            fontSize = 12.sp, color = PfMuted, textAlign = TextAlign.Center, lineHeight = 18.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(14.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Statistik("48", "Dikunjungi", PfInk)
            Statistik("26", "Disimpan", PfInk)
            Statistik("19", "Review", PfInk)
            Statistik("3.4k", "Pembaca", PfOranyeMerah)
        }
    }
}

@Composable
fun Statistik(angka: String, label: String, warna: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(angka, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = warna)
        Text(label, fontSize = 11.sp, color = PfMuted)
    }
}

// ---------- Pencapaian ----------
@Composable
fun AchievementCard(onLihatSemua: () -> Unit) {
    KartuPutih {
        Column(Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.EmojiEvents, null, tint = PfRating, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(6.dp))
                Text("Pencapaian Foodie", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = PfInk)
                Spacer(Modifier.weight(1f))
                Text(
                    "Lihat Semua (9)", fontSize = 11.sp, color = PfMerah, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clip(RoundedCornerShape(6.dp)).clickable(onClick = onLihatSemua).padding(6.dp)
                )
            }
            Spacer(Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Lencana(Icons.Filled.LocalFireDepartment, PfKrim, PfOranyeMerah, "Master Sambal", "Tier Emas")
                Lencana(Icons.Filled.Coffee, PfKrim, PfOranye, "Coffee Hunter", "24 Kafe")
                Lencana(Icons.Filled.Verified, PfKrim, PfMerah, "Reviewer Jujur", "Top 5%")
            }
        }
    }
}

@Composable
fun Lencana(icon: ImageVector, bg: Color, tint: Color, judul: String, sub: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(Modifier.size(44.dp).clip(CircleShape).background(bg), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = tint, modifier = Modifier.size(22.dp))
        }
        Spacer(Modifier.height(6.dp))
        Text(judul, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = PfInk)
        Text(sub, fontSize = 10.sp, color = PfMuted)
    }
}

// ---------- Tab: Tersimpan / Riwayat Kunjungan ----------
@Composable
fun TabUtama(jumlahTersimpan: Int, tabAktif: Int, onPilih: (Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TombolTab("Tersimpan ($jumlahTersimpan)", Icons.Filled.Bookmark, tabAktif == 0) { onPilih(0) }
        Spacer(Modifier.width(8.dp))
        TombolTab("Riwayat Kunjungan", Icons.Outlined.History, tabAktif == 1) { onPilih(1) }
        Spacer(Modifier.weight(1f))
        Icon(Icons.Outlined.FilterList, null, tint = PfMuted, modifier = Modifier.size(22.dp))
    }
}

@Composable
fun TombolTab(teks: String, icon: ImageVector, aktif: Boolean, onClick: () -> Unit) {
    Row(
        Modifier.clip(RoundedCornerShape(12.dp))
            .background(if (aktif) PfMerah else Color.Transparent)
            .border(BorderStroke(1.dp, if (aktif) PfMerah else PfBayangan), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = if (aktif) Color.White else PfInk, modifier = Modifier.size(15.dp))
        Spacer(Modifier.width(6.dp))
        Text(teks, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = if (aktif) Color.White else PfInk)
    }
}

@Composable
fun ChipFilter(teks: String, aktif: Boolean, onClick: () -> Unit) {
    Box(
        Modifier.clip(RoundedCornerShape(12.dp))
            .background(if (aktif) PfKrim else Color.Transparent)
            .border(BorderStroke(1.dp, if (aktif) PfMerah else PfBayangan), RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(teks, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = if (aktif) PfMerah else PfInk)
    }
}

// ---------- Kartu restoran (tab Tersimpan) ----------
@Composable
fun RestoCard(r: Resto) {
    KartuPutih {
        Row(Modifier.padding(12.dp)) {
            FotoAtauGradasi(
                r.foto ?: namaFoto(r.nama),
                Modifier.size(width = 84.dp, height = 96.dp).clip(RoundedCornerShape(12.dp)),
                warna = listOf(Color(0xFFD9A066), Color(0xFF7A4A21))
            ) {
                Row(
                    Modifier.padding(6.dp).clip(RoundedCornerShape(8.dp)).background(Color.White)
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Star, null, tint = PfRating, modifier = Modifier.size(12.dp))
                    Text(r.rating, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PfInk)
                }
            }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.Top) {
                    Text(
                        r.nama, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = PfInk,
                        modifier = Modifier.weight(1f), maxLines = 1, overflow = TextOverflow.Ellipsis
                    )
                    Icon(Icons.Filled.Bookmark, null, tint = PfMerah, modifier = Modifier.size(20.dp))
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.Place, null, tint = PfMuted, modifier = Modifier.size(13.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(r.lokasi, fontSize = 11.sp, color = PfMuted, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Spacer(Modifier.height(6.dp))
                Text(
                    r.tag, fontSize = 10.sp, fontWeight = FontWeight.SemiBold, color = PfMerah,
                    modifier = Modifier.clip(RoundedCornerShape(8.dp)).background(PfKrim)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                )
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        r.info, fontSize = 11.sp, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f),
                        color = if (r.info.startsWith("Buka sekarang")) PfOranyeMerah else PfMuted
                    )
                    Row(
                        Modifier.clip(RoundedCornerShape(20.dp)).background(PfKrim)
                            .padding(horizontal = 12.dp, vertical = 7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(r.aksiIcon, null, tint = PfMerah, modifier = Modifier.size(14.dp))
                        Spacer(Modifier.width(4.dp))
                        Text(r.aksi, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = PfMerah)
                    }
                }
            }
        }
    }
}

// ---------- Kartu riwayat (tab Riwayat Kunjungan) ----------
@Composable
fun RiwayatCard(k: Kunjungan) {
    KartuPutih {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.Top) {
            Box(
                Modifier.size(44.dp).clip(RoundedCornerShape(12.dp)).background(PfKrim),
                contentAlignment = Alignment.Center
            ) { Icon(Icons.Outlined.History, null, tint = PfMerah, modifier = Modifier.size(22.dp)) }
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(k.nama, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = PfInk, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(k.tanggal, fontSize = 11.sp, color = PfMuted)
                Spacer(Modifier.height(6.dp))
                Row {
                    repeat(5) { i ->
                        Icon(
                            Icons.Filled.Star, null,
                            tint = if (i < k.bintang) PfRating else PfBayangan,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }
                Spacer(Modifier.height(6.dp))
                Text(k.catatan, fontSize = 12.sp, color = PfInk, lineHeight = 17.sp)
                Spacer(Modifier.height(8.dp))
                Text(
                    if (k.sudahReview) "Review ditulis" else "Belum direview",
                    fontSize = 10.sp, fontWeight = FontWeight.SemiBold,
                    color = if (k.sudahReview) PfOranyeMerah else PfMerah,
                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                        .background(if (k.sudahReview) PfKrim else PfKrim)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        }
    }
}

// ---------- Helper ----------
@Composable
private fun KartuPutih(konten: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = PfCard),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        content = konten
    )
}

// =====================================================================
// Lembar bawah (bottom sheet): Pencapaian, Notifikasi, Pengaturan
// =====================================================================
data class Pencapaian(
    val judul: String, val sub: String, val icon: ImageVector,
    val bg: Color, val tint: Color, val terbuka: Boolean
)

private val daftarPencapaian = listOf(
    Pencapaian("Master Sambal", "Tier Emas", Icons.Filled.LocalFireDepartment, PfKrim, PfOranyeMerah, true),
    Pencapaian("Coffee Hunter", "24 kafe dikunjungi", Icons.Filled.Coffee, PfKrim, PfOranye, true),
    Pencapaian("Reviewer Jujur", "Top 5% reviewer", Icons.Filled.Verified, PfKrim, PfMerah, true),
    Pencapaian("Kolektor Resto", "26 resto tersimpan", Icons.Filled.Bookmark, PfKrim, PfMerah, true),
    Pencapaian("Penjelajah Malam", "10 resto makan malam", Icons.Outlined.Explore, PfKrim, PfOranye, true),
    Pencapaian("Influencer Mini", "3.4k pembaca", Icons.Filled.Star, PfKrim, PfEmas, true),
    Pencapaian("Raja Bakso", "Kunjungi 10 resto bakso (6/10)", Icons.Outlined.Place, Color(0xFFEDEDED), Color(0xFF9E9E9E), false),
    Pencapaian("Pemburu Diskon", "Pakai 10 promo (4/10)", Icons.Filled.EmojiEvents, Color(0xFFEDEDED), Color(0xFF9E9E9E), false),
    Pencapaian("Foto Juara", "Unggah 20 foto makanan (8/20)", Icons.Filled.Person, Color(0xFFEDEDED), Color(0xFF9E9E9E), false)
)

// Kerangka pop-up yang dipakai bersama (lapisan gelap + kartu di tengah)
@Composable
fun DialogKartu(judul: String, onTutup: () -> Unit, konten: @Composable ColumnScope.() -> Unit) {
    BackHandler(onBack = onTutup)   // tombol Back menutup pop-up
    Box(
        Modifier.fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null, onClick = onTutup),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) { },
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = PfCard)
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(judul, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = PfInk, modifier = Modifier.weight(1f))
                    IconButton(onClick = onTutup) { Icon(Icons.Filled.Close, "Tutup", tint = PfInk) }
                }
                Spacer(Modifier.height(8.dp))
                Column(
                    Modifier.heightIn(max = 460.dp).verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    content = konten
                )
            }
        }
    }
}

@Composable
fun SheetPencapaian(onTutup: () -> Unit) {
    DialogKartu("Semua Pencapaian (9)", onTutup) {
        Text("6 terbuka • 3 masih terkunci", fontSize = 12.sp, color = PfMuted)
        daftarPencapaian.forEach { p ->
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(PfBg).padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.size(44.dp).clip(CircleShape).background(p.bg), contentAlignment = Alignment.Center) {
                    Icon(p.icon, null, tint = p.tint, modifier = Modifier.size(22.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(p.judul, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = if (p.terbuka) PfInk else PfMuted)
                    Text(p.sub, fontSize = 11.sp, color = PfMuted)
                }
                if (!p.terbuka) Icon(Icons.Filled.Lock, null, tint = PfMuted, modifier = Modifier.size(18.dp))
            }
        }
    }
}

data class Notif(val judul: String, val isi: String, val waktu: String)

private val daftarNotif = listOf(
    Notif("Review kamu disukai", "Rina dan 12 orang lain menyukai review Bakso Solo Samrat.", "5 menit lalu"),
    Notif("Pembaca baru", "Dimas mulai mengikuti profil kulinermu.", "1 jam lalu"),
    Notif("Diskon untukmu", "Sei Sapi Aroma Asap: diskon 20% pakai NyamPay.", "Kemarin"),
    Notif("Pencapaian hampir terbuka", "4 promo lagi untuk membuka Pemburu Diskon.", "2 hari lalu")
)

@Composable
fun SheetNotifikasi(onTutup: () -> Unit) {
    DialogKartu("Notifikasi", onTutup) {
        daftarNotif.forEach { n ->
            Row(
                Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(PfBg).padding(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                Box(Modifier.size(38.dp).clip(CircleShape).background(PfKrim), contentAlignment = Alignment.Center) {
                    Icon(Icons.Outlined.Notifications, null, tint = PfMerah, modifier = Modifier.size(20.dp))
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(n.judul, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = PfInk)
                    Text(n.isi, fontSize = 12.sp, color = PfMuted, lineHeight = 17.sp)
                    Spacer(Modifier.height(4.dp))
                    Text(n.waktu, fontSize = 10.sp, color = PfMerah)
                }
            }
        }
    }
}

@Composable
fun SheetSetelan(onTutup: () -> Unit) {
    var notifAktif by rememberSaveable { mutableStateOf(true) }
    var lokasiOtomatis by rememberSaveable { mutableStateOf(true) }
    var profilPublik by rememberSaveable { mutableStateOf(true) }

    DialogKartu("Pengaturan", onTutup) {
        BarisSwitch("Notifikasi", "Terima kabar review dan promo", notifAktif) { notifAktif = it }
        BarisSwitch("Lokasi otomatis", "Deteksi lokasimu untuk rekomendasi", lokasiOtomatis) { lokasiOtomatis = it }
        BarisSwitch("Profil publik", "Orang lain bisa melihat profil kulinermu", profilPublik) { profilPublik = it }
    }
}

@Composable
fun BarisSwitch(judul: String, deskripsi: String, aktif: Boolean, onUbah: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth().padding(vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(judul, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = PfInk)
            Text(deskripsi, fontSize = 11.sp, color = PfMuted)
        }
        Switch(
            checked = aktif,
            onCheckedChange = onUbah,
            colors = SwitchDefaults.colors(checkedTrackColor = PfMerah, checkedThumbColor = Color.White)
        )
    }
}