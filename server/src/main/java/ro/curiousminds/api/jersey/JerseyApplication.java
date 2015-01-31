/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */
package ro.curiousminds.api.jersey;

import ro.curiousminds.api.MessageResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Add Jersey resources here.
 *
 */
//@Singleton
public class JerseyApplication extends Application {

    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();

        // resources
        classes.add(MessageResource.class);

	    classes.add(GlobalExceptionMapper.class);

        // utils
        classes.add(JsonWriter.class);
        classes.add(JsonReader.class);

        return classes;
    }

}
