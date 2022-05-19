package com.pix.repository;

import com.pix.model.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChavePixRepository extends JpaRepository<ChavePix, UUID> {
    List<ChavePix> findAllByConta(Integer conta);
}
