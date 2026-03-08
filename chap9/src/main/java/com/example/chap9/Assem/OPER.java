package com.example.chap9.Assem;

public class OPER extends Instr {
   public com.example.chap6.Temp.TempList dst;   
   public com.example.chap6.Temp.TempList src;
   public Targets jump;

   public OPER(String a, com.example.chap6.Temp.TempList d, com.example.chap6.Temp.TempList s, com.example.chap6.Temp.LabelList j) {
      assem=a; dst=d; src=s; jump=new Targets(j);
   }
   public OPER(String a, com.example.chap6.Temp.TempList d, com.example.chap6.Temp.TempList s) {
      assem=a; dst=d; src=s; jump=null;
   }

   public com.example.chap6.Temp.TempList use() {return src;}
   public com.example.chap6.Temp.TempList def() {return dst;}
   public Targets jumps() {return jump;}

}
