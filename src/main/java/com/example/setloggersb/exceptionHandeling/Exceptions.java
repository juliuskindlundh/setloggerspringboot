package com.example.setloggersb.exceptionHandeling;

public class Exceptions {

    public static class EntityNotFoundException extends RuntimeException{
        public EntityNotFoundException(String errorMessage) {
            super(errorMessage);
        }
    }

    public static class NotImplemented extends RuntimeException {
        public NotImplemented(String errorMessage){super(errorMessage);}
    }

    public static class InputFieldNotFound extends RuntimeException {
        public InputFieldNotFound(String errorMessage){super(errorMessage);}
    }

    public static class NameNotUnique extends RuntimeException {
        public NameNotUnique(String errorMessage) { super(errorMessage);}
    }

    public static class BadRequest extends RuntimeException {
        public BadRequest(String errorMessage){super(errorMessage);}
    }

    public static class CouldNotLogIn extends RuntimeException {
        public CouldNotLogIn(String errorMessage) {super(errorMessage);}
    }
}
