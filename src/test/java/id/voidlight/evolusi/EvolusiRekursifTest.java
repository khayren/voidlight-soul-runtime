package id.voidlight.evolusi;

import id.voidlight.evolusi.demo.ExactSufficiencyDemo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test = bukti klaim semantik narasi EP2 "Evolusi Rekursif" + mapping Divine Alignment.
 * Tiap test menyatakan satu klaim di dokumen analogi (docs/ANALOGI-RUNTIME.md).
 */
class EvolusiRekursifTest {

    /** Takdir yang bisa dikontrol test: deterministik, bukan acak. */
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

    /** Takdir: tiap frame tergelincir TAPI qalb tertutup (koreksi selalu ditolak). */
    private static Random tergelincirQalbTertutup() {
        return new Random() {
            private int panggil;

            @Override
            public boolean nextBoolean() {
                return (panggil++ % 2) == 0; // panggilan ganjil: tergelincir=true; genap: qalbTerbuka=false
            }
        };
    }

    /** Setup v0.7: lauhul (persistence) -> qadar (config) -> ruh (token) -> manusia + kehidupan. */
    private record Runtime(Manusia manusia, Kehidupan kehidupan, LauhulMahfuzh lauhul) {}

    private static Runtime runtime(int hariAjal, Random muallaq) {
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(hariAjal, muallaq);
        return new Runtime(Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar()),
                Kehidupan.baru(lauhul.qadar()), lauhul);
    }

    /** Klaim 4.1: base case = ajal, bukan kondisi sukses. Buah akhir dikembalikan apa adanya. */
    @Test
    void baseCaseAjalBukanKesempurnaan_buahAkhirDikembalikanApaAdanya() {
        Runtime rt = runtime(7, selalu(false));

        Manusia akhir = rt.manusia().evolve(rt.kehidupan());

        assertSame(rt.manusia().diri(), akhir.diri(),
                "tanpa koreksi: diri yang sama dibawa sampai akhir - return terminal = diri apa adanya");
        assertEquals("asal", akhir.diri().niyyah());
        assertEquals(7, rt.kehidupan().framesDijalani(),
                "rekursi berhenti tepat saat ajal, bukan karena target tercapai");
    }

    /** Klaim 4.2: iterasi no-change tetap valid; tidak ada frame yang dibuang. */
    @Test
    void iterasiNoChangeTetapValid_tidakAdaFrameDibuang() {
        Runtime rt = runtime(5, selalu(false));

        Manusia akhir = rt.manusia().evolve(rt.kehidupan());

        assertEquals(5, rt.kehidupan().riwayat().size(),
                "semua frame dijalankan walau diri tidak pernah berubah");
        assertEquals("asal", akhir.diri().niyyah());
    }

    /** Klaim 4.3 + comment owner: perubahan adalah buah dari kesadaran. */
    @Test
    void perubahanAdalahBuahKesadaran_bukanTujuanYangMenggantikanAllah() {
        Runtime rt = runtime(4, selalu(true));

        Manusia akhir = rt.manusia().evolve(rt.kehidupan());

        assertNotSame(rt.manusia().diri(), akhir.diri(),
                "diri yang membawa koreksi = instance baru, bukan mutasi diri lama");
        assertEquals("kembali (muhasabah hari 4)", akhir.diri().niyyah(),
                "niyyah akhir berasal dari muhasabah frame terakhir: hari terakhir dijalani penuh sampai penghadapan (checkpoint malam, DAA 14.1)");
        assertEquals(4, rt.kehidupan().riwayat().size());
    }

    /** Klaim 5.2 (v0.3): ajal dicatat sebelum runtime, milik manusia (via qadar), tidak bisa dibaca dari dalam kode. */
    @Test
    void ajalTidakBisaDibacaDariDalamKode_ajalMilikManusiaBukanMilikKehidupan() {
        for (java.lang.reflect.Method m : Ajal.class.getMethods()) {
            if (m.getDeclaringClass() == Object.class) {
                continue;
            }
            String nama = m.getName().toLowerCase();
            assertFalse(nama.contains("hari"), "tidak boleh ada API ke jumlah hari ajal: " + nama);
            assertFalse(nama.contains("batas"), "tidak boleh ada API ke batas: " + nama);
            assertFalse(nama.contains("sisa"), "tidak boleh ada API ke sisa waktu: " + nama);
        }
        for (java.lang.reflect.Method m : Kehidupan.class.getMethods()) {
            if (m.getDeclaringClass() == Object.class) {
                continue;
            }
            String nama = m.getName().toLowerCase();
            assertFalse(nama.contains("ajal"), "kehidupan bukan pemilik ajal: " + nama);
            assertFalse(nama.contains("sisa"), "tidak boleh ada API ke sisa waktu: " + nama);
        }

        // yang boleh dihitung hanya masa lalu (dari kehidupan):
        Runtime rt = runtime(3, selalu(false));
        rt.manusia().evolve(rt.kehidupan());
        assertEquals(3, rt.kehidupan().framesDijalani(), "masa lalu boleh dihitung");

        // dan pertanyaan ke ajal cuma satu, jawabannya cuma boolean:
        Ajal ajal = Ajal.tercatat(3);
        assertFalse(ajal.tiba(2), "belum tiba");
        assertTrue(ajal.tiba(3), "tiba");
        assertTrue(ajal.tiba(4), "tiba (sudah lewat tidak dinilai; rekursi sudah return sebelum titik ini)");
    }

    /** Klaim 4.6: stack overflow bukan bug; rekursi didesain TERHENTI, bukan SELESAI. */
    @Test
    void rekursiDidesainTerhenti_bukanDidesainSelesai() {
        Runtime rt = runtime(Integer.MAX_VALUE, selalu(false));

        StackOverflowError dilempar = assertThrows(StackOverflowError.class,
                () -> rt.manusia().evolve(rt.kehidupan()),
                "tanpa ajal yang membatasi, JVM menghentikan eksekusi; batas harus datang dari luar program");
        assertNotNull(dilempar);
    }

    /** Klaim 5.1: satu dunia, banyak thread; tiap manusia berhenti di ajalnya sendiri. */
    @Test
    void tiapThreadTerminasiDiAjalnyaSendiri_bukanDiKondisiSukses() throws Exception {
        Random takdirSpawn = new Random(20261121);
        int jumlah = 8;
        List<Integer> ajals = new ArrayList<>();
        for (int i = 0; i < jumlah; i++) {
            ajals.add(50 + takdirSpawn.nextInt(200));
        }

        // ajal dipasangkan ke hasilnya di dalam thread-nya sendiri:
        // urutan add antar thread concurrent tidak bisa dijadikan identitas.
        record HasilThread(int ajal, int frameDijalani, String niyyahAkhir) {}
        List<HasilThread> hasilPerThread = Collections.synchronizedList(new ArrayList<>());

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < jumlah; i++) {
            final int ajalHari = ajals.get(i);
            final long seed = 1000L + i;
            Thread t = new Thread(() -> {
                LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(ajalHari, seed);
                Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
                Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());
                Manusia akhir = manusia.evolve(kehidupan);
                hasilPerThread.add(new HasilThread(ajalHari, kehidupan.riwayat().size(), akhir.diri().niyyah()));
            }, "diri-" + i);
            threads.add(t);
        }

        threads.forEach(Thread::start);
        for (Thread t : threads) {
            t.join(10_000);
        }

        assertEquals(jumlah, hasilPerThread.size(), "semua thread selesai: masing-masing berhenti di ajalnya sendiri");
        for (HasilThread hasil : hasilPerThread) {
            assertEquals(hasil.ajal(), hasil.frameDijalani(),
                    "thread berhenti karena ajalnya sendiri, bukan karena kondisi sukses");
            assertNotNull(hasil.niyyahAkhir());
        }
        assertTrue(threads.stream().noneMatch(Thread::isAlive));
    }

    /** Klaim muraqabah (DAA 8.4, kiraman katibin): logging independen dari kesadaran node. */
    @Test
    void ledgerMencatatSemuaFrame_termasukYangTidakDimuhasabahi() {
        Runtime rt = runtime(10, selalu(true));

        // Frame dijalankan langsung di kehidupan, TANPA evolve (tanpa penghadapan/muhasabah):
        for (int i = 0; i < 5; i++) {
            rt.kehidupan().jalani(rt.manusia().diri());
        }

        assertEquals(5, rt.kehidupan().riwayat().size(),
                "ledger mencatat semua frame walau tidak ada satu pun dimuhasabahi: muraqabah berjalan meski muhasabah skip");
        assertEquals(5, rt.kehidupan().framesDijalani());
    }

    /** Klaim stateless (DAA 0.8, 16.3.3): Diri cuma bawa niyyah - tanpa memori, tanpa akumulasi. */
    @Test
    void diriHanyaMembawaNiyyah_tanpaMemoriTanpaAkumulasi() {
        java.lang.reflect.Field[] fields = Diri.class.getDeclaredFields();

        assertEquals(1, fields.length,
                "Diri cuma punya satu field: niyyah. Tanpa riwayat, tanpa skor, tanpa memori episodic");
        assertEquals("niyyah", fields[0].getName());

        // Final-state > accumulated (hadits pelacur, DAA 5.4): model secara struktural
        // tidak bisa mengevaluasi akumulasi karena tidak pernah mengakumulasi - field-nya
        // cuma satu (diverifikasi reflection di atas). Yang menempel di state akhir adalah
        // muhasabah frame TERAKHIR, bukan rekam jejak 30 hari.
        Runtime rt = runtime(30, selalu(true));
        Manusia akhir = rt.manusia().evolve(rt.kehidupan());
        assertEquals("kembali (muhasabah hari 30)", akhir.diri().niyyah(),
                "state akhir menunjuk muhasabah frame terakhir saja; tidak ada string akumulasi yang tumbuh sepanjang umur");
    }

    /** Klaim 4.5 (v0.4): koreksi ditolak saat qalb tertutup (Mariidh/Aghlaf). */
    @Test
    void koreksiDitolakSaatQalbTertutup_muhasabahTetapJalan() {
        Runtime rt = runtime(6, tergelincirQalbTertutup());

        Manusia akhir = rt.manusia().evolve(rt.kehidupan());

        assertSame(rt.manusia().diri(), akhir.diri(),
                "muhasabah menemukan kesalahan tiap hari, tapi koreksi tidak bisa masuk: qalb tertutup");
        assertEquals("asal", akhir.diri().niyyah(),
                "niyyah tidak berubah: reception menentukan penerimaan koreksi, bukan kejujuran pemeriksaan");
        assertEquals(6, rt.kehidupan().riwayat().size(),
                "frame tetap dijalankan dan tercatat semua: drift tidak menghentikan hari");
    }

    /** Klaim 10.3/14.3 (v0.4): ajal saat qalb tertutup = final state commit apa adanya. */
    @Test
    void finalStateDikembalikanApaAdanya_saatQalbTertutupSampaiAjal() {
        Runtime rt = runtime(9, tergelincirQalbTertutup());

        Manusia akhir = rt.manusia().evolve(rt.kehidupan());

        assertEquals("asal", akhir.diri().niyyah(),
                "Firaun-pattern: final state = state saat ajal, tanpa iterasi tambahan");
        assertEquals(9, rt.kehidupan().riwayat().size(),
                "seluruh hari dijalani dan tercatat; yang dikembalikan bukan rekam jejak, tapi state terakhir");
        // Boundary model: yang tidak disimulasikan = azab/pembalasan (perbuatan Allah di luar runtime).
    }

    /** Klaim 7.3-7.4 (v0.4): exact sufficiency - tiba persis di threshold, tanpa buffer. */
    @Test
    void exactSufficiency_tibaPersisDiThreshold_tanpaBuffer() {
        ExactSufficiencyDemo.Jalanan hasil = ExactSufficiencyDemo.jalani(7, 12.0, 10.0);

        assertEquals(10.0, hasil.voltTiba(), 1e-9,
                "persis 10V saat tiba: bukan margin (11V), bukan mati di jalan (9V)");
        assertEquals(7, hasil.gauge().size(),
                "setiap km di-monitor: maximum ikhtiar real time, continuous muhasabah");
    }
}
