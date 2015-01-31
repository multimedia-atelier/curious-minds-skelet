/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.guice;

import ro.curiousminds.api.jersey.AuthFilter;
import ro.curiousminds.api.jersey.JerseyApplication;
import ro.curiousminds.api.jersey.ResponseHeaderFilter;
import ro.curiousminds.guice.gson.AnnotationBasedExclusion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Our API - Jersey configuration.
 *
 * Handles requests comming on "/api/*".
 *
 */
public class ApiModule extends ServletModule {

    @Override
    protected void configureServlets() {

		bind(Gson.class).toInstance(configureGson());

        final Map<String, String> params = new HashMap<String, String>();
        params.put("javax.ws.rs.Application", JerseyApplication.class.getName());
	    params.put("com.sun.jersey.config.feature.DisableWADL", "true");
        params.put("com.sun.jersey.spi.container.ContainerRequestFilters", AuthFilter.class.getName());
        params.put("com.sun.jersey.spi.container.ContainerResponseFilters", ResponseHeaderFilter.class.getName());

	    serve("/api/*").with(GuiceContainer.class, params);
    }

	private Gson configureGson() {
		GsonBuilder result = new GsonBuilder();
		result.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		result = result.addSerializationExclusionStrategy(new AnnotationBasedExclusion());
		return result.create();
	}

}
