package SS2021.Java.Projekt.name.panitz.longStack;

import java.io.*;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;



@SuppressWarnings("unchecked")
public interface AST {



  public static enum BinOP{
    add ((x,y)->x+y,"+"),
    sub ((x,y)->x-y,"-"),
    mult((x,y)->x*y,"*"),
    eq  ((x,y)->x==y?1L:0L,"=");

    BinOP(BinaryOperator<Long> op,String name){
      this.op = op;
      this.name = name;
    }
    BinaryOperator<Long> op;
    String name;
  }


  public static record IntLiteral(long n) implements AST{}

  public static record Var(String name) implements AST{}

  public static record Assign(Var v, AST right) implements AST{}

  public static record OpExpr(AST left, BinOP op,AST right) implements AST{}

  public static record IfExpr(AST cond, AST alt1, AST alt2) implements AST{}

  public static record WhileExpr(AST cond, AST body) implements AST{}

  public static record Sequence(List<AST> sts) implements AST{}

  public static record FunCall(String name, List<AST> args) implements AST{}

  static record Fun(String name, List<String> args, AST body){}

  static Fun factorial= new Fun("fac",List.of("x"),
          new Sequence(List.of
                  (new Assign(new Var("r"), new IntLiteral(1))
                          ,new WhileExpr
                                  (new Var("x")
                                          ,new Sequence(List.of
                                          (new Assign
                                                          (new Var("r")
                                                                  ,new OpExpr(new Var("r"), BinOP.mult,new Var("x")))
                                                  ,new Assign
                                                          (new Var("x")
                                                                  ,new OpExpr(new Var("x"), BinOP.sub, new IntLiteral(1)))
                                          ))
                                  )
                          ,new Var("r")
                  ))
  );




  static Fun factorialRek = new Fun("f",List.of("x"),
          new IfExpr(new OpExpr(new Var("x"),BinOP.eq,new IntLiteral(0))
                  , new IntLiteral(1)
                  , new OpExpr(new Var("x"),BinOP.mult
                  ,new FunCall("f"
                  , List.of(new OpExpr(new Var("x")
                  , BinOP.sub, new IntLiteral(1)))))));




  static Fun minus =
          new Fun("minus"
                  ,List.of("x","y")
                  ,new OpExpr(new Var("x"), BinOP.sub, new Var("y")));





  static Fun fib
          = new Fun("fib",List.of("x")
          ,new IfExpr(new OpExpr(new IntLiteral(0),BinOP.eq,new Var("x"))
          ,new IntLiteral(0)
          ,new IfExpr(new OpExpr(new IntLiteral(1),BinOP.eq,new Var("x"))
          ,new IntLiteral(1)
          ,new OpExpr
          (new FunCall("fib"
                  ,List.of
                  (new OpExpr(new Var("x"),BinOP.sub, new IntLiteral(2))))
                  ,BinOP.add
                  ,new FunCall("fib"
                  ,List.of
                  (new OpExpr(new Var("x"),BinOP.sub, new IntLiteral(1))))
          ))));




  default <R> R match(PatternCase<? extends AST,R>... pts){
    for (var pt:pts) {
      if (pt.matches(this)) return pt.eval(this);
    }
    throw new RuntimeException("unmatched pattern case: "+this);
  }



  static interface PatternCase<C,R>{
    boolean matches(Object o);
    R eval(Object o);
  }



  static <C,R> PatternCase<C,R> mkPattern(Class<C> cl,Function<C, R> f){
    return new PatternCase<C,R>() {
      public boolean matches(Object o) {return cl.isInstance(o);}
      public R eval(Object o){return f.apply(cl.cast(o));}
    };
  }



  static <R> PatternCase<OpExpr,R> $OpExpr(Function<OpExpr, R> f) {
    return mkPattern(OpExpr.class, f);
  }
  static<R>PatternCase<IntLiteral,R> $IntLiteral(Function<IntLiteral,R>f){
    return mkPattern(IntLiteral.class, f);
  }
  static <R> PatternCase<Var,R> $Var(Function<Var, R> f){
    return mkPattern(Var.class, f);
  }
  static <R> PatternCase<Assign,R> $Assign(Function<Assign, R> f){
    return mkPattern(Assign.class, f);
  }
  static <R> PatternCase<IfExpr ,R> $IfExpr(Function<IfExpr, R> f){
    return mkPattern(IfExpr.class, f);
  }
  static <R> PatternCase<WhileExpr,R> $WhileExpr(Function<WhileExpr, R> f){
    return mkPattern(WhileExpr.class, f);
  }
  static <R> PatternCase<FunCall,R> $FunCall(Function<FunCall, R> f){
    return mkPattern(FunCall.class, f);
  }
  static <R> PatternCase<Sequence,R> $Sequence(Function<Sequence, R> f) {
    return mkPattern(Sequence.class, f);
  }



