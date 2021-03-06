package id.ac.tazkia.simak.kafka.dao;

import id.ac.tazkia.simak.kafka.entity.EnableFitur;
import org.springframework.data.repository.CrudRepository;

public interface EnableFiturDao extends CrudRepository<EnableFitur, Integer> {
    EnableFitur findByMahasiswaAndFiturAndTahunId(String mahasiswa, String fitur, String tahunId);
}
