package com.recruitment.application.report;

import com.recruitment.application.dto.ApplicationDTO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PdfSummaryService {
    public String export(ApplicationDTO app) throws IOException {
        String path = "D:\\C.S. UTCN\\3rd year\\SD\\final-project-biavr\\RecruitmentApplication\\backend\\pdf_reports\\";

        PDDocument pdf = new PDDocument();
        PDPage page = new PDPage();
        pdf.addPage(page);
        PDFont font = PDType1Font.HELVETICA_BOLD;
        PDPageContentStream contentStream = new PDPageContentStream(pdf,page);
        contentStream.beginText();
        contentStream.setFont(font,20);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, 700);
        contentStream.showText("Application");
        contentStream.newLine();
        contentStream.newLine();
        contentStream.newLine();
        contentStream.setFont(font,16);
        contentStream.showText("Job: "+app.getJob().getName());
        contentStream.setFont(font,14);
        contentStream.newLine();
        contentStream.showText("First name: "+app.getFirstName());
        contentStream.newLine();
        contentStream.showText("Last name: "+app.getLastName());
        contentStream.newLine();
        contentStream.showText("Phone: "+app.getPhoneNumber());
        contentStream.newLine();
        contentStream.showText("Email: "+app.getEmail());
        contentStream.newLine();
        contentStream.showText("Address: "+app.getAddress());
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText("Motivation message: ");
        contentStream.newLine();
        contentStream.showText(app.getMessage());

        contentStream.endText();
        contentStream.close();
        String fileName = "Application_" + app.getJob().getName() + "_" + app.getFirstName() + "_" + app.getLastName() + ".pdf";
        pdf.save(path + fileName);

        return "Pdf report successfully generated.";
    }
}
