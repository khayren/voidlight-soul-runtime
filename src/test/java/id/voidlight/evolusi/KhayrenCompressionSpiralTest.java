package id.voidlight.evolusi;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Compression Spiral Codex v1.0 sebagai test vector.
 *
 * Setiap test mereproduksi STRUKTUR satu fase yang didokumentasikan owner di
 * khayren_compression_spiral_codex.md (PRE- Phase 7) dengan mekanik model
 * evolusi-rekursif. Yang dibuktikan: KONSISTENSI antara pengalaman yang
 * didokumentasikan dan pemahaman yang dikodekan - bukan kebenaran historis
 * kejadiannya (observer-no-claims: codex = arsip owner; test = reproduksi mekanik).
 */
class KhayrenCompressionSpiralTest {

    private static Random selalu(boolean nilai) {
        return new Random() {
            @Override
            public boolean nextBoolean() {
                return nilai;
            }
        };
    }

    /** Takdir terjadwal: jalani() menarik 2 boolean per frame (tergelincir, qalbTerbuka). */
    private static Random antrean(Boolean... nilai) {
        Iterator<Boolean> it = Arrays.asList(nilai).iterator();
        return new Random() {
            @Override
            public boolean nextBoolean() {
                return it.hasNext() ? it.next() : false;
            }
        };
    }

