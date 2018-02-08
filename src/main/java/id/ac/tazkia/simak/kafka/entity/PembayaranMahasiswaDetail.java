package id.ac.tazkia.simak.kafka.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "bayarmhsw2") @Data
public class PembayaranMahasiswaDetail {

    @Id @Column(name = "BayarMhsw2ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "BayarMhsw2Ref")
    private Long referensi = 0L;

    @ManyToOne @JoinColumn(name = "BayarMhswID")
    private PembayaranMahasiswa pembayaranMahasiswa;

    @Column(name = "BIPOTMhswID")
    private Long bipotMahasiswa;

    @Column(name = "BIPOTNamaID")
    private Long bipotNama;

    @Column(name = "Jumlah")
    private Long jumlah;

    @Column(name = "LoginBuat")
    private String loginBuat = "Aplikasi Tagihan";

    @Column(name = "TanggalBuat")
    private Date tanggalBuat = new Date();
}
