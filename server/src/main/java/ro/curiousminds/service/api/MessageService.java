/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */
package ro.curiousminds.service.api;

import ro.curiousminds.domain.MessageEntity;
import ro.curiousminds.service.Service;

import java.util.List;

/**
 * This is all what we can do with {@link ro.curiousminds.domain.MessageEntity}
 *
 */
public interface MessageService extends Service {

	MessageEntity save(MessageEntity entity);

	MessageEntity findByKey(String key);

	List<MessageEntity> listAll();

}
