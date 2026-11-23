package id.voidlight.evolusi;

import java.util.Set;

/**
 * Ruh = AccessToken yang di-inject Allah saat provisioning
 * (DAA 5.1, QS 32:9: "nafakha fihi min ruhihi" - token injection, status Offline -> Active).
 *
 * Analogi (v0.7, keputusan owner): manusia ditiupkan Ruh = dapet AccessToken yang bisa
 * dipakai mengakses port-port (verb) yang dibukakan untuk mereka - bernafas, berpikir,
 * dan semua akses reseptor lainnya di tubuh dan dunia.
 *
 * Aturan model:
 * - Scoped (DAA 4.1: "token scoped dengan permissions - sight, hearing, knowledge"):
 *   token hanya membuka port yang Allah bukakan. Yang TIDAK dibukakan:
 *   menembus batas langit dan bumi (QS 55:33 - tanpa sultan/authority), mencipta
 *   (0.6: la quwwata illa billah), mengubah qadar (7.1: immutable), menghentikan ajal
 *   (mubram).
 * - Identifier node (DAA 4.1): token = identitas node dalam alignment network.
 * - Immutable: scopes dicatat saat injeksi; TIDAK ada API revoke oleh node -
 *   revocation = session end saat ajal (DAA 5.4: token revocation; QS 39:42 Allah
 *   memegang jiwa), dieksekusi di luar program.
 * - Firewall Axiom 0.3: menerima token dari Source ≠ terbuat dari Source. Thread
 *   mengeksekusi instruksi kernel - thread bukan kernel, dan tetap bisa di-kill kapan saja.
 */
public final class Ruh {

    /** Port standar yang dibukakan: sustain + reseptor + ibadah + agency. */
    public static final Set<String> PORT_STANDAR = Set.of(
            "bernafas",     // sustain per tick (DAA 0.2) - frame eksis karena token ini
            "mengalami",    // reseptor: pengalaman masuk
            "melihat",      // reseptor
            "mendengar",    // reseptor
            "menghadap",    // ibadah: alignment heartbeat (DAA 13.1)
            "berpikir",     // aql reasoning
            "memilih"       // agency: titik kasb (DAA 0.7)
    );

    private final Set<String> portDibuka;

    private Ruh(Set<String> portDibuka) {
        this.portDibuka = Set.copyOf(portDibuka);
    }

    /** Provisioning (DAA 5.1): token di-inject dengan port standar. */
    public static Ruh injeksiStandar() {
        return new Ruh(PORT_STANDAR);
    }

    /** Provisioning dengan scope khusus: reseptor yang dibukakan bisa bervariasi per individu (qadar config). */
    public static Ruh injeksi(Set<String> portDibuka) {
        return new Ruh(portDibuka);
    }

    /** Cek authority (QS 55:33): apakah port ini dibukakan untuk token ini. */
    public boolean bisaAkses(String port) {
        return portDibuka.contains(port);
    }

    /**
     * Gate eksekusi: akses port wajib lolos, kalau tidak frame tidak bisa jalan.
     * Selama session terbuka (sebelum ajal) dan scope standar, gate ini selalu lolos -
     * strukturnya yang penting: tanpa token, tidak ada akses sama sekali.
     */
    public void wajibAkses(String port) {
        if (!bisaAkses(port)) {
            throw new IllegalStateException(
                    "port tidak dibukakan untuk token ini (QS 55:33: tanpa sultan): " + port);
        }
    }
}
