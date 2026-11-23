package id.voidlight.evolusi;

/**
 * Pemahaman = hasil olah Aql atas temuan muhasabah (v0.6, koreksi owner).
 *
 * Tanpa label mode bernama: aql itu reasoning engine ala LLM - outputnya mengikuti
 * *weights* (orientasi hati frame ini) dan *context* (pemahaman diri saat ini), atas
 * *prompt* (temuan muhasabah). Sama seperti aql manusia: ia terpengaruh orientasi hati
 * dan pemahaman diri saat ini - bukan memilih antara dua mode yang di-hardcode.
 *
 * {@code terverifikasi} = temuan muhasabah lolos dan koreksinya boleh dieksekusi
 * di titik kasb. {@code kondisi} = deskripsi conditioning saat aql memproses
 * (transparan, bukan black-box).
 *
 * Titik patah analogi tetap berlaku: aql di sini determinisme kode, bukan reasoning
 * genuine (locus kasb tidak bisa direduksi ke komputasi).
 */
public final class Pemahaman {

    private final boolean terverifikasi;
    private final String kondisi;

    private Pemahaman(boolean terverifikasi, String kondisi) {
        this.terverifikasi = terverifikasi;
        this.kondisi = kondisi;
    }

    static Pemahaman dari(boolean terverifikasi, String kondisi) {
        return new Pemahaman(terverifikasi, kondisi);
    }

    public boolean terverifikasi() {
        return terverifikasi;
    }

    /** Deskripsi conditioning saat aql memproses: orientasi hati + pemahaman diri saat ini. */
    public String kondisi() {
        return kondisi;
    }
}
