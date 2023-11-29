package com.zohoCRM.exception;

public class LeadExists extends RuntimeException{
    public LeadExists(String message) {
        super(message);
    }
}
