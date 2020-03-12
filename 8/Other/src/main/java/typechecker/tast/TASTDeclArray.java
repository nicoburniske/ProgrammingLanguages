package typechecker.tast;

import org.json.simple.JSONArray;
import typechecker.tast.star_ast.StarAST;
import typechecker.tast.star_decl.StarDecl;
import typechecker.type.Type;
import typechecker.type.TypeFunction;
import typechecker.type.TypeInt;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TASTDeclArray implements TAST {
    List<StarDecl> declList;
    StarAST rhs;

    public TASTDeclArray(List<StarDecl> declList, StarAST rhs) {
        this.declList = declList;
        this.rhs = rhs;
    }

    @Override
    public String toJSONString() {
        JSONArray arr = new JSONArray();
        arr.addAll(declList);
        arr.add(rhs);
        return arr.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TASTDeclArray declArray = (TASTDeclArray) o;
        return declList.equals(declArray.declList) &&
                rhs.equals(declArray.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declList, rhs);
    }

    @Override
    public String toJava(Type type) {
        List<StarDecl> functions = this.declList.stream().filter(curr -> curr.name.getType() instanceof TypeFunction).collect(Collectors.toList());;
        String functionNames = functions.stream().map(curr -> curr.name.toJava() + ";\n").collect(Collectors.joining());
        String functionAssignments = functions.stream().map(curr -> curr.name.getVar() + " = " + curr.rhs.toJava() + ";\n").collect(Collectors.joining());
        List<StarDecl> integers = this.declList.stream().filter(curr -> curr.name.getType() instanceof TypeInt).collect(Collectors.toList());;
        String inputs;
        String applies;
        String typeDecl;
        if(integers.size() > 0) {
            inputs = integers.stream().map(cur -> ("(" + cur.name.toJava() + ")")).collect(Collectors.joining("->"));
            applies = integers.stream().map(cur -> ".apply(" + cur.rhs.toJava() + ")").collect(Collectors.joining());
            typeDecl = TypeFunction.toJavaHelper(integers.stream().map(curr -> curr.name.getType()).collect(Collectors.toList()), this.rhs.getType());
        } else {
            inputs = "()";
            applies = ".get()";
            typeDecl = String.format("Supplier<%s>", this.rhs.getType().toJava());
        }
        String result = this.rhs.toJava();
        return String.format("((%s)%s -> {\n%s%sreturn %s;\n})%s",typeDecl, inputs, functionNames,functionAssignments, result, applies);
    }
}
