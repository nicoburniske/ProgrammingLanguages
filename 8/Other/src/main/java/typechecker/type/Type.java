package typechecker.type;

import org.json.simple.JSONAware;

/**
 * A Type is one of:
 * <ul>
 *      <li>- "int"</li>
 *      <li>- [Type, ..., Type, "->", Type]</li>
 *      <li>- [Ref Type] </li>
 * </ul>
 **/
public interface Type extends JSONAware {
    /**
     * This function creates a Java Program that matches the Type.
     * @return a Java Type that represents this Type
     */
    public String toJava();
}
