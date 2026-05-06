package co.com.bancolombia.consumer;

import lombok.Builder;
import co.com.bancolombia.model.owner.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class VerifyOwnerResponse {

    private String code;
    private Owner data;
    private String message;

}