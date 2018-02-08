package id.ac.tazkia.simak.kafka.dao;

import id.ac.tazkia.simak.kafka.entity.BipotMahasiswa;
import org.springframework.data.repository.CrudRepository;

public interface BipotMahasiswaDao extends CrudRepository<BipotMahasiswa, Long> {
    BipotMahasiswa findByMahasiswaAndKodeBipotAndKodeSemester(String mahasiswa, Long namaBipot, String kodeSemester);
}
