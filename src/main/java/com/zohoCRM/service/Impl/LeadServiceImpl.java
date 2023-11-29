package com.zohoCRM.service.Impl;

import com.zohoCRM.entity.Lead;
import com.zohoCRM.exception.LeadExists;
import com.zohoCRM.payload.LeadDto;
import com.zohoCRM.repository.LeadRepository;
import com.zohoCRM.service.report.CSVHelperService;
import com.zohoCRM.service.LeadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LeadServiceImpl implements LeadService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LeadRepository leadRepository;

//----------------------create Lead------------------------------------------------------------------------------------------------------------------------
    @Override
    public LeadDto createLead(LeadDto leadDto){
        System.out.println(leadDto);
        boolean emailExists = leadRepository.existsByEmail(leadDto.getEmail());
        boolean mobileExists = leadRepository.existsByMobile(leadDto.getMobile());

        if(emailExists){
            throw new LeadExists("Email already exist: "+leadDto.getEmail());
        }

        if(mobileExists){
            throw new LeadExists("Mobile already exist: "+leadDto.getMobile());
        }

        Lead lead = mapToLead(leadDto);
        String lid = UUID.randomUUID().toString();
        lead.setLid(lid);

        Lead savedLead = leadRepository.save(lead);

        LeadDto dto = mapToDto(lead);

        return dto;
    }


    LeadDto mapToDto(Lead lead) {
        LeadDto dto = modelMapper.map(lead, LeadDto.class);
        return dto;
    }

    Lead mapToLead(LeadDto leadDto){
        Lead lead = modelMapper.map(leadDto, Lead.class);
        return lead;
    }
//------------------------------------delete By Id----------------------------------------------------------------------------------------
    @Override
    public void deleteById(String id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new LeadExists("Lead not exists with this id:" + id));
        leadRepository.deleteById(id);
    }
//----------------------------------get All Lead---------------------------------------------------------------------------------------------------

    @Override
    public List<LeadDto> getAllLead() {
        List<Lead> lead = leadRepository.findAll();
        List<LeadDto> dto = lead.stream().map(leads->mapToDto(leads)).collect(Collectors.toList());

        return dto;

    }

//---------------------------used for both Excel & Pdf File Report-----------------------------------------------------------------------
    @Override
    public List<Lead> getLeadExcelPdfReports() {
        return leadRepository.findAll();
    }

//----------------------------CSV File Report------------------------------------------------------------------------------------
    public ByteArrayInputStream load() {
        List<Lead> leads = leadRepository.findAll();

        ByteArrayInputStream in = CSVHelperService.leadsToCSV(leads);
        return in;
    }

}

