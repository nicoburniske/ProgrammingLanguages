package typechecker.tast;

import org.json.simple.JSONArray;
import typechecker.tast.star_ast.StarAST;
import typechecker.tast.star_decl.StarDecl;
import typechecker.type.Type;
import typechecker.type.TypeFunction;
import typechecker.type.TypeInt;
import typechecker.type.TypedVar;

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
            applies = integers.stream().map(cur -> ".apply(" + cur.rhs.toJava() + ")").collect(Collectors.joining());
            List<Type> overallType = integers.stream().map(curr -> curr.name.getType()).collect(Collectors.toList());
            return  toJavaHelper(integers.stream().map(curr -> curr.name).collect(Collectors.toList()), new TypeFunction(overallType, type), functionNames, functionAssignments) + applies;

        } else {
            return String.format("(new Supplier<%s>() {\n@Override\n%s" +
                            "            public %s get() {%sreturn %s;\n}\n}).get()",
                    type.toJava(),
                    functionNames,
                    type.toJava(),
                    functionAssignments,
                    this.rhs.toJava());
        }
                //return String.format("(new %s() {\n @Override\npublic %s %s {%s%sreturn %s;\n}})%s",typeDecl, inputs, functionNames,functionAssignments, result, applies);
    }

    private String toJavaHelper(List<TypedVar> params, Type type, String functionNames, String functionAssignments) {
        if(params.size() > 1) {
            TypeFunction functionType = (TypeFunction) type;
            Type returnType = functionType.removeOneArg();
            return String.format("(new %s() {\n@Override\n" +
                            "            public %s apply(%s) {\nreturn %s;\n}\n})",
                    functionType.toJava(),
                    returnType.toJava(),
                    params.get(0).toJava(),
                    toJavaHelper(
                            params.subList(0, params.size()),
                            returnType,functionNames, functionAssignments));
        } else {
            TypeFunction functionType = (TypeFunction) type;
            Type returnType = functionType.removeOneArg();
            return String.format("(new %s() {\n@Override\n%s" +
                            "            public %s apply(%s) {%sreturn %s;\n}\n})",
                    functionType.toJava(),
                    functionNames,
                    returnType.toJava(),
                    params.get(0).toJava(),
                    functionAssignments,
                    this.rhs.toJava());
        }
    }
}
