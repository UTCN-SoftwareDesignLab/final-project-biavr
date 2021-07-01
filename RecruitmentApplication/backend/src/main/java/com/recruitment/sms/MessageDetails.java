package com.recruitment.sms;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MessageDetails {
    private String number;
    private String message;
}
