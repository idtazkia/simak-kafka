package id.ac.tazkia.simak.kafka.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Table(name = "enable_fitur") @Data
public class EnableFitur {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull @NotEmpty
    @Column(name = "mhswid")
    private String mahasiswa;

    @NotNull @NotEmpty
    private String fitur;

    @NotNull
    private Boolean enable;
}
