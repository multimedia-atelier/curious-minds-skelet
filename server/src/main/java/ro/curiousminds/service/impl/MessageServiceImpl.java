/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */
package ro.curiousminds.service.impl;

import ro.curiousminds.domain.MessageEntity;
import ro.curiousminds.service.LogCalls;
import ro.curiousminds.service.api.MessageService;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Work;

import java.util.List;

/**
 * Implementation of our business logic. Here is a actual code.
 *
 */
public class MessageServiceImpl extends AbstractServiceImpl implements MessageService {

	@LogCalls
	public MessageEntity save(final MessageEntity entity) {

		// this methods look for the previous version of the message
		// and if it changed, it will create a backup record

		return ofy().transact(new Work<MessageEntity>() {
			@Override
			public MessageEntity run() {
				MessageEntity old = ofy().load().key(Key.create(MessageEntity.class, entity.getKey())).now();
				if (old != null) {
					if (old.getMessage().equals(entity.getMessage())) {
						// nothing changed
						return old;
					}
					MessageEntity backup = new MessageEntity();
					backup.setKey(entity.getKey()+".backup");
					backup.setMessage(old.getMessage());
					ofy().save().entity(backup);
				}
				ofy().save().entity(entity);
				return entity;
			}
		});
	}

	@LogCalls
	public MessageEntity findByKey(String key) {
		return ofy().load().key(Key.create(MessageEntity.class, key)).now();
	}

	@LogCalls
	public List<MessageEntity> listAll() {
		return ofy().load().type(MessageEntity.class).limit(100).list();
	}

}