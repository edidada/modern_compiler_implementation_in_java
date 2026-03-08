package com.example.chap4.Absyn;
import com.example.chap4.Symbol.Symbol;
public class VarExp extends Exp {
   public Var var;
   public VarExp(int p, Var v) {pos=p; var=v;}
}   
