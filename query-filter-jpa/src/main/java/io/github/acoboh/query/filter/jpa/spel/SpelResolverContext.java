package io.github.acoboh.query.filter.jpa.spel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.View;

/**
 * SPEL Context base to resolve SpEL expressions
 *
 * @author Adrián Cobo
 * 
 */
public abstract class SpelResolverContext {

	/**
	 * Http servlet request parameter
	 */
	protected final HttpServletRequest request;

	/**
	 * Http servlet response parameter
	 */
	protected final HttpServletResponse response;

	/**
	 * Default constructor
	 *
	 * @param request                    the servlet request
	 * @param response                   the servlet response
	 */
	protected SpelResolverContext(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	/**
	 * Evaluate any expression
	 *
	 * @param securityExpression expression to evaluate
	 * @param contextValues      actual context values
	 * @return object evaluated
	 */
	public Object evaluate(String securityExpression, MultiValueMap<String, Object> contextValues) {

		ExpressionParser expressionParser = getExpressionParser();

		Expression expression = expressionParser.parseExpression(securityExpression);

		EvaluationContext context = getEvaluationContext();

		if (request != null) {
			fillContextWithRequestValues(context);
		}

		fillContextWithMap(context, contextValues);

		return expression.getValue(context);

	}

	/**
	 * Get expression parser to resolve de SpEL expression
	 * 
	 * @return the expression parser to use
	 */
	public abstract ExpressionParser getExpressionParser();

	/**
	 * Get the evaluation context of the expression
	 * 
	 * @return evaluation context to use
	 */
	public abstract EvaluationContext getEvaluationContext();

	private void fillContextWithRequestValues(EvaluationContext context) {

		Object pathObject = request.getAttribute(View.PATH_VARIABLES);
		if (pathObject != null && pathObject instanceof Map<?, ?>) {
			context.setVariable("_pathVariables", (Map<?, ?>) pathObject);
		}

		Map<String, String[]> mapParameters = request.getParameterMap();
		context.setVariable("_parameters", mapParameters);
	}

	private void fillContextWithMap(EvaluationContext context, MultiValueMap<String, Object> contextValues) {
		contextValues.forEach((k, v) -> {
			if (v.size() > 1) {
				context.setVariable(k, v);
			} else if (v.size() == 1) {
				context.setVariable(k, v.get(0));
			}
		});
	}
}
