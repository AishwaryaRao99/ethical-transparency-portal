package com.aishwarya.ethical.transparency_portal.modules.product.repository;

import com.aishwarya.ethical.transparency_portal.modules.product.model.TransparencyAnalysisEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransparencyAnalysisRepository extends JpaRepository<TransparencyAnalysisEntity, Long> {
    Optional<TransparencyAnalysisEntity> findByProductId(Long productId);
}
