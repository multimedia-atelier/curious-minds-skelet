/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */
package ro.curiousminds.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.OPTIONS;
import javax.ws.rs.core.Response;

/**
 * Base class for our API resources.
 *
 */
public abstract class AbstractResource {

	protected Logger log = LoggerFactory.getLogger(this.getClass().getName());

	public AbstractResource() {
		log.info("Creating API resource "+getClass().getSimpleName());
	}

	/**
	 * Handles OPTIONS request - used by browsers in cross-domain requests.
	 * @return
	 */
	@OPTIONS
	public Response rootOptions() {
		log.info("Serving OPTIONS");
		return Response.ok(null).build();
	}

}