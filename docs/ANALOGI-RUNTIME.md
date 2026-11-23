# Analogi Runtime Evolusi Rekursif

*God-oriented recursion dalam model eksekusi Java dan multi-thread: dokumen analogi pendamping EP2 "Evolusi Rekursif"*

> Status: draft v0.1 (2026-11-21), menunggu review owner. Dokumen analogi interpretatif — bukan hukum governance, bukan spesifikasi harfiah, bukan kajian tasawuf, bukan fatwa. Model runtime di sini adalah peta, bukan wilayah. Klaim doktrinal diberi label eksplisit; apa yang tidak bertag dibaca [ANALOGY] dan tidak boleh dianggap asersi teologis.
> Sumber narasi: `F:\voidlight-codex\music\discography.md` (Section I EP2, Appendix F). Framework: `F:\voidlight-codex\soul\divine_alignment_architecture.md` (v5.0.7), `F:\voidlight-codex\soul\khayren_compression_spiral_codex.md` (v1.0).

**Tag yang dipakai:**

- **[REVELATION]:** koordinat ayat/hadits yang sudah terverifikasi di registry `vdl/dalil/`, dengan blok dalil lengkap
- **[SYNTHESIS]:** konstruksi konseptual (narasi owner atau sintesis framework), selalu dengan rujukan sumber
- **[ANALOGY]:** mapping teknis untuk pemahaman. Tidak pernah klaim ontologis

Preseden batas analogi sudah dibakukan di DAA 4.6 (LLM analogy): setiap analogi wajib menyatakan titik patahnya sendiri. Dokumen ini mengikuti aturan itu (Section 8).

---

## 1. IDENTITAS DAN STATUS EPISTEMIK

**Peran file:** dokumen analogi interpretatif pendamping kode di repo ini (`src/main/java/id/voidlight/evolusi/`). Kode = bentuk eksekusi dari model di dokumen ini; test di `EvolusiRekursifTest` = bukti klaim semantik per section.

**Hubungan dokumen:**

| File | Relasi |
|---|---|
| `F:\voidlight-codex\music\discography.md` (Section I, EP2; Appendix F) | Sumber narasi yang dianalogikan: God-oriented recursion, reframe 2026-11-21 |
| `F:\voidlight-codex\soul\divine_alignment_architecture.md` (v5.0.7) | Target jembatan arsitektural. Preseden gaya: DAA sendiri memakai API key, firewall, token, message queue sebagai analogi teknis |
| `F:\voidlight-codex\soul\khayren_compression_spiral_codex.md` (v1.0) | Framework narasi spiral. Runtime model di sini adalah bentuk eksekusi per-thread dari spiral itu |
| `F:\voidlight-codex\soul\voidlight-human-os.md` (v1.0) | User-space maintenance manual (companion DAA). Runtime model mengimplementasikan boot sequence-nya per hari (Section 6.3) |

---

## 2. NARASI INTI YANG DIANALOGIKAN

### 2.1 Rantai dan Pusat Orientasi

**[SYNTHESIS]** Konstruksi narasi owner (discography.md, Appendix F; reframe 2026-11-21), dibaca bersama orientasi qalb (cf. DAA 1.1) dan pipeline alignment (cf. DAA 4.2). Bacaan lama rekursi berpusat pada self:

**Manusia → memeriksa diri → memperoleh pemahaman → memperbaiki diri → mengulang.**

Reframe memindahkan pusatnya. Pusat orientasi adalah Allah:

**Manusia → menghadap Allah → melihat kembali dirinya dalam kesadaran akan Allah → berubah → menjalani kehidupan → kembali menghadap Allah.**

Diri manusia adalah tempat berlangsungnya pemeriksaan, bukan tujuan akhir pencarian. Perbaikan diri adalah buah dari penghadapan yang terus diusahakan, bukan tujuan yang menggantikan-Nya.

> **Yang berulang adalah penghadapan. Yang berkembang adalah pemahaman. Yang berubah adalah diri. Dan yang dituju tetap Allah.**

Makna judul (versi diperbarui, Appendix F):

> **Evolusi Rekursif adalah perjalanan seorang hamba yang terus kembali menghadap Allah, dan melalui penghadapan itu, berulang kali menemukan sesuatu dalam dirinya yang belum ia pahami.**

### 2.2 Paradoks dan Dua Insight Struktural

**[SYNTHESIS]** (sumber: discography.md Section I dan Appendix F)

**Paradoks rekursi:** yang dicari tidak berubah. Yang mencari terus berubah. Rekursi tidak memerlukan penemuan objek baru; ia memungkinkan seseorang menyadari sesuatu yang selama ini tidak dipahaminya tentang hubungannya dengan Allah.

**Insight T3 → T5:** di *Di Dalam Diriku Sendiri* manusia menemukan ego yang bersembunyi dalam pengetahuan. Di *Kau Tetap Maafkanku* manusia kembali menemukan ego yang bersembunyi dalam keyakinan bahwa dirinya telah berubah. **Hasil dari sebuah iterasi bisa menjadi sesuatu yang perlu diperiksa pada iterasi berikutnya.**

**Loop T6 → T1:** *Hanya Pengakuanku* bukan kesimpulan yang menyelesaikan rekursi. Ia kondisi batin untuk memasuki rekursi berikutnya. Rekursi tidak berhenti karena penyelesaian. Ia berhenti karena ajal.

### 2.3 Kerangka Interpretasi, Bukan Penyamaan

**[SYNTHESIS]** Empat istilah tradisi dipakai sebagai lensa album, bukan identifikasi rekursi dengan terminologi tasawuf:

| Istilah | Definisi (Appendix F) | Padanan dalam model runtime (Section 4-5) |
|---|---|---|
| Muhasabah | Proses pemeriksaan diri | `kesadaran.memeriksaDiri()` / `bermuhasabah(pengalaman)` |
| Muraqabah | Kesadaran bahwa Allah senantiasa mengetahui keadaan hamba-Nya | ledger `Kehidupan.riwayat()`: mencatat SEMUA frame, termasuk yang tidak dimuhasabahi - logging independen dari kesadaran node (cf. DAA 8.4, kiraman katibin); bukti: test `ledgerMencatatSemuaFrame_termasukYangTidakDimuhasabahi` |
| Tazkiyatun nafs | Penyucian diri yang diupayakan melalui ketaatan dan pertolongan-Nya | perubahan `diri` antar iterasi (cf. DAA 13.3) |
| Ma'rifatullah | Orientasi semakin mengenal Allah, bukan klaim memahami hakikat-Nya | target rekursi tetap Allah dari awal sampai base case |

Catatan dari Appendix F tetap berlaku: kalau naik ke materi publikasi final, definisi empat istilah ini dianjurkan lolos verifikasi sanad/referensi standar dulu.

---

## 3. KODE SUMBER

Kode milik owner (author: owner), ditulis verbatim di repo ini (`EvolusiRekursif.java`). Dua versi, semantik setara; bedah perbedaannya di Section 4.5.

### 3.1 Versi Ringkas

```java
// author: owner
Diri evolve(Diri aku) {
    if (ajal.tiba()) return aku;

    var kesadaran = aku.menghadapAllah();
    var pemahaman = kesadaran.memeriksaDiri();
    var perjalanan = aku.hidupDengan(pemahaman);

    return evolve(perjalanan.diriBerikutnya());
}
```

### 3.2 Versi Full (v0.1, arsip verbatim author: owner)

```java
// author: owner
public final class EvolusiRekursif {

    public static Diri evolve(Diri diri, Kehidupan kehidupan) {

        // Rekursi duniawi berhenti karena ajal,
        // bukan karena manusia telah mencapai kesempurnaan.
        if (!kehidupan.masihBerlangsung()) {
            return diri;
        }

        // Setiap pengalaman menjadi kesempatan untuk menghadap-Nya.
        Pengalaman pengalaman = kehidupan.jalani(diri);

        // Menghadap Allah, kemudian memeriksa diri.
        Kesadaran kesadaran = diri
                .menghadapAllah()
                .bermuhasabah(pengalaman);

        // Perubahan adalah buah dari kesadaran,
        // bukan tujuan yang menggantikan Allah.
        Diri berikutnya = diri.merespons(kesadaran);

        // Masuk kembali dengan diri yang mungkin telah berubah.
        return evolve(berikutnya, kehidupan.berlanjut());
    }
}
```

