package com.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFontFactory.EmbeddingStrategy;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.report.entity.Student;
import com.report.entity.User;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ExportByITextService {

    @Autowired
    private UserService userService;

    @Autowired 
    private StudentService studentService;
    

    public void generatePdfByItext(HttpServletResponse response){
        List<Student> students = studentService.getAllStudents();
        List<User> users = userService.getAllUsers();
        try {
            PdfWriter writer = new PdfWriter(response.getOutputStream());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            PdfFont font = PdfFontFactory.createFont("src/main/resources/fonts/NotoSansMyanmar-Regular.ttf","Identity-H",EmbeddingStrategy.FORCE_EMBEDDED, true);
            document.add(new Paragraph("မင်္ဂလာပါ").setFont(font));
            document.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
