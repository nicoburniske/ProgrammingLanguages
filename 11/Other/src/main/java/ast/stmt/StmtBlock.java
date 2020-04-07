package ast.stmt;

import ast.WhileLang;
import ast.decl.ArrDecl;
import ast.decl.Decl;
import ast.expression.Expression;
import ast.decl.IDecl;
import ast.stmt.frame.ArrDeclFrame;
import ast.stmt.frame.DeclFrame;
import org.json.simple.JSONArray;
import utils.EnvStoreTuple;
import utils.ValueEnvStoreTuple;
import utils.env.Environment;
import utils.env.StaticCheckEnv;
import utils.exceptions.ArrayIndexException;
import utils.exceptions.IntExpectedException;
import utils.exceptions.ParseException;
import utils.exceptions.TypeCheckException;
import utils.store.Store;
import value.IValue;
import value.IValueArray;
import value.IValueInt;
import value.Location;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StmtBlock implements Stmt {
    private List<IDecl> declList;
    private List<Stmt> stmtList;
    private Expression body;

    public StmtBlock(List<IDecl> declList, List<Stmt> stmtList, Expression body) {
        this.declList = declList;
        this.stmtList = stmtList;
        this.body = body;
    }

    public StmtBlock(IDecl declList, Stmt stmtList, Expression body) {
        this(Arrays.asList(declList), Arrays.asList(stmtList), body);
    }

    public StmtBlock(List<IDecl> declList, Stmt stmtList, Expression body) {
        this(declList, Arrays.asList(stmtList), body);
    }

    public StmtBlock(IDecl declList, List<Stmt> stmtList, Expression body) {
        this(Arrays.asList(declList), stmtList, body);
    }

    public StmtBlock(Stmt stmtList, Expression body) {
        this(Arrays.asList(), stmtList, body);
    }

    public StmtBlock(IDecl declList, Expression body) {
        this(declList, Arrays.asList(), body);
    }

    public StmtBlock(Expression body) {
        this(Arrays.asList(), Arrays.asList(), body);
    }


    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.addAll(declList);
        arr.add("in");
        arr.addAll(stmtList);
        arr.add(body);
        return arr.toJSONString();
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StmtBlock stmtBlock = (StmtBlock) o;
        return declList.equals(stmtBlock.declList) &&
                stmtList.equals(stmtBlock.stmtList) &&
                body.equals(stmtBlock.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declList, stmtList, body);
    }

    @Override
    public Stmt staticCheck(StaticCheckEnv env) {
        List<IDecl> checkedDecls = new ArrayList<>();
        for (IDecl d : this.declList) {
            checkedDecls.add(d.staticCheck(env));
            env = env.put(d.getVar());
        }
        StaticCheckEnv finalEnv = env;
        List<Stmt> checkedStatements = this.stmtList.stream().map(stmt -> stmt.staticCheck(finalEnv)).collect(Collectors.toList());
        return new StmtBlock(checkedDecls, checkedStatements, this.body.staticCheck(finalEnv));
    }

    /**
     * This function performs a CESK program on the {@link DeclFrame}
     * @param tuple the current evaluation context of this machine
     * @return a tuple containing the resultant {@link IValue} and the {@link EnvStoreTuple} that is the result
     *  of the evaluation
     */
    public ValueEnvStoreTuple CESK(EnvStoreTuple tuple) {
        WhileLang control;
        Stack<DeclFrame> stack = new Stack<>();

       for (IDecl d : this.declList) {
            control = d;
            while (!this.doneCESK(stack, control)) {
                if (control instanceof Decl) {
                    Decl decl = (Decl) control;
                    stack.push(new DeclFrame(decl.getVar(), tuple.getLeft()));
                    control = decl.getRHS();
                } else if (control instanceof ArrDecl) {
                    ArrDecl decl = (ArrDecl) control;
                    List<Expression> expressions = decl.getRHS();
                    Environment declEnv = tuple.getLeft();
                    IntStream.range(0, expressions.size())
                            .boxed().sorted(Collections.reverseOrder())
                            .forEach(ii -> {
                                Expression after = (ii != expressions.size() - 1) ? expressions.get(ii + 1) : null;
                                stack.push(new ArrDeclFrame(decl.getVar(), declEnv, ii, expressions.size(), after));
                            });
                    control = expressions.get(0);
                } else if (control instanceof Expression) {
                    Expression controlExpr = (Expression) control;
                    if (stack.empty()) {
                        // todo: is this the only condition
                        control = controlExpr.expressionInterpret(tuple);
                    } else if (stack.peek() instanceof ArrDeclFrame) {
                        ArrDeclFrame frame = (ArrDeclFrame) stack.pop();
                        EnvStoreTuple envDecl = new EnvStoreTuple(frame.getEnv(), tuple.getRight());
                        if (frame.getIndex() == 0) {
                            // IValueArray location points to block after head
                            tuple = tuple.insert(frame.getVar(), new IValueArray(tuple.getRight().getSize() + 1, frame.getLength()));
                        }
                        tuple = new EnvStoreTuple(tuple.getLeft(),
                                tuple.getRight().insert(controlExpr.expressionInterpret(envDecl)));

                        Expression after = frame.getAfter();
                        if (after != null) control = after;
                    } else if (stack.peek() instanceof DeclFrame) {
                        DeclFrame frame = stack.pop();
                        EnvStoreTuple envDecl = new EnvStoreTuple(frame.getEnv(), tuple.getRight());
                        tuple = tuple.insert(frame.getVar(), controlExpr.expressionInterpret(envDecl));
                    }
                }
            }
        }
        for (Stmt s : this.stmtList) {
            tuple = new EnvStoreTuple(tuple.getLeft(), s.transition(tuple));
        }
        return new ValueEnvStoreTuple(this.body.expressionInterpret(tuple), tuple);
    }

    public String run() {
        try {
          this.staticCheck(new StaticCheckEnv());
          ValueEnvStoreTuple result = this.CESK(new EnvStoreTuple());
          return result.getLeft().toOutputString(result.getStore(), new HashSet<>());
        } catch (TypeCheckException | IntExpectedException | ParseException | ArrayIndexException exception) {
            return "\"" + exception.getMessage() + "\"";
        }
    }

    private boolean doneCESK(Stack<DeclFrame> stack, WhileLang value) {
        return stack.empty() && (value instanceof IValue || value instanceof Location);
    }

    @Override
    public Store transition(EnvStoreTuple tuple) {
        return this.CESK(tuple).getStore();
    }
}