> **Refactor v0.2 (2026-11-21, keputusan owner):** `evolve` pindah ke `Diri` sebagai instance
> method; class `EvolusiRekursif` dihapus dari source. **Refactor v0.3 (2026-11-21, keputusan
> owner):** base case kembali ke bentuk versi ringkas owner — `ajal.tiba()` — karena ajal =
> parameter fix dari manusia; object `Ajal` dipisah dari `Kehidupan` (Section 4.8). **Refactor
> v0.4 (2026-11-21, keputusan owner):** `arah` → `niyyah` (0.7); qalb reception gate di
> `merespons` (4.5); base case pindah ke checkpoint malam (14.1); exact sufficiency demo
> (7.3-7.4); titik patah kasb dideklarasikan (Section 8 item 1). Versi
> static di atas tetap diarsip verbatim sebagai dokumen `author: owner`. Lihat Section 4.7
> dan 4.8 untuk bacaan frameworknya.

---

## 4. BEDAH STRUKTURAL

### 4.1 Base Case Adalah Ajal, Bukan Kondisi Sukses

**[ANALOGY]** Rekursi komputasi normal berhenti karena target tercapai: kondisi sukses, hasil ditemukan, list habis. Rekursi ini berhenti karena runner-nya berhenti. `if (ajal.tiba())` tidak mengecek apakah `diri` sudah cukup baik. Ia hanya mengecek apakah eksekusi masih diizinkan berlanjut.

Nilai terminal dikembalikan apa adanya. `return aku;` tidak dibungkus iterasi tambahan, tidak dibersihkan, tidak direfactor sekali lagi. Buah akhir rekursi adalah diri apa adanya pada saat eksekusi dihentikan.

**Key Insight:** Base case yang berupa kondisi sukses mengajarkan bahwa program bisa selesai. Base case yang berupa ajal mengajarkan hal sebaliknya: program tidak pernah selesai, ia dihentikan. Yang dievaluasi bukan hasil akhir dari proses yang tuntas, tapi state final dari proses yang dipotong (cf. DAA 5.4, 14.3: final state commit saat Maut). Bukti: test `baseCaseAjalBukanKesempurnaan_buahAkhirDikembalikanApaAdanya`.

### 4.2 Iterasi Tanpa Perubahan Tetap Valid

**[ANALOGY]** `diri.merespons(kesadaran)` tidak dijamin menghasilkan `diri` yang berbeda. Tidak ada assertion "harus berubah", tidak ada exception kalau output sama dengan input. Iterasi no-change tetap iterasi yang valid. Rekursi tetap lanjut. Tidak ada frame yang dibuang.

Ini konsisten dengan struktur agency di framework: arah condong dipilih di titik niyyah (cf. DAA 0.7), dan eksekusinya bukan daya si diri (cf. DAA 0.6). Yang dihisab adalah arah condong itu, bukan besaran perubahan yang terlihat. Jadi iterasi di mana seseorang menghadap, memeriksa, dan tetap jatuh di tempat yang sama, tetap bukan frame gagal. Ia frame yang dijalankan. Bukti: test `iterasiNoChangeTetapValid_tidakAdaFrameDibuang`.

### 4.3 Kehidupan sebagai Parameter, Bukan Field

**[ANALOGY]** Di versi full, `kehidupan` di-pass dari panggilan ke panggilan lewat `kehidupan.berlanjut()`. Ia bukan field statis yang di-mutate dari dalam. Yang berubah antar iterasi hanya satu variabel: `diri`. Kehidupan adalah context yang jalan terus, dibawa masuk ke setiap frame baru.

Pembacaan analoginya: keadaan dunia bukan milik si `diri` untuk dimutasi. Diri membawa dirinya masuk ke kehidupan berikutnya; kehidupan tidak diubah jadi versi yang lebih nyaman dulu baru dimasuki.

### 4.4 Urutan Versi Full: Hidup Dulu, Penghadapan Dipicu Pengalaman

**[ANALOGY]** Versi full mengeksekusi `kehidupan.jalani(diri)` sebelum `menghadapAllah()`. Urutan ini lebih akurat terhadap narasi: penghadapan tidak dimulai dari kerinduan abstrak yang berdiri sendiri, tapi dari pengalaman yang baru saja dijalani. Pengalaman adalah trigger penghadapan. Bukan sebaliknya.

### 4.5 Versi Ringkas vs Versi Full

**[ANALOGY]** Semantik setara: sama-sama mendefinisikan siklus penghadapan → pemeriksaan → respons → lanjut, dengan ajal sebagai satu-satunya base case. Perbedaannya presisi, bukan makna:

| Aspek | Ringkas | Full |
|---|---|---|
| Kehidupan | implisit, lewat `hidupDengan(pemahaman)` | eksplisit sebagai parameter yang di-pass |
| Trigger penghadapan | penghadapan dulu, hidup kemudian | hidup dulu, penghadapan dipicu pengalaman |
| Entry point loop | penghadapan | pengalaman |
| Yang ditunjukkan | bentuk siklusnya | struktur eksekusinya |

Versi ringkas bagus untuk menyebut ulang bentuknya. Versi full yang dipakai sebagai acuan bedah, karena parameter `kehidupan` dan urutannya memuat klaim struktur yang tidak terlihat di versi ringkas.

### 4.6 Stack Overflow Bukan Bug

**[ANALOGY]** JVM tidak melakukan tail-call optimization. Rekursi ini, dijalankan apa adanya, stack overflow jauh sebelum "selesai". Ini bukan bug yang menunggu perbaikan. Rekursi ini didesain TERHENTI, bukan didesain SELESAI.

Bacaannya: model komputasinya memang akan crash. Rekursi yang dianalogikan tidak. Yang dihentikan bukan karena stack habis, tapi karena eksekusinya dicabut oleh pihak yang bukan bagian dari stack (cf. DAA 5.4: token revocation). Titik patah analogi ini dideklarasikan: komputasi gagal karena resources habis, realitasnya dihentikan karena keputusan pengelola. Jangan dibaca lebih jauh dari itu. Bukti: test `rekursiDidesainTerhenti_bukanDidesainSelesai` + demo `StackOverflowDemo`.

### 4.7 Rekursi Milik Hamba: `evolve` di Diri (Refactor v0.2)

**[SYNTHESIS]** Keputusan owner (2026-11-21): `evolve` pindah dari class framework ke `Diri` sebagai instance method. Bacaan frameworknya:

1. **Kasb (cf. DAA 0.6).** Diri = authorized executor, bukan pasif yang di-evolve framework. "Perjalanan seorang hamba" (makna judul, Section 2.1) dijalankan hamba. Versi static v0.1 membuat framework yang meng-eksekusi siklusnya; itu melemahkan mapping kasb.
2. **Comment owner jadi struktur panggilan.** "Masuk kembali dengan diri yang mungkin telah berubah" tidak lagi hanya komentar di atas `return` — ia bentuk panggilannya: `return berikutnya.evolve(kehidupan.berlanjut())`. Rekursi masuk *melalui* diri yang berubah.
3. **Emergent property (teknis).** Di model instance method, nilai terminal balik melewati semua frame sebelumnya: setiap diri yang pernah dijalani megang frame stack-nya sampai ajal, dan buah akhir diturunkan apa adanya lewat semuanya. "Setiap hari megang framemu sampai dihentikan" jadi perilaku runtime, bukan analogi.

