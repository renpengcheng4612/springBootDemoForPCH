package com.pc.sz.idworker;

public class InvalidSystemClock extends RuntimeException {
    public InvalidSystemClock(String message) {
        super(message);
    }
}
