package org.liujing.generator;

import com.intellij.codeInsight.CodeInsightActionHandler;
import com.intellij.codeInsight.generation.actions.BaseGenerateAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.liujing.generator.convert.java.ClassConvert;
import org.liujing.generator.convert.sql.TableConvert;
import org.liujing.generator.object.java.ClassObject;
import org.liujing.generator.object.sql.TableObject;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class FormatAction extends BaseGenerateAction {
    public FormatAction() {
        super(null);
    }

    public FormatAction(CodeInsightActionHandler handler) {
        super(handler);
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        // 获取系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        // 获取剪贴板中的内容
        Transferable trans = clipboard.getContents(null);

        if (trans != null) {
            // 判断剪贴板中的内容是否支持文本
            if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    // 获取剪贴板中的文本内容
                    String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                    // 生成 POJO 文本
                    TableObject tableObject = TableConvert.convertToTableObject(text);
                    ClassObject classObject = ClassConvert.convertToClassObject(tableObject);
                    String str = new Generator().gen(classObject);
                    // 设置到剪贴板中
                    StringSelection stringSelection = new StringSelection(str);
                    clipboard.setContents(stringSelection, null);
                    // 弹出成功对话窗口
                    Messages.showMessageDialog(event.getProject(), "Successfully generated to the clipboard, paste can be used", "", Messages.getInformationIcon());
                } catch (Exception e) {
                    e.printStackTrace();
                    Messages.showMessageDialog(event.getProject(), "The build failed, please check if the correct Create table SQL is copied.", "", Messages.getInformationIcon());
                }
            }
        }


//        JFrame frame = new JFrame();
//        frame.setContentPane(new Container());
    }

}
