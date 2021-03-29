CREATE TABLE IF NOT EXISTS `BDT`.`Categorias` (
`id_categoria` INT NOT NULL AUTO_INCREMENT,
 `nombre_categoria` VARCHAR(45) NOT NULL,
  
PRIMARY KEY (`id_categoria`))
;
CREATE TABLE IF NOT EXISTS `BDT`.`Chequeos` (
`id_chequeo` INT NOT NULL AUTO_INCREMENT,
  `frase` VARCHAR(100) NOT NULL,
 
`medio` VARCHAR(45) NOT NULL,
  `enlace` VARCHAR(45) NOT NULL,
  `verificacion` TINYINT NOT NULL,
  `palabra_clave` VARCHAR(45) NOT NULL,

`fecha` DATE NOT NULL,
  `categoria` INT NOT NULL,
  PRIMARY KEY (`id_chequeo`),
  INDEX `categoria_idx` (`categoria` ASC) VISIBLE,
  CONSTRAINT `categoria`
 
 FOREIGN KEY (`categoria`)
    REFERENCES `BDT`.`Categorias` (`id_categoria`)
;    


CREATE TABLE IF NOT EXISTS `BDT`.`Explicaciones` (
`id_explicacion` INT NOT NULL AUTO_INCREMENT,
 `titulo` VARCHAR(45) NOT NULL,
 `epigrafe` VARCHAR(45) NOT NULL,
  
`contenido` VARCHAR(45) NOT NULL,
  `fecha` DATE NOT NULL,
  PRIMARY KEY (`id_explicacion`)); 

CREATE TABLE IF NOT EXISTS `BDT`.`Investigaciones` (
  `id_investigacion` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NOT NULL,
  
`tema_investigacion` VARCHAR(45) NOT NULL,
  `epigrafe` VARCHAR(45) NOT NULL,
  `contenido` VARCHAR(45) NOT NULL,
  `palabra_clave` VARCHAR(45) NOT NULL,
 
 `fecha_creacion` VARCHAR(45) NOT NULL,
  `categoria` INT NOT NULL,
  PRIMARY KEY (`id_investigacion`),
  INDEX `categoria_idx` (`categoria` ASC) VISIBLE,
  
CONSTRAINT `categoria`
    FOREIGN KEY (`categoria`)
    REFERENCES `BDT`.`Categorias` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `mydb`.`Chequeo_Explicacion` 
(
  `id_chequeo_explicacion` INT NOT NULL AUTO_INCREMENT,
  `chequeo` INT NOT NULL,
  `explicacion` INT NOT NULL,
  
PRIMARY KEY (`id_chequeo_explicacion`),
  INDEX `chequeo_idx` (`chequeo` ASC) VISIBLE,
  INDEX `explicacion_idx`
 (`explicacion` ASC) VISIBLE,
  CONSTRAINT `chequeo`
    FOREIGN KEY (`chequeo`)
    REFERENCES `BDT`.`Chequeos` (`id_chequeo`)
   
 ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `explicacion`
    FOREIGN KEY (`explicacion`)
    REFERENCES `BDT`.`Explicaciones`
(`id_explicacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);