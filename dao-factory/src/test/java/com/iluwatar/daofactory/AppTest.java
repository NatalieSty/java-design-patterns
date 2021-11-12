package com.iluwatar.daofactory;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AppTest {

    @Test
    void shouldExecuteApplicationWithoutException() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}
