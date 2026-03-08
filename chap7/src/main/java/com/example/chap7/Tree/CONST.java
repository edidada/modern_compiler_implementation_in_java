package com.example.chap7.Tree;
import com.example.chap7.Temp.Temp;
import com.example.chap7.Temp.Label;
public class CONST extends Exp {
  public int value;
  public CONST(int v) {value=v;}
  public ExpList kids() {return null;}
  public Exp build(ExpList kids) {return this;}
}

