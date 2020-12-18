package modelo;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

/**
 * El metodo solo debe ser usado para testing
 *
 */
@Documented
@Target(METHOD)
public @interface TestPurpose
{

}
