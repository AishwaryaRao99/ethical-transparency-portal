package com.aishwarya.ethical.transparency_portal.modules.product.repository;

import com.aishwarya.ethical.transparency_portal.modules.product.model.IngredientItemEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientItemRepository extends JpaRepository<IngredientItemEntity, Long> {
    List<IngredientItemEntity> findByProductId(Long productId);
}
