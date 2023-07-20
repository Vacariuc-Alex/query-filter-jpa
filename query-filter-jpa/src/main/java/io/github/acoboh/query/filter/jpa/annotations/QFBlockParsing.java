package io.github.acoboh.query.filter.jpa.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.github.acoboh.query.filter.jpa.operations.QFOperationEnum;
import io.github.acoboh.query.filter.jpa.processor.QueryFilter;

/**
 * Used to force hide from parsing phase. You can specify internal parsing like roles or admin fields you do not want to set
 * visibility from external usage. Then you can add by methods of class {@link QueryFilter}
 * <p>
 * Methods available:
 * <p>
 * {@link QueryFilter#addNewField(String, QFOperationEnum, String)}
 *
 * @author Adrián Cobo
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface QFBlockParsing {

}
