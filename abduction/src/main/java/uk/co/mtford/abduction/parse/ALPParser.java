/* Generated By:JavaCC: Do not edit this line. ALPParser.java */
package uk.co.mtford.abduction.parse;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import uk.co.mtford.abduction.AbductiveFramework;
import uk.co.mtford.abduction.logic.*;
import uk.co.mtford.abduction.asystem.*;

public class ALPParser implements ALPParserConstants {

    public static void main (String[] args) throws ParseException {
        readFromConsole();
    }

    public static AbductiveFramework readFromConsole() throws ParseException {
        ALPParser parser = new ALPParser(System.in);
        return parser.AbductiveLogicProgram();
    }

    public static AbductiveFramework readFromFile(String fileName) throws FileNotFoundException, ParseException {
        FileInputStream fstream = new FileInputStream(fileName);
        ALPParser parser = new ALPParser(fstream);
        return parser.AbductiveLogicProgram();
    }

/* Top level production */
  static final public AbductiveFramework AbductiveLogicProgram() throws ParseException {
        LinkedList<RuleInstance> program = new LinkedList<RuleInstance>();
    LinkedList<DenialInstance> constraints = new LinkedList<DenialInstance>();
    LinkedList<String> abducibles = new LinkedList<String>();

    RuleInstance r;
    DenialInstance d;
    label_1:
    while (true) {
      if (jj_2_1(2)) {
        ;
      } else {
        break label_1;
      }
      if (jj_2_2(2)) {
        r = Rule(abducibles);
          program.add(r);
      } else if (jj_2_3(2)) {
        d = Denial();
          constraints.add(d);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(0);
          {if (true) return new AbductiveFramework(program,abducibles,constraints);}
    throw new Error("Missing return statement in function");
  }

  static final public RuleInstance Rule(LinkedList<String> abducibles) throws ParseException {
    HashMap<String, VariableInstance> variablesSoFar = new HashMap<String, VariableInstance>();

    PredicateInstance head;
    List<ILiteralInstance> body = null;
    head = Predicate(variablesSoFar);
    if (jj_2_4(2)) {
      jj_consume_token(DEFINES);
      body = Body(variablesSoFar, abducibles);
    } else {
      ;
    }
    jj_consume_token(DOT);
      {if (true) return new RuleInstance(head,body);}
    throw new Error("Missing return statement in function");
  }

  static final public DenialInstance Denial() throws ParseException {
    HashMap<String, VariableInstance> variablesSoFar = new HashMap<String, VariableInstance>();
    List<ILiteralInstance> body;
    jj_consume_token(IC);
    jj_consume_token(DEFINES);
    body = Body(variablesSoFar,null);
    jj_consume_token(DOT);
      {if (true) return new DenialInstance(body);}
    throw new Error("Missing return statement in function");
  }

  static final public List<ILiteralInstance> Body(HashMap<String, VariableInstance> variablesSoFar, LinkedList<String> abducibles) throws ParseException {
    LinkedList<ILiteralInstance> body = new LinkedList<ILiteralInstance>();
    ILiteralInstance literal;
    literal = Literal(variablesSoFar, abducibles);
      body.add(literal);
    label_2:
    while (true) {
      if (jj_2_5(2)) {
        ;
      } else {
        break label_2;
      }
      jj_consume_token(COMMA);
      literal = Literal(variablesSoFar, abducibles);
          body.add(literal);
    }
      {if (true) return body;}
    throw new Error("Missing return statement in function");
  }

  static final public ILiteralInstance Literal(HashMap<String, VariableInstance> variablesSoFar, LinkedList<String> abducibles) throws ParseException {
    ILiteralInstance literal;
    if (jj_2_6(2)) {
      literal = PositiveLiteral(variablesSoFar,abducibles);
      {if (true) return literal;}
    } else if (jj_2_7(2)) {
      literal = NegativeLiteral(variablesSoFar, abducibles);
      {if (true) return literal;}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public ILiteralInstance PositiveLiteral(HashMap<String, VariableInstance> variablesSoFar, LinkedList<String> abducibles) throws ParseException {
    ILiteralInstance literal;
    if (jj_2_8(2)) {
      literal = Predicate(variablesSoFar);
        if (abducibles != null) {
            String predName = ((PredicateInstance)literal).getName();
            if (!abducibles.contains(predName)) {
                abducibles.add(predName);
            }
        }
        {if (true) return literal;}
    } else if (jj_2_9(2)) {
      literal = Equality(variablesSoFar);
      {if (true) return literal;}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public NegationInstance NegativeLiteral(HashMap<String, VariableInstance> variablesSoFar, LinkedList<String> abducibles) throws ParseException {
    ILiteralInstance subformula;
    jj_consume_token(NOT);
    subformula = PositiveLiteral(variablesSoFar, abducibles);
      {if (true) return new NegationInstance(subformula);}
    throw new Error("Missing return statement in function");
  }

  static final public PredicateInstance Predicate(HashMap<String, VariableInstance> variablesSoFar) throws ParseException {
    Token t;
    String name;
    List<IAtomInstance> parameters = new LinkedList<IAtomInstance>();
    t = jj_consume_token(LCASENAME);
      name = t.image;
    if (jj_2_10(2)) {
      jj_consume_token(LBRACKET);
      parameters = ParameterList(variablesSoFar);
      jj_consume_token(RBRACKET);
    } else {
      ;
    }
      {if (true) return new PredicateInstance(name,parameters);}
    throw new Error("Missing return statement in function");
  }

  static final public EqualityInstance Equality(HashMap<String, VariableInstance> variablesSoFar) throws ParseException {
    IAtomInstance left;
    IAtomInstance right;
    left = Parameter(variablesSoFar);
    jj_consume_token(EQUALS);
    right = Parameter(variablesSoFar);
      {if (true) return new EqualityInstance(left,right);}
    throw new Error("Missing return statement in function");
  }

  static final public List<IAtomInstance> ParameterList(HashMap<String, VariableInstance> variablesSoFar) throws ParseException {
    LinkedList<IAtomInstance> params = new LinkedList<IAtomInstance>();
    IAtomInstance param;
    if (jj_2_12(2)) {
      param = Parameter(variablesSoFar);
          params.add(param);
      label_3:
      while (true) {
        if (jj_2_11(2)) {
          ;
        } else {
          break label_3;
        }
        jj_consume_token(COMMA);
        param = Parameter(variablesSoFar);
              params.add(param);
      }
    } else {
      ;
    }
      {if (true) return params;}
    throw new Error("Missing return statement in function");
  }

  static final public IAtomInstance Parameter(HashMap<String, VariableInstance> variablesSoFar) throws ParseException {
    Token t;
    PredicateInstance predicate;
    String name;
    if (jj_2_13(2)) {
      t = jj_consume_token(UCASENAME);
            name = t.image;
            if (variablesSoFar.containsKey(name)) {
                {if (true) return variablesSoFar.get(name);}
            }
            else {
                VariableInstance variable = new VariableInstance(name);
                variablesSoFar.put(name,variable);
                {if (true) return variable;}
            }
    } else if (jj_2_14(2)) {
      t = jj_consume_token(LCASENAME);
            name = t.image;
            {if (true) return new ConstantInstance(name);}
    } else if (jj_2_15(2)) {
      predicate = Predicate(variablesSoFar);
          {if (true) return predicate;}
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  static private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  static private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  static private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  static private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  static private boolean jj_2_10(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_10(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(9, xla); }
  }

  static private boolean jj_2_11(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_11(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(10, xla); }
  }

  static private boolean jj_2_12(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_12(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(11, xla); }
  }

  static private boolean jj_2_13(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_13(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(12, xla); }
  }

  static private boolean jj_2_14(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_14(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(13, xla); }
  }

  static private boolean jj_2_15(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_15(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(14, xla); }
  }

  static private boolean jj_3R_8() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_8()) {
    jj_scanpos = xsp;
    if (jj_3_9()) return true;
    }
    return false;
  }

  static private boolean jj_3_8() {
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_3_10() {
    if (jj_scan_token(LBRACKET)) return true;
    if (jj_3R_12()) return true;
    if (jj_scan_token(RBRACKET)) return true;
    return false;
  }

  static private boolean jj_3_13() {
    if (jj_scan_token(UCASENAME)) return true;
    return false;
  }

  static private boolean jj_3R_4() {
    if (jj_3R_10()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_4()) jj_scanpos = xsp;
    if (jj_scan_token(DOT)) return true;
    return false;
  }

  static private boolean jj_3_7() {
    if (jj_3R_9()) return true;
    return false;
  }

  static private boolean jj_3_11() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_13()) return true;
    return false;
  }

  static private boolean jj_3R_13() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_13()) {
    jj_scanpos = xsp;
    if (jj_3_14()) {
    jj_scanpos = xsp;
    if (jj_3_15()) return true;
    }
    }
    return false;
  }

  static private boolean jj_3R_10() {
    if (jj_scan_token(LCASENAME)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_10()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3R_7() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_6()) {
    jj_scanpos = xsp;
    if (jj_3_7()) return true;
    }
    return false;
  }

  static private boolean jj_3_6() {
    if (jj_3R_8()) return true;
    return false;
  }

  static private boolean jj_3_5() {
    if (jj_scan_token(COMMA)) return true;
    if (jj_3R_7()) return true;
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_3R_4()) return true;
    return false;
  }

  static private boolean jj_3_1() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_2()) {
    jj_scanpos = xsp;
    if (jj_3_3()) return true;
    }
    return false;
  }

