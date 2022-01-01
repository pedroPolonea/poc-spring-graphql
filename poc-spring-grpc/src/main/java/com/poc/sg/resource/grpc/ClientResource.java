package com.poc.sg.resource.grpc;


import com.pb.proto.message.ActionResponse;
import com.pb.proto.message.ClientMessage;
import com.pb.proto.message.ClientRequest;
import com.pb.proto.service.ClientServiceGrpc;
import com.poc.sg.service.ClientService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import static com.poc.sg.domain.mapper.ClientMapper.clientMessageToEntity;
import static com.poc.sg.domain.mapper.ClientMapper.entityToClientMessage;

@Slf4j
@GrpcService
public class ClientResource extends ClientServiceGrpc.ClientServiceImplBase {

    @Autowired
    private ClientService clientService;

    @Override
    public void getClient(final ClientRequest request, final StreamObserver<ClientMessage> responseObserver) {
        log.info("M=getClient, I=Inicio, request={}", request);
        final var client = clientService.findById(request.getId());

        responseObserver.onNext(entityToClientMessage(client));
        responseObserver.onCompleted();
    }

    @Override
    public void create(final ClientMessage request, final StreamObserver<ClientMessage> responseObserver) {
        log.info("M=create, I=Inicio, request={}", request);
        final var client = clientService.save(clientMessageToEntity(request));

        responseObserver.onNext(entityToClientMessage(client));
        responseObserver.onCompleted();
    }

    @Override
    public void update(final ClientMessage request, final StreamObserver<ClientMessage> responseObserver) {
        super.update(request, responseObserver);
    }

    @Override
    public void delete(final ClientRequest request, final StreamObserver<ActionResponse> responseObserver) {
        super.delete(request, responseObserver);
    }
}
