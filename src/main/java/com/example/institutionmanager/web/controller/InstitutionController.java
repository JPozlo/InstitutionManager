package com.example.institutionmanager.web.controller;

import com.example.institutionmanager.common.dto.ApiResponse;
import com.example.institutionmanager.common.dto.InstitutionRequestDto;
import com.example.institutionmanager.common.dto.InstitutionResponseDto;
import com.example.institutionmanager.data.model.Institution;
import com.example.institutionmanager.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institution")
public class InstitutionController {
    @Autowired
    InstitutionService institutionService;

    @PostMapping("/create")
    public ResponseEntity<InstitutionResponseDto> createInstitution(@RequestBody InstitutionRequestDto institutionRequestDto){
        InstitutionResponseDto institutionResponseDto = institutionService.createInstitution(institutionRequestDto);
        return new ResponseEntity<>(institutionResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{institutionId}")
    public ResponseEntity<InstitutionResponseDto> updateInstitutionName(@PathVariable Long institutionId, @RequestBody InstitutionRequestDto institutionRequestDto){
        InstitutionResponseDto institutionResponseDto = institutionService.updateInstitutionName(institutionId, institutionRequestDto.getName());
        return new ResponseEntity<>(institutionResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{institutionId}")
    public ResponseEntity<ApiResponse> deleteInstitution(@PathVariable Long institutionId){
        ApiResponse apiResponse = institutionService.deleteInstitution(institutionId);
        return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/filter/")
    public ResponseEntity<List<InstitutionResponseDto>> filterInstitutionsByName(@RequestParam("name") String name){
        List<InstitutionResponseDto> institutionResponseDtoList = institutionService.filterByName(name);
        return new ResponseEntity<>(institutionResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/all/sort/asc")
    public ResponseEntity<List<InstitutionResponseDto>> getAllInstitutionsSortedInAscending(){
        List<InstitutionResponseDto> institutionResponseDtoList = institutionService.getAllSortByNameAscending();
        return new ResponseEntity<>(institutionResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/all/sort/desc")
    public ResponseEntity<List<InstitutionResponseDto>> getAllInstitutionsSortedInDescending(){
        List<InstitutionResponseDto> institutionResponseDtoList = institutionService.getAllSortByNameDescending();
        return new ResponseEntity<>(institutionResponseDtoList, HttpStatus.OK);
    }



}
