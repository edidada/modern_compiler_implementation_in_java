package com.example.chap10.RegAlloc;
import com.example.chap10.com.example.chap10.Graph.Node;
import com.example.chap10.com.example.chap10.Graph.Graph;

abstract public class InterferenceGraph extends Graph {
   abstract public Node tnode(Temp.Temp temp);
   abstract public Temp.Temp gtemp(Node node);
   abstract public MoveList moves();
   public int spillCost(Node node) {return 1;}
}
