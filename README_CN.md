# intellij-mysql-table-to-jpa-entity [![Build Status](https://travis-ci.com/liujingtech/IntelliJ-MySQL-Table-to-JPA-Entity.svg?branch=master)](https://travis-ci.com/liujingtech/IntelliJ-MySQL-Table-to-JPA-Entity) [![codecov](https://codecov.io/gh/liujingtech/IntelliJ-MySQL-Table-to-JPA-Entity/branch/master/graph/badge.svg)](https://codecov.io/gh/liujingtech/IntelliJ-MySQL-Table-to-JPA-Entity)
🎉一个简单易用的 MySQL table 转换到 JPA Entity 的 intelliJ 插件。

这是一个轻量的无需配置的不需界面操作插件，如果你只是想快速地将一个 MySQL Table 转换成 JPA 的 Entity。那么你就应该试试它。

## 下载安装
1. IntelliJ IDEA 官网下载[插件](https://plugins.jetbrains.com/plugin/11350-mysql-table-to-jpa-entity)
2. 启动 IntelliJ IDEA。
3. 主菜单栏中点击 Preferences.
4. 左侧点击 Plugins。
5. 在打开的右侧窗口找到 Install plugin from disk.
6. 在弹出的文件管理器中找到下载完成的 Jar。

## 使用方式
1. 从 MySQL 客户端软件中复制[标准建表语句](#例子)。
2. 在 IntelliJ IDEA 中使用快捷键 Ctrl + Shift + X。__*注意：编辑器光标需要在Java文件内，并且编辑窗口获取了焦点*__
3. 会弹出窗口确认生成成功。
4. 在合适的位置粘贴即可。

## 特性
使用 MySql [标准建表语句](#例子)，生成符合 JPA 要求的 Entity。

- 生成 class 注解 @Entity
- 根据 table name 生成 class 的 @Table(name = "table_name")
- 根据 table 注释生成 class 注释
- 根据 column 注释生成 property 注释
- 根据 column name 生成 property name，转换为小驼峰命名风格
- 根据 column type 生成 property Type，符合[ MySQL 规范](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-type-conversions.html)
- 根据 PRIMARY KEY 生成 @Id 
- 根据 AUTO_INCREMENT 生成 @GeneratedValue(strategy = GenerationType.AUTO) 
- 生成 property 的 Getter/Setter 方法。处理 boolean 类型使用了 is 开头的变量名的方法名称问题，符合[ Java Bean 规范](https://docs.oracle.com/cd/E19798-01/821-1841/bnbqc/index.htm)
- 根据 Column 所声明的 DEFAULT 值，生成无参构造函数并初始化

## 例子
MySQL **标准建表语句**：
```sql
CREATE TABLE `t_building` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `name` varchar(50) NOT NULL COMMENT '名字',
  `sale_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '销售状态 1-在售， 2-待售， 3-售罄',
  `floor_space` bigint(11) DEFAULT '0' COMMENT '占地面积:单位平方分米',
  `card_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发证时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8 COMMENT='楼盘主表';
```
JPA Entity ：
生成后的 Entity [完整代码](/src/test/java/TBuilding.java)

## 标准建表语句
可以在各种 MySQL 客户端找到，常用的几种获取方式：

 - Sequel Pro：进入数据库，左侧点击选择表，右侧上方菜单栏选择 Table Info，右侧下方在 Create syntax 出现**标准建表语句**。
My 
- MySQL Workbench：进入数据库，左侧选择表，右键表名，弹出的菜单中选择，Copy to Clipboard -> Create Statement，此时**标准建表语句**已经存在剪贴板中
- Navicat for MySQL：进入数据库，下拉左侧 Table ，左键选择表，在右侧上方菜单中找到 DDL 并点击，**标准建表语句**会出现在右侧窗口中。

以上几种方式的步骤是在 mac 上的步骤。如果是其他软件、其他平台，操作可能略有不同。在选中表之后，总会有地方显示**标准建表语句**，花点心思找到即可。

## LICENSE

    Copyright (c) Liu Jing.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
