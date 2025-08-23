package com.shiplyt.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

	@NotNull(message = "Pickup latitude is required")
	private Double pickupLat;

	@NotNull(message = "Pickup longitude is required")
	private Double pickupLng;

	@NotNull(message = "Drop latitude is required")
	private Double dropLat;

	@NotNull(message = "Drop longitude is required")
	private Double dropLng;

	@NotBlank(message = "Consignee name is required")
	@Size(max = 100, message = "Consignee name cannot exceed 100 characters")
	private String consigneeName;

	@NotBlank(message = "Consignee phone is required")
	@Size(max = 15, message = "Consignee phone cannot exceed 15 characters")
	private String consigneePhone;

	@NotBlank(message = "Consignee address is required")
	@Size(max = 255, message = "Consignee address cannot exceed 255 characters")
	private String consigneeAddress;

	@Size(max = 500, message = "Special instructions cannot exceed 500 characters")
	private String specialInstructions;
}