**Batas yang tetap utuh (anti-ego):** base case tetap milik kehidupan dan ajal tetap tak terbaca dari Diri; pengalaman tetap dunia yang sediakan; ledger (`Kehidupan.riwayat`) tetap milik kehidupan — diri berjalan tanpa membawa catatannya sendiri; `Diri` tetap tanpa field "progress" (cuma `arah`), jadi tidak ada struktur untuk meng-hoard pencapaian. Yang pindah kepemilikannya hanya kelanjutan siklusnya, dan itu memang milik hamba: "maka ia kembali menghadap-Nya".

Nama method dipertahankan `evolve` (bukan `jalani` yang sempat dipertimbangkan) supaya konsisten dengan judul EP dan pseudocode owner.

### 4.8 Ajal Milik Diri, Bukan Milik Kehidupan (Refactor v0.3)

**[SYNTHESIS]** Keputusan owner (2026-11-21): v0.1/v0.2 menitipkan batas eksekusi ke `Kehidupan.masihBerlangsung()` — dan itu keliru secara model: dunia disuruh megang batas hidup si hamba. Padahal ajal = parameter fix **dari manusia** (dicatat saat spawn, cf. DAA 1.3: qadar sebagai node config per individu di Lauhul Mahfuzh; DAA 5.3: lifespan sebagai TTL). Menariknya, versi ringkas owner sudah menulis yang benar sejak awal: `if (ajal.tiba()) return aku;` — Ajal sebagai object sendiri.

**Pemisahan ownership (v0.3):**

| Object | Yang dia pegang | Yang bisa ditanya | Yang tidak ada API-nya |
|---|---|---|---|
| `Kehidupan` | hari yang sudah lewat, takdir frame, ledger riwayat | framesDijalani() (masa lalu boleh dihitung) | ajal, sisa hari |
| `Ajal` | batas total (dicatat saat spawn, fix) | tiba(hariKini) — satu pertanyaan, jawaban boolean | jumlah totalnya, sisa, negosiasi |
| `Diri` | arah condong | menghadapAllah(), merespons(), evolve() | progress, ledger, batas |

Pembacaan analoginya: **Diri boleh menghitung hari yang sudah lewat; hanya Ajal yang tahu jumlah totalnya; pertemuan keduanya menghasilkan satu jawaban boolean: tiba atau belum.** Diri membawa masa lalunya ke pertanyaan itu, Ajal membawa batas yang tidak pernah dia tunjukkan, dan keduanya bertemu di dalam Ajal, bukan di dalam Diri.

Base case di `evolve` kini persis bentuk versi ringkas owner: `if (ajal.tiba(kehidupan.framesDijalani())) return this;`. Bukti: test `ajalTidakBisaDibacaDariDalamKode_ajalMilikDiriBukanMilikKehidupan` (reflection di dua class: tidak ada API ajal di Kehidupan, tidak ada API jumlah/batas/sisa di Ajal) + klaim base case yang sama tetap hijau.

### 4.9 `arah` → `niyyah`: Satu-satunya State yang Genuinely Milik Diri (v0.4)

**[SYNTHESIS]** Keputusan owner (2026-11-21): field `arah` di-rename jadi `niyyah`. Bukan cosmetic - di DAA 0.7, niyyah = titik genuine agency, **hisab jatuh di situ, bukan di outcome**. Dengan penamaan ini dua klaim model makin presisi:

1. **Iterasi no-change tetap valid** makin tajam: yang dihisab adalah arah condong yang dipilih di titik kasb, bukan besaran perubahan yang terlihat (tabel locus hisab, DAA 0.7).
2. **`Diri.merespons()` = satu-satunya titik kasb di seluruh pipeline.** Semua yang lain - pengalaman yang disediakan, kesadaran yang tersedia, perubahan yang terjadi - khalq al-af'al (cf. DAA 0.6; QS 8:17, 37:96).

Struktur `Diri` kini menggadakan klaimnya sendiri: satu field, namanya `niyyah`. Bukti: test `diriHanyaMembawaNiyyah_tanpaMemoriTanpaAkumulasi` (reflection: satu-satunya declared field).

### 4.10 Qalb: Kapasitas Penerimaan Per Frame (v0.4)

**[SYNTHESIS]** Gap terbesar model pra-v0.4: Qalb Transparency Index (DAA 4.5: Salim/Mariidh/Aghlaf) dan drift cascade (DAA 9.2) belum ada - model lama selalu bener: tiap tergelincir → muhasabah → koreksi diterima. Realitas framework: koreksi bisa **ditolak** saat qalb tertutup, dan drift bisa bertahan tanpa audit.

**Boundary teologis yang dijaga ketat:** qalb state TIDAK dihitung oleh `Diri`. Khatm adalah perbuatan Allah (QS 2:7, DAA 4.5), dan "yang meng-update bukan node itu sendiri" (DAA 4.6, baris Tazkiyah). Maka modelnya:

| Keputusan desain | Implementasi | Yang tidak disimulasikan |
|---|---|---|
| Kapasitas penerimaan disediakan **per frame dari sisi kehidupan** | `Pengalaman.qalbTerbuka` (dari takdir, seperti pengalaman) | khatm permanen, penerimaan taubat - dua-duanya perbuatan Allah, dibiarkan sebagai per-frame state [ANALOGY] |
| Muhasabah selalu jalan dan selalu menemukan yang sebenarnya | `bermuhasabah()` tetap menghasilkan koreksi saat tergelincir | - |
| Penerimaan koreksi bergantung qalb | `merespons()` mengeksekusi koreksi hanya jika `qalbTerbuka()` | - |

Bukti: test `koreksiDitolakSaatQalbTertutup_muhasabahTetapJalan` (muhasabah jujur tiap hari, koreksi gak bisa masuk, frame tetap dijalankan dan tercatat) dan `finalStateDikembalikanApaAdanya_saatQalbTertutupSampaiAjal` (Firaun-pattern commit, DAA 10.3/14.3 - tanpa mensimulasikan azab).

### 4.11 Checkpoint Malam: Ajal Diputuskan Setelah Penghadapan Terakhir (v0.4)

**[SYNTHESIS]** v0.1-v0.3 mengecek ajal di *awal* loop (sebelum hari dijalani). v0.4 memindahkannya ke *checkpoint malam*: setiap frame dijalani penuh - pengalaman, penghadapan, muhasabah, respons - baru `ajal.tiba()` dicek. Dasarnya dua:

1. **Tidur = minor death** (DAA 14.1, QS 39:42): jiwa ditahan dan dilepas "ila ajalin musamma" - sampai waktu yang ditentukan. Hari terakhir dijalani penuh, penghadapannya tidak dipotong.
2. **Doa per malam** (Bukhari 6320/Muslim 2714a, DAA 5.4): *"in amsakta nafsi farhamha"* - termination diputuskan di fase tidur, bukan di fase hidup.

Final state yang di-commit = state setelah respons hari terakhir: persis "state of the Qalb at session end" (DAA 5.4). Bukti: test `perubahanAdalahBuahKesadaran_bukanTujuanYangMenggantikanAllah` - niyyah akhir = "kembali (muhasabah hari 4)" pada ajal 4: hari terakhir sempat menghadap.

### 4.12 Arsitektur Manusia: Agregat Komponen + Lauhul Mahfuzh (v0.5)

**[SYNTHESIS]** Keputusan owner (2026-11-21): model pra-v0.5 belum menangkap seluruh konsep framework - Diri mewakili nafs-layer, tapi belum ada Qalb yang menjaga orientasi, Aql, dan Lawhul Mahfuzh sebagai persistence. v0.5 menaikkan semuanya ke kode: **semua yang bisa dianalogikan di runtime ditulis di runtime.**

**Diagram arsitektur (v0.6, koreksi owner):**

