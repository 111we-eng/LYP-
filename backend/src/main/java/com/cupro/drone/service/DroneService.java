package com.cupro.drone.service;

import com.cupro.drone.domain.Drone;
import java.util.List;

public interface DroneService {

    List<Drone> getAllDrones();

    Drone getDroneById(Long id);

    List<Drone> searchDrones(String keyword);

    boolean addDrone(Drone drone);

    boolean updateDrone(Drone drone);

    boolean deleteDrone(Long id);

    Drone generateDroneInfo();
}