    private static boolean punyaKataTerlarang(Class<?> kelas, String... kata) {
        for (java.lang.reflect.Method m : kelas.getMethods()) {
            if (m.getDeclaringClass() == Object.class) {
                continue;
            }
            for (String k : kata) {
                if (m.getName().toLowerCase().contains(k)) {
                    return true;
                }
            }
        }
        for (java.lang.reflect.Field f : kelas.getDeclaredFields()) {
            for (String k : kata) {
                if (f.getName().toLowerCase().contains(k)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** PRE-PHASE: warehouse theory, hold out - hidup jalan, node offline, nol alignment signal. */
    @Test
    void prePhase_holdOut_hariBerjalanTanpaPenghadapan_niyyahTidakBerubah() {
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(10, selalu(true)); // dunia tetap menyediakan hari
        Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar()); // hold out: niyyah "asal", belum operational
        Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());

        // Hari-hari berjalan TANPA satu pun evolve: Iradah sengaja di-disable (DAA 6.4)
        for (int i = 0; i < 5; i++) {
            kehidupan.jalani(manusia.diri());
        }

        assertEquals("asal", manusia.diri().niyyah(),
                "hold out: 5 hari lewat tanpa satu pun penghadapan - warehouse theory, nol operational");
        assertEquals(5, kehidupan.riwayat().size(),
                "hidup tidak menunggu: hari tetap dijalani dan tercatat walau node offline");
    }

    /** PHASE 2: boot - niyyah yang dihold sebagai prinsip terpaksa jadi operational. */
    @Test
    void phase2_boot_niyyahHoldOutJadiOperational_diFramePertamaYangDihadapi() {
        // skrip takdir: 3 hari pre-boot (lancar) lalu hari boot (tergelincir + qalb terbuka)
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(6, antrean(
                false, false,  // hari 1: lancar
                false, false,  // hari 2: lancar
                false, false,  // hari 3: lancar
                true, true,    // hari 4: tergelincir + qalb terbuka (dompet hilang)
                false, false,  // hari 5: lancar
                false, false   // hari 6: lancar
        ));
        Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
        Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());

        // 3 hari pre-boot: hidup jalan, node offline
        for (int i = 0; i < 3; i++) {
            kehidupan.jalani(manusia.diri());
        }
        assertEquals("asal", manusia.diri().niyyah(), "pre-boot: complacency belum dibayar, niyyah diam");

        // BOOT: frame pertama yang dihadapi (dompet hilang = pengalaman hari 4)
        Diri diriKontrak = manusia.diri().satuFrame(kehidupan, manusia.qadar(), manusia.ruh());

        assertNotSame(manusia.diri(), diriKontrak, "boot = sign kontrak baru: instance baru, bukan mutasi");
        assertEquals("kembali (muhasabah hari 4)", diriKontrak.niyyah(),
                "niyyah yang dihold sebagai prinsip jadi operational di frame pertama yang dihadapi - bukan di hari 1-3 yang dilewati");
        assertEquals(4, kehidupan.riwayat().size(),
                "ledger menyimpan semuanya: 3 hari pre-boot + hari boot");
    }

    /** PHASE 2: doa = alignment statement, bukan request - struktur model gak punya syscall minta outcome. */
    @Test
    void phase2_doaAdalahAlignmentStatement_bukanRequest() {
        // "Kalo memang rejeki, kembalikan. Kalo bukan, ikhlas." = submit ke mekanisme yang ada,
        // bukan override. Maka: tidak ada API request/minta/doa di seluruh model.
        String[] kataTerlarang = {"minta", "pinta", "request", "ask", "doa", "wish", "pray"};

        Class<?>[] kelasModel = {Manusia.class, Diri.class, Qalb.class, Aql.class, Qadar.class,
                LauhulMahfuzh.class, Kehidupan.class, Ajal.class, Hadapan.class,
                Kesadaran.class, Pengalaman.class, Pemahaman.class};

        for (Class<?> kelas : kelasModel) {
            assertFalse(punyaKataTerlarang(kelas, kataTerlarang),
                    kelas.getSimpleName() + " tidak boleh punya API request: doa dalam model = statement dependency (menghadap), bukan syscall untuk minta outcome");
        }

        // Yang ada cuma: menghadap, muhasabah, respons, lanjut - dan ajal yang ditanya tiba/belum.
        assertTrue(Manusia.lahir(LauhulMahfuzh.tulis(1, 1L).qadar(), Ruh.injeksiStandar()) != null);
        assertTrue(Ajal.tercatat(1).tiba(1));
    }

    /** PHASE 3: 10V - diagnosis tak diketahui saat kejadian; perjalanan selesai; ledger menyimpan untuk retrospektif. */
    @Test
    void phase3_10V_stumbleTidakMengabortJalan_diagnosisHanyaRetrospektif() {
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(5, selalu(true)); // tiap frame "ada yang off" - root cause unknown
        Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
        Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());

        Manusia akhir = manusia.evolve(kehidupan); // jalan pelan, pantau, serah: 7KM versi model

        assertEquals(5, kehidupan.riwayat().size(),
                "perjalanan selesai walau tiap frame tergelincir: stumble tidak mengabort eksekusi (sampai dulu, baru sadar apa yang rusak)");
        assertEquals("kembali (muhasabah hari 5)", akhir.diri().niyyah());

        // "Gue nggak diizinkan lihat mekanisme saat kejadian": Pengalaman tidak membawa sebab/diagnosis.
        java.util.Set<String> namaField = new java.util.HashSet<>();
        for (java.lang.reflect.Field f : Pengalaman.class.getDeclaredFields()) {
            namaField.add(f.getName().toLowerCase());
        }
        assertEquals(java.util.Set.of("hari", "tergelincir", "qalbterbuka"), namaField,
                "frame hanya mencatat BAHWA ia tergelincir, bukan KENAPA: root cause tidak bisa diakses runtime, hanya ledger yang tahu untuk retrospektif 24 jam");

        // "Mesin normal, zero trace" versi model: tidak ada konsep damage; yang ada cuma state akhir + ledger.
        assertFalse(punyaKataTerlarang(Diri.class, "damage", "luka", "trace", "rusak"),
                "diri tidak menyimpan bekas damage: state kembali apa adanya per frame, bukan akumulasi luka");
    }

    /** PHASE 4: flyover - ignored call di-amplify same day; koreksi ditolak pagi, diterima sore; zero calculation. */
    @Test
    void phase4_flyover_koreksiDitolakPagi_diterimaSoreSameDay() {
        // hari 1 pagi: tergelincir, qalb tertutup (ignore, "tolong kirim orang lain" - debt dibawa);
        // hari 1 sore (compression, same day): tergelincir lagi, qalb terbuka - zero calculation response.
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(2, antrean(true, false, true, true));
        Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
        Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());

