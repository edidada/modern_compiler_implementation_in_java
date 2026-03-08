package com.example.chap9.Assem;

public class MOVE extends Instr {
   public com.example.chap6.Temp.Temp dst;   
   public com.example.chap6.Temp.Temp src;

   public MOVE(String a, com.example.chap6.Temp.Temp d, com.example.chap6.Temp.Temp s) {
      assem=a; dst=d; src=s;
   }
   public com.example.chap6.Temp.TempList use() {return new com.example.chap6.Temp.TempList(src,null);}
   public com.example.chap6.Temp.TempList def() {return new com.example.chap6.Temp.TempList(dst,null);}
   public Targets jumps()     {return null;}

}
