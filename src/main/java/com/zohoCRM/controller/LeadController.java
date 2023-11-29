package com.zohoCRM.controller;

import com.zohoCRM.entity.Lead;
import com.zohoCRM.payload.LeadDto;
import com.zohoCRM.service.report.ExcelHelperService;
import com.zohoCRM.service.LeadService;
import com.zohoCRM.service.report.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {
    @Autowired
    private LeadService leadService;
    

    @PostMapping
    public ResponseEntity<LeadDto> createLead(@RequestBody LeadDto leadDto){
        System.out.println(leadDto);
        LeadDto dto = leadService.createLead(leadDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id){
        leadService.deleteById(id);
        return new ResponseEntity<>("Lead is deleted with id: "+id, HttpStatus.OK);
    }


    @GetMapping
    public List<LeadDto> getAllLead(){
        List<LeadDto> lead = leadService.getAllLead();

        return lead;

    }

//------------------------------ Excel Report----------------------------------------------------------------------------

    //  http://localhost:8080/api/leads/excelReports
    @GetMapping("/excelReports")
    public ResponseEntity<Resource> getLeadExcelReports(){
        List <Lead> leads = leadService.getLeadExcelPdfReports();
        ByteArrayInputStream leadReports = ExcelHelperService.leadsToExcel(leads);
        String filename = "leads.xlsx";
        InputStreamResource file = new InputStreamResource(leadReports);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

//----------------------- ------CSV File Report--------------------------------------------------------------------------

    //  http://localhost:8080/api/leads/csvDownload
    @GetMapping("/csvDownload")
    public ResponseEntity<Resource> getFile() {
        String filename = "Leads.csv";
        InputStreamResource file = new InputStreamResource(leadService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }

//----------------------- ------PDF File Report-----------------------------------------------------------------------

    //  http://localhost:8080/api/leads/pdfDownload

    @GetMapping(value = "/pdfDownload", produces =
            MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> leadPdfReport()
            throws IOException {
        List<Lead> leadPdfList =  leadService.getLeadExcelPdfReports();

        ByteArrayInputStream bis = PDFGenerator.leadPDFReport(leadPdfList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=leadPdfList.pdf");

        return ResponseEntity.ok().headers(headers).contentType
                        (MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
