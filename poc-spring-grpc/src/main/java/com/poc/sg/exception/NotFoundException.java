package com.poc.sg.exception;

import io.grpc.Status;

public class NotFoundException extends DefaultGrpcException {
    private static final String MESSAGE = "O recurso %S não existe.";
    private String info;

    public NotFoundException(final String info) {
        super(String.format(MESSAGE, info));
        this.info = info;
    }

    @Override
    public Status getStatusCode() {
        return Status.NOT_FOUND;
    }

    @Override
    public String getErrorMessage() {
        return String.format(MESSAGE, info);
    }
}
