
# intellij-mysql-table-to-jpa-entity
🎉🎉一个简单易用的 Mysql table 转换到 JPA entity 的 intelliJ 插件🎉🎉

## 特性
使用 MySql 创建表的语句，生成符合 JPA 要求的 Entity。

- 支持 类注解 @Entity
- 根据 Table name 生成类注解 @Table(name = "table_name")
- 根据 Table 注释转换成类注释
- 根据 Column 注释转换成成员注释
- 根据 Column 名生成对应的成员变量名，转换为小驼峰命名风格
- 根据 PRIMARY KEY 生成对应的 @Id 
- 根据 AUTO_INCREMENT 生成对应字段的 @GeneratedValue(strategy = GenerationType.AUTO) 注解
- 生成成员变量对应的 Getter/Setter 方法，同时按照 JavaBean 规范处理了 boolean 类型使用了 is 开头的变量名所对应的 Getter/Setter 方法
- 根据 Column 所声明的 DEFAULT 值，转换到无参构造函数中初始化

## 释例
MySQL 建表语句：
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
JPA Entity 生成：[链接](/src/test/java/TZiboBuilding.java/)

## 下载
https://plugins.jetbrains.com/plugin/11350-mysql-table-to-jpa-entity

