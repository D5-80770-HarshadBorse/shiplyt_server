package com.shiplyt.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "driver_profile_id")
	private Driver driver;

	@Column(nullable = false)
	private Double pickupLat;

	@Column(nullable = false)
	private Double pickupLng;

	@Column(nullable = false)
	private Double dropLat;

	@Column(nullable = false)
	private Double dropLng;

	@Column(nullable = false, length = 100)
	private String consigneeName;

	@Column(nullable = false, length = 15)
	private String consigneePhone;

	@Column(nullable = false, length = 255)
	private String consigneeAddress;

	@Column(length = 500)
	private String specialInstructions;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(nullable = false, updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;

	@CreationTimestamp
	private LocalDateTime updatedAt;

	public enum Status {
		CREATED, ASSIGNED, PICKED_UP, DELIVERED, CANCELLED
	}
}
