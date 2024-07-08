package cn.edu.nju.ics.qtosbase.infrastructure.idgenerator.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UuidGeneratorTest {

    @Test
    void generate() {
        assertEquals(32, new UuidGenerator(UuidGenerator.Version.V7).generate().toString().replaceAll("-", "").length());
    }

}