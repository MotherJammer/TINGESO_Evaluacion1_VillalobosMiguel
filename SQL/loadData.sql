INSERT INTO `categorias` (`letra`, `sueldo_fijo_mensual`)
VALUES
  ("A", 1700000),
  ("B", 1200000),
  ("C", 800000);

INSERT INTO `empleado` (`rut`,`apellidos`,`nombre`,`fecha_nac`,`fecha_ing`,`categoria`)
VALUES
  ("24.321.441-6","Sanchez Renato","Norman MacKenzie","1970-02-26 06:42:46","2017-02-24 20:49:22", 1),
  ("9.827.441-3","Nicolas Alexandra","Deirdre Xyla","1992-04-02 18:44:33","2017-03-16 03:10:27", 2),
  ("31.547.806-5","Laura Augustin","Talon Simone","1977-08-24 22:26:54","2016-04-29 16:52:24", 3),
  ("3.641.673-4","Gabriela Vargas","Hyatt Austin","1983-12-25 07:27:57","2010-09-04 11:44:13", 2),
  ("24.688.721-7","Zavala Contreras","Mary Ferdinand","1986-06-04 10:36:35","2019-07-29 17:01:06", 3);