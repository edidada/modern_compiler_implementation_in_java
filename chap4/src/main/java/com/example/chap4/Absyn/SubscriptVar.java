package com.example.chap4.Absyn;
import com.example.chap4.Symbol.Symbol;
public class SubscriptVar extends Var {
   public Var var;
   public Exp index;
   public SubscriptVar(int p, Var v, Exp i) {pos=p; var=v; index=i;}
}
