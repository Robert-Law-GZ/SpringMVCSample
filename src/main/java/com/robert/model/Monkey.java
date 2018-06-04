package com.robert.model;

import org.springframework.stereotype.Component;

@Component
public class Monkey {
    private String monkeyName = "MonkeyKing";

    public String toString(){
        return "MonkeyName:" + monkeyName;
    }
}
