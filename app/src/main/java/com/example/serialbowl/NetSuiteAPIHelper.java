package com.example.serialbowl;

public class NetSuiteAPIHelper {
    private String Consumer_Key;
    private String Consumer_Secret;
    private String Account;
    private String Token;
    private String Base_URL;

    public NetSuiteAPIHelper(String Consumer_Key, String Consumer_Secret, String Account, String Token, String Base_URL){
        this.Consumer_Key = Consumer_Key;
        this.Consumer_Secret = Consumer_Secret;
        this.Account = Account;
        this.Token = Token;
        this.Base_URL = Base_URL;
    }

    public String[] queryNS(String recordType, String channel, String location){
        //todo
        return null;
    }

    private String sendGET(String recordType, String channel, String location){
        //todo
        return null;
    }

    private String sendPOST(String SONum){
        //todo
        return null;
    }

    private String[] parseJSONForSO(String JSON){
        //todo
        return null;
    }
}
