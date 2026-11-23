package id.voidlight.evolusi;

import java.util.Random;

/**
 * Qadar = node config per individu (DAA 1.3, 7.1): compiled binary yang dibawa node,
 * immutable, tidak bisa diubah oleh runtime.
 *
 * Isi dua status flag (konvensi DAA):
 * - Mubram (fixed point): {@link Ajal} - dicatat sebelum runtime, tak terbaca kecuali tiba().
 * - Mu'allaq (port kondisional): sumber takdir per frame - kejadian (tergelincir) dan
 *   kapasitas penerimaan (qalbTerbuka) yang dibuka/ditutup menurut kondisi.
 *
 * Immutable total: tidak ada setter. Node tidak bisa mengubah binary-nya sendiri;
 * yang bisa dilakukan: menavigasi port mu'allaq yang terbuka (DAA 7.1).
 */
public final class Qadar {

    private final Ajal ajal;
    private final Random muallaq; // sumber port harian; package-private: dibaca Kehidupan saja

    Qadar(Ajal ajal, Random muallaq) {
        this.ajal = ajal;
        this.muallaq = muallaq;
    }

    /** Fixed point (mubram): batas eksekusi, dicatat sebelum runtime. */
    public Ajal ajal() {
        return ajal;
    }

    /** Sumber port mu'allaq; package-private: dunia (Kehidupan) yang membacanya, bukan Manusia. */
    Random muallaq() {
        return muallaq;
    }
}
