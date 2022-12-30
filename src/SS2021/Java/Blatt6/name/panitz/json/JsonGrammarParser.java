// Generated from C:\Users\aliyi\Desktop\JsonGrammar.g4 by ANTLR 4.9.2

package SS2021.Java.Blatt6.name.panitz.json;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

import static Blatt6.name.panitz.json.Json.JsonObject;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JsonGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		STRING=10, NUMBER=11, WS=12;
	public static final int
		RULE_obj = 0, RULE_pair = 1, RULE_arr = 2, RULE_json = 3, RULE_nullB = 4, 
		RULE_trueB = 5, RULE_falseB = 6, RULE_stringB = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"obj", "pair", "arr", "json", "nullB", "trueB", "falseB", "stringB"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "':'", "'['", "']'", "'null'", "'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "STRING", 
			"NUMBER", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "JsonGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JsonGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ObjContext extends ParserRuleContext {
		public JsonObject result;
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public ObjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obj; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener) ((JsonGrammarListener)listener).enterObj(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).exitObj(this);
		}
	}

	public final ObjContext obj() throws RecognitionException {
		ObjContext _localctx = new ObjContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_obj);
		int _la;
		try {
			setState(29);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				match(T__0);
				setState(17);
				pair();
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(18);
					match(T__1);
					setState(19);
					pair();
					}
					}
					setState(24);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(25);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				match(T__0);
				setState(28);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairContext extends ParserRuleContext {
		public Pair<String, Blatt6.name.panitz.json.Json> result;
		public StringBContext stringB() {
			return getRuleContext(StringBContext.class,0);
		}
		public JsonContext json() {
			return getRuleContext(JsonContext.class,0);
		}
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).exitPair(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			stringB();
			setState(32);
			match(T__3);
			setState(33);
			json();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrContext extends ParserRuleContext {
		public Blatt6.name.panitz.json.Json result;
		public List<JsonContext> json() {
			return getRuleContexts(JsonContext.class);
		}
		public JsonContext json(int i) {
			return getRuleContext(JsonContext.class,i);
		}
		public ArrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).enterArr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).exitArr(this);
		}
	}

	public final ArrContext arr() throws RecognitionException {
		ArrContext _localctx = new ArrContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_arr);
		int _la;
		try {
			setState(48);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(35);
				match(T__4);
				setState(36);
				json();
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(37);
					match(T__1);
					setState(38);
					json();
					}
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(44);
				match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(46);
				match(T__4);
				setState(47);
				match(T__5);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JsonContext extends ParserRuleContext {
		public Json result;
		public NullBContext nullB() {
			return getRuleContext(NullBContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(JsonGrammarParser.NUMBER, 0); }
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public ArrContext arr() {
			return getRuleContext(ArrContext.class,0);
		}
		public TrueBContext trueB() {
			return getRuleContext(TrueBContext.class,0);
		}
		public FalseBContext falseB() {
			return getRuleContext(FalseBContext.class,0);
		}
		public StringBContext stringB() {
			return getRuleContext(StringBContext.class,0);
		}
		public JsonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).enterJson(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).exitJson(this);
		}
	}

	public final JsonContext json() throws RecognitionException {
		JsonContext _localctx = new JsonContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_json);
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				nullB();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				match(NUMBER);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 3);
				{
				setState(52);
				obj();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 4);
				{
				setState(53);
				arr();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(54);
				trueB();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 6);
				{
				setState(55);
				falseB();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 7);
				{
				setState(56);
				stringB();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NullBContext extends ParserRuleContext {
		public NullBContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nullB; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).enterNullB(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).exitNullB(this);
		}
	}

	public final NullBContext nullB() throws RecognitionException {
		NullBContext _localctx = new NullBContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_nullB);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TrueBContext extends ParserRuleContext {
		public TrueBContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueB; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).enterTrueB(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).exitTrueB(this);
		}
	}

	public final TrueBContext trueB() throws RecognitionException {
		TrueBContext _localctx = new TrueBContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_trueB);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FalseBContext extends ParserRuleContext {
		public FalseBContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_falseB; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).enterFalseB(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).exitFalseB(this);
		}
	}

	public final FalseBContext falseB() throws RecognitionException {
		FalseBContext _localctx = new FalseBContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_falseB);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringBContext extends ParserRuleContext {
		public String result;
		public TerminalNode STRING() { return getToken(JsonGrammarParser.STRING, 0); }
		public StringBContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringB; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).enterStringB(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JsonGrammarListener ) ((JsonGrammarListener)listener).exitStringB(this);
		}
	}

	public final StringBContext stringB() throws RecognitionException {
		StringBContext _localctx = new StringBContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stringB);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16F\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\7\2"+
		"\27\n\2\f\2\16\2\32\13\2\3\2\3\2\3\2\3\2\5\2 \n\2\3\3\3\3\3\3\3\3\3\4"+
		"\3\4\3\4\3\4\7\4*\n\4\f\4\16\4-\13\4\3\4\3\4\3\4\3\4\5\4\63\n\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\5\5<\n\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\2"+
		"\2\n\2\4\6\b\n\f\16\20\2\2\2G\2\37\3\2\2\2\4!\3\2\2\2\6\62\3\2\2\2\b;"+
		"\3\2\2\2\n=\3\2\2\2\f?\3\2\2\2\16A\3\2\2\2\20C\3\2\2\2\22\23\7\3\2\2\23"+
		"\30\5\4\3\2\24\25\7\4\2\2\25\27\5\4\3\2\26\24\3\2\2\2\27\32\3\2\2\2\30"+
		"\26\3\2\2\2\30\31\3\2\2\2\31\33\3\2\2\2\32\30\3\2\2\2\33\34\7\5\2\2\34"+
		" \3\2\2\2\35\36\7\3\2\2\36 \7\5\2\2\37\22\3\2\2\2\37\35\3\2\2\2 \3\3\2"+
		"\2\2!\"\5\20\t\2\"#\7\6\2\2#$\5\b\5\2$\5\3\2\2\2%&\7\7\2\2&+\5\b\5\2\'"+
		"(\7\4\2\2(*\5\b\5\2)\'\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2\2"+
		"-+\3\2\2\2./\7\b\2\2/\63\3\2\2\2\60\61\7\7\2\2\61\63\7\b\2\2\62%\3\2\2"+
		"\2\62\60\3\2\2\2\63\7\3\2\2\2\64<\5\n\6\2\65<\7\r\2\2\66<\5\2\2\2\67<"+
		"\5\6\4\28<\5\f\7\29<\5\16\b\2:<\5\20\t\2;\64\3\2\2\2;\65\3\2\2\2;\66\3"+
		"\2\2\2;\67\3\2\2\2;8\3\2\2\2;9\3\2\2\2;:\3\2\2\2<\t\3\2\2\2=>\7\t\2\2"+
		">\13\3\2\2\2?@\7\n\2\2@\r\3\2\2\2AB\7\13\2\2B\17\3\2\2\2CD\7\f\2\2D\21"+
		"\3\2\2\2\7\30\37+\62;";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}