  static <R> PatternCase<AST,R> $(Function<AST, R> f) {
    return mkPattern(AST.class, f);
  }



  default String whatAreYou() {
    return match
            ($Var       (v -> "Variable mit Namen "+v.name+"")
                    ,$IntLiteral(il-> "Literal mit Wert "+il.n)
                    ,$FunCall   (fc-> "Aufruf der Funktion: "+fc.name)
                    ,$          (x -> "Irgend ein anderer Baumknoten")
            );
  }



  static PatternCase<OpExpr,Void> _OpExpr(Consumer<OpExpr> f) {
    return mkPattern(OpExpr.class, x->{f.accept(x);return null;});
  }
  static PatternCase<IntLiteral,Void> _IntLiteral(Consumer<IntLiteral> f){
    return mkPattern(IntLiteral.class, x->{f.accept(x);return null;});
  }
  static PatternCase<Var,Void> _Var(Consumer<Var> f){
    return mkPattern(Var.class, x->{f.accept(x);return null;});
  }
  static PatternCase<Assign,Void> _Assign(Consumer<Assign> f){
    return mkPattern(Assign.class, x->{f.accept(x);return null;});
  }
  static PatternCase<IfExpr ,Void> _IfExpr(Consumer<IfExpr> f){
    return mkPattern(IfExpr.class, x->{f.accept(x);return null;});
  }
  static PatternCase<WhileExpr,Void> _WhileExpr(Consumer<WhileExpr> f){
    return mkPattern(WhileExpr.class, x->{f.accept(x);return null;});
  }
  static PatternCase<FunCall,Void> _FunCall(Consumer<FunCall> f){
    return mkPattern(FunCall.class, x->{f.accept(x);return null;});
  }
  static PatternCase<Sequence,Void> _Sequence(Consumer<Sequence> f) {
    return mkPattern(Sequence.class, x->{f.accept(x);return null;});
  }
  static PatternCase<AST,Void> __(Consumer<AST> f) {
    return mkPattern(AST.class, x->{f.accept(x);return null;});
  }



  static class ExWriter{
    Writer w;
    String indent = "";
    void addIndent(){indent=indent+"  ";}
    void subIndent(){indent=indent.substring(2);}
    void nl(){write("\n"+indent);}

    int lbl=0;
    int next(){return lbl++;}

