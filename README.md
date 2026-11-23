# Evolusi Rekursif Runtime

God-oriented recursion sebagai model eksekusi Java. Pendamping EP2 "Evolusi Rekursif"
(album KHAYREN / Voidlight Music). Project Voidlight Labs. **Runtime analogi yang
diselaraskan dengan Divine Alignment Architecture v5.0.7 + Voidlight Human OS v1.0,
dengan pengalaman owner (Compression Spiral Codex) sebagai test vector.**

> **Evolusi Rekursif adalah perjalanan seorang hamba yang terus kembali menghadap Allah,
> dan melalui penghadapan itu, berulang kali menemukan sesuatu dalam dirinya yang belum ia pahami.**

## Arsitektur (v0.6)

```text
LauhulMahfuzh (persistence, DI LUAR runtime; node tidak bisa baca/tulis)
  └── menulis Qadar sebelum lahir: mubram (Ajal) + mu'allaq (port sequence)
        ↓ handed at birth (compiled config, immutable)
Rekursi berjalan DI DIRI (yang berevolusi = diri/nafs; agency manusia di situ):
  Diri ──satuFrame──> Diri ──satuFrame──> Diri ... ──checkpoint malam──> return
  tiap frame (semua akses melalui Ruh = AccessToken):
    wajibAkses(bernafas) -> jalani -> wajibAkses(mengalami) -> Qalb.hadapkan(port) ->
    wajibAkses(menghadap) -> menghadap -> muhasabah -> wajibAkses(berpikir) ->
    Aql.proses (weights: orientasi hati; context: pemahaman diri; prompt: temuan) ->
    wajibAkses(memilih) -> merespons (titik kasb)

Manusia (agregat/snapshot untuk pengamat)
  ├── Diri  : Nafs-layer state - niyyah (satu field) = objek rekursinya
  ├── Qalb  : Hati - the turner; komponen PER-FRAME dari mu'allaq port (bukan state dibawa)
  ├── Aql   : Akal - reasoning engine ala LLM; stateless
  ├── Qadar : node config yang dibawa (immutable; TIDAK termasuk database-nya)
  └── Ruh   : AccessToken - di-inject Allah saat provisioning; scopes = port dibukakan

Kehidupan (dunia per manusia): menyediakan frame + ledger qada
Scheduler (di luar model): commit final state + qada ke LauhulMahfuzh saat ajal (revocation)
```

