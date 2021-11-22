package com.example.institutionmanager.service;

import com.example.institutionmanager.common.dto.ApiResponse;
import com.example.institutionmanager.common.dto.CourseResponseDto;
import com.example.institutionmanager.common.dto.InstitutionRequestDto;
import com.example.institutionmanager.common.dto.InstitutionResponseDto;
import com.example.institutionmanager.common.exception.NameAlreadyTakenException;
import com.example.institutionmanager.common.exception.ResourceNotFoundException;
import com.example.institutionmanager.data.model.Course;
import com.example.institutionmanager.data.model.Institution;
import com.example.institutionmanager.data.repository.CourseRepository;
import com.example.institutionmanager.data.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl implements InstitutionService{
    @Autowired
    InstitutionRepository institutionRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public InstitutionResponseDto createInstitution(InstitutionRequestDto institutionRequestDto) {
        boolean nameExists = institutionRepository.findAll().stream().anyMatch(institution -> institution.getName().equals(institutionRequestDto.getName()));
        if(nameExists){
            throw new NameAlreadyTakenException("Institution with the same name already exists");
        } else{
            Institution institution = new Institution();
            institution.setName(institutionRequestDto.getName());
            Institution savedInstitution = institutionRepository.save(institution);
            return new InstitutionResponseDto(savedInstitution.getId(), savedInstitution.getName());
        }
    }

    @Override
    public InstitutionResponseDto updateInstitutionName(Long institutionId, String name) {
        Optional<Institution> optionalInstitution = institutionRepository.findById(institutionId);
        if(optionalInstitution.isPresent()){
            boolean nameExists = institutionRepository.findAll().stream().anyMatch(institution -> institution.getName().equals(name));
            if(nameExists){
                throw new NameAlreadyTakenException("Institution with the same name already exists");
            } else{
                optionalInstitution.get().setName(name);
                Institution updatedInstitution = institutionRepository.save(optionalInstitution.get());
                return new InstitutionResponseDto(updatedInstitution.getId(), updatedInstitution.getName());
            }
        } else{
            throw new ResourceNotFoundException("Institution with the id of " + institutionId + " not found");
        }
    }

    @Override
    public List<InstitutionResponseDto> filterByName(String name) {
        List<InstitutionResponseDto> institutionResponseDtoList = new ArrayList<>();
        institutionRepository.findByName(name).forEach(institution -> {
            InstitutionResponseDto institutionResponseDto = new InstitutionResponseDto(institution.getId(), institution.getName());
            institutionResponseDtoList.add(institutionResponseDto);
        });
        return institutionResponseDtoList;
    }

    @Override
    public ApiResponse deleteInstitution(Long institutionId) {
        Optional<Institution> institution  = institutionRepository.findById(institutionId);
        if(institution.isPresent()){
            institutionRepository.delete(institution.get());
            return new ApiResponse("Institution successfully deleted");
        } else{
            throw new ResourceNotFoundException("Institution with the id of " + institutionId + " not found");
        }
    }

    @Override
    public List<InstitutionResponseDto> getAllSortByNameAscending() {
        List<Institution> insitutions = institutionRepository.findAllSortByNameAscending();
        if(insitutions.isEmpty()){
            throw new ResourceNotFoundException("There are no institutions available yet");
        } else{
            List<InstitutionResponseDto> institutionResponseDtoList = new ArrayList<>();
            insitutions.forEach(institution -> {
                InstitutionResponseDto institutionResponseDto = new InstitutionResponseDto(institution.getId(), institution.getName());
                institutionResponseDtoList.add(institutionResponseDto);
            });
            return institutionResponseDtoList;
        }
    }

    @Override
    public List<InstitutionResponseDto> getAllSortByNameDescending() {
        List<Institution> insitutions = institutionRepository.findAllSortByNameDescending();
        if(insitutions.isEmpty()){
            throw new ResourceNotFoundException("There are no institutions available yet");
        } else{
            List<InstitutionResponseDto> institutionResponseDtoList = new ArrayList<>();
            insitutions.forEach(institution -> {
                InstitutionResponseDto institutionResponseDto = new InstitutionResponseDto(institution.getId(), institution.getName());
                institutionResponseDtoList.add(institutionResponseDto);
            });
            return institutionResponseDtoList;
        }
    }
}
