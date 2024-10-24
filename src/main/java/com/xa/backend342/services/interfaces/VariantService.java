package com.xa.backend342.services.interfaces;

import java.util.List;

import com.xa.backend342.entities.Variant;

public interface VariantService {
    Variant createVariant(Variant variant);

    Variant updateVariant(Long id, Variant variant);

    void deleteVariant(Long id);

    Variant getVariant(Long id);

    List<Variant> getVariants();
}