**Rantai gate per frame:** dunia (port mu'allaq) → Qalb (orientasi) → Aql (reasoning) → niyyah (kasb).

**Mutable vs immutable** (terbukti struktural, test `atributYangBerubah_vs_yangImmutable`):
- Berubah per frame (instance baru, bukan mutasi): `Diri` (niyyah), `Qalb` (orientasi).
- Immutable sepanjang hidup: `Qadar` (config), `Ajal` (mubram), `LauhulMahfuzh` (append-only, oleh scheduler).
- Stateless: `Aql`.
- Di luar runtime: `LauhulMahfuzh` (node bawa compiled config-nya saja), as-sirr/akhfa (tidak ada padanan komputasi, DAA 4.6).

## Klaim inti model

- **Base case = ajal, bukan kondisi sukses.** Rekursi berhenti karena runner berhenti;
  nilai terminal dikembalikan apa adanya.
- **Iterasi no-change tetap valid.** Frame tetap dijalankan, tidak ada yang dibuang.
- **Niyyah = satu-satunya state yang dibawa diri** (DAA 0.7; hisab di niyyah bukan outcome).
- **Ajal milik manusia, bukan milik kehidupan** (DAA 1.3, 5.3): dicatat LauhulMahfuzh
  sebelum runtime, cuma bisa ditanya `tiba(...)`.
- **Yang berevolusi = Diri (nafs), bukan Manusia (v0.6).** Agency manusia ada di titik kasb,
  maka rantai rekursinya = `berikutnya.evolve(...)` di level Diri; Manusia cuma agregat/snapshot.
- **Qalb menjaga orientasi tanpa setter manusia** (Human OS 4.2): komponen per-frame - the
  turner menerima putaran dari mu'allaq port (`Qalb.hadapkan(Pengalaman)`); khatm tidak
  disimulasikan (DAA 4.5).
- **Aql = reasoning engine ala LLM (v0.6).** Output mengikuti *weights* (orientasi hati
  frame itu) + *context* (pemahaman diri saat ini), atas *prompt* (temuan muhasabah) -
  sama seperti aql manusia. Tanpa label mode bernama; `Pemahaman` menggambar kondisinya
  apa adanya.
- **Ruh = AccessToken (v0.7).** Di-inject Allah saat provisioning (DAA 5.1, QS 32:9);
  scope = port-port (verb) yang dibukakan: bernafas, mengalami, melihat, mendengar,
  menghadap, berpikir, memilih. Semua akses frame melalui token (`wajibAkses`); di luar
  authority ditolak (QS 55:33 sultan; mencipta 0.6; mengubahQadar 7.1); revocation =
  session end saat ajal, di luar jangkauan node (DAA 5.4).
- **Lauhul Mahfuzh = persistence di luar runtime** (DAA 1.3, 4.6, 5.4): node bawa compiled
  config saja; commit final state + qada dieksekusi scheduler saat ajal.
- **Checkpoint malam**: ajal diputuskan setelah hari dijalani penuh sampai penghadapan
  terakhir (DAA 14.1, QS 39:42).
- **Rekursi milik hamba** (v0.2): rekursi masuk *melalui* diri yang mungkin telah berubah.

Asumsi eksekusi (author: owner):

```java
// Satu dunia, banyak thread. Tiap manusia = thread.
// Stack per thread: bounded (ajal), dicatat saat spawn, gak bisa dibaca dari dalam kode.
// Heap dunia: bounded (qiyamah), batasnya diketahui scheduler saja.
// Provisioning: just-in-time per frame. Tidak ada alokasi jaga-jaga.
```

**Rekursi ini didesain TERHENTI, bukan didesain SELESAI.**

**Titik patah analogi (deklarasi wajib):** `merespons()`/`proses()` di Java adalah
determinisme yang diprogram, bukan iradah/reasoning genuine. Analogi ini patah di locus
kasb - titik yang sama dengan analogi LLM di DAA 4.6 - dan patah itu sendiri adalah data:
locus kasb tidak bisa direduksi ke komputasi. Detail: `docs/ANALOGI-RUNTIME.md` Section 8.

## Klaim → bukti (28 test)

| Klaim narasi | Bukti |
|---|---|
| Base case = ajal, bukan kesempurnaan; buah akhir apa adanya | `baseCaseAjalBukanKesempurnaan_buahAkhirDikembalikanApaAdanya` |
| Iterasi no-change tetap valid, tidak ada frame dibuang | `iterasiNoChangeTetapValid_tidakAdaFrameDibuang` |
| Perubahan adalah buah dari kesadaran; checkpoint malam | `perubahanAdalahBuahKesadaran_bukanTujuanYangMenggantikanAllah` |
| Ajel tak terbaca; ajal milik manusia via qadar, bukan milik dunia | `ajalTidakBisaDibacaDariDalamKode_ajalMilikManusiaBukanMilikKehidupan` |
| Stack overflow bukan bug; didesain terhenti | `rekursiDidesainTerhenti_bukanDidesainSelesai` |
| Multi-thread: tiap manusia berhenti di ajalnya sendiri | `tiapThreadTerminasiDiAjalnyaSendiri_bukanDiKondisiSukses` |
| Muraqabah: ledger independen dari muhasabah (DAA 8.4) | `ledgerMencatatSemuaFrame_termasukYangTidakDimuhasabahi` |
| Stateless Diri: satu field niyyah (DAA 0.8, 16.3.3) | `diriHanyaMembawaNiyyah_tanpaMemoriTanpaAkumulasi` |
| Koreksi ditolak saat qalb tertutup (DAA 4.5) | `koreksiDitolakSaatQalbTertutup_muhasabahTetapJalan` |
| Firaun-pattern: final state commit apa adanya (10.3, 14.3) | `finalStateDikembalikanApaAdanya_saatQalbTertutupSampaiAjal` |
| Exact sufficiency: tiba persis di threshold (7.3-7.4) | `exactSufficiency_tibaPersisDiThreshold_tanpaBuffer` |
| Boot receptive + config dari lauhul | `boot_manusiaLahir_receptive_denganConfigDariLauhul` |
| Yang berevolusi = diri (nafs); rekursi masuk melalui diri yang berubah (v0.6) | `yangBerevolusiAdalahDiri_rekursiMasukMelaluiDiriYangBerubah` |
| Qalb turner per-frame: orientasi dari mu'allaq port, tanpa setter manusia | `qalbTurnerPerFrame_orientasiSelaluDariMuallaqPort_tanpaSetterManusia` |
| Aql reasoning engine ala LLM: mengikuti orientasi hati + pemahaman diri, tanpa label mode (v0.6) | `aqlReasoningEngine_outputMengikutiOrientasiHatiDanPemahamanDiri_tanpaLabelMode` |
| Lauhul Mahfuzh di luar runtime; commit oleh scheduler (DAA 5.4) | `lauhulMahfuzh_persistenceDiluarRuntime_commitOlehScheduler` |
| Mutable vs immutable terbukti struktural | `atributYangBerubah_vs_yangImmutable` |
| Ruh = AccessToken: receptor dibukakan, di luar authority ditolak (QS 55:33; 0.6; 7.1) | `ruhAccessToken_receptorDibuka_yangDiLuarAuthorityDitolak` |
| Gate eksekusi: tanpa scope port, frame gagal jalan | `ruhGateFrame_tanpaScopePort_eksekusiGagal` |
| Ruh immutable: scopes fixed, revocation di luar node (DAA 5.4) | `ruhImmutable_scopesFixed_revocationDiLuarNode` |

### Compression Spiral Codex sebagai test vector (8 test, `KhayrenCompressionSpiralTest`)

Pengalaman owner yang didokumentasikan di codex v1.0 direproduksi strukturnya per fase -
membuktikan konsistensi pengalaman ↔ pemahaman (bukan kebenaran historis; observer-no-claims).
Detail: `docs/ANALOGI-RUNTIME.md` Section 6.4. Fase: Pre-Phase hold out; boot; doa = alignment
statement (nol API request); 10V diagnosis retrospektif; flyover compression same-day;
teguran real-time; frontier tanpa arrival; credit untuk Sender bukan kurir.

## Menjalankan

```bash
mvn test
```

Demo:

```bash
# satu dunia, banyak thread (tiap manusia = thread, ajal random, commit ke lauhul)
mvn -q org.codehaus.mojo:exec-maven-plugin:3.2.0:java -Dexec.mainClass=id.voidlight.evolusi.demo.UmmahDemo

# stack overflow sebagai bukti "didesain terhenti"
mvn -q org.codehaus.mojo:exec-maven-plugin:3.2.0:java -Dexec.mainClass=id.voidlight.evolusi.demo.StackOverflowDemo

# exact sufficiency / 10V threshold (case study fanbelt, DAA 7.3-7.4)
mvn -q org.codehaus.mojo:exec-maven-plugin:3.2.0:java -Dexec.mainClass=id.voidlight.evolusi.demo.ExactSufficiencyDemo
```

## Struktur

```
src/main/java/id/voidlight/evolusi/
├── Manusia.java           # agregat user node: diri + qalb + akal + qadar + ruh; snapshot komposisi
├── Diri.java              # nafs-layer state: niyyah (satu field); titik kasb
├── Qalb.java              # hati: the turner; orientasi per frame dari dunia, tanpa setter manusia
├── Aql.java               # akal: reasoning engine ala LLM (weights: orientasi hati; context: diri); stateless
├── Pemahaman.java         # hasil olah aql: terverifikasi + kondisi (tanpa label mode)
├── Qadar.java             # node config immutable: mubram (Ajal) + mu'allaq (port source)
├── Ruh.java               # AccessToken: di-inject saat provisioning; scopes = port dibukakan; gate semua akses frame
├── Ajal.java              # batas fix, dicatat sebelum runtime; cuma bisa ditanya tiba/belum
├── Hadapan.java           # fase menghadap (hanya Diri yang bisa menghadap)
├── Kesadaran.java         # hasil muhasabah; koreksi opsional
├── Pengalaman.java        # frame kehidupan: kejadian + kapasitas penerimaan (tanpa public ctor)
├── Kehidupan.java         # dunia per manusia: menyediakan frame + ledger qada
├── LauhulMahfuzh.java     # persistence di luar runtime: menulis qadar, menyimpan commit
└── demo/
    ├── UmmahDemo.java         # multi-thread: tiap manusia berhenti di ajalnya sendiri + commit
    ├── StackOverflowDemo.java # JVM no TCO: overflow = bukti didesain terhenti
    └── ExactSufficiencyDemo.java # 10V threshold: tiba persis, tanpa buffer (DAA 7.3-7.4)
```
Versi: v0.1 static evolve → v0.2 evolve ke Diri → v0.3 Ajal dipisah dari Kehidupan →
v0.4 framework alignment pass (niyyah, qalb gate, checkpoint malam, exact sufficiency,
titik patah) → v0.4.1 Human OS alignment → v0.4.2 spiral test vector → v0.5 arsitektur
Manusia + LauhulMahfuzh → v0.6 koreksi owner (rekursi balik ke Diri, qalb per-frame
turner, aql ala LLM) → **v0.7 Ruh = AccessToken** (di-inject saat provisioning; scopes
port dibukakan; semua akses frame melalui token; revocation = session end saat ajal).

## Dokumen terkait (vault Voidlight Codex)

- Framework acuan: Divine Alignment Architecture v5.0.7 (`F:\voidlight-codex\soul\divine_alignment_architecture.md`)
- User-space manual: Voidlight Human OS v1.0 (`F:\voidlight-codex\soul\voidlight-human-os.md`)
- Pengalaman owner: Compression Spiral Codex v1.0 (`F:\voidlight-codex\soul\khayren_compression_spiral_codex.md`)
- Narasi EP2: `F:\voidlight-codex\music\discography.md` (Section I EP2, Appendix F)
- Analogi runtime panjang: `docs/ANALOGI-RUNTIME.md` di repo ini
- Project doc Labs: `F:\voidlight-codex\labs\projects\evolusi-rekursif.md`

## Batas

Dokumen dan kode ini analogi, bukan spesifikasi teologis. Titik patah utama: locus kasb
tidak bisa direduksi ke komputasi (Section 8 item 1). Khatm dan penerimaan taubat tidak
disimulasikan (kapasitas penerimaan = per-frame dari dunia). As-sirr/akhfa di luar scope.
Scheduler = nama peran pengelolaan (af'al), bukan posisi Dzat. Bukan fatwa, bukan kajian
tasawuf, bukan governance.
