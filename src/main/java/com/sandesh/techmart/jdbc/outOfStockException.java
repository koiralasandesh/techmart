package com.sandesh.techmart.jdbc;

public class outOfStockException extends Exception{
    public outOfStockException(){
        super("Item Out of Stock");
    }
}