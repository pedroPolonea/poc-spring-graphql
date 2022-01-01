package com.poc.sg.resource;


import com.pb.proto.message.ActionResponse;
import com.pb.proto.message.ClientMessage;
import com.pb.proto.message.ClientRequest;
import com.pb.proto.service.ClientServiceGrpc;
import com.poc.sg.service.ClientService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class ClientResource extends ClientServiceGrpc.ClientServiceImplBase {

    @Autowired
    private ClientService clientService;

    @Override
    public void getClient(final ClientRequest request, final StreamObserver<ClientMessage> responseObserver) {
        super.getClient(request, responseObserver);
    }

    @Override
    public void create(final ClientMessage request, final StreamObserver<ClientMessage> responseObserver) {
        super.create(request, responseObserver);
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
