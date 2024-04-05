package org.carlos.fly_core.ErrorHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorMessage {
    private String message;
    private Date timestamp;
}
