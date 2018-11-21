import org.junit.Test;
import org.liujing.generator.Generator;
import org.liujing.generator.convert.java.ClassConvert;
import org.liujing.generator.convert.sql.TableConvert;
import org.liujing.generator.object.java.ClassObject;
import org.liujing.generator.object.sql.TableObject;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Main {

    private static String testDDLStr = "CREATE TABLE `t_zibo_building` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '楼盘ID',\n" +
            "  `external_id` varchar(50) NOT NULL COMMENT '外部id(局方楼盘ID)',\n" +
            "  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '楼盘名称（局方同步）',\n" +
            "  `reference_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '参考价格类型,  1：均价, 2：单价-起， 3：总价-起， 4：售价待定',\n" +
            "  `reference_price` bigint(11) NOT NULL DEFAULT '0' COMMENT '参考价格，单位分',\n" +
            "  `average_price` bigint(11) NOT NULL DEFAULT '0' COMMENT '均价，根据参考价类型和公式计算得出,默认值为0',\n" +
            "  `property_type` varchar(50) NOT NULL DEFAULT ' ' COMMENT '物业类型，具体有0-住宅、1-别墅、2-商用、3-酒店式公寓，此处需要拼接',\n" +
            "  `developer` varchar(150) NOT NULL DEFAULT '' COMMENT '开发商（局方同步）',\n" +
            "  `district_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '楼盘区域id（局方同步-字典表取值，G端不可修改）',\n" +
            "  `address` varchar(200) NOT NULL DEFAULT '' COMMENT '楼盘地址（局方同步，第一次同步后，由我们自己系统维护）',\n" +
            "  `latitude_longitude` varchar(100) DEFAULT '' COMMENT '经纬度,逗号分开',\n" +
            "  `sale_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '销售状态 1-在售， 2-待售， 3-售罄',\n" +
            "  `building_open_time` varchar(100) DEFAULT '' COMMENT '开盘时间',\n" +
            "  `delivery_time` varchar(100) DEFAULT '' COMMENT '交房时间',\n" +
            "  `sale_office_mobile` varchar(50) DEFAULT NULL COMMENT '售楼电话',\n" +
            "  `sale_office_address` varchar(100) DEFAULT NULL COMMENT '售楼地址',\n" +
            "  `building_type` varchar(50) DEFAULT '' COMMENT '建筑类型 0-高层、1-小高层、2-多层，此处需要拼接',\n" +
            "  `property_year_limit` varchar(250) NOT NULL DEFAULT '' COMMENT '产权年限,以json格式存储',\n" +
            "  `volumetric_rate` varchar(250) NOT NULL DEFAULT '' COMMENT '容积率,以json格式存储',\n" +
            "  `floor_space` bigint(11) DEFAULT '0' COMMENT '占地面积:单位平方分米',\n" +
            "  `building_space` bigint(11) DEFAULT '0' COMMENT '建筑面积:单位平方分米(局方同步)',\n" +
            "  `card_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发证时间(局方同步)',\n" +
            "  `gerrning_rate` int(11) DEFAULT '0' COMMENT '绿化率(保存时乘以1000 显示时除以1000)',\n" +
            "  `project_process` tinyint(4) DEFAULT '0' COMMENT '工程进度  0-在建中，待补充',\n" +
            "  `property_company` varchar(100) DEFAULT '' COMMENT '物业公司',\n" +
            "  `car_place_num` int(11) DEFAULT '0' COMMENT '车位数',\n" +
            "  `car_place_rate` varchar(50) DEFAULT '' COMMENT '车位率：例如：1：2',\n" +
            "  `tags` varchar(100) NOT NULL DEFAULT '' COMMENT '楼盘标签(多选，逗号隔开)',\n" +
            "  `building_sort` int(11) DEFAULT '999' COMMENT '排序 999-普通排序，1,2,....10(数字越小越靠前)',\n" +
            "  `up_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：0-下架 1-上架，',\n" +
            "  `click_count` int(11) DEFAULT '0' COMMENT '楼盘点击量(点击量先记录redis,根据规则更新)',\n" +
            "  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0-否  1-是',\n" +
            "  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
            "  `create_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',\n" +
            "  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
            "  `update_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '最后修改人',\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE KEY `external_id` (`external_id`),\n" +
            "  UNIQUE KEY `idx_building_name_district` (`name`,`district_id`,`is_deleted`)\n" +
            "  KEY `refresh_index_1` (`house_id`,`remain_refresh_count`,`active`,`refresh_type`) USING BTREE,\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='楼盘主表';";

    @Test
    public void testGen() {
        TableObject tableObject = TableConvert.convertToTableObject(testDDLStr);
        ClassObject classObject = ClassConvert.convertToClassObject(tableObject);
        String gen = new Generator().gen(classObject);
        System.out.println(gen);

        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(gen);
        clipboard.setContents(stringSelection, null);
    }

}
