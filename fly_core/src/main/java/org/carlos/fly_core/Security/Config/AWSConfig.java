package org.carlos.fly_core.Security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSConfig {

    @Bean
    public CognitoIdentityProviderClient cognitoIdentityProviderClient(){
        System.out.println("CognitoIdentityProviderClient");
        return CognitoIdentityProviderClient.builder()
                .region(Region.US_EAST_2)
                .build();
    }

    @Bean
    public S3Client s3Client(){
        return S3Client.builder()
                .region(Region.US_EAST_2)
                .build();
    }
}
