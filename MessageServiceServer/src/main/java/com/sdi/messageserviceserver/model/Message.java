package com.sdi.messageserviceserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {
    private String message;
    private String key;
    private Integer priority;
    private Integer delay;
    private Boolean hasCallBack;
}

