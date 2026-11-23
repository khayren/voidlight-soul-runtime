package id.voidlight.evolusi.demo;

import id.voidlight.evolusi.LauhulMahfuzh;
import id.voidlight.evolusi.Manusia;
import id.voidlight.evolusi.Kehidupan;
import id.voidlight.evolusi.Ruh;

/**
 * Demo stack overflow: JVM tidak melakukan tail-call optimization.
 * Rekursi dengan ajal tak berbatas pasti stack overflow sebelum "selesai".
 * Bukan bug: rekursi ini didesain TERHENTI, bukan didesain SELESAI.
 */
public final class StackOverflowDemo {

    public static void main(String[] args) {
        System.out.println("Menjalankan rekursi dengan ajal Integer.MAX_VALUE ...");
        try {
            LauhulMahfuzh lauhul = LauhulMahfuzh.tulis(Integer.MAX_VALUE, 1L);
            Manusia manusia = Manusia.lahir(lauhul.qadar(), Ruh.injeksiStandar());
            Kehidupan kehidupan = Kehidupan.baru(lauhul.qadar());
            manusia.evolve(kehidupan);
            System.out.println("Selesai. (Di JVM, baris ini tidak akan pernah tercapai.)");
        } catch (StackOverflowError e) {
            System.out.println("Stack overflow: eksekusi dihentikan oleh runtime, bukan oleh kondisi sukses di dalam program.");
            System.out.println("Rekursi ini didesain TERHENTI, bukan didesain SELESAI.");
        }
    }
}
