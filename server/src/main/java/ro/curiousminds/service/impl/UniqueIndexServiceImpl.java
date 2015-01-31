/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.service.impl;

import ro.curiousminds.domain.UniqueIndexEntity;
import ro.curiousminds.exception.UniqueViolationException;
import ro.curiousminds.service.api.UniqueIndexService;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Provider;

/**
 * Implementation of unique indices.
 */
public class UniqueIndexServiceImpl extends AbstractServiceImpl implements UniqueIndexService {

	private Logger log = LoggerFactory.getLogger(UniqueIndexServiceImpl.class.getName());

	private Provider<Objectify> ofyProvider;

	public <T> Key<T> findUniqueIndexOwner(UniqueIndexEntity.Property property, String value) {
		Key<UniqueIndexEntity> k = UniqueIndexEntity.createKey(property, value);
		UniqueIndexEntity index =  ofy().load().key(k).now();
		if (index == null) return null;

		return (Key<T>) index.getOwnerId();
	}

	/**
	 * Save a record about unique index owner.
	 *
	 * @param property
	 * @param value
	 * @param ownerId
	 */
	public void saveUniqueIndexOwner(UniqueIndexEntity.Property property, String value, Key<? extends Object> ownerId) {
		assertTransaction();
		Key<UniqueIndexEntity> k = UniqueIndexEntity.createKey(property, value);
		UniqueIndexEntity saved = ofy().load().key(k).now();
		if (saved != null) {
			if (saved.getOwnerId().equals(ownerId)) {
				// nothing to to do, we already know this
				return;
			} else {
				log.error(ownerId + " is not an owner of " + k);
				throw new UniqueViolationException(property.toString());
			}
		}
		UniqueIndexEntity index = new UniqueIndexEntity(k, ownerId);
		ofy().save().entities(index).now();
	}

	/**
	 * Delete unique index record.
	 *
	 * @param property
	 * @param value
	 */
	public void deleteUniqueIndexOwner(UniqueIndexEntity.Property property, String value) {
		assertTransaction();
		Key<UniqueIndexEntity> k = UniqueIndexEntity.createKey(property, value);
		ofy().delete().entity(k).now();
	}

	/**
	 * Just make sure we are in transaction.
	 */
	protected void assertTransaction() {
		if (ofy().getTransaction() == null) throw new IllegalStateException("No transaction");
		if (!ofy().getTransaction().isActive()) throw new IllegalStateException("Transaction is not active!");
	}

}
