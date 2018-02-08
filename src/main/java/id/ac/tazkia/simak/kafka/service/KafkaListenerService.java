package id.ac.tazkia.simak.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.tazkia.simak.kafka.dao.BipotMahasiswaDao;
import id.ac.tazkia.simak.kafka.dao.EnableFiturDao;
import id.ac.tazkia.simak.kafka.dao.PembayaranMahasiswaDao;
import id.ac.tazkia.simak.kafka.dao.PembayaranMahasiswaDetailDao;
import id.ac.tazkia.simak.kafka.dto.PembayaranTagihan;
import id.ac.tazkia.simak.kafka.entity.BipotMahasiswa;
import id.ac.tazkia.simak.kafka.entity.EnableFitur;
import id.ac.tazkia.simak.kafka.entity.PembayaranMahasiswa;
import id.ac.tazkia.simak.kafka.entity.PembayaranMahasiswaDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class KafkaListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerService.class);
    private static final String FITUR_KRS = "krs";

    @Value("${kode.semester}") private String kodeSemester;
    @Value("${kode.bipot.spp.tetap}") private Long kodeBipotSppTetap;
    @Value("${bank.nama}") private String namaBank;
    @Value("${bank.rekening}") private String rekeningBank;

    @Autowired private BipotMahasiswaDao bipotMahasiswaDao;
    @Autowired private EnableFiturDao enableFiturDao;
    @Autowired private PembayaranMahasiswaDao pembayaranMahasiswaDao;
    @Autowired private PembayaranMahasiswaDetailDao pembayaranMahasiswaDetailDao;

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "${kafka.topic.tagihan.payment}", group = "${spring.kafka.consumer.group-id}")
    public void handlePayment(String message) {
        try {
            PembayaranTagihan pembayaranTagihan = objectMapper.readValue(message, PembayaranTagihan.class);

            LOGGER.debug("No Debitur : {}", pembayaranTagihan.getNomorDebitur());

            pembayaranMahasiswa(pembayaranTagihan);
        } catch (Exception err) {
            LOGGER.error(err.getMessage(), err);
        }
    }

    private void pembayaranMahasiswa(PembayaranTagihan pembayaranTagihan) {
        BipotMahasiswa bipotMahasiswa = bipotMahasiswaDao.findByMahasiswaAndKodeBipotAndKodeSemester(
                pembayaranTagihan.getNomorDebitur(),
                kodeBipotSppTetap,
                kodeSemester
        );

        if (bipotMahasiswa == null) {
            LOGGER.warn("BIPOT tidak ditemukan untuk mahasiswa {} semester {} bipot {}",
                    pembayaranTagihan.getNomorDebitur(),
                    kodeSemester, kodeBipotSppTetap
                    );
            return;
        }

        PembayaranMahasiswa bayar = new PembayaranMahasiswa();
        bayar.setBank(namaBank);
        bayar.setJumlah(pembayaranTagihan.getNilaiPembayaran().longValue());
        bayar.setKeterangan("Pembayaran melalui virtual account");
        bayar.setReferensi(pembayaranTagihan.getReferensiPembayaran());
        bayar.setMahasiswa(pembayaranTagihan.getNomorDebitur());
        bayar.setRekening(rekeningBank);
        bayar.setTahun(kodeSemester);
        pembayaranMahasiswaDao.save(bayar);

        PembayaranMahasiswaDetail detail = new PembayaranMahasiswaDetail();
        detail.setPembayaranMahasiswa(bayar);
        detail.setJumlah(pembayaranTagihan.getNilaiPembayaran().longValue());
        detail.setBipotMahasiswa(bipotMahasiswa.getId());
        detail.setBipotNama(kodeBipotSppTetap);
        pembayaranMahasiswaDetailDao.save(detail);

        enableFitur(pembayaranTagihan);
    }

    private void enableFitur(PembayaranTagihan pembayaranTagihan) {
        EnableFitur enableFitur = enableFiturDao.findByMahasiswaAndFitur(pembayaranTagihan.getNomorDebitur(), FITUR_KRS);
        if (enableFitur == null) {
            enableFitur = new EnableFitur();
            enableFitur.setMahasiswa(pembayaranTagihan.getNomorDebitur());
            enableFitur.setFitur(FITUR_KRS);
        }

        enableFitur.setEnable(true);
        enableFiturDao.save(enableFitur);
        LOGGER.info("Enable Fitur {} untuk nomor {}", FITUR_KRS, pembayaranTagihan.getNomorDebitur());
    }
}
