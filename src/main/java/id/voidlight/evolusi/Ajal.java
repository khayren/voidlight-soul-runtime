package id.voidlight.evolusi;

/**
 * Ajal = batas eksekusi yang dicatat sebagai parameter fix tiap diri saat spawn.
 *
 * Aturan model (v0.3, pemisahan ownership):
 * - Ajal milik diri, bukan milik kehidupan. Kehidupan menyediakan hari; Ajal menyimpan batas.
 * - Batasnya tidak bisa dibaca: tidak ada getter. Satu-satunya pertanyaan yang boleh
 *   diajukan: tiba atau belum (tiba(hariKini)), dan jawabannya cuma boolean.
 * - Diri boleh menghitung hari yang sudah lewat (dari kehidupan); hanya Ajal yang tahu
 *   jumlah totalnya; perbandingan terjadi di dalam sini, yang keluar cuma jawaban.
 * - Tidak bisa dinegosiasikan dari dalam kode: tidak ada API untuk menunda,
 *   memperpanjang, atau menanyakan sisa.
 *
 * Framework: ajal mubram, tercatat di sisi Lauhul Mahfuzh per individu (cf. DAA 1.3),
 * lifespan sebagai TTL per alignment window (cf. DAA 5.3).
 *
 * v0.3 membawa balik base case versi ringkas owner: `if (ajal.tiba()) return aku;`
 * (v0.1/v0.2 menitipkan batas ini ke Kehidupan; keputusan owner 2026-11-21: ajal
 * dipegang Ajal, karena ajal = parameter fix dari manusia, bukan property dunia.)
 */
public final class Ajal {

    private final int hari; // dicatat saat spawn; tidak ada API untuk membacanya

    /** Dicatat saat spawn: sekali, untuk selamanya thread ini. */
    public static Ajal tercatat(int hari) {
        return new Ajal(hari);
    }

    Ajal(int hari) {
        this.hari = hari;
    }

    /**
     * Satu-satunya pertanyaan ke batas: apakah sekarang?
     * Diri menghitung hari yang sudah lewat (dari kehidupan); Ajal menyimpan totalnya;
     * perbandingan terjadi di sini, dan yang keluar cuma jawaban boolean.
     */
    public boolean tiba(int hariKini) {
        return hariKini >= hari;
    }
}
