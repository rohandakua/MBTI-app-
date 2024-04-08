package com.example.mbpt;

public class qrd {
    public int qn;
    public boolean saw;
    public boolean attempt;
    public qrd(){}
    public qrd(int a){
        this.qn=a;
        this.attempt=false;
        this.saw=false;
    }
    public qrd(int a,boolean saw,boolean attempt){
        this.qn=a;
        this.attempt=attempt;
        this.saw=saw;
    }

}
