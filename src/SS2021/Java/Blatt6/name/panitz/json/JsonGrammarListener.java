// Generated from C:\Users\aliyi\Desktop\JsonGrammar.g4 by ANTLR 4.9.2

package SS2021.Java.Blatt6.name.panitz.json;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JsonGrammarParser}.
 */
public interface JsonGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JsonGrammarParser#obj}.
	 * @param ctx the parse tree
	 */
	void enterObj(JsonGrammarParser.ObjContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonGrammarParser#obj}.
	 * @param ctx the parse tree
	 */
	void exitObj(JsonGrammarParser.ObjContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonGrammarParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(JsonGrammarParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonGrammarParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(JsonGrammarParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonGrammarParser#arr}.
	 * @param ctx the parse tree
	 */
	void enterArr(JsonGrammarParser.ArrContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonGrammarParser#arr}.
	 * @param ctx the parse tree
	 */
	void exitArr(JsonGrammarParser.ArrContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonGrammarParser#json}.
	 * @param ctx the parse tree
	 */
	void enterJson(JsonGrammarParser.JsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonGrammarParser#json}.
	 * @param ctx the parse tree
	 */
	void exitJson(JsonGrammarParser.JsonContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonGrammarParser#nullB}.
	 * @param ctx the parse tree
	 */
	void enterNullB(JsonGrammarParser.NullBContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonGrammarParser#nullB}.
	 * @param ctx the parse tree
	 */
	void exitNullB(JsonGrammarParser.NullBContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonGrammarParser#trueB}.
	 * @param ctx the parse tree
	 */
	void enterTrueB(JsonGrammarParser.TrueBContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonGrammarParser#trueB}.
	 * @param ctx the parse tree
	 */
	void exitTrueB(JsonGrammarParser.TrueBContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonGrammarParser#falseB}.
	 * @param ctx the parse tree
	 */
	void enterFalseB(JsonGrammarParser.FalseBContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonGrammarParser#falseB}.
	 * @param ctx the parse tree
	 */
	void exitFalseB(JsonGrammarParser.FalseBContext ctx);
	/**
	 * Enter a parse tree produced by {@link JsonGrammarParser#stringB}.
	 * @param ctx the parse tree
	 */
	void enterStringB(JsonGrammarParser.StringBContext ctx);
	/**
	 * Exit a parse tree produced by {@link JsonGrammarParser#stringB}.
	 * @param ctx the parse tree
	 */
	void exitStringB(JsonGrammarParser.StringBContext ctx);
}