package com.fptuni.csd201.marsmessenger;

import com.fptuni.csd201.object.MessagePackage;
import com.fptuni.csd201.object.Wrapped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MessageReceiverImpl implements MessageReceiver {

    public ArrayList<MessagePackage> complete = new ArrayList<MessagePackage>();
    public ArrayList<MessagePackage> interrupt = new ArrayList<MessagePackage>();

    public int getMaxIndex(ArrayList<MessagePackage> list) {
        int max = -1;
        for (MessagePackage acc : list) {
            if (acc.getIndex() > max) {
                max = acc.getIndex();
            }
        }
        return max;

    }

    public int getSizefromIndex(int index, ArrayList<MessagePackage> list) {

        for (MessagePackage acc : list) {
            if (acc.getIndex() == index) {
                return acc.getSize();
            }
        }
        return -1;

    }

    public ArrayList<MessagePackage> Sort() {
        ArrayList<MessagePackage> sort = new ArrayList<MessagePackage>();
        for (int i = 0; i <= getMaxIndex(complete); i++) {
            for (MessagePackage acc : complete) {
                if (acc.getIndex() == i) {
                    sort.add(acc);
                    break;
                }
            }

        }
        Collections.sort(sort, new Comparator<MessagePackage>() {
            @Override
            public int compare(MessagePackage pck1, MessagePackage pck2) {
                return pck1.getIndex() < pck2.getIndex() ? -1 : 1;
            }
        });
        return sort;
    }

    public ArrayList<MessagePackage> Distribute(int index) {
        ArrayList<MessagePackage> dist = new ArrayList<MessagePackage>();
        for (MessagePackage acc : interrupt) {
            if (index == acc.getIndex()) {
                dist.add(acc);
            }
        }
        return dist;

    }

    @Override
    public void receive(MessagePackage pck) {

        System.out.println("Index: " + pck.getIndex() + " - " + pck.getContent() + " - " + pck.getSize());
        if (pck.getContent().length() == pck.getSize()) {
            complete.add(pck);
        }
        if (pck.getContent().length() < pck.getSize()) {
            boolean check = true;
            for (MessagePackage acc : interrupt) {
                if (acc == pck) {
                    check = false;
                }
            }
            if (check == true) {
                interrupt.add(pck);
            }
        }
    }

    @Override
    public String getMessage() {
        System.out.println("Message: ");
        
        for (int i = 0; i <= getMaxIndex(interrupt); i++) {
            ArrayList<MessagePackage> StarCi = Distribute(i);
            int size = getSizefromIndex(i, StarCi);
            ErrorHandler eh = new ErrorHandler(i, size);
            ArrayList<Wrapped> wStarCi = eh.toWrapped(StarCi);
            eh.toComplete(wStarCi);
            eh.toTransfer(wStarCi);
            eh.toMerge(complete, size);

        }
        String display = "";
        ArrayList<MessagePackage> Teacher = Sort();
        for (MessagePackage acc : Teacher) {
            display += acc.getContent();
        }
        if (display != "") {
            return display;
        } else {
            return "Nothing";
        }
    }

    @Override
    public int[] getMissingIndex() {
        int[] arr;
        ArrayList<Integer> arl = new ArrayList<Integer>();
        for (int i = 0; i < getMaxIndex(complete); i++) {
            boolean check = false;
            for (MessagePackage acc : complete) {

                if (acc.getIndex() == i) {
                    check = true;
                }
            }
            if (check == false) {
                arl.add(i);
            }
        }
      arr = new int[arl.size()];
      for (int i = 0; i < arl.size(); i++){
          arr[i] = arl.get(i);
      }
      return arr;
    }

}
