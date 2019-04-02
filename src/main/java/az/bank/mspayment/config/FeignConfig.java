package az.bank.mspayment.config;


import az.bank.mspayment.client.MsAccountClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {
        MsAccountClient.class})
public class FeignConfig {
}