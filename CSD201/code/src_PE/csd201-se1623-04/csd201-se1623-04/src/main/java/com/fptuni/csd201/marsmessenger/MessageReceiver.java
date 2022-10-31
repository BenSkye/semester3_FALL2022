package com.fptuni.csd201.marsmessenger;

import com.fptuni.csd201.object.MessagePackage;

interface MessageReceiver {
    void receive(MessagePackage pck);  
    String getMessage();  // Q1,2
    int[] getMissingIndex(); // Q3

}
