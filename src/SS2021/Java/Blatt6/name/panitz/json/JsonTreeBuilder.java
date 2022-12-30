
package SS2021.Java.Blatt6.name.panitz.json;
import org.antlr.v4.runtime.misc.Pair;
import java.util.*;
import java.util.stream.Collectors;
import Blatt6.name.panitz.json.JsonGrammarBaseListener;
import static Blatt6.name.panitz.json.JsonGrammarParser.*;
import static Blatt6.name.panitz.json.Json.*;

public class JsonTreeBuilder extends JsonGrammarBaseListener{
  @Override public void exitObj(ObjContext ctx) { 
     var map = new HashMap<String,Json>();
     ctx.result = new JsonObject(map);
     for (var p:ctx.pair()) map.put(p.result.a,p.result.b);
  }
  @Override public void exitPair(PairContext ctx) {
    ctx.result = new Pair<>(ctx.stringB().result,ctx.json().result);
  }
  @Override public void exitArr(JsonGrammarParser.ArrContext ctx) { 
    ctx.result = new JsonArray(ctx.json().stream()
            .map(c->c.result).collect(Collectors.toList()));
  }
  @Override public void exitStringB(StringBContext ctx) { 
    var img = ctx.STRING().getText();
    ctx.result = img.substring(1,img.length()-1);
  }
  @Override public void exitJson(JsonContext ctx) { 
    if (ctx.stringB()!=null){ 
      var img = ctx.stringB().result;
      ctx.result = new JsonString(img);
    }else if (ctx.NUMBER()!=null){
      ctx.result 
        = ctx.NUMBER().getText().contains(".")
        ? new JsonDouble(Double.parseDouble(ctx.NUMBER().getText()))
        : new JsonLong(Long.parseLong(ctx.NUMBER().getText()));
    }else if (ctx.obj()!=null) ctx.result = ctx.obj().result;
    else if (ctx.arr()!=null) ctx.result = ctx.arr().result;
    else if (ctx.trueB()!=null) ctx.result = new JsonBoolean(true);
    else if (ctx.falseB()!=null) ctx.result = new JsonBoolean(false);
    else if (ctx.nullB()!=null) ctx.result = null;
  }
}