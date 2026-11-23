package id.voidlight.evolusi;

/**
 * Pengalaman = satu frame kehidupan yang dijalani.
 *
 * Constructor paket: hanya Kehidupan yang menyediakan pengalaman.
 * Diri tidak menciptakan pengalamannya sendiri; dunia yang menawarkan,
 * Diri yang merespons.
 *
 * Dua muatan per frame (v0.4):
 * - tergelincir: kejadian hari itu (mu'allaq port yang terbuka, DAA 1.3/7.1)
 * - qalbTerbuka: kapasitas penerimaan koreksi pada frame itu. Disediakan dunia,
 *   bukan dihitung Diri - model ini tidak mensimulasikan khatm (perbuatan Allah,
 *   QS 2:7 / DAA 4.5) maupun penerimaan taubat; keduanya dibiarkan per-frame state
 *   yang datang dari sisi kehidupan [ANALOGY].
 */
public final class Pengalaman {

    private final int hari;
    private final boolean tergelincir;
    private final boolean qalbTerbuka;

    Pengalaman(int hari, boolean tergelincir, boolean qalbTerbuka) {
        this.hari = hari;
        this.tergelincir = tergelincir;
        this.qalbTerbuka = qalbTerbuka;
    }

    public int hari() {
        return hari;
    }

    public boolean tergelincir() {
        return tergelincir;
    }

    public boolean qalbTerbuka() {
        return qalbTerbuka;
    }

    @Override
    public String toString() {
        return "hari " + hari + (tergelincir ? ": tergelincir" : ": lancar")
                + (qalbTerbuka ? ", qalb terbuka" : ", qalb tertutup");
    }
}
