package id.voidlight.evolusi.demo;

import id.voidlight.evolusi.LauhulMahfuzh;
import id.voidlight.evolusi.Manusia;
import id.voidlight.evolusi.Kehidupan;
import id.voidlight.evolusi.Ruh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Demo multi-thread: satu dunia, banyak thread. Tiap manusia = thread dengan
 * komponen lengkapnya (Diri/Nafs + Qalb/Hati + Aql/Akal + Qadar), ajal random
 * dicatat di LauhulMahfuzh sebelum lahir - tidak di-publish ke runtime.
 */
public final class UmmahDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("// Satu dunia, banyak thread. Tiap manusia = thread:");
        System.out.println("//   Diri (nafs-layer) + Qalb (the turner) + Aql (reasoning) + Qadar (config) + Ruh (AccessToken).");
        System.out.println("// Qadar ditulis LauhulMahfuzh sebelum lahir: mubram (ajal) + mu'allaq (port harian).");
        System.out.println("// Ruh = AccessToken: semua akses port per frame (bernafas, menghadap, berpikir, memilih) melalui token.");
        System.out.println("// Ajal cuma bisa ditanya satu hal: tiba atau belum. Batasnya gak bisa dibaca, gak bisa dinegosiasikan.");
        System.out.println("// LauhulMahfuzh = persistence di luar runtime: node tidak bisa baca/tulis; commit dieksekusi scheduler.");
        System.out.println("// Provisioning: just-in-time per frame. Tidak ada alokasi jaga-jaga.");
        System.out.println();

        Random takdirSpawn = new Random(20261121);
        int jumlahThread = 12;
        List<Thread> threads = new ArrayList<>();
        List<LauhulMahfuzh> lauhuls = Collections.synchronizedList(new ArrayList<>());
        List<Kehidupan> kehidupans = Collections.synchronizedList(new ArrayList<>());
        List<Manusia> manusiaAkhir = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < jumlahThread; i++) {
            final int ajalHari = 100 + takdirSpawn.nextInt(900);
            final long seed = 7_000_000L + i;
            Thread t = new Thread(() -> {
                LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(ajalHari, seed);
                Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
                Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());
                Manusia akhir = manusia.evolve(kehidupan);
                // Termination = final state commit (DAA 5.4); dieksekusi scheduler, bukan node:
                lauhul.commit(akhir.diri().niyyah(), kehidupan.riwayat());
                manusiaAkhir.add(akhir);
                kehidupans.add(kehidupan);
                lauhuls.add(lauhul);
            }, "diri-" + i);
            threads.add(t);
        }

        threads.forEach(Thread::start);
        for (Thread t : threads) {
            t.join();
        }

        long totalFrame = 0;
        long totalKoreksi = 0;
        long totalCommit = 0;
        for (int i = 0; i < kehidupans.size(); i++) {
            Kehidupan k = kehidupans.get(i);
            long koreksi = k.riwayat().stream()
                    .filter(p -> p.tergelincir() && p.qalbTerbuka())
                    .count();
            int frames = k.riwayat().size();
            totalFrame += frames;
            totalKoreksi += koreksi;
            totalCommit += lauhuls.get(i).logCommit().size();
            System.out.printf(
                    "thread %-8s | qadar: rahasia | frame dijalani: %5d | koreksi arah: %3d | commit: %d | niyyah akhir: %s%n",
                    threads.get(i).getName(), frames, koreksi, lauhuls.get(i).logCommit().size(),
                    manusiaAkhir.get(i).diri().niyyah());
        }

        System.out.println();
        System.out.println("Total frame dunia (heap yang terpakai): " + totalFrame);
        System.out.println("Total koreksi arah: " + totalKoreksi);
        System.out.println("Total commit ke LauhulMahfuzh: " + totalCommit + " (final state tiap manusia, dieksekusi scheduler)");
        System.out.println("Batas total dunia (qiyamah): hanya diketahui scheduler. Tidak di-publish ke thread.");
        System.out.println("Tiap manusia berhenti di ajalnya sendiri: rekursi didesain TERHENTI, bukan didesain SELESAI.");
    }
}
