package com.report.service;

import java.util.List;
import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.report.entity.Student;
import com.report.entity.User;

import jakarta.servlet.http.HttpServletResponse;


@Service
public class ExportService {
    
    @Autowired
    private UserService userService;

    @Autowired 
    private StudentService studentService;

    public void generatePdfByOpenPDF(HttpServletResponse response){
        List<Student> students = studentService.getAllStudents();
        List<User> users = userService.getAllUsers();

        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document,response.getOutputStream());
            document.open();
            document.add(new Paragraph("List of Students"));
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setSpacingBefore(15);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(Color.BLUE);
            cell.setPadding(5);

            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA);
            
            ClassPathResource fontResource = new ClassPathResource("fonts/Myanmar3_2018.ttf");
            var fontStream = fontResource.getInputStream();
            BaseFont unicodeFont = BaseFont.createFont("Myanmar3_2018.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, true, fontStream.readAllBytes(), null);
            Font font = new Font(unicodeFont, 12);
           
            font.setColor(Color.BLACK);

            cell.setPhrase(new Phrase("student id",headerFont));
            table.addCell(cell);
            cell.setPhrase(new Phrase("student name",headerFont));
            table.addCell(cell);
            cell.setPhrase(new Phrase("student description",headerFont));
            table.addCell(cell);

            for(User data: users){
                table.addCell(new Phrase(String.valueOf(data.getId()),headerFont));
                table.addCell(new Phrase(data.getName(),headerFont));
                table.addCell(new Phrase(data.getDescription(),font));
            }


            document.add(table);
            document.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    





}
