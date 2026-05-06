package co.com.bancolombia.consumer;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.com.bancolombia.consumer.VerifyOwnerResponse;
import java.util.Map;
import co.com.bancolombia.model.owner.Owner;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RestConsumerTest {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static MockWebServer mockWebServer;
    private static RestConsumer restConsumer;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        RestClient restClient = RestClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();

        restConsumer = new RestConsumer(restClient);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("Should successfully execute POST request and return ObjectResponse")
    void verifyOwner() throws Exception {

        Owner owner = Owner.builder()
                .id(57)
                .email("mario@gmail.com")
                .lastName("Diaz")
                .name("Owner")
                .phone("5732151323")
                .birthDate("1997-07-12")
                .identificationDocument("12345678").build();

        VerifyOwnerResponse response = VerifyOwnerResponse.builder()
                .code("OK")
                .data(owner)
                .message("el ususrio es Owner")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(response);

        mockWebServer.enqueue(new MockResponse()
                .setBody(jsonResponse)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(HttpStatus.OK.value()));

        // When
        Owner actualResponse = restConsumer.verifyOwner(1);

        // Then
        assertNotNull(actualResponse);
        assertEquals("mario@gmail.com", actualResponse.getEmail());
        assertEquals("Owner", actualResponse.getName());
    }
}