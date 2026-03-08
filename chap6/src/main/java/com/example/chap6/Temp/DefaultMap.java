package com.example.chap6.Temp;

public class DefaultMap implements TempMap {
	public String tempMap(Temp.Temp t) {
	   return t.toString();
	}

	public DefaultMap() {}
}
