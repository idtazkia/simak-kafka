package id.ac.tazkia.simak.kafka.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "bayarmhsw") @Data
public class PembayaranMahasiswa {

    @Id @Column(name = "BayarMhswID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "BayarMhswRef")
    private String referensi;

    @Column(name = "KodeID")
    private String kode = "TAZKIA";

    @Column(name = "TahunID")
    private String tahun;

    @Column(name = "RekeningID")
    private String rekening;

    @Column(name = "MhswID")
    private String mahasiswa;

    @Column(name = "Bank")
    private String bank;

    @Column(name = "Tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal = new Date();

    @Column(name = "TanggalBuat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalBuat = new Date();

    @Column(name = "Jumlah")
    private Long jumlah;

    @Column(name = "Keterangan")
    private String keterangan;

    @Column(name = "LoginEdit")
    private String loginEdit = "Aplikasi Tagihan";
}
