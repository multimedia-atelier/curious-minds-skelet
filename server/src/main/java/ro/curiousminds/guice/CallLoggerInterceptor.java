/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.guice;

import ro.curiousminds.util.CallContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/**
 * This AOP interceptor logs calls of methods anotated with @{com.curiousminds.service.LogCalls}.
 */
@Singleton
public class CallLoggerInterceptor implements MethodInterceptor {

    private Logger log = LoggerFactory.getLogger(CallLoggerInterceptor.class);

    @Inject
    private Provider<CallContext> callContextProvider;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("User '"+userName()+"' is calling "+invocation.getMethod().getName());
        return invocation.proceed();
    }

    private String userName() {
        return String.valueOf(callContextProvider.get().getUser());
    }

}
