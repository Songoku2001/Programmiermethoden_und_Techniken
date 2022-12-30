package SS2021.Java.Blatt5.name.panitz.xml;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Set;

import static Blatt5.name.panitz.xml.XML.DOMUtil.*;
import static Blatt5.name.panitz.xml.XML.GetBirthYears;
import static Blatt5.name.panitz.xml.XML.toMyXML;
import static org.junit.Assert.*;

public class XMLTest {

  @Rule
  public Timeout globalTimeout = Timeout.seconds(1); 
  
  Node windsor;
  private String windsorString = """
<?xml version="1.0" ?>
<node>
  <element born="1865" death="1936">George V</element>
  <node>
    <element born="1894" death="1972">Edward VIII</element>
  </node>
  <node>
    <element born="1895">George VI</element>
    <node>
      <element  born="1926">Elizabeth</element>
      <node  width="7">
  <element  born="1948">Charles</element>
  <node>
          <element  born="1982">William</element>
          <node><element  born="2013">George</element></node>
          <node><element  born="2015">Charlotte</element></node>
          <node><element  born="2018">Louis</element></node>
  </node>
  <node>
          <element born="1984">Henry</element>
          <node><element  born="2019">Archie</element></node>
  </node>
      </node>
      <node>
  <element born="1950">Anne</element>
  <node>
          <element  born="1977">Peter</element>
          <node><element  born="2010">Savannah</element></node>
          <node><element  born="2012">Isla</element></node>
  </node>
  <node>
          <element  born="1981">Zara</element>
          <node><element born="2014">Mia</element></node>
          <node><element born="2018">Lena</element></node>
          <node><element born="2021">Lucas</element></node>
  </node>
      </node>
      <node>
  <element  born="1960">Andrew</element>
  <node><element  born="1988">Beatrice</element></node>
  <node>
          <element born="1990">Eugenie</element>
    <node><element born="2021">August</element></node>
  </node>
      </node>
      <node>
  <element  born="1964">Edward</element>
  <node><element  born="2003">Louise</element></node>
  <node><element  born="2007">James</element></node>
      </node>
    </node>
    <node>
      <element  born="1930">Magaret</element>
      <node>
  <element  born="1961">David</element>
  <node><element  born="1999">Charles</element></node>
  <node><element  born="2002">Margarita</element></node>
      </node>
      <node width="4">
  <element  born="1964">Sarah</element>
  <node width="42"><element  born="1996">Samuel</element></node>
  <node width="40"><element born="1999">Arthur</element></node>
      </node>
    </node>
  </node>
  <node>
    <element born="1897" death="1965">Mary</element>
    <node>
      <element born="1923" death="2011">George</element>
      <node>
  <element born="1950">David</element>
  <node><element born="1976">Emely</element></node>
  <node><element born="1978">Benjamin</element></node>
  <node><element born="1980">Alexander</element></node>
  <node><element born="1982">Edward</element></node>
      </node>
      <node>
  <element born="1953">James</element>
      </node>
      <node>
  <element born="1955">Jeremy</element>
      </node>
    </node>
    <node>
      <element born="1924" death="1998">Gerald</element>
    </node>
  </node>
  <node>
    <element born="1900" death="1974">Henry</element>
    <node>
      <element born="1941" death="1972">William</element>
    </node>
    <node>
      <element born="1944" >Richard</element>
      <node>
  <element born="1974" >Alexander</element>
  <node><element born="2007" >Xan</element></node>
  <node><element born="2010" >Cosima</element></node>
      </node>
      <node>
  <element born="1977" >Davina</element>
  <node>
    <element born="2010" >Senna</element>
  </node>
  <node>
    <element born="2012" >Tāne</element>
  </node>
      </node>
      <node>
  <element born="1980" >Rose</element>
  <node>
    <element born="2010" >Lyla</element>
  </node>
  <node>
    <element born="2012" >Rufus</element>
  </node>
      </node>
    </node>
  </node>
  <node>
    <element born="1902" death="1942">George</element>
    <node>
      <element born="1935" >Edward</element>
      <node>
  <element born="1962" >George</element>
  <node>
    <element born="1999" >Edward</element>
  </node>
  <node>
    <element born="1992" >Marina</element>
  </node>
  <node>
    <element born="1995" >Amelia</element>
  </node>
      </node>
      <node>
  <element born="1964" >Helen</element>
  <node>
    <element born="1994" >Columbus</element>
  </node>
  <node><element born="1996" >Cassius</element></node>
  <node>
    <element born="2003" >Eloise</element>
  </node>
  <node>
    <element born="2004" >Estella</element>
  </node>
      </node>
      <node>
  <element born="1970" >Nicholas</element>
  <node>
    <element born="2007" >Albert</element>
  </node>
  <node>
    <element born="2009" >Leopold</element>
  </node>
  <node>
    <element born="2014" >Louis</element>
  </node>
      </node>
    </node>
  </node>
  <node>
    <element born="1936" >Alexandra</element>
    <node>
      <element born="1964" >James</element>
      <node>
  <element born="1994" >Flora</element></node>
  <node><element born="1996" >Alexander</element></node>
    </node>
    <node>
      <element born="1966" >Marina</element>
      <node>
  <element born="1990" >Zenouska</element>
      </node>
      <node>
  <element born="1993" >Christian</element>
      </node>
    </node>
  </node>
  <node>
    <element born="1942" >Michael</element>
    <node>
      <element born="1979" >Frederic</element>
      <node>
  <element born="2013" >Maud</element>
      </node>
      <node>
  <element born="2016" >Isabella</element>
      </node>
    </node>
    <node>
      <element born="1981" >Gabriella</element>
    </node>
  </node>
  <node>
    <element born="1905" death="1919">John</element>
  </node>
</node>
""";

