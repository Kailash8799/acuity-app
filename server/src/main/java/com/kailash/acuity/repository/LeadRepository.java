package com.kailash.acuity.repository;

import com.kailash.acuity.model.Lead;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, UUID> {}
