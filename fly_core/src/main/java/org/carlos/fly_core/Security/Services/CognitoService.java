package org.carlos.fly_core.Security.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CognitoService {

    private final CognitoIdentityProviderClient cognitoIdentityProviderClient;

    @Value("${application.security.cognito.clientID}")
    private String clientID;

    @Value("${application.security.cognito.poolID}")
    private String userPoolId;

    public void CognitoSignUp(String email, String password){
        AttributeType userAttrs = AttributeType.builder()
                .name("email")
                .value(email)
                .build();

        List<AttributeType> userAttrsList = new ArrayList<>();
        userAttrsList.add(userAttrs);
        try {
            SignUpRequest signUpRequest = SignUpRequest.builder()
                    .userAttributes(userAttrsList)
                    .username(email)
                    .clientId(clientID)
                    .password(password)
                    .build();

            var result = cognitoIdentityProviderClient.signUp(signUpRequest);


        } catch(CognitoIdentityProviderException e) {
            throw new RuntimeException(e.awsErrorDetails().errorMessage());
        }
    }

    public AuthenticationResultType CognitoSignIn(String email, String password){
        try {
            Map<String,String> authParameters = new HashMap<>();
            authParameters.put("USERNAME", email);
            authParameters.put("PASSWORD", password);

            AdminInitiateAuthRequest authRequest = AdminInitiateAuthRequest.builder()
                    .clientId(clientID)
                    .userPoolId(userPoolId)
                    .authParameters(authParameters)
                    .authFlow(AuthFlowType.ADMIN_USER_PASSWORD_AUTH)
                    .build();

            return cognitoIdentityProviderClient.adminInitiateAuth(authRequest).authenticationResult();


        } catch(CognitoIdentityProviderException e) {
            throw new RuntimeException(e.awsErrorDetails().errorMessage());
        }
    }

    public  String extractClaim(String token, String claim){
        final Map<String, String> claims = getClaims(token);
        return  claims.get(claim);
    }

    public Map<String, String> getClaims(String token) {
        Map<String, String> claims = new HashMap<>();
        try {

            GetUserRequest authRequest = GetUserRequest.builder()
                    .accessToken(token)
                    .build();

            GetUserResponse response = cognitoIdentityProviderClient.getUser(authRequest);

            response.userAttributes().forEach(attributeType -> {
                claims.put(attributeType.name(), attributeType.value());
            });

            return claims;

        } catch(CognitoIdentityProviderException e) {
            throw new RuntimeException(e.awsErrorDetails().errorMessage());
        }

    }

    public String extractUsername(String token) {
        return extractClaim(token, "email");
    }

    public boolean IsTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
    }

}
