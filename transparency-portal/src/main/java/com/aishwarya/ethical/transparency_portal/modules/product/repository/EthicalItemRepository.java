package com.aishwarya.ethical.transparency_portal.modules.product.repository;

import com.aishwarya.ethical.transparency_portal.modules.product.model.EthicalItemEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EthicalItemRepository extends JpaRepository<EthicalItemEntity, Long> {
    List<EthicalItemEntity> findByProductId(Long productId);
}
