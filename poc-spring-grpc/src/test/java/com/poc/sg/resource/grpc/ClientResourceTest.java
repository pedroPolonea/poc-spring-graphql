package com.poc.sg.resource.grpc;

import com.pb.proto.message.AddressMessage;
import com.pb.proto.message.ClientRequest;
import com.pb.proto.service.ClientServiceGrpc;
import com.poc.sg.factory.ClientFactory;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.poc.sg.factory.AddressFactory.createMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientResourceTest {

    @GrpcClient("inProcess")
    private ClientServiceGrpc.ClientServiceBlockingStub clientServiceBlockingStub;

    @Test
    void shouldCreateClient() {
        final var addressMessage = createMessage();
        final var clientMessageRequest = ClientFactory.createMessage(addressMessage);

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

    private void assertAddressMessage(final AddressMessage request, final AddressMessage response) {
        assertNotNull(response.getId());
        assertEquals(request.getCity(), response.getCity());
        assertEquals(request.getNumber(), response.getNumber());
        assertEquals(request.getAddress(), response.getAddress());
        assertEquals(request.getDistrict(), response.getDistrict());
        assertEquals(request.getFederativeUnit(), response.getFederativeUnit());
    }
}
