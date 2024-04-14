package org.carlos.fly_core.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3service {
    private final S3Client s3Client;

    @Value("${application.s3.bucketName}")
    private String bucketName;

    public String uploadFile(MultipartFile file) throws IOException {
        String fileID;
        ArrayList<String> allowedFormats = new ArrayList<>(
                List.of("image/png", "image/jpeg", "image/jpg")
        );

        if(!allowedFormats.contains(file.getContentType())){
            throw new RuntimeException("Invalid file format");
        }

        fileID = UUID.randomUUID() + "." + file.getContentType().split("/")[1];

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileID)
                .build();

        s3Client.putObject(objectRequest, RequestBody.fromBytes(file.getBytes()));

        return fileID;

    }

    public byte[] getFile( String keyName) {
        try{
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(keyName)
                    .bucket(bucketName)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(objectRequest);

            return objectBytes.asByteArray();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
