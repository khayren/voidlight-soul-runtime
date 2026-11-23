package id.voidlight.evolusi;

/**
 * Aql (akal) = reasoning engine (DAA 4.1: application logic / processing unit;
 * Human OS 4.3: chain-of-thought processor).
 *
 * Analogi (v0.6, koreksi owner): aql bekerja seperti reasoning engine di LLM -
 * outputnya mengikuti *weights* + *prompt*. Sama seperti aql manusia, ia terpengaruh:
 * - orientasi hati frame ini (qalb) = conditioning/weights-nya;
 * - pemahaman diri saat ini (niyyah yang dibawa Diri) = context-nya;
 * - temuan muhasabah = prompt yang sedang di-reason.
 *
 * Stateless: aql tidak menyimpan apa pun antar frame (bukan database, bukan skor).
 * Tanpa label mode bernama: tidak ada enum "baik/buruk" - kondisinya digambar apa adanya.
 * Titik patah analogi tetap berlaku: aql di sini determinisme kode, bukan reasoning genuine.
 */
public final class Aql {

    /** Boot: aql stateless - tidak ada config yang disimulasikan. */
    public static Aql lahir() {
        return new Aql();
    }

    private Aql() {
        // stateless
    }

    /**
     * Reason temuan muhasabah dengan conditioning frame itu.
     * Output mengikuti orientasi hati (weights) + pemahaman diri saat ini (context),
     * atas temuan muhasabah (prompt).
     */
    public Pemahaman proses(Kesadaran kesadaran, Qalb qalb, Diri diri) {
        boolean terverifikasi = qalb.terbuka();
        String kondisi = "orientasi hati: " + (qalb.terbuka() ? "terbuka" : "tertutup")
                + "; pemahaman diri: " + diri.niyyah();
        return Pemahaman.dari(terverifikasi, kondisi);
    }
}
