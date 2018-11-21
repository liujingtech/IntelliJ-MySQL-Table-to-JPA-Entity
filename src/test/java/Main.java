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

    private static String testDDLStr = "CREATE TABLE `t_building` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',\n" +
            "  `name` varchar(50) NOT NULL COMMENT '名字',\n" +
            "  `sale_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '销售状态 1-在售， 2-待售， 3-售罄',\n" +
            "  `floor_space` bigint(11) DEFAULT '0' COMMENT '占地面积:单位平方分米',\n" +
            "  `card_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发证时间',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8 COMMENT='楼盘主表';";

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
