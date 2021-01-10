package com.stay.stay.repository;

import com.stay.stay.domain.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository extends JpaRepository<Stamp, Long> {
}
