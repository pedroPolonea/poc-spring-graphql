package com.poc.sg.service.impl;


import com.pb.proto.message.ClientMessage;
import com.pb.proto.service.ClientRequest;
import com.pb.proto.service.ClientServiceGrpc;
import com.poc.sg.domain.entity.Client;
import com.poc.sg.service.ClientService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Objects;

@GrpcService
public class ClientServiceGrpcImpl extends ClientServiceGrpc.ClientServiceImplBase {

    private ClientService clientService;

    @Override
    public void getClient(ClientRequest request, StreamObserver<ClientMessage> responseObserver) {
        Client client = clientService.findById(request.getId());

        if (Objects.nonNull(client)) {
            ClientMessage.Builder clientMessage = ClientMessage.newBuilder();
            clientMessage.setDocument(client.getDocument());
            clientMessage.setId(client.getId());
            clientMessage.setBirthDate(client.getBirthDate().toString());
            clientMessage.setName(client.getName());

            responseObserver.onNext(clientMessage.build());
            responseObserver.onCompleted();
        }
    }
}
