package SS2021.Java.Blatt2.name.panitz.util;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Iterator;

import static Blatt2.name.panitz.util.Iteratoren.*;
import static Blatt2.name.panitz.util.Iteratoren.Lines.NEW_LINE;
import static org.junit.Assert.*;

public class IteratorenTest {


	static int size(Iterable<?> xs){
		int result = 0;
		for (Object x:xs)result++;
		return result;
	}
	@Test
	public void test1() {
		Iterable<Integer> is = new IntRange(1, 10, 1);
		assertEquals("IntRange(1, 10, 1) iteriert über 10 Zahlen", 10, size(is));
	}
	@Test
	public void test2() {
		Iterable<Integer> is = new IntRange(1, 10, 2);
		assertEquals("IntRange(1, 10, 2) iteriert über 5 Zahlen", 5, size(is));
	}
	@Test
	public void test3() {
		Iterable<Integer> is = new IntRange(1, 10, 3);
		assertEquals("IntRange(1, 10, 3) iteriert über 4 Zahlen", 4, size(is));
	}
	@Test
	public void test4() {
		Iterable<Integer> is = new IntRange(-10, 10, 1);
		assertEquals("IntRange(-10, 10, 1) iteriert über 21 Zahlen", 21, size(is));
	}
	@Test
	public void test5() {
		Iterable<Integer> is = new IntRange(-10, 0, 0);
		Iterator<Integer> xs = is.iterator();
		assertEquals("IntRange(-10, 0, 0) liefert immer -10", -10, xs.next().intValue());
		assertEquals("IntRange(-10, 0, 0) liefert immer -10", -10, xs.next().intValue());
		assertEquals("IntRange(-10, 0, 0) liefert immer -10", -10, xs.next().intValue());
		assertEquals("IntRange(-10, 0, 0) liefert immer -10", -10, xs.next().intValue());
		assertEquals("IntRange(-10, 0, 0) liefert immer -10", -10, xs.next().intValue());
		assertEquals("IntRange(-10, 0, 0) liefert immer -10", -10, xs.next().intValue());
		assertEquals("IntRange(-10, 0, 0) liefert immer -10", -10, xs.next().intValue());
		assertEquals("IntRange(-10, 0, 0) liefert immer -10", -10, xs.next().intValue());
	}

	@Test
	public void test6() {
		Iterable<Integer> is = new IntRange(10, 1, -1);
		assertEquals("IntRange(10, 1, -1) iteriert über 10 Zahlen", 10, size(is));
	}

	@Test
	public void test7() {
		Iterable<Integer> is = new IntRange(10, 1, -2);
		assertEquals("IntRange(10, 1, -2) iteriert über 5 Zahlen", 5, size(is));
	}
	@Test
	public void test8() {
		Iterable<Integer> is = new IntRange(10, 1, -3);
		assertEquals("IntRange(10, 1, -3) iteriert über 4 Zahlen", 4, size(is));
	}
	@Test
	public void test9() {
		Iterable<Integer> is = new IntRange(10, -10, -1);
		assertEquals("IntRange(10, -10, -1) iteriert über 21 Zahlen", 21, size(is));
	}
	@Test
	public void test10() {
		Iterable<Integer> is = new IntRange(-5, -1, 1);
		Iterator<Integer> xs1 = is.iterator();
		Iterator<Integer> xs2 = is.iterator();
		assertEquals("mehrere Iteratoren eines Iterable scheinen nicht unabhängig zu sein", -5, xs1.next().intValue());
		assertEquals("mehrere Iteratoren eines Iterable scheinen nicht unabhängig zu sein", -5, xs2.next().intValue());
		assertEquals("mehrere Iteratoren eines Iterable scheinen nicht unabhängig zu sein", -4, xs1.next().intValue());
		assertEquals("mehrere Iteratoren eines Iterable scheinen nicht unabhängig zu sein", -4, xs2.next().intValue());
		assertEquals("mehrere Iteratoren eines Iterable scheinen nicht unabhängig zu sein", -3, xs1.next().intValue());
		assertEquals("mehrere Iteratoren eines Iterable scheinen nicht unabhängig zu sein", -3, xs2.next().intValue());
		assertEquals("mehrere Iteratoren eines Iterable scheinen nicht unabhängig zu sein", -2, xs1.next().intValue());
		assertEquals("mehrere Iteratoren eines Iterable scheinen nicht unabhängig zu sein", -2, xs2.next().intValue());
		assertEquals("mehrere Iteratoren eines Iterable scheinen nicht unabhängig zu sein", -1, xs1.next().intValue());
		assertEquals("mehrere Iteratoren eines Iterable scheinen nicht unabhängig zu sein", -1, xs2.next().intValue());
	}

