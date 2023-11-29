package com.zohoCRM.service;

import com.zohoCRM.entity.Lead;
import com.zohoCRM.payload.LeadDto;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface LeadService {

     LeadDto createLead(LeadDto leadDto);

     void deleteById(String id);

     List<LeadDto> getAllLead();
//  -----------------------used for both excel and pdf report-----------------------------------------------------------------------
    List<Lead> getLeadExcelPdfReports();

    public ByteArrayInputStream load();

}
