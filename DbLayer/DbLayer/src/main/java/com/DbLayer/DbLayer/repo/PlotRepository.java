package com.DbLayer.DbLayer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DbLayer.DbLayer.entity.PlotEntity;



public interface PlotRepository extends JpaRepository<PlotEntity,Long> {
    
}
