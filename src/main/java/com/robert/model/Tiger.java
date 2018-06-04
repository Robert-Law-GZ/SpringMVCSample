package com.robert.model;

import org.springframework.stereotype.Component;

@Component
public class Tiger {
    private String tigerName="TigerKing";

    public String toString(){
        return "TigerName:"+tigerName;
    }
}
