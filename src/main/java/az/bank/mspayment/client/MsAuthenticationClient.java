package az.bank.mspayment.client;

import az.bank.mspayment.model.client.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static az.bank.mspayment.model.client.HttpHeader.X_AUTH_TOKEN;

@Component
@FeignClient(name = "ms-auth", url = "http://localhost:8181/auth")
public interface MsAuthenticationClient {
    @PostMapping("/validate")
    UserInfo validateToken(@RequestHeader(X_AUTH_TOKEN) String token);
}