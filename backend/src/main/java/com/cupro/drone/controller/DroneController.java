package com.cupro.drone.controller;

import com.cupro.drone.domain.Drone;
import com.cupro.drone.dto.Result;
import com.cupro.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/drone")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @GetMapping("/list")
    public Result<List<Drone>> listDrones(@RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return Result.ok(droneService.searchDrones(keyword));
        }
        return Result.ok(droneService.getAllDrones());
    }

    @GetMapping("/detail/{id}")
    public Result<Drone> detail(@PathVariable Long id) {
        Drone drone = droneService.getDroneById(id);
        if (drone == null) {
            return Result.fail(404, "无人机不存在");
        }
        return Result.ok(drone);
    }

    @PostMapping("/generate")
    public Result<Drone> generate() {
        return Result.ok(droneService.generateDroneInfo());
    }

    @PostMapping("/save")
    public Result<Void> save(@Valid @RequestBody Drone drone, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.fail(400, bindingResult.getFieldError().getDefaultMessage());
        }

        boolean success;
        if (drone.getId() == null) {
            success = droneService.addDrone(drone);
        } else {
            success = droneService.updateDrone(drone);
        }
        if (success) {
            return Result.ok("操作成功", null);
        }
        return Result.fail("操作失败");
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        if (droneService.deleteDrone(id)) {
            return Result.ok("删除成功", null);
        }
        return Result.fail("删除失败，无人机不存在");
    }
}
