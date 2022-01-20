package com.poc.sg.exception;

import io.grpc.Status;

public abstract class DefaultGrpcException extends RuntimeException {
    public DefaultGrpcException(final String message) {
        super(message);
    }

    public abstract Status getStatusCode();
    public abstract String getErrorMessage();
}
