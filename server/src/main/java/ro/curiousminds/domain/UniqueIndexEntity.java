/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.domain;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Unindex;

/**
 * A trick how to create unique indices in datastore.
 */
@Entity
@Unindex
@Cache(expirationSeconds = 60*120)
public class UniqueIndexEntity {
	
	public static enum Property {
		some_unique_property,
		like_an_email;
	}

	@Id
	private String uniqueKey;

	private Key<? extends Object> ownerId;

	public static Key<UniqueIndexEntity> createKey(Property property, String uniqueValue) {
		Key<UniqueIndexEntity> k = Key.create(UniqueIndexEntity.class, property.ordinal()+"#"+uniqueValue);
		return k;
	}

	public UniqueIndexEntity() {
	}
	
	public UniqueIndexEntity(Key<UniqueIndexEntity> uniqueValue, Key<? extends Object> ownerId) {
		this.uniqueKey = uniqueValue.getName();
		this.ownerId = ownerId;
	}
	
	public Key<? extends Object> getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Key<? extends Object> objectId) {
		this.ownerId = objectId;
	}

}
