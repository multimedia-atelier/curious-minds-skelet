/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.guice.gson;

import ro.curiousminds.service.JsonIgnore;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Removes fields with JsonIgnore annotation.
 */
public class AnnotationBasedExclusion implements ExclusionStrategy {

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return f.getAnnotation(JsonIgnore.class) != null;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return clazz.getAnnotation(JsonIgnore.class) != null;
	}
}
