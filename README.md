

# intellij-mysql-table-to-jpa-entity
🎉一个简单易用的 MySQL table 转换到 JPA Entity 的 intelliJ 插件。

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
JPA Entity 生成：
[根据以上语句生成后的 Entity 完整代码链接](/blob/master/src/test/java/TZiboBuilding.java/)

##标准建表语句
可以在各种MySQL客户端找到，以下是常用的几种客户端获取方式

 - Sequel Pro：进入数据库，左侧点击选择表，右侧上方菜单栏选择 Table Info，右侧下方在 Create syntax 出现**标准建表语句**。
My 
- MySQL Workbench：进入数据库，左侧选择表，右键表名，弹出的菜单中选择，Copy to Clipboard -> Create Statement，此时**标准建表语句**已经存在剪贴板中
- Navicat for MySQL：建立数据源链接，双击打开数据库，下拉左侧 Table ，左键选择表，在右侧上方菜单中找到 DDL 并点击，**标准建表语句**会出现在右侧窗口中。

以上几种方式的步骤都是我在 mac 上装好的试出来步骤。如果是其他软件、其他平台，大致方式是一样的。在选中表之后，总会有地方显示**标准建表语句**，花点心思找到即可。

## 下载安装
1. IntelliJ IDEA官网下载[插件](https://plugins.jetbrains.com/plugin/11350-mysql-table-to-jpa-entity)
2. 启动 IntelliJ IDEA
3. 主菜单栏中点击 Preferences.
4. 左侧点击 Plugins
5. 在打开的右侧窗口找到 Install plugin from disk.
6. 在弹出的文件管理器中找到下载完成的 Jar

## 使用方式
1. 从客户端软件中**复制**创建表语句，类似[释例](#释例)
2. 在 IntelliJ IDEA 中使用快捷键 Ctrl + Shift + X
3. 会弹出窗口确认生成成功
4. 在合适的位置粘贴即可
