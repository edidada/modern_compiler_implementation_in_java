package com.example.chap4.Absyn;
import com.example.chap4.Symbol.Symbol;
public class AssignExp extends Exp {
   public Var var;
   public Exp exp;
   public AssignExp(int p, Var v, Exp e) {pos=p; var=v; exp=e;}
}
