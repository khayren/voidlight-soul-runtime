package id.voidlight.evolusi;

import java.util.List;

/**
 * Manusia = agregat/snapshot primary user node (DAA section 3.3): komposisi komponen
 * pada suatu instant.
 *
 * Komposisi (DAA 4.1 + Human OS):
 * - {@link Diri}  : Nafs-layer state - niyyah. YANG BEREVOLUSI (v0.6, koreksi owner):
 *   agency manusia ada di sini; rantai rekursi = diri -> diri.
 * - {@link Qalb}  : Hati - the turner; komponen per-frame (menerima putaran dari
 *   mu'allaq port, bukan state yang dibawa antar frame). Field ini = snapshot.
 * - {@link Aql}   : Akal - reasoning engine ala LLM; stateless.
 * - {@link Qadar} : node config (immutable): mubram (Ajal) + mu'allaq (port source),
 *   ditulis LauhulMahfuzh sebelum runtime.
 * - {@link Ruh}   : AccessToken (v0.7) - di-inject Allah saat provisioning; scopes =
 *   port-port (verb) yang dibukakan: bernafas, menghadap, berpikir, memilih, reseptor.
 *   Revocation = session end saat ajal, di luar jangkauan node.
 *
 * Yang TIDAK dibawa Manusia:
 * - LauhulMahfuzh (persistence): node tidak bisa baca/tulis database-nya. Commit
 *   final state dieksekusi scheduler saat termination.
 * - As-sirr / Akhfa: di luar cakupan analogi komputasi (DAA 4.6).
 *
 * Snapshot berubah antar frame: diri (niyyah) dan qalb (orientasi frame terakhir).
 * Immutable sepanjang hidup: qadar, ruh (scopes fixed saat injeksi). Stateless: aql.
 */
public final class Manusia {

    private final Diri diri;
    private final Qalb qalb;
    private final Aql akal;
    private final Qadar qadar;
    private final Ruh ruh;

    private Manusia(Diri diri, Qalb qalb, Aql akal, Qadar qadar, Ruh ruh) {
        this.diri = diri;
        this.qalb = qalb;
        this.akal = akal;
        this.qadar = qadar;
        this.ruh = ruh;
    }

    /**
     * Boot/Provisioning (DAA 5.1 + Human OS section 10): qadar sudah ditulis
     * LauhulMahfuzh sebelum runtime; Ruh di-inject (status Offline -> Active);
     * nafs active, qalb receptive, aql stateless.
     */
    public static Manusia lahir(Qadar qadar, Ruh ruh) {
        return new Manusia(new Diri("asal"), Qalb.lahir(), Aql.lahir(), qadar, ruh);
    }

    /** Komponen (read-only untuk scheduler/pengamat; tidak ada setter). */
    public Diri diri() {
        return diri;
    }

    public Qalb qalb() {
        return qalb;
    }

    public Aql akal() {
        return akal;
    }

    public Qadar qadar() {
        return qadar;
    }

    public Ruh ruh() {
        return ruh;
    }

    /**
     * Menjalankan rekursi: yang berevolusi adalah diri-nya (agency manusia di situ);
     * Manusia hanya mengambil snapshot akhir setelah rantai diri -> diri selesai.
     * Orientasi qalb snapshot = putaran frame terakhir (dari ledger), bukan state
     * yang dibawa rekursi.
     */
    public Manusia evolve(Kehidupan kehidupan) {
        Diri diriAkhir = diri.evolve(kehidupan, qadar, ruh);
        Qalb qalbAkhir = qalbTerakhir(kehidupan);
        return new Manusia(diriAkhir, qalbAkhir, akal, qadar, ruh);
    }

    private Qalb qalbTerakhir(Kehidupan kehidupan) {
        List<Pengalaman> riwayat = kehidupan.riwayat();
        return riwayat.isEmpty() ? qalb : Qalb.hadapkan(riwayat.get(riwayat.size() - 1));
    }
}
