/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.service.api;

import ro.curiousminds.domain.UniqueIndexEntity;
import ro.curiousminds.service.Service;
import com.googlecode.objectify.Key;

/**
 * AppEngine doesn't support unique indices, but you can store them by your own - with this simple service.
 *
 * Remember to change master entity and relevant UniqueIndexEntity in one transaction.
 *
 */
public interface UniqueIndexService extends Service {

	void saveUniqueIndexOwner(UniqueIndexEntity.Property property, String value, Key<? extends Object> ownerId);

	<T> Key<T> findUniqueIndexOwner(UniqueIndexEntity.Property property, String value);



}
