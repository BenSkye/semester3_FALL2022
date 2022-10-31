package com.fptuni.csd201.lib.ObjectStackQueue;

public class ComputerName {

    String Name;
    String CPU;
    String GPU;
    String RAM;
    String ROM;

    public ComputerName(String name, String cpu, String gpu, String ram, String rom) {
        Name = name;
        CPU = cpu;
        GPU = gpu;
        RAM = ram;
        ROM = rom;
    }
    @Override
    public String toString() {
        return "Computer's Name: " + Name + " || CPU : " + CPU + " || GPU : " + GPU + " || RAM : " + RAM + " || ROM : " + ROM;
    }
}
