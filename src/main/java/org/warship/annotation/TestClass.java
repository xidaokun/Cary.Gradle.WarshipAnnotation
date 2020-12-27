package org.warship.annotation;

public class TestClass {

	public static void main(String[] args) {
		GroovyUtil.invokeMethod("ProcessorHelper.groovy", "create"
				, "./src/main/java/org/warship/annotation/"
				, "VersionClass.java"
				, "aaaaa");
	}
}
