package id.voidlight.evolusi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Lauhul Mahfuzh = persistence database di LUAR runtime (DAA 1.3: complete graph /
 * immutable database berisi possibility space; 4.6: compiled binary berisi seluruh
 * execution tree; 5.4: termination = final state commit ke immutable database).
 *
 * Aturan model (v0.5):
 * - Menulis qadar SEBELUM runtime: "Allah menulis maqādīr para makhluk 50.000 tahun
 *   sebelum menciptakan langit dan bumi" (HR. Muslim 2653, SAHIH_TERVERIFIKASI HIGH -
 *   koordinat saja, teks verbatim di registry/DAA). Yang ditulis: mubram (ajal) +
 *   mu'allaq (port sequence).
 * - Node TIDAK punya akses ke database ini: Manusia tidak membawa dan tidak bisa
 *   membaca/menulisnya. Yang diserahkan ke runtime saat lahir cuma Qadar (compiled
 *   config) - dan itu immutable.
 * - Commit qada + final state terjadi saat termination (checkpoint malam), dieksekusi
 *   scheduler - bukan oleh Manusia (token revocation = perbuatan di luar program).
 * - Bukan as-sirr: as-sirr tetap di luar cakupan analogi komputasi (DAA 4.6); database
 *   ini menyimpan qadar/qada, bukan covenant.
 */
public final class LauhulMahfuzh {

    private final Qadar qadar;
    private final List<String> logCommit = new ArrayList<>();

    private LauhulMahfuzh(Qadar qadar) {
        this.qadar = qadar;
    }

    /** Menulis qadar sebelum runtime: ajal (mubram) + port sequence (mu'allaq), deterministik per seed. */
    public static LauhulMahfuzh tulis(int hariAjal, long seed) {
        return new LauhulMahfuzh(new Qadar(Ajal.tercatat(hariAjal), new Random(seed)));
    }

    /** Versi pengujian: mu'allaq bisa di-script eksplisit. */
    static LauhulMahfuzh tulis(int hariAjal, Random muallaq) {
        return new LauhulMahfuzh(new Qadar(Ajal.tercatat(hariAjal), muallaq));
    }

    /** Compiled config yang diserahkan ke runtime saat lahir. Ini satu-satunya yang node terima. */
    public Qadar qadar() {
        return qadar;
    }

    /**
     * Commit termination (dipanggil scheduler saat checkpoint malam terakhir, bukan oleh Manusia).
     * Public karena scheduler berada di luar package model - guard-nya struktural: Manusia
     * tidak pernah membawa referensi LauhulMahfuzh (diverifikasi ArsitekturManusiaTest).
     */
    public void commit(String finalState, List<Pengalaman> qada) {
        logCommit.add(finalState + " | qada: " + qada.size() + " frame");
    }

    /** Scheduler-side read: untuk verifikasi commit (bukan API node). */
    public List<String> logCommit() {
        return Collections.unmodifiableList(logCommit);
    }
}