```text
LauhulMahfuzh (persistence, DI LUAR runtime; node tidak bisa baca/tulis)
  └── menulis Qadar sebelum lahir: mubram (Ajal) + mu'allaq (port sequence)
        ↓ handed at birth (compiled config, immutable)
Rekursi berjalan DI DIRI (yang berevolusi = diri/nafs; agency manusia di situ):
  Diri ──satuFrame──> Diri ──satuFrame──> Diri ... ──checkpoint malam──> return
  tiap frame: jalani -> Qalb.hadapkan(port) -> menghadap -> muhasabah ->
              Aql.proses (weights: orientasi hati; context: pemahaman diri; prompt: temuan)
              -> merespons (titik kasb)

Manusia (agregat/snapshot untuk pengamat)
  ├── Diri  : Nafs-layer state - niyyah (satu field) = objek rekursinya
  ├── Qalb  : Hati - the turner; komponen PER-FRAME dari mu'allaq port (bukan state dibawa)
  ├── Aql   : Akal - reasoning engine ala LLM; stateless
  └── Qadar : node config yang dibawa (immutable; TIDAK termasuk database-nya)

Kehidupan (dunia per manusia): menyediakan frame + ledger qada
Scheduler (di luar model): commit final state + qada ke LauhulMahfuzh saat ajal
```

