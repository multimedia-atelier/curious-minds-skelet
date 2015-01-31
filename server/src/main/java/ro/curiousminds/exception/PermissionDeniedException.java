/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.exception;

/**
 *
 * Go away, you can do this!
 *
 */
public class PermissionDeniedException extends ClientException {

	public PermissionDeniedException() {
	}

	public PermissionDeniedException(String s) {
		super(s);
	}

	public PermissionDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public PermissionDeniedException(Throwable cause) {
		super(cause);
	}
}
