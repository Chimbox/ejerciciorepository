CREATE SCHEMA club_nautico;

USE club_nautico;

CREATE TABLE socio(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    dni VARCHAR(6) NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    direccion VARCHAR(50) NOT NULL);
    
CREATE TABLE barco(
	num_matricula INT PRIMARY KEY NOT NULL,
    nombre VARCHAR(50),
    num_amarre SMALLINT NOT NULL,
    cuota DOUBLE NOT NULL,
    socio_id INT NOT NULL,
    FOREIGN KEY (socio_id)
    REFERENCES socio(id));
    
CREATE TABLE destino(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50));
    
CREATE TABLE salida(
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    fecha_hora DATETIME NOT NULL,
    barco_num_matricula INT NOT NULL,
    destino_id INT NOT NULL,
    FOREIGN KEY (barco_num_matricula)
    REFERENCES barco(num_matricula),
    FOREIGN KEY (destino_id)
    REFERENCES destino(id));
