package com.poc.sg.exception;

import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class HandlerGrpcException {

    @GrpcExceptionHandler(DefaultGrpcException.class)
    public StatusRuntimeException defaultHandlerException(final DefaultGrpcException defaultGrpcException) {
        return defaultGrpcException.getStatusCode()
                .withCause(defaultGrpcException.getCause())
                .withDescription(defaultGrpcException.getErrorMessage())
                .asRuntimeException();
    }
}
