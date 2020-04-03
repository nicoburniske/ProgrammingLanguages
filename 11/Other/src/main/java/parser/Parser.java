package parser;

import ast.expression.Expression;
import ast.stmt.Stmt;
import ast.stmt.StmtBlock;
import ast.var_decl.Decl;
import org.json.simple.JSONArray;
import utils.exceptions.ParseException;

import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    /**
     * An Expression is one of:
     *  - Int                            % literal constant
     *  - Var                            % the value of a variable
     *  - [Expression, "+", Expression]  % addition
     *  - [Expression, "*", Expression]  % multiplication
     *  - [Expression, Expression]       % the value of an array index
     */

    /**
     * A LHS is one of:            % LHS stands for lefthand-side
     *  - Var                      % the location of a variable
     *  - [Expression, Expression] % the location of an array index
     */

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
    /**
     * A VarDecl has the shape:
     *  - ["let", Var, "=", Expression]      % declare and initialize variable
     *  - ["vec", Var, "=", Expression,      % declare array and
     *                      .., Expression]  % initial field values
     */
    public static StmtBlock parse(Object obj) {
        if(obj instanceof JSONArray){
            JSONArray arr = (JSONArray) obj;
            if(arr.size() >= 2 && arr.contains("in")) {
                int indexOfIn = arr.indexOf("in");
                List<Object> decls = arr.subList(0, indexOfIn);
                List<Object> stmts = arr.subList(indexOfIn + 1, arr.size() - 1);

                List<Decl> declList = decls.stream().map(decl -> parseDecl(decl)).collect(Collectors.toList());
                List<Stmt> stmtList = stmts.stream().map(stmt -> parseStmt(stmt)).collect(Collectors.toList());

                Expression expression = parseExpression(arr.get(arr.size()-1));
                return new StmtBlock(declList, stmtList, expression);
            }
            throw new ParseException(ParseException.expectedStmtBlock);
        } else {
            throw new ParseException(ParseException.expectedArray);
        }

    }


    private static Decl parseDecl(Object obj) {
        if(obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            if(arr.size() > 3) {

            }
            throw new ParseException(ParseException.expectedDecl)
        } else {
            throw new ParseException(ParseException.expectedArray);
        }
    }


    /**
     * A helper method to check if an obj is a specified string
     *
     * @param obj the obj to be checked
     * @param str the specified string
     * @return
     */
    private static boolean isStringAndisEqual(Object obj, String str) {
        return (obj instanceof String) && ((String) obj).equals(str);
    }

}
