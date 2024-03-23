package org.carlos.chatservice.Models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class UserMongo {
    @Id
    private String name;
    private StatusMongo status;
}