  Node empty;
  
  @Before
  public void setUp() throws Exception {
    windsor =  parse(windsorString);
    empty = parse("<?xml version=\"1.0\" ?>\n<a/>");
    //dom.streamCalls = 0;
  }

  private Element parse(String xml) throws SAXException, IOException, ParserConfigurationException {
    return DocumentBuilderFactory
    .newInstance()
    .newDocumentBuilder()
    .parse(new InputSource(new StringReader(xml))).getDocumentElement();
  }

  @Test
  public void testHeight1() {
    assertEquals("Ein Baum nur aus einem Wurzeltag soll die Höhe 1 haben.",1, height(empty));

  }

  @Test
  public void testHeight2() {
    assertEquals("Ein Baum nur aus einem Wurzeltag soll die Höhe 1 haben.",1, toMyXML(empty).height());

  }


  @Test
  public void testHeight3() {
    assertEquals("Das Beispieldokument hat eine Höhe von 8, die nicht korrekt berechnet wird.", 8, height(windsor));
  }

  @Test
  public void testHeight4() {
    assertEquals("Das Beispieldokument hat eine Höhee von 8, die nicht korrekt berechnet wird.", 8, toMyXML(windsor).height());
  }


  @Test
  public void testself() {
    var w = toMyXML(windsor);
    assertEquals("self Achse falsch.", List.of(w), w.self());
  }


  @Test
  public void testchild() {
    var w = toMyXML(windsor);
    assertEquals("child Achse falsch.", 9, w.child().size());
  }

  @Test
  public void testDescendant() {
    var w = toMyXML(windsor);
    assertEquals("descendant Achse falsch.", 248, w.descendant().size());
  }

  @Test
  public void testParent1() {
    var w = toMyXML(windsor);
    assertEquals("parent Achse falsch.", 0, w.parent().size());
  }

  @Test
  public void testParent2() {
    var w = toMyXML(windsor);
    assertEquals("parent Achse falsch.", 1, w.child().get(3).parent().size());
  }

  @Test
  public void testAncestor1() {
    var w = toMyXML(windsor);
    assertEquals("Ancestor Achse falsch.", 2, w.child().get(3).child().get(1).ancestor().size());
  }

  @Test
  public void testAncestor2() {
    var w = toMyXML(windsor);
    assertEquals("Ancestor Achse falsch.", 0, w.ancestor().size());
  }


