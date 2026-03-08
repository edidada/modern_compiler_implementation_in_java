package com.example.chap4.Parse;

import com.example.chap4.ErrorMsg.ErrorMsg;
import com.example.chap4.Absyn.Exp;

public class Parse {

  public ErrorMsg errorMsg;
  public Exp absyn;

  public Parse(String filename) {
      errorMsg = new ErrorMsg(filename);
      absyn = null;
  }
}

