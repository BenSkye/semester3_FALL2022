/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.csd201.lib.StringBinaryTree;

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args)
    {
    StringBinaryTree tree = new StringBinaryTree();
    tree.insert("Peach");
    tree.insert("Orange");
    tree.insert("Starfruit.");
   tree.insert("Apple");
   tree.insert("Blueberry");
  // tree.insert("Guava");
  // tree.insert("Brocolli");
  // tree.insert("Apricot");
 //  tree.breadth();
  // tree.preOrder();
   
 

 
    tree.breadth();
    System.out.println(tree.height());
    System.out.println(tree.nodeLevel("Blueberry"));
    System.out.println(tree.isAVLTree());
}
}