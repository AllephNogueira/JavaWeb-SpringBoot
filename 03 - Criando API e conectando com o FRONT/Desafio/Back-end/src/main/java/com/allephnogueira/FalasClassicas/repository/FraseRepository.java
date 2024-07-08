package com.allephnogueira.FalasClassicas.repository;

import com.allephnogueira.FalasClassicas.model.Frases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FraseRepository extends JpaRepository<Frases, Long> {

    @Query("SELECT f FROM Frases f order by function('RANDOM') LIMIT 1")
    Optional<Frases> findRandom();

}
