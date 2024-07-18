package cn.edu.nju.ics.qtosplatform.infrastructure.config;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.util.IdGenerator;

@SpringBootConfiguration
public class IdGeneratorConfiguration {
    @Bean
    public IdGenerator idGenerator(TimeBasedEpochGenerator timeBasedEpochGenerator) {
        return timeBasedEpochGenerator::generate;
    }

    @Bean
    public TimeBasedEpochGenerator timeBasedEpochGenerator() {
        return Generators.timeBasedEpochGenerator();
    }
}
