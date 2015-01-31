/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */
package ro.curiousminds.api;

import ro.curiousminds.domain.MessageEntity;
import ro.curiousminds.service.api.MessageService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * This is our REST resource for messages.
 *
 *
 * echo '{"key":"button.label", "message": "Hello button!"}' | curl -d @- --header "Content-Type:application/json" http://localhost:8080/api/messages;
 *
 * curl http://localhost:8080/api/messages/
 *
 * curl http://localhost:8080/api/messages/button.label
 *
 */
@Path("/messages")
public class MessageResource extends AbstractResource {

	@Inject
	MessageService messageService;

	@POST
	public MessageEntity save(MessageEntity entity) {
		return messageService.save(entity);
	}

	@GET
	@Path("/{key}")
	public MessageEntity findByKey(@PathParam("key") String key) {
		return messageService.findByKey(key);
	}

	@GET
	public List<MessageEntity> listAll() {
		return messageService.listAll();
	}

}