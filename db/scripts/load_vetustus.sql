/******************************************************************************
 * Copyright (c) 2005 Actuate Corporation.
 * All rights reserved. This file and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial implementation
 *  Carlos Rosas         - personal implementation
 *
 * Classic Models Inc. sample database developed as part of the
 * Eclipse BIRT Project. For more information, see http:\\www.eclipse.org\birt
 *
 *******************************************************************************/

/* Loads the Vetustus tables using the MySQL LOAD command */

/* Preparing the load files for importing. Input file requirements:
 *   - Column order in the file must be the same as the columns in the table
 *   - Columns are Comma delimited
 *   - Text is quoted (")
 *   - NULL columns must be ,NULL,  ( ,, is not acceptable)
 *   - Dates must be in Y/M/D format

 * Input files expected in the datafiles direcory, parallel to this script.
*/

/* First make sure all the tables are empty */

USE vetustus;

DELETE FROM purchase_detail;
SHOW WARNINGS;
DELETE FROM purchase;
SHOW WARNINGS;
DELETE FROM product;
SHOW WARNINGS;
DELETE FROM product_line;
SHOW WARNINGS;
DELETE FROM payment;
SHOW WARNINGS;
DELETE FROM customer;
SHOW WARNINGS;
DELETE FROM employee;
SHOW WARNINGS;
DELETE FROM office;
SHOW WARNINGS;

COMMIT;

/* Load records into the tables. There should be no warnings. */

LOAD DATA LOCAL INFILE '../datafiles/office.csv' INTO TABLE office FIELDS TERMINATED BY ','  ENCLOSED BY '"'  LINES TERMINATED BY '\n';
SHOW WARNINGS;

LOAD DATA LOCAL INFILE '../datafiles/employee.csv' INTO TABLE employee FIELDS TERMINATED BY ','  ENCLOSED BY '"'  LINES TERMINATED BY '\n';
SHOW WARNINGS;

LOAD DATA LOCAL INFILE '../datafiles/customer.csv' INTO TABLE customer FIELDS TERMINATED BY ','  ENCLOSED BY '"'  LINES TERMINATED BY '\n';
SHOW WARNINGS;

LOAD DATA LOCAL INFILE '../datafiles/payment.csv' INTO TABLE payment FIELDS TERMINATED BY ','  ENCLOSED BY '"'  LINES TERMINATED BY '\n';
SHOW WARNINGS;

LOAD DATA LOCAL INFILE '../datafiles/product_line.csv' INTO TABLE product_line FIELDS TERMINATED BY ','  ENCLOSED BY '"'  LINES TERMINATED BY '\n';
SHOW WARNINGS;

LOAD DATA LOCAL INFILE '../datafiles/product.csv' INTO TABLE product FIELDS TERMINATED BY ','  ENCLOSED BY '"'  LINES TERMINATED BY '\n';
SHOW WARNINGS;

LOAD DATA LOCAL INFILE '../datafiles/purchase.csv' INTO TABLE purchase FIELDS TERMINATED BY ','  ENCLOSED BY '"'  LINES TERMINATED BY '\n';
SHOW WARNINGS;

LOAD DATA LOCAL INFILE '../datafiles/purchase_detail.csv' INTO TABLE purchase_detail FIELDS TERMINATED BY ','  ENCLOSED BY '"'  LINES TERMINATED BY '\n';
SHOW WARNINGS;

COMMIT;