  static private boolean jj_3_3() {
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3_12() {
    if (jj_3R_13()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_11()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_6() {
    if (jj_3R_7()) return true;
    return false;
  }

  static private boolean jj_3R_9() {
    if (jj_scan_token(NOT)) return true;
    if (jj_3R_8()) return true;
    return false;
  }

  static private boolean jj_3R_12() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_12()) jj_scanpos = xsp;
    return false;
  }

  static private boolean jj_3_15() {
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_3R_5() {
    if (jj_scan_token(IC)) return true;
    if (jj_scan_token(DEFINES)) return true;
    return false;
  }

  static private boolean jj_3_9() {
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3_14() {
    if (jj_scan_token(LCASENAME)) return true;
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_scan_token(DEFINES)) return true;
    if (jj_3R_6()) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    if (jj_3R_13()) return true;
    if (jj_scan_token(EQUALS)) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ALPParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[0];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[15];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public ALPParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public ALPParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ALPParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public ALPParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ALPParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public ALPParser(ALPParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ALPParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[15];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 0; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 15; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 15; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
            case 9: jj_3_10(); break;
            case 10: jj_3_11(); break;
            case 11: jj_3_12(); break;
            case 12: jj_3_13(); break;
            case 13: jj_3_14(); break;
            case 14: jj_3_15(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
