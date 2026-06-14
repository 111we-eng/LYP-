package com.cupro.drone.repository;

import com.cupro.drone.domain.Drone;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DroneMapper {

    List<Drone> selectAll();

    Drone selectById(@Param("id") Long id);

    List<Drone> searchByName(@Param("name") String name);

    int insert(Drone drone);

    int update(Drone drone);

    int deleteById(@Param("id") Long id);

    int deleteAll();
}
