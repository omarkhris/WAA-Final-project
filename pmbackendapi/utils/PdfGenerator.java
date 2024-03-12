package com.miu.pmtbackendapi.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

@Component
public class PdfGenerator {
    public static byte[] generatePdf(List<String> content) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Font font = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        for (String line : content) {
            Paragraph paragraph = new Paragraph(line, font);
            document.add(paragraph);
        }

        document.close();
        return outputStream.toByteArray();
    }
}