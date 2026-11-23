package id.voidlight.evolusi.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo Exact Sufficiency / 10V Threshold (DAA 7.3-7.4) - case study fanbelt owner.
 *
 * [ANALOGY] Skenario: perjalanan dengan resource yang terdeplesi PERSIS saat checkpoint
 * berikutnya tercapai - bukan lebih awal, bukan lebih akhir. Root cause unknown (fanbelt
 * putus, undetected), amanah memaksa keputusan (istri & anak di mobil), monitoring real
 * time = maximum ikhtiar continuous, hasil diserahkan sambil tetap monitor = tawakkal exact.
 * Yang dievaluasi framework bukan margin kenyamanan, tapi presisi: cukup sustain untuk
 * sampai checkpoint tanpa stall.
 *
 * Titik patah analogi: kejadian dunia nyata ini dinyatakan owner sebagai lived experience;
 * model di sini hanya merepresentasikan struktur monitoring-nya, bukan mengklaim mekanisme
 * penyebabnya.
 */
public final class ExactSufficiencyDemo {

    /** Hasil perjalanan: log gauge per km (maximum ikhtiar real time) + volt saat tiba. */
    public record Jalanan(int jarakKm, double voltTiba, List<String> gauge) {}

    /**
     * Menjalani perjalanan dengan exact sufficiency: deplesi dihitung per km,
     * volt tiba = voltTiba PERSIS di km terakhir. Tidak ada buffer.
     */
    public static Jalanan jalani(int jarakKm, double voltMulai, double voltTiba) {
        double dropPerKm = (voltMulai - voltTiba) / jarakKm;
        List<String> gauge = new ArrayList<>();
        double volt = voltMulai;
        for (int km = 1; km <= jarakKm; km++) {
            volt = voltMulai - km * dropPerKm; // setiap km di-track: continuous muhasabah
            gauge.add(String.format("km %d: %.1fV", km, volt));
        }
        return new Jalanan(jarakKm, volt, gauge);
    }

    public static void main(String[] args) {
        System.out.println("// Exact Sufficiency (DAA 7.3-7.4): tidak ada buffer surplus.");
        System.out.println("// Root cause unknown; monitoring real time = maximum ikhtiar;");
        System.out.println("// hasil diserahkan sambil tetap monitor = tawakkal exact.");
        System.out.println();

        Jalanan jalanan = jalani(7, 12.0, 10.0);
        for (String bacaan : jalanan.gauge()) {
            System.out.println("gauge: " + bacaan);
        }
        System.out.printf("%nTiba di tujuan: %.1fV - persis 10V Threshold. Bukan 11 (margin), bukan 9 (mati di jalan).%n",
                jalanan.voltTiba());
        System.out.println("Ketersediaan resource berikutnya: exact next step (DAA 7.4, QS 94:5-6).");
    }
}
