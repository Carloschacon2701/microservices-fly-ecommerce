package org.carlos.apigateway.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;

@Configuration
public class AWSConfig {

    @Bean
    public CognitoIdentityProviderClient cognitoIdentityProviderClient(){
        System.out.println("CognitoIdentityProviderClient");
        return CognitoIdentityProviderClient.builder()
                .region(Region.US_EAST_2)
                .build();
    }

}
