package com.example.chap8.Canon;

class MoveCall extends com.example.chap7.Tree.Stm {
  com.example.chap7.Tree.TEMP dst;
  com.example.chap7.Tree.CALL src;
  MoveCall(com.example.chap7.Tree.TEMP d, com.example.chap7.Tree.CALL s) {dst=d; src=s;}
  public com.example.chap7.Tree.ExpList kids() {return src.kids();}
  public com.example.chap7.Tree.Stm build(com.example.chap7.Tree.ExpList kids) {
	return new com.example.chap7.Tree.MOVE(dst, src.build(kids));
  }
}   
  
class ExpCall extends com.example.chap7.Tree.Stm {
  com.example.chap7.Tree.CALL call;
  ExpCall(com.example.chap7.Tree.CALL c) {call=c;}
  public com.example.chap7.Tree.ExpList kids() {return call.kids();}
  public com.example.chap7.Tree.Stm build(com.example.chap7.Tree.ExpList kids) {
	return new com.example.chap7.Tree.EXP(call.build(kids));
  }
}   
  
class StmExpList {
  com.example.chap7.Tree.Stm stm;
  com.example.chap7.Tree.ExpList exps;
  StmExpList(com.example.chap7.Tree.Stm s, com.example.chap7.Tree.ExpList e) {stm=s; exps=e;}
}

public class Canon {
  
 static boolean isNop(com.example.chap7.Tree.Stm a) {
   return a instanceof com.example.chap7.Tree.EXP
          && ((com.example.chap7.Tree.EXP)a).exp instanceof com.example.chap7.Tree.CONST;
 }

 static com.example.chap7.Tree.Stm seq(com.example.chap7.Tree.Stm a, com.example.chap7.Tree.Stm b) {
    if (isNop(a)) return b;
    else if (isNop(b)) return a;
    else return new com.example.chap7.Tree.SEQ(a,b);
 }

 static boolean commute(com.example.chap7.Tree.Stm a, com.example.chap7.Tree.Exp b) {
    return isNop(a)
        || b instanceof com.example.chap7.Tree.NAME
        || b instanceof com.example.chap7.Tree.CONST;
 }

 static com.example.chap7.Tree.Stm do_stm(com.example.chap7.Tree.SEQ s) { 
	return seq(do_stm(s.left), do_stm(s.right));
 }

 static com.example.chap7.Tree.Stm do_stm(com.example.chap7.Tree.MOVE s) { 
	if (s.dst instanceof com.example.chap7.Tree.TEMP 
	     && s.src instanceof com.example.chap7.Tree.CALL) 
		return reorder_stm(new MoveCall((com.example.chap7.Tree.TEMP)s.dst,
						(com.example.chap7.Tree.CALL)s.src));
	else if (s.dst instanceof com.example.chap7.Tree.ESEQ)
	    return do_stm(new com.example.chap7.Tree.SEQ(((com.example.chap7.Tree.ESEQ)s.dst).stm,
					new com.example.chap7.Tree.MOVE(((com.example.chap7.Tree.ESEQ)s.dst).exp,
						  s.src)));
	else return reorder_stm(s);
 }

 static com.example.chap7.Tree.Stm do_stm(com.example.chap7.Tree.EXP s) { 
	if (s.exp instanceof com.example.chap7.Tree.CALL)
	       return reorder_stm(new ExpCall((com.example.chap7.Tree.CALL)s.exp));
	else return reorder_stm(s);
 }

 static com.example.chap7.Tree.Stm do_stm(com.example.chap7.Tree.Stm s) {
     if (s instanceof com.example.chap7.Tree.SEQ) return do_stm((com.example.chap7.Tree.SEQ)s);
     else if (s instanceof com.example.chap7.Tree.MOVE) return do_stm((com.example.chap7.Tree.MOVE)s);
     else if (s instanceof com.example.chap7.Tree.EXP) return do_stm((com.example.chap7.Tree.EXP)s);
     else return reorder_stm(s);
 }

 static com.example.chap7.Tree.Stm reorder_stm(com.example.chap7.Tree.Stm s) {
     StmExpList x = reorder(s.kids());
     return seq(x.stm, s.build(x.exps));
 }

 static com.example.chap7.Tree.ESEQ do_exp(com.example.chap7.Tree.ESEQ e) {
      com.example.chap7.Tree.Stm stms = do_stm(e.stm);
      com.example.chap7.Tree.ESEQ b = do_exp(e.exp);
      return new com.example.chap7.Tree.ESEQ(seq(stms,b.stm), b.exp);
  }

 static com.example.chap7.Tree.ESEQ do_exp (com.example.chap7.Tree.Exp e) {
       if (e instanceof com.example.chap7.Tree.ESEQ) return do_exp((com.example.chap7.Tree.ESEQ)e);
       else return reorder_exp(e);
 }
         
 static com.example.chap7.Tree.ESEQ reorder_exp (com.example.chap7.Tree.Exp e) {
     StmExpList x = reorder(e.kids());
     return new com.example.chap7.Tree.ESEQ(x.stm, e.build(x.exps));
 }

 static StmExpList nopNull = new StmExpList(new com.example.chap7.Tree.EXP(new com.example.chap7.Tree.CONST(0)),null);

 static StmExpList reorder(com.example.chap7.Tree.ExpList exps) {
     if (exps==null) return nopNull;
     else {
       com.example.chap7.Tree.Exp a = exps.head;
       if (a instanceof com.example.chap7.Tree.CALL) {
         Temp.Temp t = new Temp.Temp();
	 com.example.chap7.Tree.Exp e = new com.example.chap7.Tree.ESEQ(new com.example.chap7.Tree.MOVE(new com.example.chap7.Tree.TEMP(t), a),
				    new com.example.chap7.Tree.TEMP(t));
         return reorder(new com.example.chap7.Tree.ExpList(e, exps.tail));
       } else {
	 com.example.chap7.Tree.ESEQ aa = do_exp(a);
	 StmExpList bb = reorder(exps.tail);
	 if (commute(bb.stm, aa.exp))
	      return new StmExpList(seq(aa.stm,bb.stm), 
				    new com.example.chap7.Tree.ExpList(aa.exp,bb.exps));
	 else {
	   Temp.Temp t = new Temp.Temp();
	   return new StmExpList(
			  seq(aa.stm, 
			    seq(new com.example.chap7.Tree.MOVE(new com.example.chap7.Tree.TEMP(t),aa.exp),
				 bb.stm)),
			  new com.example.chap7.Tree.ExpList(new com.example.chap7.Tree.TEMP(t), bb.exps));
	 }
       }
     }
 }
        
 static com.example.chap7.Tree.StmList linear(com.example.chap7.Tree.SEQ s, com.example.chap7.Tree.StmList l) {
      return linear(s.left,linear(s.right,l));
 }
 static com.example.chap7.Tree.StmList linear(com.example.chap7.Tree.Stm s, com.example.chap7.Tree.StmList l) {
    if (s instanceof com.example.chap7.Tree.SEQ) return linear((com.example.chap7.Tree.SEQ)s, l);
    else return new com.example.chap7.Tree.StmList(s,l);
 }

 static public com.example.chap7.Tree.StmList linearize(com.example.chap7.Tree.Stm s) {
    return linear(do_stm(s), null);
 }
}
