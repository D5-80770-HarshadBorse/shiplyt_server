package com.shiplyt.model.dto;

import java.time.LocalDateTime;

import com.shiplyt.model.entity.Order.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

	private Long id;
	private Double pickupLat;
	private Double pickupLng;
	private Double dropLat;
	private Double dropLng;
	private String consigneeName;
	private String consigneePhone;
	private String consigneeAddress;
	private String specialInstructions;
	private Status status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}