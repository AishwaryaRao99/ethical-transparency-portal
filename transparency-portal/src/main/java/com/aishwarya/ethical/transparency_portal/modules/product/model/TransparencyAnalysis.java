package com.aishwarya.ethical.transparency_portal.modules.product.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransparencyAnalysis {
	private List<String> scoreHighReasons;
	private List<String> improvementAreas;
	private ScoreBreakdown scoreBreakdown;
}
