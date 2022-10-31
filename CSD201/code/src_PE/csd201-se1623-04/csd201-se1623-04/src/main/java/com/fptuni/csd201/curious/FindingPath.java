/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.curious;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 *
 * @author DUNGHUYNH
 */
public class FindingPath {

    Curious explorer = null;

    public Curious getExplorer() {
        return explorer;
    }

    public void setExplorer(Curious explorer) {
        this.explorer = explorer;
    }

    //right wall follower
    public int run() {
        explorer.setSleepTime(300);
        int xStart = explorer.getLocation()[0];
        int yStart = explorer.getLocation()[1];
        boolean flag = false;
        while (!explorer.isWater()) {
            if ((explorer.getLocation()[0] == xStart) && (explorer.getLocation()[1] == yStart) && flag) {
                System.out.println("Water not found");
                break;
            }
            explorer.turnRight();
            if (!explorer.move()) {
                explorer.turnLeft();
                if (!explorer.move()) {
                    explorer.turnLeft();
                } else {
                    flag = true;
                }
            } else {
                flag = true;
            }
        }
        return 1;
    }

    // DFS
    public int run1() {
        explorer.setSleepTime(250);
        Stack<Location> stack = new Stack<>();
        boolean marked[][] = new boolean[100][100];
        for (boolean[] bs : marked) {
            Arrays.fill(bs, false);
        }
        Location currentLoc = new Location(explorer.getLocation()[0], explorer.getLocation()[1]);
        Location prevLoc = currentLoc;
        stack.push(currentLoc);
        while (!stack.isEmpty()) {
            currentLoc = stack.pop();
            if (marked[currentLoc.y][currentLoc.x]) {
                continue;
            }
            if (!prevLoc.isEqual(currentLoc)) {
                while (!isAdjacentAndMove(prevLoc, currentLoc)) {
                    Location traceLoc = prevLoc.trace;
                    if (traceLoc.y < prevLoc.y) {
                        explorer.moveBack();
                    }
                    if (traceLoc.y > prevLoc.y) {
                        explorer.move();
                    }
                    if (traceLoc.x < prevLoc.x) {
                        explorer.turnLeft();
                        explorer.move();
                        explorer.turnRight();
                    }
                    if (traceLoc.x > prevLoc.x) {
                        explorer.turnRight();
                        explorer.move();
                        explorer.turnLeft();
                    }
                    prevLoc = prevLoc.trace;
                }
            }
            marked[currentLoc.y][currentLoc.x] = true;

            if (!((prevLoc.y == currentLoc.y + 1) && (prevLoc.x == currentLoc.x)) && explorer.move()) {
                Location checkLoc = new Location(explorer.getLocation()[0], explorer.getLocation()[1]);
                if (!marked[checkLoc.y][checkLoc.x]) {
                    stack.push(checkLoc);
                    if (explorer.isWater()) {
                        return 1;
                    }
                }
                explorer.moveBack();
            }

            explorer.turnLeft();
            if (!((prevLoc.y == currentLoc.y) && (prevLoc.x == currentLoc.x - 1)) && explorer.move()) {
                Location checkLoc = new Location(explorer.getLocation()[0], explorer.getLocation()[1]);
                if (!marked[checkLoc.y][checkLoc.x]) {
                    stack.push(checkLoc);
                    if (explorer.isWater()) {
                        return 1;
                    }
                }
                explorer.moveBack();
            }
            explorer.turnRight();

            explorer.turnRight();
            if (!((prevLoc.y == currentLoc.y) && (prevLoc.x == currentLoc.x + 1)) && explorer.move()) {
                Location checkLoc = new Location(explorer.getLocation()[0], explorer.getLocation()[1]);
                if (!marked[checkLoc.y][checkLoc.x]) {
                    stack.push(checkLoc);
                    if (explorer.isWater()) {
                        return 1;
                    }
                }
                explorer.moveBack();
            }
            explorer.turnLeft();

            if (!((prevLoc.y == currentLoc.y - 1) && (prevLoc.x == currentLoc.x)) && explorer.moveBack()) {
                Location checkLoc = new Location(explorer.getLocation()[0], explorer.getLocation()[1]);
                if (!marked[checkLoc.y][checkLoc.x]) {
                    stack.push(checkLoc);
                    if (explorer.isWater()) {
                        return 1;
                    }
                }
                explorer.move();
            }

            prevLoc = currentLoc;
        }
        System.out.println("Water not found");
        return 0;
    }

    private boolean isAdjacentAndMove(Location loc1, Location loc2) {
        if ((Math.abs(loc1.x - loc2.x) + Math.abs(loc1.y - loc2.y)) == 1) {
            if (loc2.y < loc1.y) {
                explorer.moveBack();
            }
            if (loc2.y > loc1.y) {
                explorer.move();
            }
            if (loc2.x < loc1.x) {
                explorer.turnLeft();
                explorer.move();
                explorer.turnRight();
            }
            if (loc2.x > loc1.x) {
                explorer.turnRight();
                explorer.move();
                explorer.turnLeft();
            }
            loc2.trace = loc1;
            return true;
        } else {
            return false;
        }
    }
}
