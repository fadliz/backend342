package com.xa.backend342.services.interfaces;

import java.util.List;

import com.xa.backend342.dtos.requests.VariantRequestDto;
import com.xa.backend342.dtos.responses.VariantResponseDto;


public interface VariantService {
    VariantResponseDto createVariant(VariantRequestDto variantRequestDto);

    VariantResponseDto updateVariant(Long id, VariantRequestDto variantRequestDto);

    void deleteVariant(Long id);

    VariantResponseDto getVariantById(Long id);

    List<VariantResponseDto> getVariants();
}
