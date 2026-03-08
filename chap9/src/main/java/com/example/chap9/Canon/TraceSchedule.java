package com.example.chap9.Canon;

public class TraceSchedule {

  public com.example.chap7.Tree.StmList stms;
  BasicBlocks theBlocks;
  java.util.Dictionary table = new java.util.Hashtable();

  com.example.chap7.Tree.StmList getLast(com.example.chap7.Tree.StmList block) {
     com.example.chap7.Tree.StmList l=block;
     while (l.tail.tail!=null)  l=l.tail;
     return l;
  }

  void trace(com.example.chap7.Tree.StmList l) {
   for(;;) {
     com.example.chap7.Tree.LABEL lab = (com.example.chap7.Tree.LABEL)l.head;
     table.remove(lab.label);
     com.example.chap7.Tree.StmList last = getLast(l);
     com.example.chap7.Tree.Stm s = last.tail.head;
     if (s instanceof com.example.chap7.Tree.JUMP) {
	com.example.chap7.Tree.JUMP j = (com.example.chap7.Tree.JUMP)s;
        com.example.chap7.Tree.StmList target = (com.example.chap7.Tree.StmList)table.get(j.targets.head);
	if (j.targets.tail==null && target!=null) {
               last.tail=target;
	       l=target;
        }
	else {
	  last.tail.tail=getNext();
	  return;
        }
     }
     else if (s instanceof com.example.chap7.Tree.CJUMP) {
	com.example.chap7.Tree.CJUMP j = (com.example.chap7.Tree.CJUMP)s;
        com.example.chap7.Tree.StmList t = (com.example.chap7.Tree.StmList)table.get(j.iftrue);
        com.example.chap7.Tree.StmList f = (com.example.chap7.Tree.StmList)table.get(j.iffalse);
        if (f!=null) {
	  last.tail.tail=f; 
	  l=f;
	}
        else if (t!=null) {
	  last.tail.head=new com.example.chap7.Tree.CJUMP(com.example.chap7.Tree.CJUMP.notRel(j.relop),
					j.left,j.right,
					j.iffalse,j.iftrue);
	  last.tail.tail=t;
	  l=t;
        }
        else {
	  com.example.chap6.Temp.Label ff = new com.example.chap6.Temp.Label();
	  last.tail.head=new com.example.chap7.Tree.CJUMP(j.relop,j.left,j.right,
					j.iftrue,ff);
	  last.tail.tail=new com.example.chap7.Tree.StmList(new com.example.chap7.Tree.LABEL(ff),
		           new com.example.chap7.Tree.StmList(new com.example.chap7.Tree.JUMP(j.iffalse),
					    getNext()));
	  return;
        }
     }
     else throw new Error("Bad basic block in TraceSchedule");
    }
  }

  com.example.chap7.Tree.StmList getNext() {
      if (theBlocks.blocks==null) 
	return new com.example.chap7.Tree.StmList(new com.example.chap7.Tree.LABEL(theBlocks.done), null);
      else {
	 com.example.chap7.Tree.StmList s = theBlocks.blocks.head;
	 com.example.chap7.Tree.LABEL lab = (com.example.chap7.Tree.LABEL)s.head;
	 if (table.get(lab.label) != null) {
          trace(s);
	  return s;
         }
         else {
	   theBlocks.blocks = theBlocks.blocks.tail;
           return getNext();
         }
      }
  }

  public TraceSchedule(BasicBlocks b) {
    theBlocks=b;
    for(StmListList l = b.blocks; l!=null; l=l.tail)
       table.put(((com.example.chap7.Tree.LABEL)l.head.head).label, l.head);
    stms=getNext();
    table=null;
  }        
}


