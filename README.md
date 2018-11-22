
# intellij-mysql-table-to-jpa-entity
An easy-to-use MySQL table to convert to JPA Entity's intelliJ plugin.[中文介绍](/README_CN.md/)

## Features
Create a table statement using MySql to generate a JPA-compliant Entity.

- Generate class annotation @Entity
- @Table(name = "table_name") that generates a class based on table name
- generate class comments based on table annotations
- Generate property annotations based on column annotations
- Generate property name based on column name, converted to small hump naming style
- Generate property Type based on column type, in accordance with [MySQL Specification] (https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-type-conversions.html)
- Generate @Id based on PRIMARY KEY
- Generate @GeneratedValue(strategy = GenerationType.AUTO) based on AUTO_INCREMENT
- Generate a Getter/Setter method for the property. Handling boolean type method names with variable names starting with is in accordance with [Java Bean Specification] (https://docs.oracle.com/cd/E19798-01/821-1841/bnbqc/index.htm)
- Generate a no-argument constructor and initialize it based on the DEFAULT value declared by Column

## example
MySQL ** standard build statement**:
```sql
CREATE TABLE `t_building` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Primary key ID',
  `name` varchar(50) NOT NULL COMMENT 'name',
  `sale_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Sales status 1-on sale, 2-for sale, 3-sales',
  `floor_space` bigint(11) DEFAULT '0' COMMENT 'Site: Units of square decimeter',
  `card_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'certification time',
  PRIMARY KEY (`id`)
ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8 COMMENT='Property Master';
```
JPA Entity :
Entity complete code [link] generated after the above statement (/src/test/java/TBuilding.java)

## Standard construction statement
Can be found in a variety of MySQL clients, the following are commonly used client access methods

 - Sequel Pro: Enter the database, click on the selection table on the left, select Table Info on the menu bar on the upper right, and **Standard build statement** in the Create syntax on the right side.
My
- MySQL Workbench: enter the database, select the table on the left, right-click the table name, select from the pop-up menu, Copy to Clipboard -> Create Statement, at this time ** standard table statement ** already exists in the clipboard
- Navicat for MySQL: Create a data source link, double-click to open the database, drop the left table, left click to select the table, find DDL in the upper menu on the right side and click, **Standard form statement** will appear in the right window .

The steps in the above ways are all the steps I tried to install on the mac. If it is other software, other platforms, the general approach is the same. After the table is selected, there will always be a place to display the ** standard form statement **, and you can find it.

## Download and install
1. IntelliJ IDEA official website download [plug-in] (https://plugins.jetbrains.com/plugin/11350-mysql-table-to-jpa-entity)
2. Start IntelliJ IDEA
3. Click Preferences in the main menu bar.
4. Click Plugins on the left
5. Locate the Install plugin from disk in the right window that opens.
6. Find the downloaded jar in the file manager that pops up.

## How to use
1. Copy ** from the client software to create a table statement, similar to [release] (#release)
2. Use shortcut keys in IntelliJ IDEA Ctrl + Shift + X
3. A pop-up window will confirm that the build was successful.
4. Paste in the right place
