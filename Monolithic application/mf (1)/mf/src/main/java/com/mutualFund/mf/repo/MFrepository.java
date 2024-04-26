package com.mutualFund.mf.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mutualFund.mf.entity.MFEntity;

public interface MFrepository extends JpaRepository<MFEntity,Long> {
    List<MFEntity> findByCodeIn(List<String> list);
    List<MFEntity> findByShortName(String name);
    List<MFEntity> findByShortNameOrderByRet3Desc(String shortName);
    List<MFEntity> findByShortNameOrderByRet1Desc(String shortName);
    List<MFEntity> findByShortNameOrderByNavDesc(String shortName);
    List<MFEntity> findByNameContainingAndShortNameContaining(String name,String str);
}
