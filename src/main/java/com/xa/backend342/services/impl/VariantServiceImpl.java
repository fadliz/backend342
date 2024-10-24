package com.xa.backend342.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.backend342.dtos.requests.VariantRequestDto;
import com.xa.backend342.dtos.responses.VariantResponseDto;
import com.xa.backend342.entities.Variant;
import com.xa.backend342.repositories.VariantRepository;
import com.xa.backend342.services.interfaces.VariantService;
import com.xa.backend342.utils.SlugUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VariantServiceImpl implements VariantService {

    @Autowired
    private VariantRepository variantRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VariantResponseDto createVariant(VariantRequestDto variantRequestDto) {
        if (variantRequestDto.getSlug() == null) {
            variantRequestDto.setSlug(SlugUtils.toSlug(variantRequestDto.getName()));
        }
        Variant variant = modelMapper.map(variantRequestDto, Variant.class);
        return modelMapper.map(variantRepository.save(variant), VariantResponseDto.class);
    }
    
    @Override
    public VariantResponseDto getVariantById(Long id) {
        Variant variant = variantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Variant not found"));
        VariantResponseDto variantResponseDto = modelMapper.map(variant, VariantResponseDto.class);
        return variantResponseDto;
    }

    @Override
    public List<VariantResponseDto> getVariants() {
        List<Variant> variants = variantRepository.findAll();
        List<VariantResponseDto> variantResponseDtos = variants.stream()
                .map(variant -> modelMapper.map(variant, VariantResponseDto.class))
                .collect(Collectors.toList());
        return variantResponseDtos;
    }

    @Override
    public VariantResponseDto updateVariant(Long id, VariantRequestDto variantRequestDto) {
        // JPA finds the entity by its ID
        // returns Optional util class
        // because entity by ID might not exists
        // Optional is a class with <T> as attribute
        // <T> can be empty/null, isPresent checks the <T> for it
        Optional<Variant> existingVariantOpt = variantRepository.findById(id);
        if (existingVariantOpt.isPresent()) {
            Variant existingVariant = existingVariantOpt.get();
            if (variantRequestDto.getSlug() == null) {
                variantRequestDto.setSlug(SlugUtils.toSlug(variantRequestDto.getName()));
            }
            if (variantRequestDto.getCreatedBy() == null) {
                variantRequestDto.setCreatedBy(existingVariant.getCreatedBy());
            }
            modelMapper.map(variantRequestDto, existingVariant);
            return modelMapper.map(variantRepository.save(existingVariant), VariantResponseDto.class);
        } else {
            throw new RuntimeException("Variant not found");
        }
    }

    @Override
    public void deleteVariant(Long id) {
        variantRepository.deleteById(id);
    }
    
}
