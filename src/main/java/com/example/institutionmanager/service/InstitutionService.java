package com.example.institutionmanager.service;


import com.example.institutionmanager.common.dto.ApiResponse;
import com.example.institutionmanager.common.dto.InstitutionRequestDto;
import com.example.institutionmanager.common.dto.InstitutionResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface InstitutionService {
    InstitutionResponseDto createInstitution(InstitutionRequestDto institutionRequestDto);
    InstitutionResponseDto updateInstitutionName(Long institutionId, String name);
    List<InstitutionResponseDto> filterByName(String name);
    ApiResponse deleteInstitution(Long institutionId);
    List<InstitutionResponseDto> getAllSortByNameAscending();
    List<InstitutionResponseDto> getAllSortByNameDescending();
}
