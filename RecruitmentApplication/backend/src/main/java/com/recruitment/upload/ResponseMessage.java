package com.recruitment.upload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Access;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
    private String message;
}