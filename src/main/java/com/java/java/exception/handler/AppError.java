package com.java.java.exception.handler;

import java.time.LocalDate;

public record AppError(String message, LocalDate date, int status) {
}
