package com.school.coffeemachine.exception;

public class DuplicateRecipeNameException extends RuntimeException {
    public DuplicateRecipeNameException(String message) {
        super(message);
    }
}
