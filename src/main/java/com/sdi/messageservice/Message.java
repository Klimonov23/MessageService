package com.sdi.messageservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {
    String message;
    String key;
    Integer priority;
    Integer delay;
    Boolean hasCallBack;
}

