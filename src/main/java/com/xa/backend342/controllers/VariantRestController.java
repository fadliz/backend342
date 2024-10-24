package com.xa.backend342.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
import com.xa.backend342.entities.Variant;
import com.xa.backend342.services.impl.VariantServiceImpl;
import com.xa.backend342.utils.SlugUtils;

@RestController
@RequestMapping("/api/variant")
@CrossOrigin("http://localhost:9002")
public class VariantRestController {

    @Autowired
    VariantServiceImpl variantService;

    @GetMapping("")
    public ResponseEntity<?> getVariants() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            List<Variant> variants = variantService.getVariants();
            List<VariantResponseDto> variantResponseDtos = variants.stream()
                    .map(variant -> modelMapper.map(variant, VariantResponseDto.class))
                    .collect(Collectors.toList());
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", variantResponseDtos);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVariantById(@PathVariable Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            Variant variant = variantService.getVariant(id);
            VariantResponseDto variantResponseDto = modelMapper.map(variant, VariantResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", variantResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVariant(@PathVariable Long id,
            @RequestBody VariantRequestDto variantRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        if (variantRequestDto.getSlug() == null) {
            variantRequestDto.setSlug(SlugUtils.toSlug(variantRequestDto.getName()));
        }
        try {
            Variant variant = modelMapper.map(variantRequestDto, Variant.class);
            Variant updatedVariant = variantService.updateVariant(id, variant);
            VariantResponseDto variantResponseDto = modelMapper.map(updatedVariant, VariantResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", variantResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariant(@PathVariable Long id) {
        variantService.deleteVariant(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<?> saveVariant(@RequestBody VariantRequestDto variantRequestDto) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        if (variantRequestDto.getSlug() == null) {
            variantRequestDto.setSlug(SlugUtils.toSlug(variantRequestDto.getName()));
        }
        try {
            Variant variant = modelMapper.map(variantRequestDto, Variant.class);
            Variant createdVariant = variantService.createVariant(variant);
            VariantResponseDto variantResponseDto = modelMapper.map(createdVariant, VariantResponseDto.class);
            resultMap.put("status", 200);
            resultMap.put("message", "success");
            resultMap.put("data", variantResponseDto);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "success");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
