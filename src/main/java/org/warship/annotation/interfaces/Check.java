package org.warship.annotation.interfaces;

public @interface Check {

	int minSize() default 0;

	int maxSize() default 255;

}
