package com.zohoCRM.service.report;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import com.zohoCRM.entity.Lead;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

public class CSVHelperService {

        public static ByteArrayInputStream leadsToCSV(List<Lead> leads) {
            final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

            try (
                 ByteArrayOutputStream out = new ByteArrayOutputStream();
                 CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);
                 ) {
                for (Lead lead : leads) {
// ------------------Can also use -----> List<String> data = Arrays.asList();  ------------------------------
                    List<? extends Serializable> data = Arrays.asList(
                            lead.getLid(),
                            lead.getFirstName(),
                            lead.getLastName(),
                            lead.getEmail(),
                            lead.getMobile(),
                            lead.getAddress(),
                            lead.getCompany(),
                            lead.getDesignation(),
                            lead.getLeadType(),
                            lead.getNote()
                    );

                    csvPrinter.printRecord(data);
                }

                csvPrinter.flush();
                return new ByteArrayInputStream(out.toByteArray());
            } catch (IOException e) {
                throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
            }
        }
}
