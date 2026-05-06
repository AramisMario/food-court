package co.com.bancolombia.api;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {
    private HttpStatus httpStatus;
    //private Map<String, String> headersMap;
    private ApiResponseBody<T> data;

    public ResponseEntity<ApiResponseBody<T>> response() {

        Map<String, String> headersMap = Map.of(
            "Content-Type", "application/json"
        );

        HttpHeaders headers = new HttpHeaders();
        headersMap.forEach(headers::add);

        return ResponseEntity
                .status(this.httpStatus)
                .headers(headers)
                .body(this.data);
    }
}
