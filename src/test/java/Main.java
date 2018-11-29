import org.junit.Assert;
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
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Primary key ID',\n" +
            "  `name` varchar(50) NOT NULL COMMENT 'name',\n" +
            "  `sale_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Sales status 1-on sale, 2-for sale, 3-sales',\n" +
            "  `floor_space` bigint(11) DEFAULT '0' COMMENT 'Site: Units of square decimeter',\n" +
            "  `card_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'certification time',\n" +
            "  PRIMARY KEY (`id`)\n" +
            "ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8 COMMENT='Property Master';";

    @Test
    public void testGen() {
        TableObject tableObject = TableConvert.convertToTableObject(testDDLStr);
        ClassObject classObject = ClassConvert.convertToClassObject(tableObject);
        String gen = new Generator().formatPOJOString(classObject);
        Assert.assertNotNull("最后输出不能为空", gen);

        try {
            // 获取系统剪贴板
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection = new StringSelection(gen);
            clipboard.setContents(stringSelection, null);
        } catch (HeadlessException e) {
            //在 travis-ci 系统上无法获取剪贴板
            e.printStackTrace();
        }
    }

}
