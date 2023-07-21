package io.github.acoboh.query.filter.jpa.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when the enumeration class can not be parsed
 *
 * @author Adrián Cobo
 */
public class QFEnumException extends QueryFilterException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Failed to parse field '{}' with value '{}' to enum class '{}'. Allowed values {}";

	private final Object[] arguments;

	/**
	 * Default constructor
	 * 
	 * @param field         field of filter
	 * @param value         selected value
	 * @param enumClass     enumeration class
	 * @param allowedValues allowed values
	 */
	public QFEnumException(String field, String value, @SuppressWarnings("rawtypes") Class<? extends Enum> enumClass,
			String[] allowedValues) {
		super(MESSAGE, field, value, enumClass, allowedValues);
		this.arguments = new Object[] { field, value, enumClass.getSimpleName(), Arrays.toString(allowedValues) };
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	@Override
	public Object[] getArguments() {
		return arguments;
	}

	@Override
	public String getMessageCode() {
		return "qf.exceptions.enum";
	}
}
