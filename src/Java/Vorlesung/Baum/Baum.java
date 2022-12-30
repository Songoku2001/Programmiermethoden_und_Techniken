package Java.Vorlesung.Baum;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Baum<E> {
  public  E el;
  public List<Baum<E>> kinder;
  boolean isEmpty;
  public Baum<E> parent = null;

  public Baum(){
    isEmpty=true;
  }
  @SafeVarargs public Baum(E hd, Baum<E>... kinder) {
    super();
    this.el = hd;
    this.kinder = new ArrayList<>();
    for (var kind: kinder) {
      this.kinder.add(kind);
      kind.parent = this;
    }
    this.isEmpty = false;

  }
  public int groesse() {
    if (isEmpty) return 0;
    var result = 1;
    for (var kind:kinder) {
      result=result+kind.groesse();
    }
    return result;
  }
  public int groesse2() {
    if (isEmpty) return 0;
    return kinder.parallelStream()
            .reduce(0, (r,kind)->r+kind.groesse2(), (x,y)->x+y)+1;
  }

  public int tiefe() {
    if (isEmpty) return 0;
    var result = 0;
    for (var kind:kinder) {
      int tiefe = kind.tiefe();
      result=tiefe>result?tiefe:result;
    }
    return result+1;
  }
  public int tiefe2() {
    if (isEmpty) return 0;
    return kinder.parallelStream()
            .reduce(0
                    , (r,kind)->{
                      int tiefe = kind.tiefe2();
                      return tiefe>r?tiefe:r;
                    }, (x,y)->x>y?x:y)+1;
  }
  @Override
  public String toString() {
    var result = new StringBuffer();
    toString("\n",result);
    return result.toString();
  }
  private void toString(String ind, StringBuffer result) {
    result.append(ind);
    result.append(el);
    for (var kind:kinder) {
      kind.toString(ind+"  ",result);
    }
  }
  private void toString(String ind, Writer result) throws IOException {
    result.write(ind);
    result.write(el.toString());
    for (var kind:kinder) {
      kind.toString(ind+"  ",result);
    }
  }
  public String toXML() {
    var result = new StringWriter();
    try {
      toXML("\n",result);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result.toString();
  }

  public void toXML(String indent, Writer result) throws IOException {
    result.write(indent);
    result.write("<knoten>");
    var ind2 = indent+"  ";
    result.write(ind2);
    result.write("<element>");
    result.write(el.toString());
    result.write("</element>");
    result.write(ind2);
    result.write("<childNodes>");
    for (var kind:kinder) {
      kind.toXML(ind2+"  ", result);
    }
    result.write(ind2);
    result.write("</childNodes>");
    result.write(indent);
    result.write("</knoten>");
  }
  public String toString2() {
    var result = new StringWriter();
    try {
      toString("\n",result);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result.toString();
  }
  public List<E> alleElemente(){
    var result = new ArrayList<E>();
    if (isEmpty) return result;
    extracted(result);
    return result;
  }
  private void extracted(ArrayList<E> result) {
    result.add(el);
    for (var kind:kinder) {
      kind.extracted(result);
    }
  }
  public List<Baum<E>> geschwister(){
    var result = new ArrayList<Baum<E>>();
    if (parent!=null) {
      for (var kind:parent.kinder) {
        if (this!=kind) result.add(kind);
      }
    }
    return result;
  }
}