  @Test
  public void followingSibling1() {
    var w = toMyXML(windsor);
    assertEquals("followingSibling Achse falsch.", 2, w.child().get(3).child().get(0).followingSibling().size());
  }

  @Test
  public void followingSibling2() {
    var w = toMyXML(windsor);
    assertEquals("followingSibling Achse falsch.", 0, w.followingSibling().size());
  }

  @Test
  public void following1() {
    var w = toMyXML(windsor);
    assertEquals("following Achse falsch.", 147, w.child().get(3).child().get(0).following().size());
  }

  @Test
  public void following2() {
    var w = toMyXML(windsor);
    assertEquals("following Achse falsch.", 0, w.following().size());
  }



  @Test
  public void testText1() {
    assertEquals("Das Beispieldokument enthält keinen Text.", "",text(empty));
//    assertEquals("Ohne Kinder, sollte auch kein Spliterator abgearbeitet werden.",0, streamCalls);  
  }
  @Test
  public void testText3() {
  assertEquals("Das Beispieldokument enthält Text, der falsch gesammelt wird.","GeorgeVEdwardVIIIGeorgeVIElizabethCharlesWilliamGeorgeCharlotteLouisHenryArchieAnnePeterSavannahIslaZaraMiaLenaLucasAndrewBeatriceEugenieAugustEdwardLouiseJamesMagaretDavidCharlesMargaritaSarahSamuelArthurMaryGeorgeDavidEmelyBenjaminAlexanderEdwardJamesJeremyGeraldHenryWilliamRichardAlexanderXanCosimaDavinaSennaTāneRoseLylaRufusGeorgeEdwardGeorgeEdwardMarinaAmeliaHelenColumbusCassiusEloiseEstellaNicholasAlbertLeopoldLouisAlexandraJamesFloraAlexanderMarinaZenouskaChristianMichaelFredericMaudIsabellaGabriellaJohn", text(windsor).replaceAll("\\s", ""));
//    assertEquals("Ohne Kinder, sollte auch kein Spliterator abgearbeitet werden.",0, streamCalls);  
  }

  @Test
  public void testCollectTagnames1() {
    Set<String> result = collectTagnames(windsor);
    assertEquals("Falsche Anzahl von Tagnamen im Ergebnis.",2,result.size());
    assertTrue("Tagname »element« fehlt in Ergebnismenge.",result.contains("element"));
    assertTrue("Tagname »node« fehlt in Ergebnismenge.",result.contains("node"));
  }
  @Test
  public void testCollectTagnames3() {
    Set<String> result = collectTagnames(empty);
    assertEquals("Falsche Anzahl von Tagnamen im Ergebnis.",1,result.size());
    assertTrue("Tagname »a« fehlt in Ergebnismenge.",result.contains("a"));
    assertFalse("Tagname »node« irrtümlich in Ergebnismenge.",result.contains("node"));
  }
  @Test
  public void testAttr1() {
    assertEquals("Faksches Ergebnis: Attribut width existiert nicht im Dokument.",0, getMaxHAttributeValue(empty,"width"));
  }
  @Test
  public void testAttr2() {
    assertEquals("Falsche maximaler Attributwert",42, getMaxHAttributeValue(windsor,"width"));
  }

  @Test
  public void getBirthYears() throws Exception {
    var by = new GetBirthYears();
    XML.parse(new StringReader(windsorString),by); 
    assertEquals("Falsche Menge von Geburtsjahren",Set.of(1923, 1924, 1926, 1930, 1935, 1936, 1941, 1942, 1944, 1948, 1950, 1953, 1955, 1960, 1961, 1962, 1964, 1966, 1970, 1974, 1976, 1977, 1978, 1979, 1980, 1981, 1982, 1984, 1988, 1990, 1992, 1865, 1993, 1994, 1995, 1996, 1999, 2002, 2003, 2004, 2007, 2009, 2010, 2012, 2013, 2014, 2015, 2016, 2018, 2019, 2021, 1894, 1895, 1897, 1900, 1902, 1905

),by.result );
  }
}