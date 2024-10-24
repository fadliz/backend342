package com.xa.backend342.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xa.backend342.dtos.requests.VariantRequestDto;
import com.xa.backend342.dtos.responses.VariantResponseDto;
import com.xa.backend342.payloads.ApiResponse;
import com.xa.backend342.services.impl.VariantServiceImpl;

@RestController
@RequestMapping("/api/variant")
@CrossOrigin("http://localhost:9002")
public class VariantRestController {

    @Autowired
    VariantServiceImpl variantService;

    @PostMapping("")
    public ResponseEntity<?> createVariant(@RequestBody VariantRequestDto variantRequestDto) {
        VariantResponseDto variantResponseDto = variantService.createVariant(variantRequestDto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), variantResponseDto));
    }

    @GetMapping("")
    public ResponseEntity<?> getVariants() {
        List<VariantResponseDto> variantResponseDtos = variantService.getVariants();
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), variantResponseDtos));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVariantById(@PathVariable Long id) {
        VariantResponseDto variantResponseDto = variantService.getVariantById(id);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), variantResponseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVariant(@PathVariable Long id,
            @RequestBody VariantRequestDto variantRequestDto) {
        VariantResponseDto variantResponseDto = variantService.updateVariant(id, variantRequestDto);
        return ResponseEntity.ok(ApiResponse.success(HttpStatus.OK.value(), variantResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariant(@PathVariable Long id) {
        variantService.deleteVariant(id);
        return ResponseEntity.noContent().build();
    }

}
