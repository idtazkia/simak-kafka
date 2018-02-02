package id.ac.tazkia.simak.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.tazkia.simak.kafka.dao.EnableFiturDao;
import id.ac.tazkia.simak.kafka.dto.PembayaranTagihan;
import id.ac.tazkia.simak.kafka.entity.EnableFitur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class KafkaListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaListenerService.class);
    private static final String FITUR_KRS = "krs";

    @Autowired private EnableFiturDao enableFiturDao;

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "${kafka.topic.tagihan.payment}", group = "${spring.kafka.consumer.group-id}")
    public void handlePayment(String message) {
        try {
            PembayaranTagihan pembayaranTagihan = objectMapper.readValue(message, PembayaranTagihan.class);

            LOGGER.debug("No Debitur : {}", pembayaranTagihan.getNomorDebitur());

            EnableFitur enableFitur = enableFiturDao.findByMahasiswaAndFitur(pembayaranTagihan.getNomorDebitur(), FITUR_KRS);
            if (enableFitur == null) {
                enableFitur = new EnableFitur();
                enableFitur.setMahasiswa(pembayaranTagihan.getNomorDebitur());
                enableFitur.setFitur(FITUR_KRS);
            }

            enableFitur.setEnable(true);
            enableFiturDao.save(enableFitur);
        } catch (Exception err) {
            LOGGER.error(err.getMessage(), err);
        }
    }
}
