package com.fstg.JobOfferManagement.shared;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ErrorMessage {
    String message;
    Date timestamp;
    Integer code;
    


	public static ErrorMessageBuilder builder() {
        return new ErrorMessageBuilder();
    }

    public static class ErrorMessageBuilder {
        private String message;
        private Date timestamp;
        private Integer code;

        ErrorMessageBuilder() {}

        public ErrorMessageBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ErrorMessageBuilder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErrorMessageBuilder code(Integer code) {
            this.code = code;
            return this;
        }

        public ErrorMessage build() {
            return new ErrorMessage(message, timestamp, code);
        }
    }
    
}
