package com.zohoCRM.service.report;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import com.zohoCRM.entity.Lead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {

    private static Logger logger = LoggerFactory.
            getLogger(PDFGenerator.class);

    public static ByteArrayInputStream leadPDFReport
            (List<Lead> leads) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Add Text to PDF file ->
            Font font = FontFactory
                    .getFont(FontFactory.COURIER, 14,BaseColor.BLACK);
            Paragraph para = new Paragraph("Employee Table", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(10);
            // Add PDF Table Header ->
            Stream.of("Lead ID", "First Name", "Last Name", "Email", "Mobile", "Address", "Company", "Designation", "Lead Type", "Note")
                    .forEach(headerTitle ->
                    {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.
                                getFont(FontFactory.HELVETICA_BOLD);
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);
                    });

            for (Lead lead : leads) {

                PdfPCell leadIdCell = new PdfPCell(new Phrase(lead.getLid()));
                leadIdCell.setPaddingLeft(4);
                leadIdCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                leadIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(leadIdCell);

                PdfPCell firstNameCell = new PdfPCell(new Phrase
                        (lead.getFirstName()));
                firstNameCell.setPaddingLeft(4);
                firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(firstNameCell);

                PdfPCell lastNameCell = new PdfPCell(new Phrase
                        (lead.getLastName()));
                lastNameCell.setPaddingRight(4);
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(lastNameCell);

                PdfPCell emailNameCell = new PdfPCell(new Phrase
                        (lead.getEmail()));
                emailNameCell.setPaddingRight(4);
                emailNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                emailNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(emailNameCell);

                PdfPCell mobileNameCell = new PdfPCell(new Phrase
                        (lead.getMobile()));
                mobileNameCell.setPaddingRight(4);
                mobileNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                mobileNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(mobileNameCell);


                PdfPCell addressTypeNameCell = new PdfPCell(new Phrase
                        (lead.getAddress()));
                addressTypeNameCell.setPaddingRight(4);
                addressTypeNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                addressTypeNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(addressTypeNameCell);

                PdfPCell companyTypeNameCell = new PdfPCell(new Phrase
                        (lead.getCompany()));
                companyTypeNameCell.setPaddingRight(4);
                companyTypeNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                companyTypeNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(companyTypeNameCell);

                PdfPCell designationTypeNameCell = new PdfPCell(new Phrase
                        (lead.getDesignation()));
                lastNameCell.setPaddingRight(4);
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(designationTypeNameCell);

                PdfPCell leadTypeNameCell = new PdfPCell(new Phrase
                        (lead.getLeadType()));
                lastNameCell.setPaddingRight(4);
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(leadTypeNameCell);

                PdfPCell noteNameCell = new PdfPCell(new Phrase
                        (lead.getNote()));
                lastNameCell.setPaddingRight(4);
                lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastNameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(noteNameCell);

            }
            document.add(table);

            document.close();
        } catch (DocumentException e) {
            logger.error(e.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}