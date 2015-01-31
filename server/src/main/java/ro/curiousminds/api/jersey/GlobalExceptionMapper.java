/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */
package ro.curiousminds.api.jersey;

import ro.curiousminds.exception.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * This class decides, what should be sent to API caller when an exception occures.
 */
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

	protected Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Override
	public Response toResponse(Throwable exception) {
		log.error("Exception: "+exception, exception);

		if (exception instanceof ClientException) {
			return Response.status(422).entity(new ErrorResponse(exception)).build();
		} else {
			return Response.status(500).entity(new ErrorResponse(exception)).build();
		}
	}
}
