package io.github.acoboh.query.filter.jpa.contributor;

import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;

/**
 * SQL Function implementation of PostgreSQL Array operations
 *
 * @author Adrián Cobo
 */

class QfArraySQLFunction implements SQLFunction {

	/**
	 * <p>
	 * Constructor for QfArraySQLFunction.
	 * </p>
	 *
	 * @param operator a {@link java.lang.String} object
	 */
	public QfArraySQLFunction(String operator) {
		this.operator = operator;
	}

	private String operator;

	/**
	 * <p>
	 * Getter for the field <code>operator</code>.
	 * </p>
	 *
	 * @return a {@link java.lang.String} object
	 */
	public String getOperator() {
		return operator;
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasArguments() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasParenthesesIfNoArguments() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public Type getReturnType(Type firstArgumentType, Mapping mapping) throws QueryException {
		return BooleanType.INSTANCE;
	}

	/** {@inheritDoc} */
	@Override
	public String render(Type firstArgumentType, @SuppressWarnings("rawtypes") List arguments,
			SessionFactoryImplementor factory) throws QueryException {

		if (arguments.size() < 2) {
			throw new QueryException("Array function not enough arguments");
		}

		StringBuilder builder = new StringBuilder("(").append(arguments.get(0).toString());
		builder.append(operator).append("ARRAY[");

		String prefix = "";
		for (int i = 1; i < arguments.size(); i++) {
			builder.append(prefix).append(arguments.get(i).toString());
			prefix = ", ";
		}

		builder.append("]) and true "); // Added and true just needed by hibernate functions

		return builder.toString();
	}

}
