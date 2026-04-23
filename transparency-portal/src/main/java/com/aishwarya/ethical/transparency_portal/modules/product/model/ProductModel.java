package com.aishwarya.ethical.transparency_portal.modules.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String productDescription;
    private Double price;
    private Integer stockQuantity;
    private String manufacturer;
    private String rawMaterials;
    private String productionProcess;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Embedded
    private SustainabilityInfo sustainabilityInfo;

    @Embedded
    private TransparencyInfo transparencyInfo;

    @Embedded
    private SocialImpactInfo socialImpactInfo;

    @ElementCollection(targetClass = Certification.class)
    @Enumerated(EnumType.STRING)
    private Set<Certification> certifications;

}