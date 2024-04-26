package com.mutualFund.mf.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutualFund.mf.entity.PlotEntity;

public interface PlotRepository extends JpaRepository<PlotEntity,Long> {
    
}
