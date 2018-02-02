create table enable_fitur (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  mhsw_id VARCHAR(10) NOT NULL,
  fitur VARCHAR(20) NOT NULL,
  enable BOOLEAN NOT NULL
);

ALTER TABLE `enable_fitur` ADD UNIQUE `unique_fitur_mahasiswa`(`mhsw_id`, `fitur`);