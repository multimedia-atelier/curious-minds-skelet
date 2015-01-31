/*
 * Created by Tomas Zverina <zverina@m-atelier.cz>
 * Released under WTFPL.
 */

package ro.curiousminds.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation on service methods you want to monitor in logs.
 *
 */
@Retention(RetentionPolicy.RUNTIME) @Target({ElementType.METHOD, ElementType.TYPE})
public @interface LogCalls { }
