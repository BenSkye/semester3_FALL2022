
package com.fptuni.csd201.object;

public class MessagePackage {

    private int index;
    private String content;
    private int size;
    

    public MessagePackage() {
    }

    public MessagePackage(int index, String content, int size) {
        this.index = index;
        this.content = content;
        this.size = size;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

   

}
