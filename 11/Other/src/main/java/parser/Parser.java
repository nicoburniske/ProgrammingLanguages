package parser;

import ast.Var;
import ast.expression.*;
import ast.lhs.ArrIndexLoc;
import ast.lhs.LHS;
import ast.lhs.VarLoc;
import ast.stmt.*;
import ast.decl.IDecl;
import ast.decl.ArrDecl;
import ast.decl.Decl;
import org.json.simple.JSONArray;
import utils.exceptions.ParseException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Parser {




    /**
     * This function pases a {@link StmtBlock} from an {@param obj}
     * @param obj
     * @return the {@link Stmt} parsed
     */
    public static StmtBlock parse(Object obj) {
        /**
         *  - [VarDecl, ..., VarDecl,          % declaration block
         *     "in"                            % set up local variables
         *      Stmt, ..., Stmt,               % execute statements in order
         *      Expression]                    % its value is the result
         */
        if(obj instanceof JSONArray){
            JSONArray arr = (JSONArray) obj;
            if(arr.size() >= 2 && arr.contains("in")) {
                int indexOfIn = arr.indexOf("in");
                List<Object> decls = arr.subList(0, indexOfIn);
                List<Object> stmts = arr.subList(indexOfIn + 1, arr.size() - 1);

                List<IDecl> declList = decls.stream().map(decl -> parseDecl(decl)).collect(Collectors.toList());
                List<Stmt> stmtList = stmts.stream().map(stmt -> parseStmt(stmt)).collect(Collectors.toList());

                Expression expression = parseExpression(arr.get(arr.size()-1));
                return new StmtBlock(declList, stmtList, expression);
            }
            throw new ParseException(ParseException.expectedStmtBlock);
        } else {
            throw new ParseException(ParseException.expectedArray);
        }

    }


    /**
     * This function parses Decls from objects and throws an error if it fails to do so
     */
    private static IDecl parseDecl(Object obj) {
        /**
         * A VarDecl has the shape:
         *  - ["let", Var, "=", Expression]      % declare and initialize variable
         *  - ["vec", Var, "=", Expression,      % declare array and
         *                      .., Expression]  % initial field values
         */
        if(obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            if(arr.size() == 4) {
                if(isStringAndIsEqual(arr.get(0), "let")) {
                    return new Decl(parseVar(arr.get(1)), parseExpression(arr.get(3)));
                }
                if(isStringAndIsEqual(arr.get(0), "vec") && (arr.get(3) instanceof JSONArray)) {
                    List<Object> array = (JSONArray) arr.get(3);
                    List<Expression> arrayExp = array.stream().map(val -> parseExpression(val)).collect(Collectors.toList());
                    return new ArrDecl(parseVar(arr.get(1)), arrayExp);
                }
            }
            throw new ParseException(ParseException.expectedDecl);
        } else {
            throw new ParseException(ParseException.expectedArray);
        }
    }


    /**
     * This function parses {@link Expression}s from an Object
     */
    private static Expression parseExpression(Object obj) {
        /**
         * An Expression is one of:
         *  - Int                            % literal constant
         *  - Var                            % the value of a variable
         *  - [Expression, "+", Expression]  % addition
         *  - [Expression, "*", Expression]  % multiplication
         *  - [Expression, Expression]       % the value of an array index
         */
        if(obj instanceof Long) {
            return new Int(((Long)obj).intValue());
        }
        if(obj instanceof String) {
            return new VarExpr((String) obj);
        }
        if(obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            if(arr.size() == 3 && Operator.operators.keySet().stream().anyMatch(op -> isStringAndIsEqual(arr.get(1), op))) {
                return new Operator(parseExpression(arr.get(0)), parseExpression(arr.get(2)), (String)arr.get(1));
            }
            else if(arr.size() == 2) {
                return new ArrayAccess(parseExpression(arr.get(0)), parseExpression(arr.get(1)));
            }
        }
        throw new ParseException(ParseException.expectedExpression);
    }


    /**
     * This function parses {@link Stmt}s from a Object
     */
    private static Stmt parseStmt(Object obj) {
        /**
         * A Stmt is one of:
         *  - [LHS, "=", Expression]           % assignment
         *  - ["if0", Expression, Stmt, Stmt]  % if
         *  - ["do0", Expression, Stmt]        % loop while not 0
         *  - [VarDecl, ..., VarDecl,          % declaration block
         *     "in"                            % set up local variables
         *      Stmt, ..., Stmt,               % execute statements in order
         *      Expression]                    % its value is the result
         */
        if(obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            if(arr.size() == 4 && isStringAndIsEqual(arr.get(0), "if0")) {
                return new Conditional(parseExpression(arr.get(1)), parseStmt(arr.get(2)), parseStmt(arr.get(3)));
            }
            if(arr.size() == 3 && isStringAndIsEqual(arr.get(0), "do0")) {
                return new LoopConditional(parseExpression(arr.get(1)), parseStmt(arr.get(2)));
            }
            if(arr.size() == 3 && isStringAndIsEqual(arr.get(1), "=")) {
                return new Assignment(parseLHS(arr.get(0)), parseExpression(arr.get(2)));
            }
            return parse(arr);
        }
        throw new ParseException(ParseException.expectedStmt);
    }


    /**
     * This function parses {@link LHS} from objects.
     */
    private static LHS parseLHS(Object obj) {
        /**
         * A LHS is one of:            % LHS stands for lefthand-side
         *  - Var                      % the location of a variable
         *  - [Expression, Expression] % the location of an array index
         */
        if(obj instanceof String) {
            return new VarLoc((String) obj);
        }
        if(obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            if(arr.size() == 2) {
                return new ArrIndexLoc(parseExpression(arr.get(0)), parseExpression(arr.get(1)));
            }
        }
        throw new ParseException(ParseException.expectedLHS);
    }

    /**
     * This function parses functions from objects.
     */
    private static Var parseVar(Object obj) {
        /**
         * Var is a String
         */
        if(obj instanceof String) {
            return new Var((String) obj);
        }
        throw new ParseException(ParseException.expectedVar);
    }



    /**
     * A helper method to check if an obj is a specified string
     *
     * @param obj the obj to be checked
     * @param str the specified string
     * @return
     */
    private static boolean isStringAndIsEqual(Object obj, String str) {
        return (obj instanceof String) && ((String) obj).equals(str);
    }

}
