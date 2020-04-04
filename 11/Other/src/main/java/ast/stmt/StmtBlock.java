package ast.stmt;

import ast.expression.Expression;
import ast.decl.IDecl;
import org.json.simple.JSONArray;
import utils.env.StaticCheckEnv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
}
