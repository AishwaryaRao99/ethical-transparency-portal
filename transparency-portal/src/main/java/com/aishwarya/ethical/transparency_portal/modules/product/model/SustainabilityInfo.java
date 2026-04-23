package com.aishwarya.ethical.transparency_portal.modules.product.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * Embeddable class to group sustainability-related product information.
 */
@Embeddable
@Getter
@Setter
public class SustainabilityInfo {
    private String sustainabilityPractices;
    private String environmentalImpact;
    private String ethicalSourcing;
    private String laborPractices;
    private String animalWelfare;
    private String carbonFootprint;
    private String waterUsage;
    private String wasteManagement;
    private String energyConsumption;
    private String packagingSustainability;
    private String endOfLifeDisposal;
    private String corporateSocialResponsibility;
}
