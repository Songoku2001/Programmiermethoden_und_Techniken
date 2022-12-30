
package SS2021.Java.Blatt4.name.panitz.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static Blatt4.name.panitz.util.Tree.windsor;
import static org.junit.Assert.*;

public class TTest {



static class TT<E> extends Tree<E> {
    
  @SafeVarargs
  public TT(E el, Tree<E>... ts) {
    super(el, ts);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((childNodes == null) ? 0 : childNodes.hashCode());
    result = prime * result + ((element == null) ? 0 : element.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof Tree))
      return false;
    @SuppressWarnings("rawtypes")
    Tree other = (Tree) obj;
    if (childNodes == null) {
      if (other.childNodes != null)
        return false;
    } else if (!childNodes.equals(other.childNodes))
      return false;
    if (element == null) {
      if (other.element != null)
        return false;
    } else if (!element.equals(other.element))
      return false;
    return true;
  }
  int i = 0;
  @Override
  public boolean contains(Predicate<? super E> pred) {
    i++;
    return super.contains(pred);
  }

}


  TT<String> george;
  Tree<String> elizabeth;
  Tree<String> charles;
  Tree<String> william;
  Tree<String> george2;
  Tree<String> charlotte;
  Tree<String> louis;
  Tree<String> harry;
  Tree<String> archie;
  Tree<String> anne;
  Tree<String> peter;
  Tree<String> savannah;
  Tree<String> isla;
  Tree<String> zara;
  Tree<String> mia;
  Tree<String> lena;
  Tree<String> andrew;
  Tree<String> beatrice;
  Tree<String> eugenie;
  Tree<String> edward;
  Tree<String> louise;
  Tree<String> james;
  Tree<String> magaret;
  Tree<String> david;
  Tree<String> charles2;
  Tree<String> margarita;
  Tree<String> sarah;
  Tree<String> samuel;
  Tree<String> arthur;
  int i = 0;  
  @Before
  public void init() {
    i = 0;
    george2 = new TT<>("George");
    charlotte = new TT<>("Charlotte");
    louis= new TT<>("Louis");
    william = new TT<>("William",george2,charlotte,louis);

    archie = new TT<>("Archie");    
    harry = new TT<>("Harry",archie);
        
    charles = new TT<>("Charles",william,harry);
   

    savannah = new TT<>("Savannah");
    isla = new TT<>("Isla");
    peter = new TT<>("Peter",savannah,isla);
    
    mia = new TT<>("Mia");
    lena=new TT<>("Lena");
    zara = new TT<>("Zara",mia,lena);

    anne = new TT<>("Anne",peter,zara);                

    beatrice = new TT<>("Beatrice");
    eugenie = new TT<>("Eugenie");
    andrew = new TT<>("Andrew",beatrice,eugenie);
  
    louise = new TT<>("Louise");      
    james = new TT<>("James");
    edward = new TT<>("Edward",louise,james);
    
    elizabeth = new TT<>("Elizabeth",charles,anne,andrew,edward);


    charles2 = new TT<>("Charles");
    margarita = new TT<>("Margarita");
    david = new TT<>("David",charles2,margarita);

    samuel = new TT<>("Samuel");
    arthur = new TT<>("Arthur");
    sarah = new TT<>("Sarah",samuel,arthur);  

    magaret = new TT<>("Magaret",david,sarah);
  
    george = new TT<>("George",elizabeth,magaret);
  }

  @Test
  public void testContains1() {
    assertTrue("Das Element \"George\" an der Wurzel wird nicht gefunden.",george.contains(x->x.equals("George")));
  }

  @Test
  public void testContains2() {
    assertTrue("Das Element \"Charlotte\" an einem Blatt wird nicht gefunden.",george.contains(x->x.equals("Charlotte")));
  }

  @Test
  public void testContains3() {
    assertFalse("Fehler: Es gibt kein Element das \"Wilhelm\" heißt im Testbaum.",george.contains(x->x.equals("Wilhelm")));
  }


  @Test
  public void testContains4() {
    assertTrue("Es wird nicht erkannt, dass es Elemente gibt, die mit einem 'C' beginnen.",george.contains(x -> x.startsWith("C")));
  }


  @Test
  public void testContains5() {
    assertFalse("Es wird fälschlicher Weise behauptet, dass der Baum ein Element dass mit \"Wi\" beginnt enthält.",george.contains(x -> x.startsWith("WI")));
  }


  @Test
  public void testAncestors1() {
    assertEquals("",List.of(),new TT<String>("TEST").ancestors());
  }

  @Test
  public void testAncestors2() {
    assertEquals("",List.of("William", "Charles", "Elizabeth", "George"),charlotte.ancestors());
  }

  @Test
  public void testAncestors3() {
    assertEquals("",List.of("Elizabeth", "George"),charles.ancestors());
  }

  @Test
  public void testSiblings1() {
    assertEquals("",List.of("Anne", "Andrew", "Edward"),charles.siblings());
  }

  @Test
  public void testSiblings2() {
    assertEquals("",List.of(),george.siblings());
  }
  @Test
  public void testSiblings3() {
    assertEquals("",List.of(),archie.siblings());
  }
    
  @Test
  public void testforeach1() {
    int[] i={0};
    george.forEach(x -> {i[0]++;});
    assertEquals("",george.size(),i[0]);
  }
  @Test
  public void testforeach2() {
    int[] i={0};
    windsor.forEach(x -> {i[0]++;});
    assertEquals("",windsor.size(),i[0]);
  }
  

  @Test
  public void testFringe2() {
    assertEquals("Ein Baum aus einem Wurzelknoten muss genau ein Element im fringe haben.",Arrays.asList("TEST"),new TT<String>("TEST").fringe());
  }
  @Test
  public void testFringe3() {
    TT<String> tt = new TT<String>("nix",new TT<>("TEST"));
    assertEquals("Der Baum: »"+tt+"« hat falschen fringe.",(Arrays.asList("TEST")),tt.fringe());
  }
  @Test
  public void testFringe4() {
    TT<String> tt = new TT<String>("nix",new TT<>("TEST1"),new TT<>("TEST2"));
    assertEquals("Der Baum: »"+tt+"« hat falschen fringe.",(Arrays.asList("TEST1","TEST2")),tt.fringe());
  }
  @Test
  public void testFringe5() {
    assertEquals("Der Baum: »"+george+"« hat falschen fringe.",(Arrays.asList("George", "Charlotte", "Louis","Archie", "Savannah", "Isla", "Mia", "Lena","Beatrice", "Eugenie", "Louise", "James", "Charles", "Margarita", "Samuel", "Arthur")),george.fringe());
  }

  @Test
  public void testPathTo0() {
    george.i = 0;
    george.pathTo("Charlotte");
    assertEquals("pathTo ruft contains auf. Das soll es aber laut Aufgabe nicht.",0, george.i);
  } 

  
  @Test
  public void testPathTo1() {
    assertEquals("Falscher Pfad zu »Charlotte« in Baum: "+george,Arrays.asList(george.element,elizabeth.element,charles.element,william.element,charlotte.element),george.pathTo("Charlotte"));
  } 
  @Test
  public void testPathTo1a() {
    assertEquals("Falscher Pfad zu »Charlotte« in Baum: "+elizabeth,Arrays.asList(elizabeth.element,charles.element,william.element,charlotte.element),elizabeth.pathTo("Charlotte"));
  } 


  @Test
  public void testPathTo2() {
    assertEquals("Pfad zu Elementen, die nicht im Baum sind, muss leer sein.",Arrays.asList(),george.pathTo("Wilhelm"));
  } 
  @Test
  public void testPathTo3() {
    assertEquals("Pfad zu Wurzelelement muss aus genau einem Element bestehen.",Arrays.asList(george.element),george.pathTo("George"));
  } 

  
  @Test
  public void testMapNew0() {
    assertTrue("Fehler: Ihr map erzeugt keinen neuen Baum.",george!= george.map(x->x));
  }

  @Test
  public void testMapNew1() {
    assertEquals("tree.map(x->x) muss neuen aber gleichen Baum erzeugen",george, george.map(x->x));
  }

  @Test
  public void testMapNew2() {
    assertEquals("map muss neuen Baum gleicher Größe erzeugen.",george.size(), george.map(x->x.length()).size());
  }

  @Test
  public void testMapNew3() {
    assertNotEquals("Ihr map scheint keine neuen Elemente mit der Funktion zu erzeugen.",george, george.map(x->x.length()));
  }

  @Test
  public void testMapNew4() {
    assertEquals("Ihr map scheint nicht die richtigen neuen Baumelemente zu erzeugen.",6, 0+george.map(x->x.length()).element);
  }

  @Test
  public void testLevel1() {
    assertEquals("Level 0 besteht nicht aus dem tis Element",List.of("George"), windsor.getLevel(0));
  }
  @Test
  public void testLevel2() {
    assertEquals("Level 1 ist falsch",List.of("Elizabeth", "Magaret"), windsor.getLevel(1));
  }
  @Test
  public void testLevel3() {
    assertEquals("Level 2 ist falsch",List.of("Charles", "Andrew", "Edward", "Anne", "David", "Sarah"), windsor.getLevel(2));
  }

  @Test
  public void testMyGeneration1() {
    assertEquals("myGeneration für Wurzel falsch",List.of("George"), windsor.myGeneration());
  }
  @Test
  public void testMyGeneration2() {
    assertEquals("myGeneration für Generation 1 falsch",List.of("Elizabeth", "Magaret"), windsor.childNodes.get(1).myGeneration());
  }
  @Test
  public void testMyGeneration3() {
    assertEquals("myGeneration für Generation 2 falsch",List.of("Charles", "Andrew", "Edward", "Anne", "David", "Sarah"), windsor.childNodes.get(1).childNodes.get(1).myGeneration());
  }


}