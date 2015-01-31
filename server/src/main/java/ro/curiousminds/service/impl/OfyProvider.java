/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.service.impl;

import ro.curiousminds.domain.MessageEntity;
import ro.curiousminds.domain.UniqueIndexEntity;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import javax.inject.Provider;

/**
 * Objectify wrapper - this is a place to register all your domain objects - entities.
 *
 */
public class OfyProvider implements Provider<Objectify> {

    {
        ObjectifyService.register(MessageEntity.class);
        ObjectifyService.register(UniqueIndexEntity.class);
    }

    @Override
    public Objectify get() {
        return ObjectifyService.ofy();
    }

}
