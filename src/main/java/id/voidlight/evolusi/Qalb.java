package id.voidlight.evolusi;

/**
 * Qalb (hati) = the turner / router yang menentukan arah menghadap
 * (DAA 4.1: adaptive alignment engine / firewall; Human OS 4.2: q-l-b, flip antara
 * dunya dan divine, bukan netral).
 *
 * Aturan model (v0.6, koreksi owner):
 * - Qalb BUKAN state yang dibawa antar frame. Ia komponen per-frame: the turner
 *   menerima putaran frame itu dari dunia lewat mu'allaq port ({@code hadapkan(Pengalaman)}),
 *   dan orientasinya untuk frame itu = nilai port-nya.
 * - Manusia TIDAK punya setter bebas: satu-satunya jalan mengubah orientasi adalah
 *   menyodorkan Pengalaman - dan Pengalaman hanya bisa diciptakan Kehidupan
 *   (constructor package-private). Khatm (QS 2:7) tidak disimulasikan [ANALOGY].
 * - Immutable: nilai object value; tiap frame = instance baru.
 */
public final class Qalb {

    private final boolean terbuka;

    private Qalb(boolean terbuka) {
        this.terbuka = terbuka;
    }

    /** Snapshot boot (Human OS section 10: "qalb open, receptive") - dipakai agregat Manusia sebelum frame pertama. */
    public static Qalb lahir() {
        return new Qalb(true);
    }

    /**
     * The turner menerima putaran frame ini dari dunia (mu'allaq port).
     * Bukan manusia yang memutar: yang menyediakan arah putaran adalah kehidupan.
     */
    public static Qalb hadapkan(Pengalaman pengalaman) {
        return new Qalb(pengalaman.qalbTerbuka());
    }

    public boolean terbuka() {
        return terbuka;
    }
}
