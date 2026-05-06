package co.com.bancolombia.consumer;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.http.HttpStatus;
import co.com.bancolombia.model.owner.Owner;
import co.com.bancolombia.model.owner.gateways.OwnerPort;
import co.com.bancolombia.consumer.VerifyOwnerRequest;
import co.com.bancolombia.consumer.VerifyOwnerResponse;
import co.com.bancolombia.exceptions.UserNotOwnerException;
@Service
public class RestConsumer implements OwnerPort // implements Gateway from domain
{
    private final RestClient restClient;

    public RestConsumer(RestClient restClient) {
        this.restClient = restClient;
    }

    public Owner verifyOwner(Integer ownerId) {

        System.out.println("SE HARA LA PETICION");

        VerifyOwnerRequest requestBody = VerifyOwnerRequest.builder().userId(ownerId).build();

        VerifyOwnerResponse response = restClient
                .post()
                .uri("/api/verifyowner/path")
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .onStatus(status -> status == HttpStatus.UNPROCESSABLE_CONTENT, (req, res) -> {
                    //VerifyOwnerResponse error = res.body(new ParameterizedTypeReference<>() {
                    //});

                    System.out.println("HUBO ERROR, USUARIO NO ES OWNER");

                    throw new UserNotOwnerException();
                })
                .body(new ParameterizedTypeReference<VerifyOwnerResponse>() {
                });

        System.out.println();

        Owner owner = Owner.builder()
                .id(response.getData().getId())
                .name(response.getData().getName())
                .lastName(response.getData().getLastName())
                .email(response.getData().getEmail())
                .birthDate(response.getData().getBirthDate())
                .phone(response.getData().getPhone())
                .identificationDocument(response.getData().getIdentificationDocument())
                .build();
        return owner;
    }

    // These methods are an example that illustrates the implementation of
    // RestClient.
    // You should use the methods that you implement from the Gateway from the
    // domain.

    // @CircuitBreaker(name = "testGet"/* , fallbackMethod = "testGetOk" */) // This
    // name should match with settings name
    // in application.yaml
    /*
     * public ObjectResponse testGet() {
     * return restClient
     * .get()
     * .uri("/list-users")
     * .accept(MediaType.APPLICATION_JSON)
     * .headers(headers -> {
     * headers.setContentType(MediaType.APPLICATION_JSON);
     * headers.set("HEADER-EXAMPLE", "example-value");
     * })
     * .retrieve()
     * .body(new ParameterizedTypeReference<>() {
     * });
     * }
     */

    // Possible fallback method
    // public String testGetOk(Exception exception) {
    // return restClient
    // .get()
    // .uri("/test")
    // .accept(MediaType.APPLICATION_JSON)
    // .retrieve()
    // .body(new ParameterizedTypeReference<>() {
    // });
    // }
    /*
     * @CircuitBreaker(name = "testPost") // This name should match with settings
     * name in application.yaml
     * public ObjectResponse testPost() {
     * ObjectRequest requestBody = ObjectRequest.builder()
     * .val1("exampleval1")
     * .val2("exampleval2")
     * .build();
     * 
     * return restClient
     * .post()
     * .uri("/create-user")
     * .contentType(MediaType.APPLICATION_JSON)
     * .body(requestBody)
     * .retrieve()
     * .body(new ParameterizedTypeReference<>() {
     * });
     * }
     */
}
