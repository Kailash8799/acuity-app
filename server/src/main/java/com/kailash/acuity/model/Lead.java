package com.kailash.acuity.model;

import com.kailash.acuity.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "leads")
@Data
@NoArgsConstructor
public class Lead extends BaseEntity {}
