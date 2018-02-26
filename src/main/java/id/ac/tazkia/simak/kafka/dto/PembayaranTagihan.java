package id.ac.tazkia.simak.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PembayaranTagihan {
    private String jenisTagihan;
    private String nomorTagihan;
    private String nomorDebitur;
    private String namaDebitur;
    private String keteranganTagihan;
    private String tanggalTagihan;
    private String tanggalJatuhTempo;
    private String statusTagihan;
    private BigDecimal nilaiTagihan;
    private BigDecimal nilaiPembayaran;
    private BigDecimal nilaiAkumulasiPembayaran;
    private String bank;
    private String waktuPembayaran;
    private String referensiPembayaran;
}
