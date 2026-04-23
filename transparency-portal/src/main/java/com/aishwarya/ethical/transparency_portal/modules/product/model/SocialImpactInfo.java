package com.aishwarya.ethical.transparency_portal.modules.product.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class SocialImpactInfo {
    private String socialImpact;
    private String consumerFeedback;
    private String thirdPartyAudits;
    private String traceability;
    private String ethicalCertifications;
    private String socialMediaPresence;
    private String consumerEducation;
    private String communityEngagement;
}
