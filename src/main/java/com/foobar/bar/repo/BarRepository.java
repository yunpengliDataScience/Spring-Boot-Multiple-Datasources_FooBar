package com.foobar.bar.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foobar.bar.domain.Bar;

@Repository
public interface BarRepository extends JpaRepository<Bar, Long> {

  Optional<Bar> findById(Long id);

}
