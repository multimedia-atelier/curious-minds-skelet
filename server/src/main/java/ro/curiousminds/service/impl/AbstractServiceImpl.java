/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.service.impl;

import ro.curiousminds.service.Service;
import ro.curiousminds.service.api.UniqueIndexService;
import ro.curiousminds.util.CallContext;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.cmd.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.List;

/**
 * Base class for our datastore services. It only prepares logging, call context provider and objectify provider.
 */
public abstract class AbstractServiceImpl implements Service {

	protected Logger log = LoggerFactory.getLogger(getClass().getName());

	@Inject
	private Provider<Objectify> ofyProvider;

	@Inject
	private Provider<CallContext> callContextProvider;

	@Inject
	protected UniqueIndexService uniqueIndexService;

	protected <T> T singleResult(Query<T> query) {
		List<T> result = query.list();
		if (result == null) return null;
		if (result.isEmpty()) return null;
		if (result.size() > 1) throw new IllegalStateException("Multiple results in query: "+query);
		return result.get(0);
	}

	protected Objectify ofy() {
		return ofyProvider.get();
	}

	protected CallContext callContext() {
		return callContextProvider.get();
	}

}
