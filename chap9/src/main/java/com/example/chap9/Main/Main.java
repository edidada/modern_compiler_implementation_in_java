package com.example.chap9.Main;
import com.example.chap9.Semant.Semant;
import com.example.chap9.Translate.Translate;
import com.example.chap9.Parse.Parse;

class Main {

 static Frame.Frame frame = new <MyTargetMachine>.Frame();

 static void prStmList(com.example.chap7.Tree.Print print, com.example.chap7.Tree.StmList stms) {
     for(com.example.chap7.Tree.StmList l = stms; l!=null; l=l.tail)
	print.prStm(l.head);
 }

 static com.example.chap9.Assem.InstrList codegen(Frame.Frame f, com.example.chap7.Tree.StmList stms) {
  	com.example.chap9.Assem.InstrList first=null, last=null;
	    for(com.example.chap7.Tree.StmList s=stms; s!=null; s=s.tail) {
	       com.example.chap9.Assem.InstrList i = f.codegen(s.head);
	       if (last==null) {first=last=i;}
	       else {while (last.tail!=null) last=last.tail;
		     last=last.tail=i;
		    }
	    }
	    return first;
	}
		

 static void emitProc(java.io.PrintStream out, Translate.ProcFrag f) {
     java.io.PrintStream debug = 
          /* new java.io.PrintStream(new NullOutputStream()); */
          out;
     com.example.chap6.Temp.TempMap tempmap= new com.example.chap6.Temp.CombineMap(f.frame,new com.example.chap6.Temp.DefaultMap());
     com.example.chap7.Tree.Print print = new com.example.chap7.Tree.Print(debug, tempmap);
     debug.println("# Before canonicalization: ");
     print.prStm(f.body);
     debug.print("# After canonicalization: ");
     com.example.chap7.Tree.StmList stms = com.example.chap8.Canon.com.example.chap8.Canon.linearize(f.body);
     prStmList(print,stms);
     debug.println("# Basic Blocks: ");
     com.example.chap8.Canon.BasicBlocks b = new com.example.chap8.Canon.BasicBlocks(stms);
     for(com.example.chap8.Canon.StmListList l = b.blocks; l!=null; l=l.tail) {
       debug.println("#");
       prStmList(print,l.head);
     }       
     print.prStm(new com.example.chap7.Tree.LABEL(b.done));
     debug.println("# Trace Scheduled: ");
     com.example.chap7.Tree.StmList traced = (new com.example.chap8.Canon.TraceSchedule(b)).stms;
     prStmList(print,traced);
     com.example.chap9.Assem.InstrList instrs= codegen(f.frame,traced);
     debug.println("# Instructions: ");
     for(com.example.chap9.Assem.InstrList p=instrs; p!=null; p=p.tail)
	debug.print(p.head.format(tempmap));
 }

 public static void main(String args[]) throws java.io.IOException {
   Parse.Parse parse = new Parse.Parse(args[0]);
   java.io.PrintStream out = 
        new java.io.PrintStream(new java.io.FileOutputStream(args[0] + ".s"));
   Translate.Translate translate = new Translate.Translate(frame);
   Semant semant = new Semant(translate,parse.errorMsg);
   Translate.Frag frags = semant.transProg(parse.absyn);
   for(Translate.Frag f = frags; f!=null; f=f.next)
       if (f instanceof Translate.ProcFrag)
          emitProc(out, (Translate.ProcFrag)f);
       else if (f instanceof Translate.DataFrag)
          out.print(((Translate.DataFrag)f).data);
   out.close();
 }

}

class NullOutputStream extends java.io.OutputStream {
    public void write(int b) {}
}


