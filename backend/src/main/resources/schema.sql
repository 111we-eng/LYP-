-- 无人机表
CREATE TABLE IF NOT EXISTS drone (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    model VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    manufacturer VARCHAR(100),
    weight DECIMAL(10,2),
    max_speed DECIMAL(10,2),
    max_altitude DECIMAL(10,2),
    flight_range DECIMAL(10,2),
    max_flight_time DECIMAL(10,2),
    propulsion_system VARCHAR(50),
    control_system VARCHAR(50),
    communication_protocol VARCHAR(50),
    status VARCHAR(20),
    description TEXT,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
