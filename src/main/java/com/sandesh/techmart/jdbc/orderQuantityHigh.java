package com.sandesh.techmart.jdbc;

public class orderQuantityHigh extends Exception{
    public orderQuantityHigh(){
        super("Order Less Quantity");
    }
}