package com.poc.sg.exception;

import io.grpc.Status;

public class AlreadyExistsException extends DefaultGrpcException {
    private static final String MESSAGE = "O recurso %S já existe.";
    private String message;


    public AlreadyExistsException(final String message) {
        super(String.format(MESSAGE, message));
        this.message = message;
    }


    @Override
    public Status getStatusCode() {
        return Status.ALREADY_EXISTS;
    }

    @Override
    public String getErrorMessage() {
        return String.format(MESSAGE, message);
    }
}
