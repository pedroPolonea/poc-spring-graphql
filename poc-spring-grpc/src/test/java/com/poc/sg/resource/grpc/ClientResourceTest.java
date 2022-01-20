package com.poc.sg.resource.grpc;

import com.pb.proto.message.AddressMessage;
import com.pb.proto.message.ClientMessage;
import com.pb.proto.message.ClientRequest;
import com.pb.proto.service.ClientServiceGrpc;
import com.poc.sg.repository.ClientRepository;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.poc.sg.domain.mapper.ClientMapper.entityToClientMessage;
import static com.poc.sg.factory.AddressFactory.createMessage;
import static com.poc.sg.factory.ClientFactory.createClient;
import static com.poc.sg.factory.ClientFactory.createMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientResourceTest {

    @Autowired
    private ClientRepository clientRepository;

    @GrpcClient("inProcess")
    private ClientServiceGrpc.ClientServiceBlockingStub clientServiceBlockingStub;

    @Test
    void shouldCreateClient() {
        final var addressMessage = createMessage();
        final var clientMessageRequest = createMessage(addressMessage);

        final var clientMessageResponse = clientServiceBlockingStub.create(clientMessageRequest);

        assertNotNull(clientMessageResponse.getId());
        assertEquals(clientMessageRequest.getName(), clientMessageResponse.getName());
        assertEquals(clientMessageRequest.getDocument(), clientMessageResponse.getDocument());
        assertEquals(clientMessageRequest.getBirthDate(), clientMessageResponse.getBirthDate());
        assertAddressMessage(clientMessageRequest.getAddressCharge(), clientMessageResponse.getAddressCharge());

    }

    @Test
    void shouldReturnClientById() {
        final var clientMessageResponse = clientServiceBlockingStub.getClient(
                ClientRequest.newBuilder()
                        .setId(1L)
                        .build()
        );
        assertNotNull(clientMessageResponse);
    }

    @Test
    void shouldReturnClientByIdNotFound() {
        StatusRuntimeException statusRuntimeException = assertThrows(StatusRuntimeException.class, () -> {
            clientServiceBlockingStub.getClient(
                    ClientRequest.newBuilder()
                            .setId(0L)
                            .build()
            );
        }, "O recurso 0 não existe");

        assertEquals(Status.NOT_FOUND.getCode(), statusRuntimeException.getStatus().getCode());
        assertEquals("O recurso 0 não existe.", statusRuntimeException.getStatus().getDescription());
    }

    @Test
    void shouldX() {
        final var clientEntity = createClient();
        final var clientSave = clientRepository.save(clientEntity);

        clientSave.setName(clientSave.getName() + " 1");
        clientSave.getAddressCharge().setAddress(clientSave.getAddressCharge().getAddress() + " 1");
        final var clientMessageRequest = entityToClientMessage(clientSave);
        ClientMessage clientMessageResponse = clientServiceBlockingStub.update(clientMessageRequest);

        assertEquals(clientSave.getName(), clientMessageResponse.getName());
        assertEquals(clientSave.getAddressCharge().getAddress(), clientMessageResponse.getAddressCharge().getAddress());

    }

    private void assertAddressMessage(final AddressMessage request, final AddressMessage response) {
        assertNotNull(response.getId());
        assertEquals(request.getCity(), response.getCity());
        assertEquals(request.getNumber(), response.getNumber());
        assertEquals(request.getAddress(), response.getAddress());
        assertEquals(request.getDistrict(), response.getDistrict());
        assertEquals(request.getFederativeUnit(), response.getFederativeUnit());
    }
}
