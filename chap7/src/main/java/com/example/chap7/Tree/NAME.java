package com.example.chap7.Tree;
import com.example.chap6.Temp.Temp;
import com.example.chap6.Temp.Label;
public class NAME extends Exp {
  public Label label;
  public NAME(Label l) {label=l;}
  public ExpList kids() {return null;}
  public Exp build(ExpList kids) {return this;}
}

