package com.example.chap3.Parse;

import com.example.chap3.ErrorMsg.ErrorMsg;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Parse {

  public ErrorMsg errorMsg;

  public Parse(String filename) {
       errorMsg = new ErrorMsg(filename);
       Reader inp;
       try {inp=new FileReader(filename);
       } catch (java.io.FileNotFoundException e) {
	 throw new Error("File not found: " + filename);
       }
       parser parser = new parser(new Yylex(inp,errorMsg), errorMsg);

      try {
          parser./*debug_*/parse();
      } catch (Throwable e) {
	e.printStackTrace();
	throw new Error(e.toString());
      } 
      finally {
         try {inp.close();} catch (java.io.IOException e) {}
      }
  }
}
   

