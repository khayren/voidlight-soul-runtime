package id.voidlight.evolusi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kehidupan = dunia per diri: context yang menyediakan pengalaman dan mencatat
 * riwayatnya. Di-pass antar frame lewat berlanjut().
 *
 * Aturan (v0.5):
 * - Port mu'allaq dibaca dari Qadar (config yang ditulis LauhulMahfuzh sebelum runtime).
 *   Kehidupan TIDAK pegang ajal: ajal milik manusia (via Qadar), bukan milik dunia.
 * - Dua muatan per frame dari sisi dunia: kejadian (tergelincir) + kapasitas penerimaan
 *   (qalbTerbuka). Khatm tidak disimulasikan [ANALOGY].
 * - riwayat = qada: realized path, "yang tidak ter-generate tidak pernah ada" - ledger
 *   mencatat SEMUA frame, independen dari kesadaran node (muraqabah/kiraman katibin, 8.4).
 * - Provisioning: just-in-time per frame. Tidak ada alokasi jaga-jaga.
 */
public final class Kehidupan {

    private int hari; // yang sudah dijalani
    private final Qadar qadar;
    private final List<Pengalaman> riwayat = new ArrayList<>();

    /** Dunia baru untuk satu manusia: port mu'allaq dibaca dari qadar-nya. */
    public static Kehidupan baru(Qadar qadar) {
        return new Kehidupan(qadar);
    }

    private Kehidupan(Qadar qadar) {
        this.qadar = qadar;
    }

    /**
     * Frame kehidupan: pengalaman disediakan dunia, tidak dipilih Diri.
     * Dua muatan per frame: kejadian + kapasitas penerimaan, keduanya dari port mu'allaq.
     */
    public Pengalaman jalani(Diri diri) {
        hari++;
        Pengalaman pengalaman =
                new Pengalaman(hari, qadar.muallaq().nextBoolean(), qadar.muallaq().nextBoolean());
        riwayat.add(pengalaman);
        return pengalaman;
    }

    /**
     * Proses yang sama berlanjut; yang mungkin berubah antar iterasi hanya Manusia-nya.
     * Kehidupan bukan field milik Diri untuk di-mutate jadi versi yang lebih nyaman.
     */
    public Kehidupan berlanjut() {
        return this;
    }

    /** Hari yang sudah dijalani. Masa lalu boleh dihitung; sisa hari tidak. */
    public int framesDijalani() {
        return hari;
    }

    /** Catatan kehidupan (ledger = qada, realized path). Milik kehidupan, bukan milik Manusia. */
    public List<Pengalaman> riwayat() {
        return Collections.unmodifiableList(riwayat);
    }
}
