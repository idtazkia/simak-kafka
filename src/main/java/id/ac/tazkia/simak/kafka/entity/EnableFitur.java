package id.ac.tazkia.simak.kafka.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity @Table(name = "enable_fitur") @Data
public class EnableFitur {
    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    @NotBlank
    @Column(name = "mhswid")
    private String mahasiswa;

    @NotBlank
    private String fitur;

    @NotBlank
    @Column(name = "tahun_id")
    private String tahunId;

    @NotNull
    private Boolean enable;
}
