
package SS2021.Java.Blatt6.name.panitz.json;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.bson.Document;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public interface Json{



  static String ex1 = """
{ "vorname": "Eva"
, "name": "Luator"
, "gebJahr": 1962
}
""";



  static String xs = """
{ "head": 1
, "tail": 
  { "head": 2
  , "tail": 
    { "head": 3
    , "tail":
       { "head": 4
       , "tail": null
       }
    }
  }
}
""";



  static String windsor = """
{ "name": "George"
, "children": 
  [{ "nam": "Elizabeth"
   , "children":  
     [{ "name": "Charles"
      , "children": 
        [{ "name": "William"
         , "children": 
           [{"name":"George"},{"name":"Charlotte"},{"name":"Louise"}]}
        ,{ "name": "Henry","children": [{ "name": "Archie"}]}
      ]}
     ,{ "name": "Anne"
      , "children" :
         [{ "name": "Peter"
          , "children": [{ "name": "Savannah"},{ "name": "Isla"}]}
         ,{ "name": "Zara","children":[{ "name": "Mia"},{ "name": "Lena"}]}
         ]}
     ,{ "name": "Andrew"
      , "children": [{"name":"Beatrice"},{"name": "Eugenie"}]}
     ,{ "name": "Edward"
      , "children": [{"name":"Louise"},{ "name": "James"}]}]}
  ,{ "name": "Magaret"
   , "children":  
     [{ "name": "David"
      , "children": [{ "name": "Charles"},{ "name": "Margarita"}]}
     ,{ "name": "Sarah"
      , "children": [{ "name": "Samuel"},{ "name": "Arthur"}]}
     ]}
  ]}
""";


  public static record JsonLong(long n) implements Json{}
  public static record JsonDouble(double n) implements Json{}
  public static record JsonBoolean(boolean b) implements Json{}
  public static record JsonString(String s) implements Json{}
  public static record JsonObject(Map<String,Json> map) implements Json{}
  public static record JsonArray(List<Json> elements) implements Json{}



  public static JsonObject parse(Reader in) throws Exception{
    var codePointCharStream = CharStreams.fromReader(in);
    var lexer = new JsonGrammarLexer(codePointCharStream);
    var parser = new JsonGrammarParser(new CommonTokenStream(lexer));

    var tree = parser.obj();
    ParseTreeWalker.DEFAULT.walk(new JsonTreeBuilder(), tree);
    return tree.result;
  }


/**Aufgabe 1**/
//Tutor
  default void forEach(Consumer<JsonObject> consume){
    if(this instanceof JsonObject object) {
      consume.accept(object);

      object.map().entrySet().stream().forEach(entry -> {
        if (entry.getValue()!=null) {
          entry.getValue().forEach(consume); //Rekursiv foreach
        }
      });
    }

    if(this instanceof JsonArray array) {
      array.elements().stream().forEach(x -> x.forEach(consume));
    }
  }



  default Set<String> collectNames(){
    var result = new HashSet<String>();
    collectNames(result);
    return result;
  }

  // ToDo
  default void collectNames(Set<String> result) {
    this.forEach(x -> {
      if (x.map().get("name") instanceof JsonString str) {
        result.add(str.s);
      }
    });
  }



  default void write(Writer out) throws Exception{
    write("\n",out);
  }


  default void write(String indent, Writer out) throws Exception{
    if (this instanceof JsonString s) {
      out.write("\""+s.s+"\"");
    }
    else if (this instanceof JsonLong jsonLong) {
      out.write(Long.toString(jsonLong.n));
    }
    else if (this instanceof JsonDouble jsonDouble) {
      out.write(Double.toString(jsonDouble.n));
    }
    else if (this instanceof JsonBoolean jsonBoolean) {
      out.write(Boolean.toString(jsonBoolean.b));
    }
    else if (this instanceof JsonArray jsonArray){
      boolean first=true;
      out.write("[");

      for (var x:jsonArray.elements) {
        try {
          if(!first) {
            out.write(",");
          }
          first=false;
          x.write(indent + "  ", out);

        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
      out.write(indent+"]");
    }

    else if (this instanceof JsonObject o){
      out.write(indent+"{");
      boolean last = true;
      for (Map.Entry<String, Json> entry : o.map.entrySet()) {
        try {
          if(!last) {
            out.write(",");
          }
          last = false;
          out.write("\""+entry.getKey()+"\""+":");
          if(entry.getValue() == null) {
            out.write("null");
          }
          else {
            entry.getValue().write(indent, out);
          }
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }

      out.write(indent+"}");
    }
    else
      throw new RuntimeException("Missing pattern: "+this.getClass());
  }



  default String show() {
    try{
      var out = new StringWriter();
      write(out);
      return out.toString();
    }catch(Exception e){ return "";}
  }


/**Aufgaeb 2**/
  static record Person(String name,String vorname,int gebJahr){


//Tutor
    Json toJson(){
      var map = new HashMap<String,Json>();
      /* ToDo: Fill map with correct data. */
      map.put("name", new JsonString(name()));
      map.put("vorname", new JsonString(vorname()));
      map.put("gebJahr", new JsonLong(gebJahr()));
      return  new JsonObject(map);
    }
  }

//ToDo
  default Person getPerson() {
    if (this instanceof JsonObject obj ) {
      if(obj.map.containsKey("name") &&
              obj.map.containsKey("vorname") &&
              obj.map.containsKey("gebJahr")) {

        return new Person(obj.map.get("name").show(),
                obj.map.get("vorname").show(),
                obj.map.get("gebJahr").toString().toCharArray()[0]);
      }
    }
    throw new RuntimeException("json not a Person: " + this);
  }

  public static void main(String[] args) throws Exception {
    var x = parse(new StringReader(ex1));
    System.out.println(x.getPerson());
  }


/**Aufgabe 3**/
  default Document toBSON(){
    if (this instanceof JsonObject o){
      return (Document)toBSON2();
    }
    return null;
  }

//Tutor
  default Object toBSON2(){
    if (this instanceof JsonObject o) {
      var doc = new Document();
      for (var element : o.map.entrySet()) {
        doc.append(element.getKey(), element.getValue() == null ? null : element.getValue().toBSON2());
      }
      return doc;
    }
    else if (this instanceof JsonArray array) {
      return array.elements.stream().map(json -> json.toBSON2()).collect(Collectors.toList());
    }
    else if (this instanceof JsonDouble jsonDouble) {
      return jsonDouble.n();
    }
    else if (this instanceof JsonLong jsonLong) {
      return jsonLong.n();
    }
    else if (this instanceof JsonBoolean jsonBoolean) {
      return jsonBoolean.b();
    }
    else if (this instanceof JsonString jsonString) {
      return jsonString.s();
    }
    return null;
  }


  static Json toJson(Document bson){
    try{
      return null;/*ToDo*/
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }



  default void saveToCollection(String dbname,String collectionName){
    var mongoClient = MongoClients.create();
    var database = mongoClient.getDatabase(dbname);
    var collection = database.getCollection(collectionName);
    collection.insertOne(toBSON());
  }



  static MongoCollection<Document>
              getCollection(String dbname,String collectionName){
    var mongoClient = MongoClients.create();
    var database = mongoClient.getDatabase(dbname);
    return database.getCollection(collectionName);
  }



  static Set<String> getNames(String dbname,String collectionName){
    var result = new HashSet<String>();
    /* ToDo */
    return result;
  }

}
