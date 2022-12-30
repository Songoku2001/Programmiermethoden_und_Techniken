
package SS2021.Java.Blatt3.name.panitz.util;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static Blatt3.name.panitz.util.Strom.*;
import static org.junit.Assert.assertEquals;
public class TTest {

  @Before
  public void init() {

  }

@Test
  public void readBinary1() {
    assertEquals("reading 101010",42L,readBinary("101010"));
  }
  @Test
  public void readBinary2() {
    assertEquals("11111111",255L,readBinary("11111111"));
  }
  @Test
  public void readBinary3() {
    assertEquals("0",0L,readBinary("00000"));
  }
  @Test
  public void readBinary4() {
    assertEquals("000001",1L,readBinary("000001"));
  }
  @Test
  public void quersumme1() {
    assertEquals("1234",10L,quersumme(1234L));
  }
  @Test
  public void quersumme2() {
    assertEquals("88888",40L,quersumme(88888L));
  }
  @Test
  public void quersumme3() {
    assertEquals("0",0L,quersumme(0L));
  }
  @Test
  public void quersumme4() {
    assertEquals("111111",6L,quersumme(111111L));
  }
  @Test
  public void factorial1() {
    assertEquals("1",1L,factorial(1));
  }
  @Test
  public void factorial2() {
    assertEquals("5",120L,factorial(5));
  }
  @Test
  public void factorial3() {
    assertEquals("10",3628800L,factorial(10));
  }
  @Test
  public void factorial4() {
    assertEquals("3",6L,factorial(3));
  }

  @Test
  public void asBinary1() {
    assertEquals("1","1",asBinary(1));
  }
  @Test
  public void asBinary2() {
    assertEquals("0","0",asBinary(0));
  }
  @Test
  public void asBinary3() {
    assertEquals("42","101010",asBinary(42));
  }
  @Test
  public void asBinary4() {
    assertEquals("255","11111111",asBinary(255));
  }
  @Test
  public void asBinary5() {
    assertEquals("256","100000000",asBinary(256));
  }
  @Test
  public void convertToBase1() {
    assertEquals("2,256","100000000",convertToBase(2,256));
  }
  @Test
  public void convertToBase2() {
    assertEquals("3,256","100111",convertToBase(3,256));
  }
  @Test
  public void convertToBase3() {
    assertEquals("4,256","10000",convertToBase(4,256));
  }
  @Test
  public void convertToBase4() {
    assertEquals("5,256","2011",convertToBase(5,256));
  }
  @Test
  public void convertToBase5() {
    assertEquals("6,256","1104",convertToBase(6,256));
  }
  @Test
  public void convertToBase6() {
    assertEquals("16,255","FF",convertToBase(16,255));
  }
  @Test
  public void convertToBase7() {
    assertEquals("16,0","0",convertToBase(16,0));
  }
  @Test
  public void readFromBase1() {
    assertEquals("reading 2 101010",42L,readFromBase(2,"101010"));
  }
  @Test
  public void readFromBase2() {
    assertEquals("reading 3 101010",273L,readFromBase(3,"101010"));
  }
  @Test
  public void readFromBase3() {
    assertEquals("reading 4 101010",1092L,readFromBase(4,"101010"));
  }
  @Test
  public void readFromBase4() {
    assertEquals("reading 5 101010",3255L,readFromBase(5,"101010"));
  }
  @Test
  public void readFromBase5() {
    assertEquals("reading 6 101010",7998,readFromBase(6,"101010"));
  }
  @Test
  public void readFromBase6() {
    assertEquals("reading 16 101010",1052688,readFromBase(16,"101010"));
  }


  @Test
  public void fib100_1() {
    assertEquals("fibs100() hat falsche Anzahl",100,fibs100().count());
  }
  @Test
  public void fibs1() {
    assertEquals("Falsche 9. Fib-Zahl",21,fibs().skip(8).findFirst().get().longValue());
  }
  @Test
  public void fibs2() {
    assertEquals("Falsche 1. Fib-Zahl",0,fibs().findFirst().get().longValue());
  }
  @Test
  public void fibs3() {
    assertEquals("Falsche 2. Fib-Zahl",1,fibs().skip(1).findFirst().get().longValue());
  }
  @Test
  public void fibs4() {
    assertEquals("Falsche 3. Fib-Zahl",1,fibs().skip(2).findFirst().get().longValue());
  }
  @Test
  public void fibs5() {
    assertEquals("Falscher 4. TwoLong",new TwoLong(2,3),fibPairs().skip(3).findFirst().get());
  }
  @Test
  public void fibs6() {
    assertEquals("Falscher 5. TwoLong",new TwoLong(3,5),fibPairs().skip(4).findFirst().get());
  }
  @Test
  public void fibs7() {
    assertEquals("Falsche 4. Fib-Zahl",2,fibs().skip(3).findFirst().get().longValue());
  }
  @Test
  public void fibs8() {
    assertEquals("Falsche 50. Fib-Zahl",7778742049L,fib(50));
  }

 @Test
  public void facs1() {
    assertEquals("Falsche 1. Fac-Zahl",new BigInteger("1"),fac(1));
  }
 @Test
  public void facs2() {
    assertEquals("Falsche 2. Fac-Zahl",new BigInteger("2"),fac(2));
  }
 @Test
  public void facs3() {
    assertEquals("Falsche 3. Fac-Zahl",new BigInteger("6"),fac(3));
  }
 @Test
  public void facs4() {
    assertEquals("Falsche 4. Fac-Zahl",new BigInteger("24"),fac(4));
  }


  @Test
  public void facs5() {
    assertEquals("Falsche 100. Fac-Zahl",new BigInteger("93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000"),fac(100));
  }
  

  int i = 0;
  @Test
  public void test1() {
    SpliterateString ss1 = new SpliterateString("1234567890");
    i = 0;
    ss1.forEachRemaining(x->i++);
    assertEquals("»1234567890« sollte über 10 chars iterieren", 10, i);
  }

  @Test
  public void test2() {
    Stream<Character> stream 
    = StreamSupport
      .stream(new SpliterateString("hallo"), false);
    
    assertEquals("erster Aufruf sollte erstes Zeichen des String liefern", 'h', stream.findFirst().get().charValue());
  }
  @Test
  public void test3() {
    Stream<Character> stream 
    = StreamSupport
      .stream(new SpliterateString("hallo"), false);
    StringBuffer result = new StringBuffer();
    stream.forEach(c -> result.append(c));
    assertEquals("erster Aufruf sollte erstes Zeichen des String liefern", "hallo", result.toString());
  }


}