package com.dogpro.dao;

import com.dogpro.domain.model.DogLocation;
import com.dogpro.domain.model.DogLocationExample;
import com.dogpro.domain.model.extend.DoglocationDistance;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import util.PaginationUtil;

public interface DogLocationMapper {
    int countByExample(DogLocationExample example);

    int deleteByExample(DogLocationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DogLocation record);

    int insertSelective(DogLocation record);

    List<DogLocation> selectByExample(DogLocationExample example);

    DogLocation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DogLocation record, @Param("example") DogLocationExample example);

    int updateByExample(@Param("record") DogLocation record, @Param("example") DogLocationExample example);

    int updateByPrimaryKeySelective(DogLocation record);

    int updateByPrimaryKey(DogLocation record);
    
    List<DoglocationDistance> selectByDistance(@Param("latitude") double latitude,@Param("longitude") double longitude,@Param("pagination")PaginationUtil pagination);

    List<DoglocationDistance> selectHotLocation(@Param("latitude") double latitude,@Param("longitude") double longitude,@Param("districts") String districts,@Param("pagination")PaginationUtil pagination);
}