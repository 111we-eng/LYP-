package com.cupro.drone.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;

@Configuration
public class DatabaseInitConfig implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseInitConfig.class);

    private final DataSource dataSource;

    @Value("${spring.datasource.url}")
    private String url;

    public DatabaseInitConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initDatabaseDirectory() {
        if (url != null && url.contains("sqlite")) {
            String dbPath = url.replace("jdbc:sqlite:", "");
            File dbFile = new File(dbPath);
            File dbDir = dbFile.getParentFile();
            if (dbDir != null && !dbDir.exists()) {
                dbDir.mkdirs();
                log.info("Created database directory: {}", dbDir.getAbsolutePath());
            }
            log.info("SQLite database will be created at: {}", dbFile.getAbsolutePath());
        }
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Initializing database schema...");
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("schema.sql"));
        populator.setContinueOnError(true);
        populator.execute(dataSource);
        log.info("Database schema initialized successfully.");
    }
}
