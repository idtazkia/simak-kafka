package id.ac.tazkia.simak.kafka.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity @Table(name = "enable_fitur") @Data
public class EnableFitur {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "mhswid")
    private String mahasiswa;

    @NotBlank
    private String fitur;

    @NotNull
    private Boolean enable;
}
