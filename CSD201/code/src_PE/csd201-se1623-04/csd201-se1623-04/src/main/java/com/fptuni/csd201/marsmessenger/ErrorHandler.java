/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.csd201.marsmessenger;

import com.fptuni.csd201.object.MessagePackage;
import com.fptuni.csd201.object.Wrapped;
import java.util.ArrayList;

public class ErrorHandler {

    int index;
    int size;
    ArrayList<MessagePackage> first = new ArrayList<MessagePackage>();
    ArrayList<MessagePackage> last = new ArrayList<MessagePackage>();

    public ErrorHandler(int index, int size) {
        this.index = index;
        this.size = size;
    }
    public ArrayList<Wrapped> toWrapped(ArrayList<MessagePackage> list){
      ArrayList<Wrapped> wrap = new ArrayList<Wrapped>();
      for (MessagePackage acc : list){
         Wrapped x = new Wrapped();
         x.pck = acc;
         x.setType("Unclassified");
         wrap.add(x);
      }
    return wrap;
    }
    public Boolean checkUnclassified(ArrayList<Wrapped> list) {
        for (Wrapped acc : list) {
            if (acc.getType().equals("Unclassified")) {
                return true;
            }
        }
        return false;
    }

    public void toFirst(ArrayList<Wrapped> list) {
        if (list.size() <= 1) {
            return;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int min = list.get(i).pck.getContent().length();
                if (list.get(j).pck.getContent().length() < min) {
                    min = list.get(j).pck.getContent().length();
                }
                String temp = "";
                for (int k = 0; k < min; k++) {
                    if (list.get(i).pck.getContent().charAt(k) == list.get(j).pck.getContent().charAt(k)) {
                        temp += list.get(i).pck.getContent().charAt(k);
                    }
                }
                if (temp != "" && (temp.length() == list.get(i).pck.getContent().length() || temp.length() == list.get(j).pck.getContent().length())) {
                    list.get(i).setType("First");
                    list.get(j).setType("First");
                }
            }
        }

    }
    public boolean doubleCheck(String s1, String s2){
        for (int i = 0; i < s1.length(); i++){
            if (s1.charAt(i) == s2.charAt(0)){
                String temp = "";
             for (int j = 0; j < s2.length() && i + j < s1.length(); j++)
             { if (s1.charAt(i + j) == s2.charAt(j)){
                 temp += s1.charAt(i);
             } else {break;}
                
                
            }
             if ((temp.length() == s1.length() - i) && temp.length() < s1.length() && temp.length() < s2.length() ){
                 return true;
             }
        } 
    }return false;  
    }
    public void toLast(ArrayList<Wrapped> list) {
        if (list.size() <= 1) {
            return;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                String si = list.get(i).pck.getContent();
                String sj = list.get(j).pck.getContent();
                StringBuilder sbi = new StringBuilder(si);
                StringBuilder sbj = new StringBuilder(sj);
                si = sbi.reverse().toString();
                sj = sbj.reverse().toString();
                int min = si.length();
                if (sj.length() < min) {
                    min = sj.length();
                }

                String temp = "";
                for (int k = 0; k < min; k++) {
                    if (si.charAt(k) == sj.charAt(k)) {
                        temp += si.charAt(k);
                    }
                }
                if (temp != "" && (temp.length() == si.length() || temp.length() == sj.length())) {
                    list.get(i).setType("Last");
                    list.get(j).setType("Last");
                }
            }
        }

    }

    public void toComplete(ArrayList<Wrapped> list) {
        if (list.size() <= 1) {
            return;
        }
        if (list.size() == 2) {
            toFirst(list);
            toLast(list);
          if ( doubleCheck(list.get(0).pck.getContent(), list.get(1).pck.getContent()) == true){
                list.get(0).setType("First");
                    list.get(1).setType("Last");
            return;
        }
           if ( doubleCheck(list.get(1).pck.getContent(), list.get(0).pck.getContent()) == true){
                list.get(1).setType("First");
                    list.get(0).setType("Last");
            return;
        }
        }
        if (list.size() >= 3) {
            toFirst(list);
            toLast(list);
            if (checkUnclassified(list) == true) {
                int StarCi = 0;
                for (Wrapped acc : list) {
                    if (acc.getType().equals("First")) {
                        StarCi = 1;
                    } else if (acc.getType().equals("Last")) {
                        StarCi = -1;
                    } else {
                    }
                }
                if (StarCi == 1) {
                    for (Wrapped acc : list) {
                        if (acc.getType().equals("Unclassified")) {
                            acc.setType("Last");
                        }

                    }

                }
                if (StarCi == -1) {
                    for (Wrapped acc : list) {
                        if (acc.getType().equals("Unclassified")) {
                            acc.setType("First");
                        }

                    }

                }

            }

        }

    }

    public void toTransfer(ArrayList<Wrapped> list) {

        for (Wrapped acc : list) {
            if (acc.getType().equals("First")) {
                first.add(acc.pck);
            }
            if (acc.getType().equals("Last")) {
                last.add(acc.pck);
            }
        }
    }

    public void toMerge( ArrayList<MessagePackage> complete, int size) {
        MessagePackage maxFirst = new MessagePackage(-1, "", 0);
        MessagePackage maxLast = new MessagePackage(-1, "", 0);

        for (MessagePackage acc : first) {
            if (acc.getContent().length() > maxFirst.getContent().length()) {
                maxFirst = acc;
            }
        }
        for (MessagePackage acc : last) {
            if (acc.getContent().length() > maxLast.getContent().length()) {
                maxLast = acc;
            }
        }
        if (maxFirst.getContent().length() + maxLast.getContent().length() < size) {
            return;
        }
        int t = maxFirst.getContent().length() + maxLast.getContent().length() - size;
        String x = maxFirst.getContent();
        for (int i = t; i < maxLast.getContent().length(); i++) {
            x = x + maxLast.getContent().charAt(i);
        }
        maxFirst.setContent(x);
        complete.add(maxFirst);
    }

    public static void main(String[] args) {
            MessagePackage pckx = new MessagePackage(1, "om the sun. Befit", 23);
        MessagePackage pck1 = new MessagePackage(1, "net from the s", 23);
      
        MessagePackage pck2 = new MessagePackage(1, " copied the ancien", 23);
        MessagePackage pck3 = new MessagePackage(1, "Red Planet's bl", 24);
        MessagePackage pck4 = new MessagePackage(1, "ting the Red Pla", 24);
        MessagePackage pck5 = new MessagePackage(1, "", 8);
  //      Index: 6 - omans copied the  - 23
//Index: 6 - omans copied the ancien - 23
        ArrayList<MessagePackage> list = new ArrayList<MessagePackage>();
        ArrayList<MessagePackage> list2 = new ArrayList<MessagePackage>();
        ErrorHandler eh = new ErrorHandler(1, 23);
       // list.add(pck1);
       // list.add(pck2);
       // list.add(pck3);
     //  Index: 2 -  Red Planet's bl - 24
//Index: 8 - d the planet af - 24
//Index: 2 - ting the Red Pla - 24
        list.add(pck1); 
         list.add(pckx); 
       // list.add(pckx);
        //list = eh.toClear(list);
     //  list.add(pck4);
       // list.add(pck5);
       // eh.toFirst(list);
         //eh.toLast(list);
         ArrayList<Wrapped> wr = eh.toWrapped(list);
        //list.add(pck5);
       // Index: 1 - net from the s - 23

//Index: 1 - om the sun. Befit - 23
          eh.toComplete(wr);
          eh.toTransfer(wr);
         eh.toMerge(list2, 23);
 //    System.out.println(eh.doubleCheck("tu cuong", "cuong"));
        for (MessagePackage acc : list2) {
          System.out.println(acc.getContent());
        }
    }
}
