/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.exception;

/**
 * There is already an entity with this unique index.
 *
 */
public class UniqueViolationException extends ClientException {

	public UniqueViolationException(String property) {
		super(property);
	}

}
