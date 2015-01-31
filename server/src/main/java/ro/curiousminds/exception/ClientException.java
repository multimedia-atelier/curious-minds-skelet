/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.exception;

/**
 * Client did something wrong. This exceptions translates into 40x HTTP status.
 *
 */
public abstract class ClientException extends IllegalArgumentException {

	public ClientException() {
	}

	public ClientException(String s) {
		super(s);
	}

	public ClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientException(Throwable cause) {
		super(cause);
	}
}
