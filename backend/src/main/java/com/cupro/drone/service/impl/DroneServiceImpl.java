package com.cupro.drone.service.impl;

import com.cupro.drone.domain.Drone;
import com.cupro.drone.repository.DroneMapper;
import com.cupro.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class DroneServiceImpl implements DroneService {

    @Autowired
    private DroneMapper droneMapper;

    private static final String[] MANUFACTURERS = {"大疆创新", "Parrot", "Autel Robotics", "Skydio", "Holy Stone"};
    private static final String[] MODELS = {"Phantom", "Mavic", "Inspire", "Matrice", "FPV"};
    private static final String[] PROPULSION = {"四旋翼", "六旋翼", "八旋翼", "固定翼", "混合翼"};
    private static final String[] CONTROL = {"GPS", "GLONASS", "北斗", "视觉定位", "惯性导航"};
    private static final String[] PROTOCOL = {"DJI O3", "MAVLink", "DroneCAN", "PWM", "SBUS"};
    private static final String[] STATUS = {"正常", "维护中", "退役", "训练中"};

    @Override
    public List<Drone> getAllDrones() {
        return droneMapper.selectAll();
    }

    @Override
    public Drone getDroneById(Long id) {
        return droneMapper.selectById(id);
    }

    @Override
    public List<Drone> searchDrones(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllDrones();
        }
        return droneMapper.searchByName(keyword);
    }

    @Override
    @Transactional
    public boolean addDrone(Drone drone) {
        drone.setCreateTime(LocalDateTime.now());
        drone.setUpdateTime(LocalDateTime.now());
        return droneMapper.insert(drone) > 0;
    }

    @Override
    @Transactional
    public boolean updateDrone(Drone drone) {
        drone.setUpdateTime(LocalDateTime.now());
        return droneMapper.update(drone) > 0;
    }

    @Override
    @Transactional
    public boolean deleteDrone(Long id) {
        return droneMapper.deleteById(id) > 0;
    }

    @Override
    public Drone generateDroneInfo() {
        Random random = new Random();
        Drone drone = new Drone();

        String manufacturer = MANUFACTURERS[random.nextInt(MANUFACTURERS.length)];
        String model = MODELS[random.nextInt(MODELS.length)] + "-" + (100 + random.nextInt(900));

        drone.setManufacturer(manufacturer);
        drone.setModel(model);
        drone.setName(manufacturer + " " + model);
        drone.setWeight(BigDecimal.valueOf(0.5 + random.nextDouble() * 10));
        drone.setMaxSpeed(BigDecimal.valueOf(20 + random.nextDouble() * 80));
        drone.setMaxAltitude(BigDecimal.valueOf(100 + random.nextDouble() * 900));
        drone.setFlightRange(BigDecimal.valueOf(1 + random.nextDouble() * 19));
        drone.setMaxFlightTime(BigDecimal.valueOf(15 + random.nextDouble() * 45));
        drone.setPropulsionSystem(PROPULSION[random.nextInt(PROPULSION.length)]);
        drone.setControlSystem(CONTROL[random.nextInt(CONTROL.length)]);
        drone.setCommunicationProtocol(PROTOCOL[random.nextInt(PROTOCOL.length)]);
        drone.setStatus(STATUS[random.nextInt(STATUS.length)]);
        drone.setDescription("AI自动生成的" + manufacturer + "品牌" + model + "型号无人机，具有优秀的飞行性能。");
        drone.setCreateTime(LocalDateTime.now());
        drone.setUpdateTime(LocalDateTime.now());

        return drone;
    }
}
