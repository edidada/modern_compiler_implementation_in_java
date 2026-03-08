package com.example.chap5.Types;

public class STRING extends Type {
	public STRING(){}
	public boolean coerceTo(Type t) {return (t.actual() instanceof STRING);}
}

