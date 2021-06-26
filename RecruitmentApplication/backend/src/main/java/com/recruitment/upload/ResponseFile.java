package com.recruitment.upload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;
}