  Fib fib = new Fib();
  
  @Before
  public void setUp() throws Exception {
    fib = new Fib();
  }

  @Test
  public void testfib1() {
    Iterator<BigInteger> it = fib.iterator();
    assertEquals("erster Aufruf des Iterators muss 0 ergeben",BigInteger.ZERO, it.next());
    assertEquals("2. Aufruf des Iterators muss 1 ergeben",BigInteger.ONE, it.next());
    assertEquals("3. Aufruf des Iterators muss 1 ergeben",BigInteger.ONE, it.next());
    assertEquals("4. Aufruf des Iterators muss 2 ergeben",BigInteger.valueOf(2), it.next());
    assertEquals("5. Aufruf des Iterators muss 3 ergeben",BigInteger.valueOf(3), it.next());
  }
  @Test
  public void testfib2() {
    Iterator<BigInteger> it = fib.iterator();
    it.next();
    it.next();
    it.next();
    it.next();
    Iterator<BigInteger> it2 = fib.iterator();
    assertEquals("Iteratoren scheinen nicht unabhängig zu sein: erster Aufruf des Iterators muss 0 ergeben",BigInteger.ZERO, it2.next());
    assertEquals("Iteratoren scheinen nicht unabhängig zu sein: 2. Aufruf des Iterators muss 1 ergeben",BigInteger.ONE, it2.next());
    assertEquals("Iteratoren scheinen nicht unabhängig zu sein: 3. Aufruf des Iterators muss 1 ergeben",BigInteger.ONE, it2.next());
    assertEquals("Iteratoren scheinen nicht unabhängig zu sein: 4. Aufruf des Iterators muss 2 ergeben",BigInteger.valueOf(2), it2.next());
    assertEquals("Iteratoren scheinen nicht unabhängig zu sein: 5. Aufruf des Iterators muss 3 ergeben",BigInteger.valueOf(3), it2.next());
  }
  @Test
  public void testfib3() {
    Iterator<BigInteger> it = fib.iterator();
    for (int i = 0; i < 999; i++) {
       it.next();
    }
    assertEquals("1000. Aufruf des Iterators falsch",new BigInteger("26863810024485359386146727202142923967616609318986952340123175997617981700247881689338369654483356564191827856161443356312976673642210350324634850410377680367334151172899169723197082763985615764450078474174626"), it.next());
  }


  String[] xs = {"hello","world"};
  Integer[] is = {1,2,3,4,5};
  Object[] os = {};

  @Test
  public void test1ar() {
    assertEquals("erste element muss bei ersten next zurück gegeben werden","hello",new ArrayIterable<>(xs).iterator().next());
  }


  @Test
  public void test2ar() {
    assertTrue("Iterator muss genügend Elemente liefern",new ArrayIterable<>(xs).iterator().hasNext());
  }

  @Test
  public void test3ar() {
    Iterator<String> it = new ArrayIterable<>(xs).iterator();
    it.next();
    it.next();
    
    assertFalse("Iterator muss genügend Elemente liefern",it.hasNext());
  }

  @Test
  public void test4ar() {
    int result = 0;
    for (int i:new ArrayIterable<>(is)){
      result += i;
    }

    assertEquals("Iterator muss über alle Elemente iterieren",15,result);
  }

  @Test
  public void test5ar() {
    int result = 0;
    for (Object i:new ArrayIterable<>(os)){
      result += 1;
    }

    assertEquals("Iterator darf kein Element für leeren Array liefern",0,result);
  }

  @Test
  public void test1str() {
    assertFalse("über leeren String gibt es nichts zu iterieren",new IterableString("").iterator().hasNext());
  }
  @Test
  public void test2str() {
    assertTrue("über nichtleeren String gibt es etwas zu iterieren",new IterableString(" ").iterator().hasNext());
  }
  @Test
  public void test3str() {
    IterableString is = new IterableString("hallo");
    Iterator<Character> it = is.iterator();
    assertEquals("1. Zeichen von \"hallo\" falsch", 'h', it.next().charValue());
    assertEquals("2. Zeichen von \"hallo\" falsch", 'a', it.next().charValue());
    assertEquals("3. Zeichen von \"hallo\" falsch", 'l', it.next().charValue());
    assertEquals("4. Zeichen von \"hallo\" falsch", 'l', it.next().charValue());
    assertEquals("5. Zeichen von \"hallo\" falsch", 'o', it.next().charValue());
    assertFalse("Ende des Strings nicht erkannt. hasNext falsch",it.hasNext());
    
  }

