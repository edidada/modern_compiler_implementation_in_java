package com.example.chap7.Tree;
import com.example.chap6.Temp.Temp;
import com.example.chap6.Temp.Label;

public class ExpStmt extends Stm {
  public Exp exp; 
  public ExpStmt(Exp e) {exp=e;}
  public ExpList kids() {return new ExpList(exp,null);}
  public Stm build(ExpList kids) {
    return new ExpStmt(kids.head);
  }
}
