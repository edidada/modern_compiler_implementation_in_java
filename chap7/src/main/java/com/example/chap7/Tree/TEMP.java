package com.example.chap7.Tree;
import com.example.chap6.Temp.Temp;

public class TEMP extends Exp {
  public Temp temp;
  public TEMP(Temp t) {temp=t;}
  public ExpList kids() {return null;}
  public Exp build(ExpList kids) {return this;}
}