    public ExWriter(Writer w) {
      this.w = w;
    }
    void lnwrite(Object o) {
      nl();
      write(o);
    }
    void write(Object o) {
      try {
        w.write(o+"");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }




  default String show() {
    var r = new ExWriter(new StringWriter());
    show(r);
    return r.w.toString();
  }




  static String show(Fun fd) {
    var r = new ExWriter(new StringWriter());
    r.write("fun ");
    r.write(fd.name);
    r.write("(");
    var first=true;
    for (var arg:fd.args){
      if (first) first=false;else r.write(", ");
      r.write(arg);
    }
    r.write(") = ");
    r.addIndent();
    r.nl();
    fd.body.show(r);
    return r.w.toString();
  }



  //ToDo Aufgabe 1 -----------------------------------------------------------------
  default Void show(ExWriter r) {
    return match(
            _Var (v -> {
              r.write(v.name);
            }),

            _IntLiteral(il-> {
              r.write(il.n+"");
            }),

            _Assign(as -> {
              as.v.show(r);
              r.write(" := ");
              as.right.show(r);
            }),

            _OpExpr(opExpr -> {
              opExpr.left.show(r);
              switch (opExpr.op) {
                case eq -> r.write("=");
                case add -> r.write("+");
                case mult -> r.write("*");
                case sub -> r.write("-");
                default -> r.write("not yet");
              }
              opExpr.right.show(r);
            }),

            _IfExpr(ifExpr -> {
              r.write("if ");
              ifExpr.cond.show(r);
              r.addIndent();
              r.nl();
              r.write(" then ");
              ifExpr.alt1.show(r);
              r.nl();
              r.write(" else ");
              ifExpr.alt2.show(r);
              r.subIndent();
            }),

            _WhileExpr(whileExpr -> {
              r.write("while ");
              whileExpr.cond.show(r);
              r.addIndent();
              r.nl();
              r.write("do");
              r.addIndent();
              r.nl();
              whileExpr.body.show(r);
              r.subIndent();
            }),

            _Sequence(sequence -> {
              r.write("{");
              sequence.sts.get(0).show(r);
              for(int i=0; i<sequence.sts.size(); i++){
                r.nl();
                r.write(";");
                sequence.sts.get(i).show(r);
              }
              r.nl();
              r.write("}");
            }),

            _FunCall(funCall -> {
              r.write(funCall.name);
              r.write("{");
              funCall.args.forEach(x-> x.show(r));
              r.write("}");
            }),

            __(x -> {
              r.write("not yet implemented. Show: "+x);})
    );
  }



  default long ev() {return ev(List.of());}



  default long ev(List<Fun> funs) {
    HashMap<String, Fun> fs = new HashMap<>();
    funs.forEach(fun->fs.put(fun.name, fun));
    return ev(fs,new HashMap<>());
  }


  //ToDo Aufgabe 2 ---------------------------------------------------------------------------
  default long ev(Map<String,Fun> fs, Map<String, Long> env) {
    return match
            ($IntLiteral(il -> il.n),
                    $Var       (v  -> env.get(v.name)),
                    $OpExpr    (ae -> ae.op.op.apply(ae.left.ev(fs, env), ae.right.ev(fs, env))),
                    $IfExpr    (ie -> ie.cond().ev(fs, env) != 0 ? ie.alt1.ev(fs, env) : ie.alt2.ev(fs, env)),
                    $Assign    (as -> {
                      long result = as.right.ev(fs, env);
                      env.put(as.v.name, result);
                      return result;
                    }),
                    $WhileExpr (we -> {
                      long result = 42L;
                      while (we.cond.ev(fs, env) != 0) {
                        result = we.body.ev(fs, env);
                      }
                      return result;
                    }),
                    $Sequence  (sq -> {
                      long result = 42L;
                      for(int i=0; i<sq.sts.size(); i++) {
                        result = sq.sts.get(i).ev(fs, env);
                      }
                      return result;
                    }),
                    $FunCall   (fc -> {
                      Fun fun = new Fun(fc.name, fs.get(fc.name).args, fs.get(fc.name).body);
                      for (int i=0; i<fun.args.size(); i++){
                        env.put(fun.args.get(i), fc.args.get(i).ev(fs, env));
                      }
                      return fun.body.ev(fs, env);
                    })
            );
  }



  String[] rs = {"%rdi","%rsi","%rdx","%rcx","%r8","%r9"};




  default Set<String> getVars(){
    var r = new TreeSet<String>();
    getVars(r);
    return r;
  }



  //ToDo Aufgabe 3 ---------------------------------------------------------------------------
  default void getVars(TreeSet<String> r) {
    match(
            _Assign (as -> {
              r.add(as.v.name);
              as.right.getVars(r);
            }),
            _OpExpr(op ->{
              op.left.getVars(r);
              r.add(op.op.name);
              op.right.getVars(r);
            }),
            _IfExpr(ie -> {
              ie.cond.getVars();
              ie.alt1.getVars();
              ie.alt2.getVars();
            }),
            _WhileExpr(we ->{
              we.cond.getVars(r);
              we.body.getVars(r);
            }),
            _Sequence(se ->{
              for (var x:se.sts) {
                x.getVars(r);
              }
            }),
            _FunCall(fn ->{
              for (var x:fn.args) {
                x.getVars(r);
              }
            }),
            __(x  -> {

            })
    );
  }




  static void asm(Fun f,ExWriter r) {





    r.nl();
    r.lnwrite(".globl  "+f.name);
    r.lnwrite(".type  "+f.name+", @function");
    r.lnwrite(f.name+":");
    r.addIndent();





    r.lnwrite("pushq %rbp");
    r.lnwrite("movq %rsp, %rbp");




    var registerArgs = Math.min(rs.length, f.args.size());
    var env = new HashMap<String,Integer>();



    var sp=-8;
    for (var i=0;i<registerArgs;i++) {
      r.lnwrite("movq "+rs[i]+", "+sp+"(%rbp)");
      env.put(f.args.get(i), sp);
      sp = sp-8;
    }





    var vs = f.body.getVars();
    vs.removeAll(f.args);
    for (var v:vs) {
      env.put(v, sp);
      sp = sp-8;
    }
    r.lnwrite("subq $"+(-sp)+", %rsp");





    sp = 16;
    for (var i=registerArgs;i<f.args.size();i++) {
      env.put(f.args.get(i), sp);
      sp+=8;
    }





    f.body.asm(Map.of(),env,r);





    r.lnwrite("movq %rbp, %rsp");
    r.lnwrite("popq %rbp");
    r.lnwrite("ret");
    r.subIndent();
  }



  default String asm() {
    var r = new ExWriter(new StringWriter());
    asm(new HashMap<>(),new HashMap<>(),r);
    return r.w.toString();
  }



  static String asm(List<Fun> fs) {
    var r = new ExWriter(new StringWriter());
    fs.forEach(f->asm(f,r));
    return r.w.toString();
  }



  //ToDo Aufgabe 4-8 ---------------------------------------------------------------------------
  default void asm(Map<String,Fun> fs,Map<String, Integer> e, ExWriter r){
    match(
            _IntLiteral(il -> {
              r.lnwrite("movq $"+il.n+", %rax");
            }),
            _Var(v -> {
              r.lnwrite("movq "+e.get(v.name)+"(%rbp), %rax");
            }),
            _Assign(as ->{
              //ToDo Aufgabe 4
              r.lnwrite(" ");
            }),
            _OpExpr(op ->{
              //ToDo Aufgabe 5
              op.right.asm(fs, e, r);
              r.lnwrite("pushq %rax");
              op.left.asm(fs, e, r);
              r.lnwrite("popq %rbx");
              r.lnwrite(switch (op.op.name){
                case "+" : {yield "addq %rbx, %rax";}
                case "-" : {yield "subq %rbx, %rax";}
                case "*" : {yield "imulq %rbx, %rax";}
                case "=" : {yield "cmpq %rax, %rbx\r\n" + "sete %al\r\n" + "movzbl %al, %eax";}
                default: throw new IllegalArgumentException("error");
              });
            }),
            _Sequence(seq ->{
              //ToDo Aufgabe 6
              r.lnwrite(" ");
            }),
            _WhileExpr(we ->{
              //ToDo Aufgabe 7
              we.cond.asm(fs, e, r);
              r.lnwrite("jmp .L1");
              r.lnwrite(".L2:");
              we.body.asm(fs, e, r);
              r.lnwrite("cmpq $9, %rax");
              r.lnwrite("jne .L2");
            }),
            _IfExpr(ie ->{
              //ToDo Aufgabe 8
              ie.cond.asm(fs, e, r);
              r.lnwrite("cmq $0 %rax");
              r.lnwrite("jne .L1");
              ie.alt2.asm(fs, e, r);
              r.lnwrite("jmp .L2");
              r.lnwrite(".L1:");
              ie.alt1.asm(fs, e, r);
              r.lnwrite(".L2:");
            }),
            _FunCall(fc -> {
              for (int i=0;i<Math.min(5, fc.args.size());i++) {
                fc.args.get(i).asm(fs, e, r);
                r.lnwrite("movq %rax, "+rs[i]);
              };
              fc.args.stream().skip(rs.length).forEach(arg -> {
                arg.asm(fs, e, r);
                r.lnwrite("pushq %rax");
              });
              ;r.lnwrite("call "+fc.name);
            })
    );
  }





  static String[] help =
          {"<expr>         evaluate <expr>"
                  ,"':q'           quits the interpreter"
                  ,"':defs'        System.out.println();shows defined function names"
                  ,"':s <funname>' prints function definition of <funname>"
                  ,"':?            this help"};

  static void printHelp(){
    for (var h:help)System.out.println(h);
  }




  public static void main(String[] args) throws Exception {
    args = new String[]{"-i","C:\\Users\\minha\\Downloads\\t1.ls"};
    if (args.length==0){
      System.out.println
              ("usage: java name.panitz.longStack.AST [-i] filename");
      System.out.println
              ("  where -i starts interpreter "
                      +"otherwise assembler code is generated");
      return;
    }



    var interpreter = args[0].equals("-i");
    var funDefs =
            LongStackParser.parseFunDefs(args[0].equals("-i")?args[1]:args[0]);



    if (interpreter){
      var in =  new BufferedReader(new InputStreamReader(System.in));
      System.out.println("TUGS (Tiny Usable Great System) :? for help");



      while (true){
        System.out.print("> ");
        var ln = in.readLine();



        if (ln.equals(":q")) break;



        if (ln.equals(":?")){printHelp();continue;}



        if (ln.equals(":defs")){
          funDefs.parallelStream()
                  .forEach(fd->System.out.println(fd.name));
          continue;
        }
        var showFunction = ln.startsWith(":s ");
        try{



          if (showFunction){
            var funname = ln.substring(2).trim();
            Fun fundef = funDefs.stream()
                    .reduce
                            (null
                                    ,(r,fd)->(r==null&&fd.name.equals(funname))?fd:r
                                    ,(fd1,fd2)->fd1==null?fd2:fd1);
            if (fundef!=null)
              System.out.println(show(fundef));
            else System.out.println("unknown function: "+funname);



          }else{
            System.out.println
                    (LongStackParser.parseExpr(ln).ev(funDefs));
          }
        }catch(Exception e){
          System.out.println(e);
        }
      }



    }else{
      var out = new FileWriter
              (args[0].substring(0,args[0].lastIndexOf('.'))+".s");
      var o = new ExWriter(out);
      for (var fun:funDefs) asm(fun,o);
      o.nl();
      out.close();
    }
  }
}