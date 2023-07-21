package io.github.acoboh.query.filter.jpa.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when the field is not sortable
 * 
 * @author Adrián Cobo
 *
 */
public class QFNotSortableException extends QueryFilterException {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "The field '{}' is not sortable";

	private final String field;
	private final Object[] arguments;

	/**
	 * Default constructor
	 * 
	 * @param field field
	 */
	public QFNotSortableException(String field) {
		super(MESSAGE, field);
		this.field = field;
		arguments = new Object[] { field };
	}

	/**
	 * Get field
	 * 
	 * @return field
	 */
	public String getField() {
		return field;
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
		return "qf.exceptions.notSortable";
	}

}
