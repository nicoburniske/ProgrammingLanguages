package tast.star_decl;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import tast.star_ast.StarAST;
import type.TypedVar;

import java.util.Objects;

/**
 * A StarDecl is a JSON array: ["let", TVar, "=", *AST ]
 */
public class StarDecl implements JSONAware {
    TypedVar name;
    StarAST rhs;

    public StarDecl(TypedVar name, StarAST rhs) {
        this.name = name;
        this.rhs = rhs;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.add("let");
        arr.add(name);
        arr.add("=");
        arr.add(rhs);
        return arr.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarDecl starDecl = (StarDecl) o;
        return name.equals(starDecl.name) &&
                rhs.equals(starDecl.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rhs);
    }
}
