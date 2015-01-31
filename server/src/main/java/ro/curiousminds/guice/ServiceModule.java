/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.guice;

import ro.curiousminds.service.LogCalls;
import ro.curiousminds.service.api.MessageService;
import ro.curiousminds.service.api.UniqueIndexService;
import ro.curiousminds.service.impl.MessageServiceImpl;
import ro.curiousminds.service.impl.OfyProvider;
import ro.curiousminds.service.impl.UniqueIndexServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.googlecode.objectify.Objectify;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * Services - business logic above our entities.
 *
 */
public class ServiceModule extends AbstractModule {

    @Override
    protected void configure() {

	    // services
	    bind(MessageService.class).to(MessageServiceImpl.class);
	    bind(UniqueIndexService.class).to(UniqueIndexServiceImpl.class);

	    // Objectify is not especially DI friendly, but
        bind(Objectify.class).toProvider(OfyProvider.class);

        // ... AOP magic
	    {
		    MethodInterceptor logger = new CallLoggerInterceptor();
		    requestInjection(logger);
		    bindInterceptor(Matchers.any(), Matchers.annotatedWith(LogCalls.class), logger);
		    bindInterceptor(Matchers.annotatedWith(LogCalls.class), Matchers.any(), logger);
	    }

	    // add another AOP interceptors to handle validation, permissions, even transactions ... whatever.
    }

}
