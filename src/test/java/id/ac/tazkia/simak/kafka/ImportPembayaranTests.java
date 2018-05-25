package id.ac.tazkia.simak.kafka;

import id.ac.tazkia.simak.kafka.dto.PembayaranTagihan;
import id.ac.tazkia.simak.kafka.service.KafkaListenerService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportPembayaranTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImportPembayaranTests.class);

    @Autowired private KafkaListenerService kafkaListenerService;
    @Value("classpath:/csv/pembayaran.csv") private Resource pembayaranCsv;
    @Value("${kode.bipot.spp.variabel}") private Long kodeBipotSppVariabel;
    @Value("${bank.nama}") private String namaBank;


    @Test @Ignore
    public void testImportCsvPembayaran() throws Exception {
        Assert.assertNotNull(pembayaranCsv);
        BufferedReader reader = new BufferedReader(new InputStreamReader(pembayaranCsv.getInputStream()));

        String rowData;
        while((rowData = reader.readLine())!=null){
            LOGGER.info("Row Data : {}", rowData);
            String[] data = rowData.split(",");
            PembayaranTagihan pembayaranTagihan = new PembayaranTagihan();
            pembayaranTagihan.setJenisTagihan("13");
            pembayaranTagihan.setNomorDebitur(data[1]);
            pembayaranTagihan.setNamaDebitur(data[2]);
            pembayaranTagihan.setBank(data[3]);
            pembayaranTagihan.setNilaiPembayaran(new BigDecimal(data[4]));
            pembayaranTagihan.setWaktuPembayaran(data[5]+" "+data[6]);
            pembayaranTagihan.setReferensiPembayaran(data[7]);

            LOGGER.info("Pembayaran Tagihan {}", pembayaranTagihan);

            kafkaListenerService.pembayaranMahasiswa(pembayaranTagihan);
        }

    }
}
