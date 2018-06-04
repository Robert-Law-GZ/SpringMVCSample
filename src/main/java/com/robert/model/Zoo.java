package com.robert.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Zoo {
    @Autowired
    private Tiger tiger;
    @Autowired
    private Monkey monkey;

    public String toString(){
        return tiger + "\n" + monkey;
    }
}