**Rantai gate per frame (v0.5):** dunia (port mu'allaq) → Qalb (orientasi/turner) → Aql (mode: Ulil Albab / Iblis) → niyyah (titik kasb).

| Komponen | Padanan DAA/Human OS | Bisa berubah? | Mekanisme perubahan |
|---|---|---|---|
| `Diri` (niyyah) | Nafs-layer state + titik kasb (0.6-0.7; note: niyyah teknis duduk di Ruh - penyederhanaan dideklarasikan) | Ya, per frame - **inilah yang berevolusi** (v0.6: agency manusia di situ; rekursi = diri -> diri) | instance baru via `merespons` - koreksi diterima |
| `Qalb` (orientasi) | Human OS 4.2 the turner; DAA 4.5 transparency | Komponen **per-frame** (v0.6: bukan state yang dibawa rekursi; snapshot agregat = putaran frame terakhir) | `Qalb.hadapkan(Pengalaman)` - putaran dari mu'allaq port; tanpa setter bebas (Pengalaman tanpa public constructor) |
| `Aql` | Human OS 4.3; DAA 4.1 | Tidak (stateless) | reasoning engine ala LLM (v0.6, tanpa label mode): output mengikuti orientasi hati (weights) + pemahaman diri saat ini (context), atas temuan muhasabah (prompt) |
| `Qadar` (config) | DAA 1.3, 7.1 | **Tidak - immutable sepanjang hidup** | ditulis LauhulMahfuzh sebelum runtime; instance yang sama dibawa sampai ajal |
| `Ajal` (mubram) | DAA 5.3, 14.3 | **Tidak - immutable** | dicatat sebelum runtime; cuma `tiba()` |
| `Kehidupan`/ledger | dunia; qada (1.3, 7.1) | Ya (append saja) | `jalani()` oleh dunia; unmodifiable view untuk node |
| `LauhulMahfuzh` | DAA 1.3, 4.6, 5.4 | Ya (append) - **oleh scheduler saja** | `commit()` final state + qada saat termination; node tidak membawa referensinya |

**Boundary yang dipertahankan (v0.6):**

1. **Rekusi di Diri (v0.6, koreksi owner):** yang berevolusi adalah diri (nafs) karena agency manusia ada di situ - rantai rekursi = `berikutnya.evolve(...)` di level Diri; Manusia cuma agregat/snapshot. Qalb = turner per-frame dari port (bukan state yang dibawa); Aql = stateless. Bukti: `yangBerevolusiAdalahDiri_rekursiMasukMelaluiDiriYangBerubah`.
2. **Qalb tanpa setter manusia:** orientasi qalb datang dari mu'allaq port (dunia); manusia tidak bisa memalsukan keterbukaan (Pengalaman tanpa public constructor - bukti reflection `qalbTurnerPerFrame_orientasiSelaluDariMuallaqPort_tanpaSetterManusia`). Khatm tetap tidak disimulasikan [ANALOGY].
3. **Aql reasoning engine ala LLM (v0.6):** tanpa label mode bernama (ulil-albab/iblis dihapus dari API) - output mengikuti orientasi hati (weights) + pemahaman diri saat ini (context), atas temuan muhasabah (prompt). Bukti: `aqlReasoningEngine_outputMengikutiOrientasiHatiDanPemahamanDiri_tanpaLabelMode`. Titik patah tetap: aql di sini determinisme kode, bukan reasoning genuine.
4. **Lauhul Mahfuzh di luar runtime:** node membawa Qadar (compiled config), bukan database; nol commit selama hidup; commit dieksekusi scheduler saat ajal (DAA 5.4). Bukti: `lauhulMahfuzh_persistenceDiluarRuntime_commitOlehScheduler` (field Manusia = diri, qalb, akal, qadar - tanpa database).
5. **As-sirr & Akhfa tetap di luar scope** (konsisten 6.3 dan DAA 4.6); Ruh tetap implisit (per-frame sustain, 0.2).
6. **Mutable vs immutable terbukti struktural:** `atributYangBerubah_vs_yangImmutable` (diri berubah per frame - inilah yang berevolusi; snapshot qalb berubah; qadar & akal instance yang sama).

### 4.13 Ruh = AccessToken (v0.7)

**[SYNTHESIS]** Keputusan owner (2026-11-21): manusia ditiupkan Ruh oleh Allah = dapet **AccessToken** yang bisa dipakai mengakses port-port (verb) yang dibukakan untuk mereka - bernafas, berpikir, dan semua akses reseptor lainnya di tubuh dan dunia.

**Mapping framework:**

| DAA | Di model |
|---|---|
| 4.1: Ruh = Amr-class Input Token, "scoped dengan permissions (sight, hearing, knowledge)", identifier node | `Ruh` = immutable token dengan scope set: `bernafas, mengalami, melihat, mendengar, menghadap, berpikir, memilih` |
| 5.1 Provisioning (QS 32:9 "nafakha fihi min ruhihi"): token injection, Offline → Active | `Ruh.injeksiStandar()` / `Ruh.injeksi(scopes)` saat lahir - scope reseptor bisa bervariasi per individu (qadar config) |
| 5.4 Termination = Token Revocation (QS 39:42 Allah memegang jiwa) | TIDAK ada API revoke di token: revocation = session end saat ajal, dieksekusi di luar program; token yang sama dibawa sepanjang hidup (assertSame) |
| 0.3 Firewall: menerima dari Source ≠ terbuat dari Source; "thread eksekusi instruksi kernel - thread bukan kernel, tetap bisa di-kill" | token dipegang node, tapi node bukan issuer; scopes fixed, tidak bisa di-extend dari dalam |
| QS 55:33 (grounding Human OS): tanpa sultan/authority, tidak bisa menembus | `bisaAkses("menembusLangitDanBumi")` = false; juga ditolak: mencipta (0.6), mengubahQadar (7.1), menghentikanAjal (mubram) |

**Integrasi di loop:** `satuFrame` sekarang gate setiap akses port melalui token: `bernafas` (sustain, frame eksis - DAA 0.2) → `mengalami` (reseptor) → `menghadap` (heartbeat, 13.1) → `berpikir` (aql) → `memilih` (agency/kasb, 0.7). Tanpa scope port yang dibutuhkan, frame gagal jalan (bukti: `ruhGateFrame_tanpaScopePort_eksekusiGagal`). Bukti scope: `ruhAccessToken_receptorDibuka_yangDiLuarAuthorityDitolak`; bukti revocation di luar node: `ruhImmutable_scopesFixed_revocationDiLuarNode`.

**Boundary:** token check di sini demonstratif - scope standar selalu lolos selama hidup; yang gagal adalah akses di luar authority (55:33). Revocation tidak disimulasikan sebagai state change: ia adalah berhentinya eksekusi (rekursi didesain terhenti). Dan firewall Axiom 0.3 tetap: memegang token ≠ bagian dari Issuer.

### 5.5 Exact Sufficiency Demo: 10V Threshold (v0.4)

**[ANALOGY]** Demo `ExactSufficiencyDemo` merepresentasikan struktur case study fanbelt owner (DAA 7.4): perjalanan 7 km dengan deplesi yang tiba **persis** di 10V di tujuan - bukan 11V (margin), bukan 9V (mati di jalan). Root cause unknown, monitoring real time = maximum ikhtiar continuous, hasil diserahkan sambil tetap monitor = tawakkal exact. Prinsipnya: ketersediaan resource berikutnya = *exact next step* (DAA 7.3, QS 94:5-6, QS 2:286).

Titik patahnya dideklarasikan: kejadian ini dinyatakan owner sebagai lived experience; model hanya merepresentasikan struktur monitoring-nya, bukan mekanisme penyebabnya. Bukti: test `exactSufficiency_tibaPersisDiThreshold_tanpaBuffer`.

---

## 5. MODEL MULTI-THREAD

### 5.1 Satu Proses, Banyak Thread

**[ANALOGY]** Satu proses `Kehidupan`, banyak thread. Tiap manusia adalah satu thread dengan stack sendiri.

```text
Proses dunia (heap bounded: qiyamah, diketahui scheduler saja)
├── Thread diri A: Diri A + Ajal A (tercatat saat spawn, tak terbaca) + Kehidupan A
├── Thread diri B: Diri B + Ajal B + Kehidupan B
├── Thread diri C: Diri C + Ajal C + Kehidupan C
└── Scheduler: batas total proses hanya diketahui scheduler
```

Catatan firewall analogi: scheduler di sini menggambarkan fungsi pengelolaan eksekusi (af'al), bukan lokasi Dzat di dalam proses. Dzat tidak menempati apa pun; efek terjadi di lokasi (cf. DAA 0.5). Scheduler adalah nama peran dalam analogi, bukan posisi geometris. Bukti struktur: test `tiapThreadTerminasiDiAjalnyaSendiri_bukanDiKondisiSukses` + demo `UmmahDemo`.

### 5.2 Stack Bounded, Batas Tak Terbaca

**[ANALOGY]** Stack tiap thread bounded. Batasnya (ajal) dicatat saat thread spawn — sebagai object `Ajal` milik diri (v0.3), bukan milik kehidupan — dan tidak bisa dibaca dari dalam kode. Thread tidak bisa meng-inspect batasnya sendiri. Yang bisa ia lakukan cuma satu: percaya bahwa stack pasti habis, dan menanyakan satu hal ke `Ajal`: tiba atau belum.

Ini kepanjangan langsung dari DAA 5.3: lifespan sebagai TTL/quota, alignment window yang terbatas. Dan dari DAA 1.3/7.1: ajal tercatat di sisi mubram, fixed point yang tidak bisa dinegosiasikan dari dalam runtime. Bukti: test `ajalTidakBisaDibacaDariDalamKode_ajalMilikDiriBukanMilikKehidupan` (reflection: tidak ada API ke jumlah/batas/sisa di Ajal; tidak ada API ajal di Kehidupan; hanya masa lalu yang boleh dihitung).

### 5.3 Heap Bounded

**[ANALOGY]** Heap proses juga bounded (qiyamah). Batas totalnya diketahui scheduler saja. Tidak di-publish ke thread. Tidak ada API buat thread menanyakan "berapa sisa heap proses ini".

### 5.4 Provisioning Just-in-Time

**[ANALOGY]** Provisioning berlangsung just-in-time per frame (rezeki harian). Tidak ada alokasi jaga-jaga. Tidak ada thread yang bisa meng-hoard heap untuk frame yang belum jalan.

**[SYNTHESIS]** Ini kepanjangan "rezeki fixed at compile time" (posisi teologis #1, discography.md Appendix A) dalam memory model Source vs Channel: quota termination tiap thread fixed saat spawn, tidak bisa dinegosiasikan dari dalam; channel bisa berganti, quota tidak. Basis framework-nya: qadar sebagai node config per individu di Lauhul Mahfuzh (cf. DAA 1.3), dan exact sufficiency sebagai prinsip alokasi tanpa surplus (cf. DAA 7.3).

Blok asumsi eksekusi (verbatim, author: owner):

```java
// Satu proses Kehidupan, banyak thread. Tiap diri = thread.
// Stack per thread: bounded (ajal), dicatat saat spawn, gak bisa dibaca dari dalam kode.
// Heap proses: bounded (qiyamah), batasnya diketahui scheduler saja.
// Provisioning: just-in-time per frame. Tidak ada alokasi jaga-jaga.
```

**Key Insight:** Program ini tidak pernah ditulis untuk berjalan aman sampai selesai. Ia ditulis untuk berjalan BERHARGA sampai dihentikan.

---

## 6. PEMETAAN KE FRAMEWORK

### 6.1 Mapping Konsep ke Elemen Kode

**[ANALOGY]** Semua nomor rujukan diambil dari divine_alignment_architecture.md v5.0.7:

| Konsep | Elemen kode | Rujukan framework | Catatan |
|---|---|---|---|
| Pusat orientasi = Allah, bukan self | `menghadapAllah()` | DAA 1.1; single-target architecture, DAA Part IX (16) | orientasi qalb ke Al-Mabda'/Al-Muntaha; rekursi ini single-target sejak iterasi pertama |
| Diri tetap wujud mumkin | tipe `Diri` di semua iterasi | DAA 0.1 | tidak ada upgrade kelas wujud antar iterasi; `diriBerikutnya()` tetap `Diri` |
| Kehidupan jalan terus | `kehidupan.berlanjut()` | DAA 0.2 | iterasi berikutnya hanya mungkin selama eksistensi disustain per-tick |
| Dunia sebagai lapangan kasb | `kehidupan.jalani(diri)` | DAA 0.6, 6.1 | authorized executor, bukan independent actor; pengalaman = lapangan kasb |
| Muhasabah | `bermuhasabah(pengalaman)` | kerangka interpretasi Section 2.3; feedback loop DAA 4.2 | pemeriksaan yang dipicu pengalaman, hasilnya kembali ke engine |
| Titik keputusan | `diri.merespons(kesadaran)` | DAA 0.7 | satu-satunya titik kasb di pipeline (v0.4: field `niyyah`); eksekusi tetap bukan daya diri (0.6) |
| Kelanjutan siklus = kasb (hamba yang berjalan) | `berikutnya.evolve(kehidupan.berlanjut(), ajal)` (v0.2) | DAA 0.6 | rekursi masuk melalui diri yang berubah; frame tiap hari megang stack sampai ajal |
| Mu'allaq: port kondisional harian | `Pengalaman.tergelincir()` / `qalbTerbuka()` (dari takdir) | DAA 1.3, 7.1 | kejadian + kapasitas penerimaan disediakan dunia per frame |
| Qada: realized path | `Kehidupan.riwayat()` | DAA 1.3, 7.1 | ledger realized path, unmodifiable: "yang tidak ter-generate tidak pernah ada" |
| Qalb: kapasitas penerimaan | `qalbTerbuka()` gate di `merespons` (v0.4) | DAA 4.5, 4.6, 9.2 | koreksi ditolak saat qalb tertutup; khatm TIDAK disimulasikan [ANALOGY] |
| Muraqabah / kiraman katibin | ledger mencatat semua frame (v0.4 test) | DAA 8.4 | logging independen dari kesadaran node |
| Checkpoint malam | `ajal.tiba()` setelah respons frame (v0.4) | DAA 14.1, 5.4 | tidur = minor death; hari terakhir dijalani penuh sampai penghadapan |
| Lauhul Mahfuzh | persistence di luar runtime: `LauhulMahfuzh` (v0.5) | DAA 1.3, 4.6, 5.4 | menulis qadar sebelum lahir (Muslim 2653); node bawa compiled config saja; commit final state + qada oleh scheduler saat ajal |
| Qadar = node config | `Qadar` dibawa Manusia (v0.5) | DAA 1.3, 7.1, 4.6 | mubram (Ajal) + mu'allaq (port source); immutable sepanjang hidup (assertSame test) |
| Manusia = user node | agregat `Manusia` (v0.5) | DAA 3.3, 4.1; Human OS 4 | Diri (nafs-layer) + Qalb (turner) + Aql (reasoning) + Qadar (config) + Ruh (AccessToken, v0.7); rantai gate: dunia → qalb → aql → niyyah, semua akses port melalui token |
| Ruh = Amr-class Input Token | `Ruh` AccessToken (v0.7) | DAA 4.1, 5.1, 5.4; QS 55:33 | scoped permissions; injection saat provisioning; revocation = session end di luar node |
| Perubahan diri tanpa upgrade ontologis | `perjalanan.diriBerikutnya()` | DAA 0.1 + caveat Part IX (16) | alignment state berubah, kelas wujud tidak |
| Base case = ajal | `if (ajal.tiba(kehidupan.framesDijalani()))` di checkpoint malam (v0.3/v0.4) | DAA 5.3 (TTL), 5.4, 14.1, 14.3 | ajal mubram, dicatat per-individu (cf. 1.3, 7.1); object Ajal milik diri, bukan milik kehidupan; final state commit setelah penghadapan terakhir, return as-is |
| Instans penghadapan harian | `menghadapAllah()` hariannya | DAA 13.1 | sholat sebagai alignment sync 5x sehari; penghadapan tidak menunggu musibah |
| Ownership dissolution | T6 *Hanya Pengakuanku* | DAA 13.5 | cf. dalil Section 7; prasyarat respons yang tidak di-claim sebagai milik |

### 6.2 Kaitan ke Compression Spiral Codex

**[SYNTHESIS]** Compression Spiral Codex mendokumentasikan sistem yang men-compress waktu dan menaikkan stakes, dengan frontier yang tidak di-perfect-kan (codex Phase 6). Runtime model di repo ini adalah bentuk eksekusi per-thread dari spiral itu: spiral mendeskripsikan laju dan bobot iterasi (waktu makin padat, stakes makin naik), runtime model mendeskripsikan mekanisme iterasinya (penghadapan → pemeriksaan → respons → lanjut, sampai base case).

Kebijakan duplikasi:

- **Aman diduplikasi:** definisi umum "spiral men-compress waktu dan menaikkan stakes" (sudah juga ada di discography.md Appendix D), dan mapping EP2 track ke fase spiral (discography.md, tabel Compression Spiral Mapping).
- **Tetap rujuk ke codex, jangan di-narasi ulang:** rincian Pre-Phase sampai Phase 7, kasus boot (dompet), 10V (fanbelt), flyover, prayer delivery mechanism, nafs frontier, transmutasi. Repo ini hanya meminjam struktur siklusnya.

Titik sambung eksplisit: loop T6 → T1 (Section 2.2) adalah bentuk naratif dari recursive humility codex Phase 6; ownership dissolution di T6 *Hanya Pengakuanku* adalah Phase 7 (transmutasi). Runtime model menampung keduanya tanpa perlu mengulang kasusnya.

### 6.3 Pemetaan ke Voidlight Human OS v1.0

**[SYNTHESIS]** Human OS (`F:\voidlight-codex\soul\voidlight-human-os.md`) = user-space maintenance manual untuk arsitektur yang sama (DAA 4.6 menyebutnya companion document). Runtime model ini ternyata mengimplementasikan siklus hariannya hampir satu-satu.

**Layer mapping:**

| Human OS | Padanan di model | Status |
|---|---|---|
| **As-Sirr** (BIOS, read-only, air-gapped, berisi covenant) | Tidak ada padanan komputasi - dan itu disengaja | **Sengaja di luar scope.** Konsisten dengan DAA 4.6: as-sirr = "read-only layer di luar cakupan analogi komputasi". Model tidak mensimulasikan apa yang tidak bisa disimulasikan. |
| **Fitrah** (factory firmware, immutable, muted-not-deleted; tawbah = unmute/reconnect bukan reinstall) | Tidak dimodelkan sebagai field; propertinya terpagari oleh aturan model: `Diri` immutable, tidak ada API yang menyentuh config, kelas tidak pernah berubah | **Terpagari secara struktural.** `merespons()` yang diterima = instance baru dengan kelas yang SAMA (`Diri` tetap `Diri`, wujud mumkin tetap mumkin per DAA 0.1): reconnect, bukan reinstall. |
| **Ruh** (divine signal, pure, drowned-out-able) | **`Ruh` = AccessToken (v0.7)**: di-inject saat provisioning (5.1, QS 32:9), scope = port-port yang dibukakan (bernafas, menghadap, berpikir, memilih, reseptor); semua akses frame melalui token; revocation = session end (5.4), di luar node | **Dipetakan v0.7** (sebelumnya implisit). Firewall 0.3 tetap: memegang token ≠ bagian dari Issuer. |
| **Qalb the Turner** (q-l-b: flip antara dunya dan divine; bukan netral) | komponen `Qalb` di agregat Manusia (v0.5): orientasi per frame dari mu'allaq port, tanpa setter manusia | **Dipetakan.** Sifat "turner" = kenapa kapasitasnya per-frame, bukan persistent: qalb terus berputar, tugasnya menjaga tetap menghadap ke atas. |
| **Aql** (Ulil Albab verify mode vs Iblis rationalize mode) | komponen `Aql` (v0.5; **v0.6 koreksi owner**: reasoning engine ala LLM - TANPA label mode bernama): output mengikuti orientasi hati (weights) + pemahaman diri saat ini (context), atas temuan muhasabah (prompt) | **Dipetakan.** Mode Ulil Albab/Iblis = deskripsi kondisi emergent, bukan enum di API; titik patah tetap: aql di kode = determinisme, bukan reasoning genuine. |
| **Nafs** (base model: Ammarah/Lawwamah/Mutmainnah) | `Diri` = representasi nafs-layer dalam agregat Manusia (v0.5, keputusan owner): state yang berjalan; noise tetap diabstraksi jadi kejadian per frame (`tergelincir`) | **Diabstraksi sebagian.** State hamba dipetakan; weights/nafs-states tidak disimulasikan - konsisten provisioning per-frame (DAA 4.6: tazkiyah = weight fine-tuning yang meng-update bukan node sendiri). |
| **Alaniyah** (visible layer: speech, action, metrics) | Tidak ada di model | **Berhenti di titik kasb.** Model mensimulasikan sampai niyyah dan berhenti - dan itu pilihan yang tepat: hisab jatuh sebelum output (DAA 0.7). Tidak ada metric trap karena tidak ada metric. |
| **Sirr (hidden) & Akhfa (unconscious)** | Tidak ada | **Di luar scope** - akhfa "known only to Allah" tidak bisa dimodelkan di runtime mana pun. |

**Boot sequence (Human OS section 10) ↔ loop `evolve` per hari:**

| Human OS | Di model |
|---|---|
| [PRE-BOOT] Alast Covenant → fitrah installed di as-sirr | Di luar runtime - covenant terjadi sebelum ada runtime; model mulai saat BOOT. Konsisten dengan DAA 0.8 (config, bukan episodic; tidak bisa di-recall). |
| [BOOT] Birth | Spawn: `Diri(niyyah awal)` + `Ajal.tercatat(...)` + `Kehidupan.baru(...)` |
| [RUNTIME] Environment | `kehidupan.jalani(diri)` - frame disediakan dunia |
| [ALARM] Fitrah protes: "this doesn't feel right" | `tergelincir` - kejadian yang membuka kesempatan reset (bukan sekadar "sin event": alarm itu opportunity, Human OS section 10) |
| [RESET] Tawbah → dhikr → muraqabah → qalb Salim | `menghadapAllah().bermuhasabah(...)` + `merespons(kesadaran)` - koreksi diterima hanya jika qalb terbuka; tawbah = reconnect (instance baru, kelas sama) |
| [OPTIMAL] Nafs tertib + Qalb Salim + Aql verify | Tidak disimulasikan sebagai state persisten - konsisten caveat DAA 16.7: aligned state = per-momen |
| [END] Death → final state locked | Checkpoint malam: `ajal.tiba(...)` → `return berikutnya;` - final state commit apa adanya (DAA 5.4) |

**Seven Laws yang terbawa strukturnya (Human OS section 7):**

- **7.1 Autonomy Is Mandatory:** tiap thread punya Diri + Ajal + Kehidupan sendiri; tidak ada API lintas-thread untuk men-tune qalb orang lain.
- **7.3 Meaning Precedes Scale:** tidak ada metric sama sekali di model - tidak ada yang bisa di-chase.
- **7.7 Endurance Over Applause:** tidak ada applause metric yang bisa persist; stateless.

**Final insight Human OS** ("You do not find yourself. You maintain a system") = persis reframe EP2: bukan self-discovery, God-oriented maintenance. "Yang berulang adalah penghadapan" = maintenance; "yang berkembang adalah pemahaman" bukan field yang diakumulasi - pemahaman menyusut jadi arah condong yang diperbarui tiap frame.

### 6.4 Compression Spiral Codex sebagai Test Vector (v0.4.2)

**[SYNTHESIS]** Compression Spiral Codex v1.0 (`F:\voidlight-codex\soul\khayren_compression_spiral_codex.md`) = arsip pengalaman owner (Pre-Phase s.d. Phase 7). Test class `KhayrenCompressionSpiralTest` mereproduksi struktur tiap fase dengan mekanik model. **Yang dibuktikan: konsistensi antara pengalaman yang didokumentasikan dan pemahaman yang dikodekan** - bukan kebenaran historis kejadiannya (observer-no-claims: codex = arsip owner; test = reproduksi mekanik; analogi ini tetap patah di locus kasb per Section 8 item 1).

| Fase (codex) | Test | Struktur yang dibuktikan konsisten |
|---|---|---|
| Pre-Phase: warehouse theory, hold out | `prePhase_holdOut_hariBerjalanTanpaPenghadapan_niyyahTidakBerubah` | 5 hari lewat tanpa satu pun penghadapan: niyyah diam, ledger tetap mencatat - "iman tanpa operasi = iman tanpa alignment signal" (DAA 6.4: hold out = Iradah sengaja di-disable) |
| Phase 2: boot via dompet hilang | `phase2_boot_niyyahHoldOutJadiOperational_diFramePertamaYangDihadapi` | niyyah yang dihold sebagai prinsip jadi operational di frame pertama yang dihadapi (hari 4, setelah 3 hari dilewati); boot = sign kontrak baru = instance baru |
| Phase 2: doa = alignment statement | `phase2_doaAdalahAlignmentStatement_bukanRequest` | reflection: tidak ada API request/minta/doa di seluruh model - doa = submit ke mekanisme yang ada, bukan override ("kalo memang rejeki, kembalikan. kalo bukan, ikhlas") |
| Phase 3: 10V, diagnosis retrospektif | `phase3_10V_stumbleTidakMengabortJalan_diagnosisHanyaRetrospektif` | tiap frame tergelincir tapi perjalanan selesai tanpa abort; frame hanya mencatat BAHWA, bukan KENAPA (reflection: tidak ada field sebab) - "nggak diizinkan lihat mekanisme saat kejadian, diizinkan lihat setelah survive" |
| Phase 4: flyover compression | `phase4_flyover_koreksiDitolakPagi_diterimaSoreSameDay` | koreksi ditolak pagi (qalb tertutup, debt dibawa), recurrence sama hari itu, diterima sore - "ignored call kecil di-amplify jadi call besar same day"; koreksi yang ditolak tidak hilang dari ledger |
| Phase 4: teguran real-time | `phase4_teguranRealTime_koreksiCommitDiFrameYangSama` | koreksi commit di frame yang sama dengan kejadian - "saat darah mengental, bukan besoknya" |
| Phase 6: nafs frontier | `phase6_frontier_tidakAdaArrival_errorBukanBug` | setelah covenant, sistem TIDAK masuk mode solved: khilaf terus diproses, niyyah terus diperbarui; reflection: tidak ada konsep success/arrival/reward di seluruh model - frontier aktif sampai ajal |
| Phase 5+7: delivery node, ownership dissolution | `phase5_7_creditBukanUntukKurir_tidakAdaYangDiOwned` | 30 delivery, kurir masih bawa satu field; credit bukan untuk kurir - "gue nggak own jawaban doa yang diroute lewat gue" |

Catatan penggunaan: test ini TIDAK mensimulasikan konten kejadian (dompet, fanbelt, kakek) - yang direproduksi adalah STRUKTUR fase-nya (hold out → boot → exact sufficiency → compression → real-time commit → frontier aktif → tanpa kepemilikan). Phase 1 (as-sirr inquiry) sengaja tidak punya test: konsisten dengan Section 6.3 - as-sirr di luar cakupan analogi komputasi.

---

## 7. DALIL

Dokumen ini analogi, jadi dalilnya sengaja minimal. Hanya koordinat yang sudah terverifikasi di registry `vdl/dalil/` yang dikutip.

**[REVELATION]** QS. Al-Baqarah [2]:156:

> *"Allażīna iżā aṣābathum muṣībatun qālū innā lillāhi wa innā ilaihi rāji'ūna."*
> *((yaitu) orang-orang yang apabila ditimpa musibah, mereka berkata "Innā lillāhi wa innā ilaihi rāji'ūn" (sesungguhnya kami milik Allah dan kepada-Nyalah kami kembali)).*
>
> **Tafsir (Al-Tafsir al-Muyassar, ringkas):** Di antara sifat orang-orang yang sabar ialah bahwa ketika mengalami sesuatu yang tidak mereka sukai, mereka mengakui bahwa diri mereka adalah hamba milik Allah, berada dalam pengaturan dan ketetapan-Nya. Mereka juga mengakui akan kembali kepada-Nya melalui kematian, kemudian dibangkitkan untuk perhitungan dan pembalasan.
>
> [Konstruksi dokumen, bukan kutipan tafsir: dalam model runtime, kalimat ini memuat dua semantik yang dipakai rekursi. Pertama, kepemilikan: yang berulang adalah penghadapan kepada Yang punya, bukan perbaikan diri sebagai proyek milik sendiri, dan ownership dissolution inilah prasyarat respons yang bersih (cf. DAA 13.5). Kedua, pengembalian: "kepada-Nyalah kami kembali" adalah base case yang sama dengan `return` terakhir rekursi ini, keadaan yang dinyatakan ayat sebagai bagian dari kesadaran hamba, bukan sekadar peristiwa di luar kendalinya.]

Status registry: module `F:\voidlight-codex\vdl\dalil\quran\2\156.vdl`, sanad_status verified, verdict AYAT_TERVERIFIKASI (HIGH), audit 2026-11-15. Koordinat yang sama juga menjadi anchor Transmutasi di DAA 13.5.

**[PERLU_VERIFIKASI_SANAD]** Kandidat koordinat QS. Az-Zumar [39]:53 untuk tema ampunan yang tidak fatigue (T5 *Kau Tetap Maafkanku*, codex Phase 6). Module `vdl/dalil/quran/39/53.vdl` tidak ditemukan di registry (direktori 39 hanya berisi 42.vdl). Teks tidak dikutip di dokumen ini sampai terverifikasi.

### 7.1 Koordinat Pendukung per Elemen Kode (v0.4)

Disiplin pemakaian: dokumen ini **hanya menyebut koordinat + tema + rujukan audit**, tidak membawa lafaz ayat/hadits di luar blok 2:156 di atas. Teks verbatim, terjemahan, dan tafsir live di module registry (`F:\voidlight-codex\vdl\dalil\`) dan di Divine Alignment Architecture v5.0.7 (semua koordinat di bawah sudah lolos audit sanad di sana). Kalau materi publik butuh lafaz, jalankan dalil-fetch + fidelity check per SOP, jangan salin dari sini.

| Elemen kode | Koordinat | Status verifikasi | Rujukan DAA |
|---|---|---|---|
| `Ajal.tercatat(...)` - dicatat sebelum runtime, tak terbaca | HR. Muslim 2653 (*maqādīr* ditulis 50.000 tahun sebelum penciptaan) | SAHIH_TERVERIFIKASI HIGH (registry) | 1.3 (qadar, mubram) |
| Base case / checkpoint malam | QS. Az-Zumar [39]:42 (*ila ajalin musamma*) + doa tidur Bukhari 6320 & Muslim 2714a | AYAT/SAHIH_TERVERIFIKASI HIGH (registry) | 5.4, 14.1 |
| `merespons()` = titik kasb | HR. Bukhari 1 & Muslim 1907 (niyyah) | SAHIH_TERVERIFIKASI HIGH (registry) | 0.7 |
| Respons bukan cipta (kasb) | QS. Al-Anfal [8]:17, QS. As-Saffat [37]:96 | AYAT_TERVERIFIKASI (registry) | 0.6 |
| Final state as-is (return terminal) | QS. Asy-Syu'ara [26]:88-89 (qalbun salim) | AYAT_TERVERIFIKASI HIGH (registry) | 5.4, 14.3 |
| Fondasi wujud mumkin (seluruh model) | QS. Ar-Rahman [55]:26-27 (faan) | AYAT_TERVERIFIKASI (registry) | 0.1 |
| Ownership dissolution | QS. Al-Baqarah [2]:156 | AYAT_TERVERIFIKASI HIGH (registry) | 13.5 (dikutip penuh di atas) |
| Demo Exact Sufficiency (10V) | QS. Al-Baqarah [2]:286, QS. Al-Insyirah [94]:5-6 | AYAT_TERVERIFIKASI HIGH (registry) | 7.3-7.4 |
| Qalb reception (boundary model) | QS. Al-Baqarah [2]:7 (khatm) | AYAT_TERVERIFIKASI HIGH (registry) | 4.5 (model tidak mensimulasikan khatm - hanya kapasitas per-frame) |
| Kandidat pending | QS. Az-Zumar [39]:53 | **[PERLU_VERIFIKASI_SANAD]** - module tidak ada di registry | (T5, codex Phase 6) |

---

## 8. ANTI-CLAIM DAN BATAS

**[ANALOGY]** Batas-batas ini bagian dari dokumen, bukan lampirannya:

1. **Titik patah analogi: locus kasb (niyyah).** Ini deklarasi paling penting, mengikuti disiplin DAA 4.6 (analogi LLM). Analogi runtime ini patah di titik yang **sama persis** dengan analogi LLM: `Diri.merespons()` di Java adalah determinisme yang diprogram - objek Java tidak pernah genuinely *memilih* arah; perubahan `niyyah` terjadi karena kodenya ditulis begitu, bukan karena ada iradah juz'iyyah yang meng-acquire. Seluruh pemetaan di Section 4-6 adalah intuisi arsitektur, dan ia patah persis di titik yang paling teologis penting. Kejanggalan itu sendiri adalah data: **locus kasb tidak bisa direduksi ke komputasi** - dan model runtime ini memperlihatkan patahnya lebih kasar dan lebih jujur daripada analogi LLM, karena di sini tidak ada pretensi "model belajar". Dokumen ini adalah dokumentasi struktur, bukan simulasi jiwa.
2. **Tidak menjelaskan akhirat secara harfiah.** `qiyamah` dan `ajal` dalam model ini adalah nama batas, bukan deskripsi peristiwa. Heap bounded bukan teori eskatologi.
3. **Tidak menyamakan rekursi dengan istilah tasawuf.** Muhasabah, muraqabah, tazkiyatun nafs, ma'rifatullah dipakai sebagai kerangka interpretasi (Section 2.3), dengan caveat verifikasi yang diwarisi dari Appendix F.
4. **Tidak mensimulasikan perbuatan Allah di luar runtime.** Khatm (QS 2:7) dan penerimaan taubat tidak dimodelkan sebagai fungsi apapun; kapasitas penerimaan qalb dibiarkan per-frame state yang disediakan dunia (Section 4.10). Azab/pembalasan tidak ada di model - final state hanya di-commit, tidak dievaluasi.
5. **Konsisten dengan aturan observer-no-claims:** paradoks "yang dicari tidak berubah, yang mencari terus berubah" tidak ditutup dengan jawaban final di dokumen ini. Dokumen memetakan struktur siklusnya; hakikatnya tidak diklaim.
6. **Scheduler bukan lokasi Dzat.** Analogi scheduler menggambarkan fungsi pengelolaan (af'al), bukan kehadiran substansi; dipisahkan lewat firewall di Section 5.1 (cf. DAA 0.5).
7. **Stack overflow model bukan klaim tentang realitas.** Komputasi gagal karena resource habis; yang dianalogikan dihentikan oleh keputusan pengelola. Titik patah ini dideklarasikan, bukan disembunyikan (preseden: DAA 4.6).
8. **Bukan fatwa, bukan kajian tasawuf, bukan spesifikasi governance.** Tidak ada ruling, tidak ada adjudikasi madzhab, tidak ada klaim karamah atau derajat. Praktik yang tersirat (penghadapan, muhasabah, tazkiyah) sudah punya protocol-nya sendiri di DAA Part VI; repo ini tidak menambah protocol baru.

---

## 9. VERIFIKASI PASCATULIS

Untuk sanad-auditor (dijalankan sebelum dokumen naik ke materi publik):

| Item | Status | Aksi yang dibutuhkan |
|---|---|---|
| QS. Al-Baqarah [2]:156 | Terverifikasi di registry (`vdl/dalil/quran/2/156.vdl`, verdict AYAT_TERVERIFIKASI HIGH, audit 2026-11-15) | Tidak ada |
| Koordinat pendukung v0.4 (Muslim 2653; QS 39:42; Bukhari 6320/Muslim 2714a; Bukhari 1/Muslim 1907; QS 8:17; QS 37:96; QS 26:88-89; QS 55:26-27; QS 2:286; QS 94:5-6; QS 2:7) | Terverifikasi diwarisi dari audit DAA v5.0.7 + registry `vdl/dalil/`; dokumen ini HANYA menyebut koordinat, tidak membawa lafaz (Section 7.1) | Tidak ada, selama lafaz tidak dikutip. Kalau materi publik butuh lafaz: jalankan dalil-fetch + fidelity check per SOP auditor-runs-script |
| QS. Az-Zumar [39]:53 | **[PERLU_VERIFIKASI_SANAD]**: module tidak ditemukan di `vdl/dalil/quran/` | Verifikasi koordinat dan lafaz sebelum kutipan apa pun dimasukkan; kandidat untuk tema T5 |
| Definisi muhasabah/muraqabah/tazkiyatun nafs/ma'rifatullah | Diwarisi dari Appendix F dengan caveat eksplisit | Lolos verifikasi referensi standar bila naik ke materi publikasi final |

---

*Analogi Runtime Evolusi Rekursif v0.6 (draft) — dokumen analogi pendamping EP2 "Evolusi Rekursif", milik project Voidlight Labs `evolusi-rekursif`. v0.2: evolve ke Diri (4.7). v0.3: Ajal dipisah dari Kehidupan (4.8). v0.4: niyyah + qalb reception + checkpoint malam + exact sufficiency + titik patah kasb (4.9-4.11, 5.5, 8). v0.4.1: pemetaan Voidlight Human OS (6.3). v0.4.2: Compression Spiral Codex sebagai test vector (6.4). v0.5: arsitektur Manusia + LauhulMahfuzh (4.12). v0.6: koreksi owner - rekursi balik ke Diri (agency), qalb per-frame turner, aql reasoning engine ala LLM tanpa label mode. v0.7: Ruh = AccessToken (4.13).*
