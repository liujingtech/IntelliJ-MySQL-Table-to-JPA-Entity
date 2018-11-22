# intellij-mysql-table-to-jpa-entity
An easy-to-use MySQL table to convert to JPA Entity's intelliJ plugin.

## Features
Create a table statement using MySql to generate a JPA-compliant Entity.

- Support Class Annotations @Entity
- Generate class annotations based on Table name @Table(name = "table_name")
- Convert to class annotation based on Table annotation
- Convert to member comments based on Column comments
- Generate a corresponding member variable name based on the Column name and convert it to a small hump naming style
- Generate a corresponding @Id based on PRIMARY KEY
- Generate @GeneratedValue(strategy = GenerationType.AUTO) annotation for the corresponding field based on AUTO_INCREMENT
- Generate a Getter/Setter method corresponding to the member variable, and handle the Getter/Setter method corresponding to the variable name of the boolean type starting with is in accordance with the JavaBean specification.
- Convert to a no-argument constructor based on the DEFAULT value declared by Column

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