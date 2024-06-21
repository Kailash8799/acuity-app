package com.kailash.acuity.model;

import com.kailash.acuity.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
public class Contact extends BaseEntity {}
