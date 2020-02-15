package type;

import org.json.simple.JSONAware;

/**
 * A Type is one of:
 * <ul>
 *      <li>- "int"</li>
 *      <li>- [Type, ..., Type, "->", Type]</li>
 * </ul>
 **/
public interface Type extends JSONAware {
}
