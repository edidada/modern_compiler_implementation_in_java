package com.example.chap8.Canon;

public class BasicBlocks {
  public StmListList blocks;
  public Temp.Label done;

  private StmListList lastBlock;
  private com.example.chap7.Tree.StmList lastStm;

  private void addStm(com.example.chap7.Tree.Stm s) {
	lastStm = lastStm.tail = new com.example.chap7.Tree.StmList(s,null);
  }

  private void doStms(com.example.chap7.Tree.StmList l) {
      if (l==null) 
	doStms(new com.example.chap7.Tree.StmList(new com.example.chap7.Tree.JUMP(done), null));
      else if (l.head instanceof com.example.chap7.Tree.JUMP 
	      || l.head instanceof com.example.chap7.Tree.CJUMP) {
	addStm(l.head);
	mkBlocks(l.tail);
      } 
      else if (l.head instanceof com.example.chap7.Tree.LABEL)
           doStms(new com.example.chap7.Tree.StmList(new com.example.chap7.Tree.JUMP(((com.example.chap7.Tree.LABEL)l.head).label), 
	  			   l));
      else {
	addStm(l.head);
	doStms(l.tail);
      }
  }

  void mkBlocks(com.example.chap7.Tree.StmList l) {
     if (l==null) return;
     else if (l.head instanceof com.example.chap7.Tree.LABEL) {
	lastStm = new com.example.chap7.Tree.StmList(l.head,null);
        if (lastBlock==null)
  	   lastBlock= blocks= new StmListList(lastStm,null);
        else
  	   lastBlock = lastBlock.tail = new StmListList(lastStm,null);
	doStms(l.tail);
     }
     else mkBlocks(new com.example.chap7.Tree.StmList(new com.example.chap7.Tree.LABEL(new Temp.Label()), l));
  }
   

  public BasicBlocks(com.example.chap7.Tree.StmList stms) {
    done = new Temp.Label();
    mkBlocks(stms);
  }
}
