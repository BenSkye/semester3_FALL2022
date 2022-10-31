/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

/**
 *
 * @author super
 */
public class NodeDoubly {

    private int info;
    private NodeDoubly prev, next;

    public NodeDoubly() {
    }

    public NodeDoubly(int info, NodeDoubly prev, NodeDoubly next) {
        this.info = info;
        this.prev = prev;
        this.next = next;
    }

    public NodeDoubly(int info) {
        this.info = info;
        this.prev = null;
        this.next = null;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public NodeDoubly getPrev() {
        return prev;
    }

    public void setPrev(NodeDoubly prev) {
        this.prev = prev;
    }

    public NodeDoubly getNext() {
        return next;
    }

    public void setNext(NodeDoubly next) {
        this.next = next;
    }

}
