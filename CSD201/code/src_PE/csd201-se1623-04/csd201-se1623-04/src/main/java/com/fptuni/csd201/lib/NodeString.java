/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

/**
 *
 * @author vuqua
 */
public class NodeString {

    private String info;
    private NodeString next;

    public NodeString() {
    }
    public NodeString(String x){
        this.info=x;
        this.next=null;
    }
    public NodeString(String info, NodeString next) {
        this.info = info;
        this.next = next;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public NodeString getNext() {
        return next;
    }

    public void setNext(NodeString next) {
        this.next = next;
    }

}
