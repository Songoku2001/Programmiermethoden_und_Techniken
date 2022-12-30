package SS2021.Java.Blatt6.name.panitz.json;
import org.junit.Test;

import java.io.StringReader;
import java.util.Set;

import static Blatt6.name.panitz.json.Json.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JsonTest {
  @Test
  public void testCollectNames() {
    try{
      var windsorJ = parse(new StringReader(windsor));
      assertEquals(Set.of("Margarita", "Charlotte", "Arthur", "Louise", "Eugenie", "Edward", "Magaret", "Samuel", "George", "Sarah", "Anne", "Zara", "Isla", "Henry", "Charles", "Savannah", "Mia", "James", "Andrew", "Lena", "William", "Beatrice", "Archie", "Elizabeth", "David", "Peter"),windsorJ.collectNames());
    }catch (Exception e){
      fail(e.toString());
    }
  }


  @Test
  public void testforEach1() {
    try{
      var windsorJ = parse(new StringReader(windsor));
      int[] r = {0};
      windsorJ.forEach(x->r[0]++);
      assertEquals(29,r[0]);
    }catch (Exception e){
      fail(e.toString());
    }
  }

  @Test
  public void testforEach2() {
    try{
      var windsorJ = parse(new StringReader(xs));
      int[] r = {0};
      windsorJ.forEach(x->r[0]++);
      assertEquals(4,r[0]);
    }catch (Exception e){
      fail(e.toString());
    }
  }



  @Test
  public void test1() {
    try{
      var windsorJ = parse(new StringReader(windsor));
      var win2 =  windsorJ.show();
      assertEquals(windsorJ,parse(new StringReader(win2)));
    }catch (Exception e){
      fail(e.toString());
    }
  }
  @Test
  public void test2() {
    try{
      var windsorJ = parse(new StringReader(xs));
      var win2 =  windsorJ.show();
      assertEquals(windsorJ,parse(new StringReader(win2)));
    }catch (Exception e){
      fail(e.toString());
    }
  }
  @Test
  public void test3() {
    try{
      var windsorJ = parse(new StringReader(ex1));
      var win2 =  windsorJ.show();
      assertEquals(windsorJ,parse(new StringReader(win2)));
    }catch (Exception e){
      fail(e.toString());
    }
  }

  @Test
  public void test4() {
    try{
      var windsorJ = parse(new StringReader(ex1));
      var win2 =  windsorJ.toBSON().toJson();
      assertEquals(windsorJ,parse(new StringReader(win2)));
    }catch (Exception e){
      fail(e.toString());
    }
  }
  @Test
  public void test5() {
    try{
      var windsorJ = parse(new StringReader(xs));
      var win2 =  windsorJ.toBSON().toJson();
      assertEquals(windsorJ,parse(new StringReader(win2)));
    }catch (Exception e){
      fail(e.toString());
    }
  }

/*  @Test
  public void test6() {
    try{
      parse(new StringReader(windsor)).forEach(el->el.saveToCollection("testdb","windsor"));
      parse(new StringReader(xs)).forEach(el->el.saveToCollection("testdb","windsor"));

      assertEquals(Set.of("Margarita", "Charlotte", "Arthur", "Louise", "Eugenie", "Edward", "Magaret", "Samuel", "George", "Sarah", "Anne", "Zara", "Isla", "Henry", "Charles", "Savannah", "Mia", "James", "Andrew", "Lena", "William", "Beatrice", "Archie", "Elizabeth", "David", "Peter"),getNames("testdb","windsor"));
    }catch (Exception e){
      fail(e.toString());
    }
  }*/


}