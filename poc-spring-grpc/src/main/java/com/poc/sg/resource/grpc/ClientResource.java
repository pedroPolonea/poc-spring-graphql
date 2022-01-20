package com.poc.sg.resource.grpc;


import com.pb.proto.message.ActionResponse;
import com.pb.proto.message.ClientMessage;
import com.pb.proto.message.ClientMessageList;
import com.pb.proto.message.ClientRequest;
import com.pb.proto.message.EmptyRequest;
import com.pb.proto.service.ClientServiceGrpc;
import com.poc.sg.service.ClientService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import static com.poc.sg.domain.mapper.ClientMapper.clientMessageToEntity;
import static com.poc.sg.domain.mapper.ClientMapper.entityListToClientMessageList;
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
        log.info("M=update, I=Inicio, request={}", request);
        final var client = clientService.update(clientMessageToEntity(request));

        responseObserver.onNext(entityToClientMessage(client));
        responseObserver.onCompleted();
    }

    @Override
    public void delete(final ClientRequest request, final StreamObserver<ActionResponse> responseObserver) {
        log.info("M=delete, I=Inicio, request={}", request);
        clientService.delete(request.getId());

        responseObserver.onNext(
                ActionResponse.newBuilder()
                .setId(request.getId())
                        .setCode("200")
                        .setMessage("Cliente deletado com sucesso!")
                        .build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void getClients(final EmptyRequest request, final StreamObserver<ClientMessageList> responseObserver) {
        log.info("M=getClients, I=Inicio");
        responseObserver.onNext(entityListToClientMessageList(clientService.getAll()));
        responseObserver.onCompleted();
    }

    @Override
    public void getClientFilter(final ClientMessage request, final StreamObserver<ClientMessageList> responseObserver) {
        log.info("M=getClientFilter, I=Inicio, request={}", request);
        responseObserver.onNext(entityListToClientMessageList(clientService.getAll(request)));
        responseObserver.onCompleted();
    }
}
