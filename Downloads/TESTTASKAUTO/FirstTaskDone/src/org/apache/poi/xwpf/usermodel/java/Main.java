package org.apache.poi.xwpf.usermodel.java;

import java.io.*;
import java.util.Properties;

import org.apache.poi.xwpf.usermodel.*;

public class Main {

    public static void createDocFile(String fileName) {
        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());

            XWPFDocument d1 = new XWPFDocument();
            XWPFParagraph p1 = d1.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r1 = p1.createRun();
            r1.setText("Расписка");
            r1.setFontFamily("Times New Roman");
            r1.setFontSize(14);

            XWPFParagraph p2 = d1.createParagraph();
            p2.setAlignment(ParagraphAlignment.DISTRIBUTE);
            XWPFRun r2 = p2.createRun();
            r2.setText("Я, Иванов Иван Иванович, паспорт серии 5375 № 153763, выдан ОВД района Басманный УВД ЦАО г. Николаева 12.06.2004 г. " +
                    "получила от Филатова Ярослава Витальевича, паспорт серии 5321 № 124635, выдан ОВД Фили-Давыдково г. Николаева 11.03.2003 г. денежные " +
                    "средства в сумме 400000 (четыреста тысяч) рублей РФ за автомобиль Dodge, идентификационный номер автомобиля X9L21230050011096" +
                    " по договору купли-продажи автомобиля, заключенному сторонами 02 декабря 2017 г.\n");
            r2.setFontFamily("Times New Roman");
            r2.setFontSize(14);

            XWPFParagraph p3 = d1.createParagraph();
            XWPFRun r3 = p3.createRun();
            r3.setText("Оплата произведена в полном размере.");
            r3.setFontFamily("Times New Roman");
            r3.setFontSize(14);

            XWPFParagraph p4 = d1.createParagraph();
            XWPFRun r4 = p4.createRun();
            r4.setText("02.12.2017                                                                        Иванов          Е. В. Иванов");
            r4.setFontFamily("Times New Roman");
            r4.setFontSize(14);
            d1.write(fos);
            fos.close();


            System.out.println(file.getAbsolutePath() + " created successfully!");

        } catch (Exception e) {
            System.out.print(e);
        }
    }
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10; i++) {
            java.util.Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("props.xml"));
            createDocFile(prop.getProperty("first") + i + prop.getProperty("second") + ".docx");
        }
    }
}