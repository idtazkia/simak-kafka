package id.ac.tazkia.simak.kafka.dao;

import id.ac.tazkia.simak.kafka.entity.PembayaranMahasiswa;
import org.springframework.data.repository.CrudRepository;

public interface PembayaranMahasiswaDao extends CrudRepository<PembayaranMahasiswa, String> {
}
