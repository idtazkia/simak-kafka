create table enable_fitur (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  mhswid VARCHAR(10) NOT NULL,
  fitur VARCHAR(20) NOT NULL,
  enable BOOLEAN NOT NULL
);

ALTER TABLE `enable_fitur` ADD UNIQUE `unique_fitur_mahasiswa`(`mhsw_id`, `fitur`);

CREATE TABLE `bayarmhsw` (
  `BayarMhswID` varchar(50) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  `BayarMhswRef` varchar(50) COLLATE latin1_general_ci DEFAULT NULL,
  `KodeID` varchar(50) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  `COAID` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `TahunID` varchar(10) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  `RekeningID` varchar(50) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  `PMBID` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `MhswID` varchar(20) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  `Autodebet` smallint(6) NOT NULL DEFAULT '0',
  `TrxID` int(11) NOT NULL DEFAULT '1',
  `PMBMhswID` smallint(6) NOT NULL DEFAULT '1',
  `Bank` varchar(50) COLLATE latin1_general_ci DEFAULT NULL,
  `BuktiSetoran` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `Tanggal` date NOT NULL,
  `Jumlah` bigint(20) NOT NULL DEFAULT '0',
  `JumlahLain` bigint(20) NOT NULL DEFAULT '0',
  `CicilanID` bigint(20) NOT NULL DEFAULT '0',
  `Proses` int(11) NOT NULL DEFAULT '0',
  `Keterangan` varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  `Cetak` int(11) NOT NULL DEFAULT '0',
  `LoginBuat` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `TanggalBuat` datetime DEFAULT NULL,
  `LoginEdit` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `TanggalEdit` datetime DEFAULT NULL,
  `NA` enum('Y','N') COLLATE latin1_general_ci NOT NULL DEFAULT 'N',
  PRIMARY KEY (`BayarMhswID`),
  KEY `KodeID` (`KodeID`),
  KEY `RekeningID` (`RekeningID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `bayarmhsw2` (
  `BayarMhsw2ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BayarMhsw2Ref` bigint(20) NOT NULL DEFAULT '0',
  `BayarMhswID` varchar(50) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  `BIPOTMhswID` bigint(20) NOT NULL DEFAULT '0',
  `BIPOTNamaID` bigint(20) NOT NULL DEFAULT '0',
  `Jumlah` bigint(20) NOT NULL DEFAULT '0',
  `LoginBuat` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `TanggalBuat` date DEFAULT NULL,
  `LoginEdit` varchar(20) COLLATE latin1_general_ci DEFAULT NULL,
  `TanggalEdit` date DEFAULT NULL,
  `NA` enum('Y','N') COLLATE latin1_general_ci NOT NULL DEFAULT 'N',
  PRIMARY KEY (`BayarMhsw2ID`)
) ENGINE=MyISAM AUTO_INCREMENT=35073 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `bipotmhsw` (
  `BIPOTMhswID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MhswID` varchar(20) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  `BIPOTNamaID` bigint(20) NOT NULL DEFAULT '0',
  `TahunID` varchar(10) COLLATE latin1_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`BIPOTMhswID`)
) ENGINE=MyISAM AUTO_INCREMENT=37538 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;