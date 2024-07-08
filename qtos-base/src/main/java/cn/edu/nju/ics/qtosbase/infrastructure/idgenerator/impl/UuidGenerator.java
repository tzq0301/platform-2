package cn.edu.nju.ics.qtosbase.infrastructure.idgenerator.impl;

import cn.edu.nju.ics.qtosbase.infrastructure.idgenerator.IdGenerator;
import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class UuidGenerator implements IdGenerator<UUID> {
    private final Version version;

    public UuidGenerator(Version version) {
        this.version = version;
    }

    @Override
    public UUID generate() {
        return switch (version) {
            case V7 -> Generators.timeBasedEpochGenerator().generate();
        };
    }

    public enum Version {
        V7
    }
}
