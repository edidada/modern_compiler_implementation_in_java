package com.example.chap9.Assem;

public class LABEL extends Instr {
   public com.example.chap6.Temp.Label label;

   public LABEL(String a, com.example.chap6.Temp.Label l) {
      assem=a; label=l;
   }

   public com.example.chap6.Temp.TempList use() {return null;}
   public com.example.chap6.Temp.TempList def() {return null;}
   public Targets jumps()     {return null;}

}
