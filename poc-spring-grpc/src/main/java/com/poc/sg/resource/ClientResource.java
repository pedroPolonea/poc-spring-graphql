package com.poc.sg.resource;


import com.pb.proto.message.ClientMessage;
import com.pb.proto.service.ClientRequest;
import com.pb.proto.service.ClientServiceGrpc;
import com.poc.sg.domain.entity.Client;
import com.poc.sg.service.ClientService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Objects;

@GrpcService
public class ClientResource extends ClientServiceGrpc.ClientServiceImplBase {

    @Autowired
    private ClientService clientService;

    @Override
    public void getClient(ClientRequest request, StreamObserver<ClientMessage> responseObserver) {
        Client client = clientService.findById(request.getId());

        if (Objects.nonNull(client)) {
            var clientMessage = ClientMessage.newBuilder()
                    .setDocument(client.getDocument())
                    .setId(client.getId())
                    .setBirthDate(client.getBirthDate().toString())
                    .setName(client.getName());

            responseObserver.onNext(clientMessage.build());
            responseObserver.onCompleted();
        }
    }
}
