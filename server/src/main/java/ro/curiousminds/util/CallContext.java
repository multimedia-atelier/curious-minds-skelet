/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.util;

import com.google.inject.servlet.RequestScoped;

/**
 * Information about current request. Lives only in request scope.
 *
 */
@RequestScoped
public class CallContext {

    /**
     * Change to your own implementation of logged user, and fill it in {@link ro.curiousminds.api.jersey.AuthFilter}.
     */
    private Object user;

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }
}