        Manusia akhir = manusia.evolve(kehidupan);

        assertEquals("asal", manusia.diri().niyyah());
        assertEquals("kembali (muhasabah hari 2)", akhir.diri().niyyah(),
                "covenant lahir di recurrence yang sama hari itu: call yang di-ignore pagi datang balik sore, dan kali ini responsnya zero calculation");
        assertEquals(2, kehidupan.riwayat().size(),
                "kedua kejadian tercatat: koreksi yang ditolak pagi tidak hilang - debt-nya masih di ledger");
    }

    /** PHASE 4: teguran real-time - koreksi commit di frame yang sama, bukan besoknya. */
    @Test
    void phase4_teguranRealTime_koreksiCommitDiFrameYangSama() {
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(2, selalu(true));
        Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
        Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());

        // Satu frame dijalankan manual (bukan via evolve) supaya state mid-loop bisa diamati:
        Diri diriCovenant = manusia.diri().satuFrame(kehidupan, manusia.qadar(), manusia.ruh());

        assertEquals("kembali (muhasabah hari 1)", diriCovenant.niyyah(),
                "teguran real-time: saat darah mengental, bukan besoknya - koreksi commit di frame yang sama dengan kejadian");
        assertNotSame(manusia.diri(), diriCovenant);
    }

    /** PHASE 6: nafs frontier - tidak ada arrival state; error bukan bug; sistem tidak di-perfect-kan. */
    @Test
    void phase6_frontier_tidakAdaArrival_errorBukanBug() {
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(3, selalu(true)); // frontier tetap aktif: khilaf terus datang
        Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
        Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());

        Manusia akhir = manusia.evolve(kehidupan);

        assertEquals("kembali (muhasabah hari 3)", akhir.diri().niyyah(),
                "anti-inflation: setelah covenant hari 1, sistem TIDAK masuk mode solved - khilaf hari 2 dan 3 tetap diproses, niyyah terus diperbarui sampai frame terakhir");
        assertEquals(3, kehidupan.riwayat().size(), "error bukan bug: setiap khilaf = frame yang dijalankan dan tercatat, sistem tetap alive");

        // Tidak ada konsep success/arrival/lulus di model: satu-satunya yang menghentikan rekursi adalah ajal.
        String[] kataTerlarang = {"success", "selesai", "lulus", "arrived", "perfect", "skor", "pahala", "reward"};
        for (Class<?> kelas : new Class<?>[]{Manusia.class, Diri.class, Qalb.class, Aql.class,
                Qadar.class, LauhulMahfuzh.class, Kehidupan.class, Ajal.class}) {
            assertFalse(punyaKataTerlarang(kelas, kataTerlarang),
                    kelas.getSimpleName() + " tidak boleh punya konsep arrival/reward: frontier aktif sampai ajal (DAA: final state, bukan accumulated score)");
        }
    }

    /** PHASE 5+7: delivery node - credit untuk Sender, bukan kurir; tidak ada yang di-own. */
    @Test
    void phase5_7_creditBukanUntukKurir_tidakAdaYangDiOwned() {
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(30, selalu(true)); // 30 hari delivery: koreksi diterima tiap frame
        Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
        Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());

        Manusia akhir = manusia.evolve(kehidupan);

        assertEquals(30, kehidupan.riwayat().size(), "delivery log: 30 frame tercatat di kehidupan");
        assertEquals(1, Diri.class.getDeclaredFields().length,
                "setelah 30 delivery, kurir masih bawa satu field: niyyah. Credit bukan untuk kurir - gue nggak own jawaban doa yang diroute lewat gue");
        assertEquals("kembali (muhasabah hari 30)", akhir.diri().niyyah(),
                "yang tersisa bukan rekam jejak 30 delivery, tapi orientasi terakhir: pinjam kekuatan ilahiah versi tiny, arah yang ditentukan");
    }
}
