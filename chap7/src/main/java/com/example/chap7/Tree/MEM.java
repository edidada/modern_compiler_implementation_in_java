package com.example.chap7.Tree;
import com.example.chap7.Temp.Temp;
import com.example.chap7.Temp.Label;
public class MEM extends Exp {
  public Exp exp;
  public MEM(Exp e) {exp=e;}
  public ExpList kids() {return new ExpList(exp,null);}
  public Exp build(ExpList kids) {
    return new MEM(kids.head);
  }
}

