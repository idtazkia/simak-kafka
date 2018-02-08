package id.ac.tazkia.simak.kafka.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name = "bipotmhsw") @Data
public class BipotMahasiswa {
    @Id @Column(name = "BIPOTMhswID")
    private Long id;

    @Column(name = "TahunID")
    private String kodeSemester;

    @Column(name = "MhswID")
    private String mahasiswa;

    @Column(name = "BIPOTNamaID")
    private Long kodeBipot;
}
