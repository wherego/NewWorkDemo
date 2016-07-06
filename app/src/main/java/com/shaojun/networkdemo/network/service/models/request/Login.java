package com.shaojun.networkdemo.network.service.models.request;

import java.io.Serializable;

public class Login implements Serializable {

    public String accountName;
    public String password;

    @Override
    public String toString() {
        return super.toString();
    }
}