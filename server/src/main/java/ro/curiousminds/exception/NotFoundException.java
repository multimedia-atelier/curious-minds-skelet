/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.exception;

/**
 * 404
 *
 */
public class NotFoundException extends ClientException {

	public NotFoundException() {
	}

	public NotFoundException(String s) {
		super(s);
	}

}
