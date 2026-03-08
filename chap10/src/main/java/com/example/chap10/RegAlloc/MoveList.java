package com.example.chap10.RegAlloc;

public class MoveList {
   public com.example.chap10.Graph.Node src, dst;
   public MoveList tail;
   public MoveList(com.example.chap10.Graph.Node s, com.example.chap10.Graph.Node d, MoveList t) {
	src=s; dst=d; tail=t;
   }
}

