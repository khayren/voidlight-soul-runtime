package id.voidlight.evolusi;

/**
 * Fase menghadap: state antara menghadapAllah() dan bermuhasabah().
 *
 * Dibuat hanya oleh Diri.menghadapAllah() (constructor paket).
 * Urutan sikapnya kebaca dari API-nya: hadap dulu, baru periksa.
 */
public final class Hadapan {

    Hadapan() {
        // hanya Diri yang boleh menghadap
    }

    /** Pemeriksaan diri terhadap pengalaman yang baru dijalani. */
    public Kesadaran bermuhasabah(Pengalaman pengalaman) {
        return new Kesadaran(pengalaman);
    }
}
