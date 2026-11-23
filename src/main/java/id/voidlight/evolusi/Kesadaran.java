package id.voidlight.evolusi;

/**
 * Kesadaran = hasil pemeriksaan diri dalam kesadaran akan Allah.
 *
 * Mungkin membawa koreksi niyyah, mungkin tidak: pemeriksaan tanpa temuan
 * tetap kesadaran yang valid, dan iterasinya tetap dijalankan.
 * Constructor paket: hanya Hadapan yang menghasilkan Kesadaran.
 *
 * v0.4: kesadaran membawa kapasitas penerimaan frame itu (qalbTerbuka) ke titik
 * respons. Muhasabah selalu menemukan yang sebenarnya (signal masuk); penerimaan
 * koreksinya bergantung qalb (DAA 4.5). Gate-nya dieksekusi di Diri.merespons -
 * titik kasb - bukan di sini.
 */
public final class Kesadaran {

    private final Pengalaman pengalaman;
    private final String koreksiNiyyah; // null = pemeriksaan tanpa koreksi

    Kesadaran(Pengalaman pengalaman) {
        this.pengalaman = pengalaman;
        this.koreksiNiyyah = pengalaman.tergelincir()
                ? "kembali (muhasabah hari " + pengalaman.hari() + ")"
                : null;
    }

    public boolean adaKoreksi() {
        return koreksiNiyyah != null;
    }

    public String niyyahKoreksi() {
        return koreksiNiyyah;
    }

    /** Kapasitas penerimaan qalb pada frame itu (disediakan dunia, bukan dihitung Diri). */
    public boolean qalbTerbuka() {
        return pengalaman.qalbTerbuka();
    }

    public Pengalaman pengalaman() {
        return pengalaman;
    }
}
