package io.github.acoboh.query.filter.jpa.spel;

import org.springframework.util.MultiValueMap;

/**
 * Interface for Spel resolving
 *
 * @author Adrián Cobo
 * @version $Id: $Id
 */
public interface SpelResolverInterface {

	/**
	 * Evaluate any expression
	 *
	 * @param securityExpression expression to evaluate
	 * @param contextValues      actual context values
	 * @return object evaluated
	 */
	public Object evaluate(String securityExpression, MultiValueMap<String, Object> contextValues);
}
