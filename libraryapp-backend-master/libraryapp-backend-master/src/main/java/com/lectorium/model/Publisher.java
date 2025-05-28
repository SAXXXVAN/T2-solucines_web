package com.lectorium.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="publishers")//, schema="sistemas")
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // para comparar objetos
public class Publisher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idPublisher;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = false, length = 150)//, name="direccion")
	private String address;
	
}
