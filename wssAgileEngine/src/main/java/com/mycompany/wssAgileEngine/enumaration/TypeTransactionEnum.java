/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wssAgileEngine.enumaration;

/**
 *
 * @author Tomas Mazzocchi
 */
public enum TypeTransactionEnum {
    CREDIT("credit"),
    DEBIT("debit");
    
    public final String label;
    
    private TypeTransactionEnum(String label) {
        this.label = label;
    }
}
