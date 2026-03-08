package com.example.chap9.Assem;

public abstract class Instr {
  public String assem;
  public abstract com.example.chap6.Temp.TempList use();
  public abstract com.example.chap6.Temp.TempList def();
  public abstract Targets jumps();

  private com.example.chap6.Temp.Temp nthTemp(com.example.chap6.Temp.TempList l, int i) {
    if (i==0) return l.head;
    else return nthTemp(l.tail,i-1);
  }

  private com.example.chap6.Temp.Label nthLabel(com.example.chap6.Temp.LabelList l, int i) {
    if (i==0) return l.head;
    else return nthLabel(l.tail,i-1);
  }

  public String format(com.example.chap6.Temp.TempMap m) {
    com.example.chap6.Temp.TempList dst = def();
    com.example.chap6.Temp.TempList src = use();
    Targets j = jumps();
    com.example.chap6.Temp.LabelList jump = (j==null)?null:j.labels;
    StringBuffer s = new StringBuffer();
    int len = assem.length();
    for(int i=0; i<len; i++)
	if (assem.charAt(i)=='`')
	   switch(assem.charAt(++i)) {
              case 's': {int n = Character.digit(assem.charAt(++i),10);
			 s.append(m.tempMap(nthTemp(src,n)));
			}
			break;
	      case 'd': {int n = Character.digit(assem.charAt(++i),10);
			 s.append(m.tempMap(nthTemp(dst,n)));
			}
 			break;
	      case 'j': {int n = Character.digit(assem.charAt(++i),10);
			 s.append(nthLabel(jump,n).toString());
			}
 			break;
	      case '`': s.append('`'); 
			break;
              default: throw new Error("bad Assem format");
       }
       else s.append(assem.charAt(i));

    return s.toString();
  }


}
