/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.curious;

/**
 *
 * @author super
 */
public class Location {

    public int y;
    public int x;
    public Location trace;

    public Location(int y, int x) {
        this.y = y;
        this.x = x;
        this.trace = null;
    }

    public boolean isEqual(Location loc) {
        if ((this.x == loc.x) && (this.y == loc.y)) {
            return true;
        } else {
            return false;
        }
    }
}
