package com.aishwarya.ethical.transparency_portal.modules.product.repository;

import com.aishwarya.ethical.transparency_portal.modules.product.model.ScoreBreakdownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreBreakdownRepository extends JpaRepository<ScoreBreakdownEntity, Long> {
}
