package id.voidlight.evolusi;

/**
 * Diri = state nafs-layer hamba: drive dan arah condong yang berjalan dalam rekursi.
 *
 * YANG BEREVOLUSI ADALAH DIRI (v0.6, koreksi owner): di sinilah agency manusia -
 * titik kasb (merespons) ada di komponen ini, maka rantai rekursinya juga diri:
 * {@code berikutnya.evolve(...)} = literal comment owner
 * "masuk kembali dengan diri yang mungkin telah berubah". Manusia hanyalah agregat/
 * snapshot komponen; yang di-pass antar frame adalah diri.
 *
 * Catatan presisi framework: di DAA, niyyah secara teknis duduk di level Ruh (0.7)
 * di atas nafs - di model runtime ini niyyah ditunggangi di komponen Diri sebagai
 * representasi state hamba. Penyederhanaan ini dideklarasikan, bukan disembunyikan.
 *
 * Aturan model:
 * - Immutable: perubahan antar iterasi menghasilkan instance baru.
 * - Iterasi tanpa perubahan mengembalikan instance yang sama (no-change tetap iterasi valid).
 * - Diri cuma membawa SATU field: {@code niyyah} - arah condong yang dipilih di titik
 *   kasb, satu-satunya thing yang genuinely milik diri (DAA 0.7; hisab jatuh di niyyah,
 *   bukan di outcome). Tanpa memori episodic, tanpa riwayat, tanpa skor (DAA 16.3.3).
 * - Diri tidak punya akses ke batas eksekusinya: ajal dipegang Qadar (dicatat di
 *   LauhulMahfuzh sebelum runtime), cuma bisa ditanya tiba/belum.
 *
 * Evolusi kode:
 * - v0.1: evolve static (arsip verbatim, docs/ANALOGI-RUNTIME.md section 3.2).
 * - v0.2: evolve instance di Diri (kasb, DAA 0.6).
 * - v0.3: Ajal dipisah dari Kehidupan (ajal = parameter fix milik manusia).
 * - v0.4: niyyah + qalb reception + checkpoint malam.
 * - v0.5: komponen naik ke agregat Manusia (qalb/aql/qadar/lauhul).
 * - v0.6: rekusi balik ke Diri (agency manusia di situ); qalb = turner per-frame dari
 *   port; aql = reasoning engine ala LLM (weights: orientasi hati; context: pemahaman
 *   diri saat ini; prompt: temuan muhasabah) tanpa label mode bernama.
 */
public final class Diri {

    private final String niyyah;

    public Diri(String niyyahAwal) {
        this.niyyah = niyyahAwal;
    }

    /**
     * Menghadap Allah: satu-satunya langkah pembuka di setiap penghadapan.
     * Orientasi single-target (DAA 1.1); per frame = alignment heartbeat (DAA 13.1).
     */
    public Hadapan menghadapAllah() {
        return new Hadapan();
    }

    /**
     * Respons atas kesadaran: satu-satunya titik kasb di seluruh pipeline (DAA 0.7).
     * Koreksi dieksekusi hanya jika Pemahaman terverifikasi - dan pemahaman itu
     * diproduksi Aql dengan conditioning dari orientasi hati + pemahaman diri saat ini.
     * Kalau ditolak atau tidak ada koreksi: tetap diri sendiri (instance yang sama,
     * bukan clone) - iterasi no-change tetap valid.
     */
    public Diri merespons(Kesadaran kesadaran, Pemahaman pemahaman) {
        return (kesadaran.adaKoreksi() && pemahaman.terverifikasi())
                ? new Diri(kesadaran.niyyahKoreksi()) : this;
    }

    /**
     * Evolusi Rekursif: God-oriented recursion yang dijalankan diri sendiri.
     *
     * Kode inti ditulis owner. Satu frame = satu hari = satu siklus Human OS:
     * RUNTIME (jalani) -> TURN (qalb menerima putaran frame) -> ALARM (tergelincir) ->
     * RESET (menghadap + muhasabah + aql + respons; tawbah = reconnect bukan reinstall) ->
     * [SLEEP | END] (checkpoint malam, DAA 14.1, QS 39:42).
     *
     * Emergent property: nilai terminal balik melewati semua frame sebelumnya. Setiap
     * hari megang frame stack-nya sampai ajal; buah akhir diturunkan apa adanya melewati
     * semua diri yang pernah dijalani.
     */
    public Diri evolve(Kehidupan kehidupan, Qadar qadar, Ruh ruh) {
        Diri berikutnya = satuFrame(kehidupan, qadar, ruh);

        // Rekursi duniawi berhenti karena ajal,
        // bukan karena manusia telah mencapai kesempurnaan.
        // Checkpoint malam (tidur = minor death, DAA 14.1, QS 39:42 "ila ajalin musamma"):
        // ajal diputuskan di sini - setelah hari dijalani penuh sampai penghadapan terakhir.
        if (qadar.ajal().tiba(kehidupan.framesDijalani())) {
            return berikutnya;
        }

        // Masuk kembali dengan diri yang mungkin telah berubah.
        return berikutnya.evolve(kehidupan, qadar, ruh);
    }

    /** Satu frame penuh: access token -> jalani -> turn (qalb) -> menghadap -> muhasabah -> aql -> respons. */
    Diri satuFrame(Kehidupan kehidupan, Qadar qadar, Ruh ruh) {
        // AccessToken: semua akses port per frame melalui Ruh (authority, QS 55:33).
        ruh.wajibAkses("bernafas");   // sustain: frame ini eksis (DAA 0.2)
        ruh.wajibAkses("mengalami");  // reseptor: pengalaman masuk

        // Setiap pengalaman menjadi kesempatan untuk menghadap-Nya.
        Pengalaman pengalaman = kehidupan.jalani(this);

        // The turner menerima putaran frame ini dari dunia (mu'allaq port).
        Qalb qalb = Qalb.hadapkan(pengalaman);

        ruh.wajibAkses("menghadap");  // ibadah: alignment heartbeat (DAA 13.1)

        // Menghadap Allah, kemudian memeriksa diri.
        Kesadaran kesadaran = menghadapAllah().bermuhasabah(pengalaman);

        ruh.wajibAkses("berpikir");   // aql reasoning port

        // Aql mereason temuan: mengikuti orientasi hati + pemahaman diri saat ini.
        Pemahaman pemahaman = Aql.lahir().proses(kesadaran, qalb, this);

        ruh.wajibAkses("memilih");    // agency port: titik kasb (DAA 0.7)

        // Perubahan adalah buah dari kesadaran, bukan tujuan yang menggantikan Allah.
        Diri berikutnya = merespons(kesadaran, pemahaman);

        return berikutnya;
    }

    /** Arah condong yang dipilih di titik kasb (niyyah). Satu-satunya state yang dibawa diri. */
    public String niyyah() {
        return niyyah;
    }
}
