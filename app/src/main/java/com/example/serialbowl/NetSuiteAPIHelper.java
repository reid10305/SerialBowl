package com.example.serialbowl;

import android.content.res.Resources;

import java.io.Serializable;

public class NetSuiteAPIHelper implements Serializable {
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

    public String getNumOrdersByChannel(String channel, String Location){
        //todo
        switch (Location){
            case ("Carrollton : Stock TX"):
                return "20";
            case ("Chino : Stock CA"):
                return "10";
            default:
                return "10000";
        }
    }

    public String[][] queryNS(String recordType, String channel, String location){
        //todo

        String [][] test = {{"test1", "10", "10.2.2023"},
                            {"test2", "1", "10.1.2023"},
                            {"test3", "3", "09.1.2023"}};
        return test;
    }

    public String[][] getOrderLineItems(String orderNum){
        //todo

        String [][] test = {{"DE-E13-BONE-L/XL", "1"},
                           {"DE-E10-WGY", "3"}};

        return test;
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

    private String sendSuiteQLQuery(String q){
        //todo
        return null;
    }

    public void setConsumer_Key(String newKey){ Consumer_Key = newKey; }
    public void setConsumer_Secret(String newKey){ Consumer_Secret = newKey; }
    public void setAccount(String acct){ Account = acct; }
    public void setToken(String newToken){ Token = newToken; }
    public void setURL(String newURL){ Base_URL = newURL; }




}