  @Test
  public void test4str() {
    IterableString is = new IterableString("hallo");
    Iterator<Character> it = is.iterator();
    Iterator<Character> it2 = is.iterator();
    it2.next();
    assertEquals("Iteratoren nicht unabhängig? 1. Zeichen von \"hallo\" falsch", 'h', it.next().charValue());
    assertEquals("Iteratoren nicht unabhängig? 2. Zeichen von \"hallo\" falsch", 'a', it.next().charValue());
    assertEquals("Iteratoren nicht unabhängig? 3. Zeichen von \"hallo\" falsch", 'l', it.next().charValue());
    it2.next();
    assertEquals("Iteratoren nicht unabhängig? 4. Zeichen von \"hallo\" falsch", 'l', it.next().charValue());
    it2.next();
    it2.next();
    assertEquals("Iteratoren nicht unabhängig? 5. Zeichen von \"hallo\" falsch", 'o', it.next().charValue());
    it2.next();
    assertFalse("Iteratoren nicht unabhängig? Ende des Strings nicht erkannt. hasNext falsch",it.hasNext());
    
  }
  
  @Test
  public void test5str() {
    IterableString is = new IterableString("›\t\nλ漢");
    Iterator<Character> it = is.iterator();
    assertEquals("1. Zeichen von \"›\t\nλ漢\" falsch", '›', it.next().charValue());
    assertEquals("2. Zeichen von \"›\t\nλ漢\" falsch", '\t', it.next().charValue());
    assertEquals("3. Zeichen von \"›\t\nλ漢\" falsch", '\n', it.next().charValue());
    assertEquals("4. Zeichen von \"›\t\nλ漢\" falsch", 'λ', it.next().charValue());
    assertEquals("5. Zeichen von \"›\t\nλ漢\" falsch", '漢', it.next().charValue());
    assertFalse("Ende des Strings nicht erkannt. hasNext falsch",it.hasNext());
    
  }


  @Test
  public void test1line() {
    Lines lns = new Lines("");
    Iterator<String> ls = lns.iterator();
    assertFalse("leerer String enthält keine Zeilen", ls.hasNext());
  }
  @Test
  public void test2line() {
    Lines lns = new Lines("hallo");
    Iterator<String> ls = lns.iterator();
    assertEquals("hallo", ls.next());
    assertFalse("String ohne Zeilenende hat nur eineZeile", ls.hasNext());
  }
  @Test
  public void test3line() {
    Lines lns = new Lines("hallo"+NEW_LINE+"welt"+NEW_LINE);
    Iterator<String> ls = lns.iterator();
    assertEquals("hallo", ls.next());
    assertEquals("welt", ls.next());
    assertEquals("String endet mit leerer Zeile","", ls.next());
    assertFalse("leerer String enthält keine Zeilen", ls.hasNext());
  }
  
  @Test
  public void test5line() {
    Lines lns = new Lines("hallo"+NEW_LINE+"welt"+NEW_LINE+"world");
    Iterator<String> ls = lns.iterator();
    assertEquals("hallo", ls.next());
    assertEquals("welt", ls.next());
    assertEquals("world", ls.next());
    assertFalse("leerer String enthält keine Zeilen", ls.hasNext());
  }
  @Test
  public void test6line() {
    Lines lns = new Lines("hallo"+NEW_LINE+NEW_LINE+NEW_LINE+"welt");
    Iterator<String> ls = lns.iterator();
    assertEquals("hallo", ls.next());
    assertEquals("", ls.next());
    assertEquals("", ls.next());
    assertEquals("welt", ls.next());
    assertFalse("leerer String enthält keine Zeilen", ls.hasNext());
  }
  
