/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */
package ro.curiousminds.api.jersey;

import ro.curiousminds.util.CallContext;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.HttpHeaders;

/**
 * Jersey filter which pull authorization header from request and populates it into
 * {@link ro.curiousminds.util.CallContext}.
 *
 * Change it in any way you need.
 *
 */
public class AuthFilter implements ContainerRequestFilter {

    private static Logger log = LoggerFactory.getLogger(AuthFilter.class.getName());

    @Inject
    private Provider<CallContext> callContextProvider;

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        String authKey = request.getHeaderValue(HttpHeaders.AUTHORIZATION);
        log.info("Use AuthFilter to validate API caller: "+authKey);

        // do some AES magic here, or read user from datatstore

        callContextProvider.get().setUser(null);
        return request;
    }
}
