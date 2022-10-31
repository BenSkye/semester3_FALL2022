/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package another;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
class Person {

    String name;
    int age;

    Person(String xName, int xAge) {
        name = xName;
        age = xAge;
    }

    public String toString() //Do not edit the toString() function, because it is used to display required output
    {
        return ("(" + name + "," + age + ")");
    }
}
