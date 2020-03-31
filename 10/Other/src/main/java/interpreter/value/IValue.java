package interpreter.value;

import org.json.simple.JSONAware;

/**
 * An IValue represents a yielded by interpretation
 * <p>
 * Is one of:
 * <ul>
 *     <li>ValueCell</li>
 *     <li>ValueClosure</li>
 *     <li>ValueInt</li>
 *     <li>ValuePrimop</li>
 *     <li>ValueLambdaClosure</li>
 * </ul>
 */
public interface IValue extends JSONAware {

}
