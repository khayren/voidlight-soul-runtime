package id.voidlight.evolusi;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test arsitektur: Manusia sebagai agregat (Diri/Nafs + Qalb/Hati + Aql/Akal + Qadar),
 * LauhulMahfuzh sebagai persistence di luar runtime, dan rekursi yang berjalan di Diri
 * (yang berevolusi = diri, agency manusia; v0.6 koreksi owner).
 * Tiap test menyatakan satu klaim struktur di docs/ANALOGI-RUNTIME.md section 4.12.
 */
class ArsitekturManusiaTest {

    private static Random antrean(Boolean... nilai) {
        Iterator<Boolean> it = Arrays.asList(nilai).iterator();
        return new Random() {
            @Override
            public boolean nextBoolean() {
                return it.hasNext() ? it.next() : false;
            }
        };
    }

    private static Random selalu(boolean nilai) {
        return new Random() {
            @Override
            public boolean nextBoolean() {
                return nilai;
            }
        };
    }

    /** Boot (Human OS section 10): nafs active, qalb receptive (snapshot boot), config sudah ditulis sebelum runtime. */
    @Test
    void boot_manusiaLahir_receptive_denganConfigDariLauhul() {
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(10, 1L);
        Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());

        assertTrue(manusia.qalb().terbuka(), "boot: qalb receptive (snapshot awal)");
        assertEquals("asal", manusia.diri().niyyah(), "boot: niyyah awal, belum operational");
        assertSame(lauhul.qadar(), manusia.qadar(),
                "node membawa compiled config (qadar), bukan database-nya");
    }

    /** Rekursi berjalan di Diri: yang berevolusi adalah diri (nafs), agency manusia di situ. */
    @Test
    void yangBerevolusiAdalahDiri_rekursiMasukMelaluiDiriYangBerubah() {
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(3, selalu(true));
        Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
        Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());

        // Rekursi dipanggil langsung di diri - bukan di Manusia:
        Diri akhir = manusia.diri().evolve(kehidupan, manusia.qadar(), manusia.ruh());

        assertNotSame(manusia.diri(), akhir, "diri yang mungkin telah berubah = objek rekursinya");
        assertEquals("kembali (muhasabah hari 3)", akhir.niyyah(),
                "rantai diri -> diri -> diri: agency manusia ada di komponen yang berevolusi");
        assertEquals(3, kehidupan.framesDijalani());
    }

    /** Qalb = the turner per-frame: orientasi selalu dari mu'allaq port, tanpa setter manusia. */
    @Test
    void qalbTurnerPerFrame_orientasiSelaluDariMuallaqPort_tanpaSetterManusia() {
        // Qalb: tidak ada public setter boolean; satu-satunya pintu = hadapkan(Pengalaman).
        for (java.lang.reflect.Method m : Qalb.class.getMethods()) {
            if (m.getDeclaringClass() == Object.class) {
                continue;
            }
            if (m.getName().equals("hadapkan")) {
                assertEquals(1, m.getParameterCount(), "hadapkan menerima Pengalaman, bukan boolean bebas");
                assertEquals(Pengalaman.class, m.getParameterTypes()[0],
                        "putaran qalb hanya bisa datang dari frame yang disediakan dunia");
            }
        }
        // Pengalaman tidak bisa dipalsukan Manusia: constructor-nya tidak public.
        assertEquals(0, Pengalaman.class.getConstructors().length,
                "Pengalaman tanpa public constructor: hanya Kehidupan yang menyediakan frame, manusia tidak bisa memproduksi orientasi sendiri");

        // Manusia tidak punya API set qalb/dirinya.
        for (java.lang.reflect.Method m : Manusia.class.getMethods()) {
            String nama = m.getName().toLowerCase();
            assertFalse(nama.startsWith("set"), "manusia tanpa setter: " + nama);
        }

        // Qalb bukan state yang dibawa antar frame: dua hadapkan berbeda = dua orientasi berbeda,
        // keduanya murni fungsi frame-nya (the turner menerima putaran, bukan menyimpannya).
        assertTrue(Qalb.hadapkan(new Pengalaman(1, false, true)).terbuka());
        assertFalse(Qalb.hadapkan(new Pengalaman(1, false, false)).terbuka());
    }

    /** Aql = reasoning engine ala LLM: output mengikuti orientasi hati + pemahaman diri saat ini, tanpa label mode bernama. */
    @Test
    void aqlReasoningEngine_outputMengikutiOrientasiHatiDanPemahamanDiri_tanpaLabelMode() {
        Aql akal = Aql.lahir();
        Kesadaran kesadaranBermasalah = new Kesadaran(new Pengalaman(1, true, true)); // muhasabah menemukan koreksi
        Diri diri = new Diri("asal"); // pemahaman diri saat ini

        // Orientasi hati terbuka: temuan lolos, kondisi menggambar orientasi + pemahaman diri.
        Pemahaman pemTerbuka = akal.proses(kesadaranBermasalah, Qalb.lahir(), diri);
        assertTrue(pemTerbuka.terverifikasi(), "qalb terbuka: temuan muhasabah lolos");
        assertTrue(pemTerbuka.kondisi().contains("terbuka"));
        assertTrue(pemTerbuka.kondisi().contains("asal"), "context-nya (pemahaman diri saat ini) ikut digambar");

        // Orientasi hati tertutup: reasoning yang sama, output berbeda - karena conditioning-nya berubah.
        Qalb qalbTertutup = Qalb.hadapkan(new Pengalaman(1, true, false));
        Pemahaman pemTertutup = akal.proses(kesadaranBermasalah, qalbTertutup, diri);
        assertFalse(pemTertutup.terverifikasi(), "qalb tertutup: temuan dimanusiakan - logis, orientationally false");
        assertTrue(pemTertutup.kondisi().contains("tertutup"));

        // Tanpa label mode bernama: tidak ada enum ulil-albab/iblis di API.
        String namaApiPemahaman = Arrays.stream(Pemahaman.class.getMethods())
                .filter(m -> m.getDeclaringClass() == Pemahaman.class)
                .map(java.lang.reflect.Method::getName)
                .collect(Collectors.joining(","));
        assertFalse(namaApiPemahaman.toLowerCase().contains("iblis"), "tidak ada mode bernama di API");
        assertFalse(namaApiPemahaman.toLowerCase().contains("ulil"), "tidak ada mode bernama di API");
    }

    /** LauhulMahfuzh = persistence di luar runtime: node tidak membawa, tidak membaca, tidak menulis. */
    @Test
    void lauhulMahfuzh_persistenceDiluarRuntime_commitOlehScheduler() {
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(3, selalu(true));
        Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
        Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());

        // Manusia tidak membawa database: field-nya cuma komponen runtime.
        Set<String> fieldManusia = Arrays.stream(Manusia.class.getDeclaredFields())
                .map(java.lang.reflect.Field::getName)
                .collect(Collectors.toSet());
        assertEquals(Set.of("diri", "qalb", "akal", "qadar", "ruh"), fieldManusia,
                "Manusia = diri + qalb + akal + qadar + ruh; LauhulMahfuzh tidak dibawa ke runtime");

        Manusia akhir = manusia.evolve(kehidupan);
        assertEquals(3, kehidupan.framesDijalani());

        // Commit dieksekusi scheduler saat termination (token revocation), bukan oleh Manusia:
        assertTrue(lauhul.logCommit().isEmpty(), "selama hidup: nol commit - node tidak menulis ke database");
        lauhul.commit(akhir.diri().niyyah(), kehidupan.riwayat());
        assertEquals(1, lauhul.logCommit().size(),
                "commit final state + qada tercatat di persistence saat ajal (DAA 5.4)");
        assertTrue(lauhul.logCommit().get(0).contains("qada: 3 frame"),
                "qada (realized path) ikut di-commit: riwayat pindah dari runtime ke database");
    }

    /** Mutable vs immutable: diri & qalb berubah per frame; qadar immutable; akl stateless. */
    @Test
    void atributYangBerubah_vs_yangImmutable() {
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(4, selalu(true));
        Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
        Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());

        Manusia akhir = manusia.evolve(kehidupan);

        assertNotSame(manusia.diri(), akhir.diri(), "mutable: diri berubah per frame (koreksi diterima) - inilah yang berevolusi");
        assertNotSame(manusia.qalb(), akhir.qalb(), "snapshot qalb berubah: putaran frame terakhir");

        assertSame(manusia.qadar(), akhir.qadar(),
                "immutable: qadar (config) instance yang sama sepanjang hidup - node tidak bisa mengubah binary-nya");
        assertSame(manusia.akal(), akhir.akal(), "stateless: aql tanpa state yang berubah antar frame");
        assertSame(manusia.ruh(), akhir.ruh(), "immutable: token yang sama dibawa sepanjang hidup (scopes fixed saat injeksi)");
    }

    /** Ruh = AccessToken: reseptor dibukakan sesuai scope; di luar authority ditolak (QS 55:33). */
    @Test
    void ruhAccessToken_receptorDibuka_yangDiLuarAuthorityDitolak() {
        Ruh ruh = Ruh.injeksiStandar();

        // Port yang dibukakan: sustain + reseptor + ibadah + agency.
        for (String port : List.of("bernafas", "mengalami", "melihat", "mendengar",
                "menghadap", "berpikir", "memilih")) {
            assertTrue(ruh.bisaAkses(port), "port dibukakan: " + port);
        }

        // Di luar authority (QS 55:33: tanpa sultan, tidak bisa menembus):
        assertFalse(ruh.bisaAkses("menembusLangitDanBumi"),
                "tanpa sultan/authority, akses ke luar batas tidak dibukakan");
        assertFalse(ruh.bisaAkses("mencipta"), "la quwwata illa billah (DAA 0.6): port mencipta tidak ada di token");
        assertFalse(ruh.bisaAkses("mengubahQadar"), "qadar immutable (DAA 7.1): node tidak bisa menulis binary-nya");
        assertFalse(ruh.bisaAkses("menghentikanAjal"), "ajal mubram (DAA 5.3): revocation bukan wewenang token");
    }

    /** Gate eksekusi: tanpa scope port, frame tidak bisa jalan - semua akses melalui token. */
    @Test
    void ruhGateFrame_tanpaScopePort_eksekusiGagal() {
        // Token tanpa port "berpikir": reasoning tidak bisa dieksekusi.
        Ruh tanpaBerpikir = Ruh.injeksi(Set.of("bernafas", "mengalami", "menghadap", "memilih"));
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(3, selalu(true));
        Manusia manusia = Manusia.lahir(lauhul.qadar(), tanpaBerpikir);
        Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());

        assertThrows(IllegalStateException.class, () -> manusia.evolve(kehidupan),
                "tanpa port berpikir di token, reasoning tidak bisa jalan: semua akses melalui Ruh (authority, QS 55:33)");

        // Token standar: frame jalan normal - akses lolos karena dibukakan.
        LauhulMahfuzh lauhulStandar = LauhulMahfuzh.tulis(2, selalu(true));
        Manusia manusiaStandar = Manusia.lahir(lauhulStandar.qadar(), Ruh.injeksiStandar());
        Kehidupan kehidupanStandar = Kehidupan.baru(lauhulStandar.qadar());
        Manusia akhir = manusiaStandar.evolve(kehidupanStandar);
        assertEquals(2, kehidupanStandar.framesDijalani(), "dengan port dibukakan: frame dieksekusi penuh");
        assertTrue(akhir.diri().niyyah().startsWith("kembali"), "koreksi hari 2 diterima (qalb terbuka di skrip)");
    }

    /** Ruh immutable: scopes fixed saat injeksi; revocation = session end (ajal), bukan API node. */
    @Test
    void ruhImmutable_scopesFixed_revocationDiLuarNode() {
        // Tidak ada API revoke/tarik/stop di Ruh: revocation = diambil Allah (QS 39:42), bukan node.
        String namaApi = Arrays.stream(Ruh.class.getMethods())
                .filter(m -> m.getDeclaringClass() == Ruh.class)
                .map(java.lang.reflect.Method::getName)
                .collect(Collectors.joining(","));
        assertFalse(namaApi.contains("revoke"), "tidak ada API revoke oleh node");
        assertFalse(namaApi.contains("tarik"), "tidak ada API tarik oleh node");
        assertFalse(namaApi.contains("stop"), "tidak ada API stop oleh node");

        // Token sama sepanjang hidup (scopes fixed saat injeksi):
        LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(3, selalu(true));
        Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
        Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());
        Manusia akhir = manusia.evolve(kehidupan);
        assertSame(manusia.ruh(), akhir.ruh(), "token yang sama dibawa sepanjang hidup");
    }
}
