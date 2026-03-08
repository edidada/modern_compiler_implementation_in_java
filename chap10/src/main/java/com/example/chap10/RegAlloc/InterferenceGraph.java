package com.example.chap10.RegAlloc;
import com.example.chap10.Graph.Node;
import com.example.chap10.Graph.Graph;
import com.example.chap6.Temp.Temp;
import com.example.chap6.Temp.TempList;

abstract public class InterferenceGraph extends Graph {
   abstract public Node tnode(Temp temp);
   abstract public Temp gtemp(Node node);
   abstract public MoveList moves();
   public int spillCost(Node node) {return 1;}
}
