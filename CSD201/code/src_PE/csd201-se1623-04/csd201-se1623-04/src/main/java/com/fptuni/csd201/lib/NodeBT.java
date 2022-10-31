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
public class NodeBT {
    private int info;
    private NodeBT left, right;

    public NodeBT(int info, NodeBT left, NodeBT right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
    
    public NodeBT(int info) {
        this(info,null,null);
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public NodeBT getLeft() {
        return left;
    }

    public void setLeft(NodeBT left) {
        this.left = left;
    }

    public NodeBT getRight() {
        return right;
    }

    public void setRight(NodeBT right) {
        this.right = right;
    }
    
}
