package org.warship.annotation;

import java.io.IOException;

import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

public class GroovyUtil {
	static GroovyScriptEngine groovyScriptEngine;

	static {
		try {
			groovyScriptEngine = new GroovyScriptEngine("src/main/java/org/warship/annotation/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用于调用指定Groovy脚本中的指定方法
	 *
	 * @param scriptName 脚本名称
	 * @param methodName 方法名称
	 * @param params     方法参数
	 * @return
	 */
	@SuppressWarnings({"rawtypes"})
	public static Object invokeMethod(String scriptName, String methodName, Object... params) {
		Object ret = null;
		Class scriptClass = null;
		GroovyObject scriptInstance = null;

		System.out.println("scriptName:"+scriptName);
		System.out.println("methodName:"+methodName);
		System.out.println("params1:"+params[0]);
		System.out.println("params2:"+params[1]);
		System.out.println("params3:"+params[2]);

		try {
			scriptClass = groovyScriptEngine.loadScriptByName(scriptName);
			scriptInstance = (GroovyObject) scriptClass.newInstance();
		} catch (ResourceException | ScriptException | InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}

		try {
			ret = (String) scriptInstance.invokeMethod(methodName, params);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}
}
