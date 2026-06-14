package com.cupro.drone.domain;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Drone implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "无人机型号不能为空")
    private String model;

    @NotBlank(message = "无人机名称不能为空")
    private String name;

    private String manufacturer;

    private BigDecimal weight;

    private BigDecimal maxSpeed;

    private BigDecimal maxAltitude;

    private BigDecimal flightRange;

    private BigDecimal maxFlightTime;

    private String propulsionSystem;

    private String controlSystem;

    private String communicationProtocol;

    private String status;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }
    public BigDecimal getWeight() { return weight; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }
    public BigDecimal getMaxSpeed() { return maxSpeed; }
    public void setMaxSpeed(BigDecimal maxSpeed) { this.maxSpeed = maxSpeed; }
    public BigDecimal getMaxAltitude() { return maxAltitude; }
    public void setMaxAltitude(BigDecimal maxAltitude) { this.maxAltitude = maxAltitude; }
    public BigDecimal getFlightRange() { return flightRange; }
    public void setFlightRange(BigDecimal flightRange) { this.flightRange = flightRange; }
    public BigDecimal getMaxFlightTime() { return maxFlightTime; }
    public void setMaxFlightTime(BigDecimal maxFlightTime) { this.maxFlightTime = maxFlightTime; }
    public String getPropulsionSystem() { return propulsionSystem; }
    public void setPropulsionSystem(String propulsionSystem) { this.propulsionSystem = propulsionSystem; }
    public String getControlSystem() { return controlSystem; }
    public void setControlSystem(String controlSystem) { this.controlSystem = controlSystem; }
    public String getCommunicationProtocol() { return communicationProtocol; }
    public void setCommunicationProtocol(String communicationProtocol) { this.communicationProtocol = communicationProtocol; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
}
