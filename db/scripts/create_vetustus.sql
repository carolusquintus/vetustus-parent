SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `vetustus` ;
CREATE SCHEMA IF NOT EXISTS `vetustus` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `vetustus` ;

-- -----------------------------------------------------
-- Table `vetustus`.`office`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vetustus`.`office` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `vetustus`.`office` (
  `office_code` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(50) NOT NULL,
  `address_line_1` VARCHAR(50) NOT NULL,
  `address_line_2` VARCHAR(50) NULL DEFAULT NULL,
  `state` VARCHAR(50) NULL DEFAULT NULL,
  `country` VARCHAR(50) NOT NULL,
  `postal_code` VARCHAR(15) NOT NULL,
  `territory` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`office_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `vetustus`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vetustus`.`employee` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `vetustus`.`employee` (
  `employee_number` INT NOT NULL AUTO_INCREMENT,
  `last_name` VARCHAR(50) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `extension` VARCHAR(10) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `office_code` VARCHAR(10) NOT NULL,
  `reports_to` INT NULL DEFAULT NULL,
  `job_title` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`employee_number`),
  CONSTRAINT `fk_employee_office`
    FOREIGN KEY (`office_code`)
    REFERENCES `vetustus`.`office` (`office_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_employee_office_idx` ON `vetustus`.`employee` (`office_code` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `vetustus`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vetustus`.`customer` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `vetustus`.`customer` (
  `customer_number` INT NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(50) NOT NULL,
  `contact_last_name` VARCHAR(50) NOT NULL,
  `contact_first_name` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(50) NOT NULL,
  `address_line_1` VARCHAR(50) NOT NULL,
  `address_line_2` VARCHAR(50) NULL DEFAULT NULL,
  `city` VARCHAR(50) NOT NULL,
  `state` VARCHAR(50) NULL DEFAULT NULL,
  `postal_code` VARCHAR(15) NULL DEFAULT NULL,
  `country` VARCHAR(50) NOT NULL,
  `sales_rep_employee_number` INT NULL,
  `credit_limit` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`customer_number`),
  CONSTRAINT `fk_customer_employee1`
    FOREIGN KEY (`sales_rep_employee_number`)
    REFERENCES `vetustus`.`employee` (`employee_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_customer_employee1_idx` ON `vetustus`.`customer` (`sales_rep_employee_number` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `vetustus`.`purchase`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vetustus`.`purchase` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `vetustus`.`purchase` (
  `purchase_number` INT NOT NULL AUTO_INCREMENT,
  `purchase_date` DATETIME NOT NULL,
  `required_date` DATETIME NOT NULL,
  `shipped_date` DATETIME NULL DEFAULT NULL,
  `status` VARCHAR(15) NOT NULL,
  `comments` TEXT NULL DEFAULT NULL,
  `customer_number` INT NOT NULL,
  PRIMARY KEY (`purchase_number`),
  CONSTRAINT `fk_purchase_customer1`
    FOREIGN KEY (`customer_number`)
    REFERENCES `vetustus`.`customer` (`customer_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_purchase_customer1_idx` ON `vetustus`.`purchase` (`customer_number` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `vetustus`.`product_line`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vetustus`.`product_line` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `vetustus`.`product_line` (
  `product_line` VARCHAR(3) NOT NULL,
  `product_line_name` VARCHAR(50) NOT NULL,
  `text_description` TEXT NOT NULL,
  `html_description` TEXT NULL,
  `image` BLOB NULL,
  PRIMARY KEY (`product_line`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `vetustus`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vetustus`.`product` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `vetustus`.`product` (
  `product_code` VARCHAR(15) NOT NULL,
  `product_name` VARCHAR(70) NOT NULL,
  `product_line` VARCHAR(3) NOT NULL,
  `product_scale` VARCHAR(10) NOT NULL,
  `product_vendor` VARCHAR(50) NOT NULL,
  `product_description` TEXT NOT NULL,
  `quantity_in_stock` SMALLINT NOT NULL,
  `buy_price` DOUBLE NOT NULL,
  `msrp` DOUBLE NOT NULL,
  PRIMARY KEY (`product_code`),
  CONSTRAINT `fk_product_product_line1`
    FOREIGN KEY (`product_line`)
    REFERENCES `vetustus`.`product_line` (`product_line`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_product_product_line1_idx` ON `vetustus`.`product` (`product_line` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `vetustus`.`purchase_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vetustus`.`purchase_detail` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `vetustus`.`purchase_detail` (
  `purchase_number` INT NOT NULL,
  `product_code` VARCHAR(15) NOT NULL,
  `quantity_ordered` INT NOT NULL,
  `price_each` DOUBLE NOT NULL,
  `purchase_line_number` SMALLINT NOT NULL,
  PRIMARY KEY (`purchase_number`, `product_code`),
  CONSTRAINT `fk_order_detail_order1`
    FOREIGN KEY (`purchase_number`)
    REFERENCES `vetustus`.`purchase` (`purchase_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_detail_product1`
    FOREIGN KEY (`product_code`)
    REFERENCES `vetustus`.`product` (`product_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_purchase_detail_purchase1_idx` ON `vetustus`.`purchase_detail` (`purchase_number` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_purchase_detail_product1_idx` ON `vetustus`.`purchase_detail` (`product_code` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `vetustus`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vetustus`.`payment` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `vetustus`.`payment` (
  `customer_number` INT NOT NULL,
  `check_number` VARCHAR(50) NOT NULL,
  `payment_date` DATETIME NOT NULL,
  `amount` DOUBLE NOT NULL,
  PRIMARY KEY (`check_number`, `customer_number`),
  CONSTRAINT `fk_payment_customer1`
    FOREIGN KEY (`customer_number`)
    REFERENCES `vetustus`.`customer` (`customer_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
CREATE INDEX `fk_payment_customer1_idx` ON `vetustus`.`payment` (`customer_number` ASC);

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