  @Test
  public void test4line() {
    Lines lns = new Lines(NEW_LINE);
    Iterator<String> ls = lns.iterator();
    assertEquals("", ls.next());
    assertEquals("", ls.next());
    assertFalse("leerer String enthält keine Zeilen", ls.hasNext());
  }
  @Test
  public void test7line() {
    Lines lns = new Lines(NEW_LINE+NEW_LINE);
    Iterator<String> ls = lns.iterator();
    assertEquals("", ls.next());
    assertEquals("", ls.next());
    assertEquals("", ls.next());
    assertFalse("leerer String enthält keine Zeilen", ls.hasNext());
  }


  @Test
  public void test1words() {
    Iterable<String> ws = new Words("hallo");
    assertEquals("falsche Wortanzahl",1,size(ws));

    Iterator<String> it = ws.iterator();
    assertTrue("ein Wort muss erkannt werden",it.hasNext());
    assertEquals("erste Wort darf nichts abgeschnitten haben","hallo",it.next());
  }
  @Test
  public void test2words() {
    Iterable<String> ws = new Words("");
    assertEquals("falsche Wortanzahl",0,size(ws));

    Iterator<String> it = ws.iterator();
    assertFalse("leerer String darf kein Wort enthalten",it.hasNext());
  }
  @Test
  public void test3words() {
    Iterable<String> ws = new Words("    hallo");
    assertEquals("falsche Wortanzahl",1,size(ws));

    Iterator<String> it = ws.iterator();
    assertTrue("ein Wort muss erkannt werden",it.hasNext());
    assertEquals("erste Wort darf nichts abgeschnitten haben","hallo",it.next());
  }
  @Test
  public void test4words() {
    Iterable<String> ws = new Words("           ");
    assertEquals("falsche Wortanzahl",0,size(ws));

    Iterator<String> it = ws.iterator();
    assertFalse("leerer String darf kein Wort enthalten",it.hasNext());
  }

  @Test
  public void test5words() {
    Iterable<String> ws = new Words("    hallo freunde, wie gehts   ");
    assertEquals("falsche Wortanzahl",4,size(ws));

    Iterator<String> it = ws.iterator();
    assertTrue("ein Wort muss erkannt werden",it.hasNext());
    assertEquals("erste Wort darf nichts abgeschnitten haben","hallo",it.next());
    assertEquals("zweite Wort darf nichts abgeschnitten haben","freunde,",it.next());
    assertEquals("dritte Wort darf nichts abgeschnitten haben","wie",it.next());
    assertEquals("vierte Wort darf nichts abgeschnitten haben","gehts",it.next());
    
  }

  @Test
  public void test6words() {
    Iterable<String> ws = new Words("    hallo freunde, wie gehts   ");
    assertEquals("falsche Wortanzahl",4,size(ws));

    Iterator<String> it = ws.iterator();
    assertTrue("ein Wort muss erkannt werden",it.hasNext());
    assertEquals("erste Wort darf nichts abgeschnitten haben","hallo",it.next());
    assertEquals("zweite Wort darf nichts abgeschnitten haben","freunde,",it.next());
    assertEquals("dritte Wort darf nichts abgeschnitten haben","wie",it.next());
    assertEquals("vierte Wort darf nichts abgeschnitten haben","gehts",it.next());
    
    it = ws.iterator();
    assertTrue("ein Wort muss erkannt werden",it.hasNext());
    assertEquals("erste Wort darf nichts abgeschnitten haben","hallo",it.next());
    assertEquals("zweite Wort darf nichts abgeschnitten haben","freunde,",it.next());
    assertEquals("dritte Wort darf nichts abgeschnitten haben","wie",it.next());
    assertEquals("vierte Wort darf nichts abgeschnitten haben","gehts",it.next());
      
  }

  @Test
  public void test1index() {
    IndexIterable<Long> its = new IndexIterable<>(x->x);
    Iterator<Long> is = its.iterator();
    assertEquals(1l, is.next().longValue());
    assertEquals(2l, is.next().longValue());
    assertEquals(3l, is.next().longValue());
    assertEquals(4l, is.next().longValue());
  }
  @Test
  public void test2index() {
    IndexIterable<Long> its = new IndexIterable<>(x->x);
    Iterator<Long> is = its.iterator();
    is.next();
    is.next();
    is.next();
    is.next();
    is.next();
    is.next();
    is.next();
    is = its.iterator();
    assertEquals(1l, is.next().longValue());
    assertEquals(2l, is.next().longValue());
    assertEquals(3l, is.next().longValue());
    assertEquals(4l, is.next().longValue());
  }


