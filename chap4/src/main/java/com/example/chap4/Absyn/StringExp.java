package com.example.chap4.Absyn;
import com.example.chap4.Symbol.Symbol;
public class StringExp extends Exp {
   public String value;
   public StringExp(int p, String v) {pos=p; value=v;}
}