  @Test
  public void test3index() {
    IndexIterable<Long> its = new IndexIterable<>(x->x*x);
    Iterator<Long> is = its.iterator();
    assertEquals(1l, is.next().longValue());
    assertEquals(4l, is.next().longValue());
    assertEquals(9l, is.next().longValue());
    assertEquals(16l, is.next().longValue());
  }

  @Test
  public void test4index() {
    IndexIterable<Long> its = new IndexIterable<>(x->x*x);
    Iterator<Long> is = its.iterator();
    assertEquals(1l, is.next().longValue());
    assertEquals(4l, is.next().longValue());
    Iterator<Long> is2 = its.iterator();
    assertEquals(1l, is2.next().longValue());
    assertEquals(4l, is2.next().longValue());
  }

  @Test
  public void test5index() {
    IndexIterable<String> its = new IndexIterable<>(x->x+"");
    Iterator<String> xs = its.iterator();
    assertEquals("1", xs.next());
    assertEquals("2", xs.next());
    assertEquals("3", xs.next());
    assertEquals("4", xs.next());
  }

  @Test
  public void test1fun() {
    Iterable<Integer> is = new GenerationIterable<Integer>(0, x->x+1);
    Iterator<Integer> it = is.iterator();
    assertEquals(0 ,it.next().intValue());
    assertEquals(1 ,it.next().intValue());
    assertEquals(2 ,it.next().intValue());
    assertEquals(3 ,it.next().intValue());
    assertEquals(4 ,it.next().intValue());
  }

  @Test
  public void test2fun() {
    Iterable<Long> is = new GenerationIterable<Long>(1l, x->x*2);
    Iterator<Long> it = is.iterator();
    assertEquals(1 ,it.next().longValue());
    assertEquals(2 ,it.next().longValue());
    assertEquals(4 ,it.next().longValue());
    assertEquals(8 ,it.next().longValue());
    assertEquals(16 ,it.next().longValue());
    assertEquals(32 ,it.next().longValue());
    assertEquals(64 ,it.next().longValue());
    assertEquals(128 ,it.next().longValue());
    assertEquals(256 ,it.next().longValue());
    assertEquals(512 ,it.next().longValue());
    assertEquals(1024 ,it.next().longValue());
    assertEquals(2048 ,it.next().longValue());
    assertEquals(4096 ,it.next().longValue());
    assertEquals(8192 ,it.next().longValue());
  }
  
  @Test
  public void test3fun() {
    Iterable<String> is = new GenerationIterable<String>("a", x->x+1);
    Iterator<String> it = is.iterator();
    assertEquals("a" ,it.next());
    assertEquals("a1" ,it.next());
    assertEquals("a11" ,it.next());
    assertEquals("a111" ,it.next());
    assertEquals("a1111" ,it.next());
    assertEquals("a11111" ,it.next());
  }
  @Test
  public void test1odd() {
    Iterator<Long> it = new OddIterable().iterator();
    assertEquals(1l, it.next().longValue());
    assertEquals(3l, it.next().longValue());
    assertEquals(5l, it.next().longValue());
    assertEquals(7l, it.next().longValue());
    assertEquals(9l, it.next().longValue());
    assertEquals(11l, it.next().longValue());
  }  

  @Test
  public void test1limit() {
    var it = new Limit<>(new GenerationIterable<String>("a", x->x+1),2).iterator();
    assertTrue(it.hasNext());
    assertEquals("a" ,it.next());
    assertTrue(it.hasNext());
    assertEquals("a1" ,it.next());
    assertFalse(it.hasNext());
  }

  @Test
  public void test1maperable() {
    var it = new Maperable<>(new Limit<>(new GenerationIterable<String>("a", x->x+1),2),x->x.length()).iterator();
    assertTrue(it.hasNext());
    assertEquals(1l ,it.next().longValue());
    assertTrue(it.hasNext());
    assertEquals(2l ,it.next().longValue());
    assertFalse(it.hasNext());
  }

  @Test
  public void test1filterable() {
    var it = new Limit<>(new Filterable<>(new GenerationIterable<>(1L, x->x+1),x->x%3==0),3).iterator();
    assertTrue(it.hasNext());
    assertEquals(3l ,it.next().longValue());
    assertTrue(it.hasNext());
    assertEquals(6l ,it.next().longValue());
    assertTrue(it.hasNext());
    assertEquals(9l ,it.next().longValue());
    assertFalse(it.hasNext());
  }
}