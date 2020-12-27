package org.warship.annotation.processors;

import org.warship.annotation.GroovyUtil;
import org.warship.annotation.interfaces.Version;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;

@SupportedAnnotationTypes({"org.warship.annotation.interfaces.Author",
		"org.warship.annotation.interfaces.Branch",
		"org.warship.annotation.interfaces.CommitId",
		"org.warship.annotation.interfaces.Date",
		"org.warship.annotation.interfaces.Description",
		"org.warship.annotation.interfaces.VersionCode"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class CheckProcessor extends AbstractProcessor {
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {

		System.out.println("process START===>");

		String classPackage = null;
		String className = null;
		for (Element element : roundEnvironment.getRootElements()) {
			className = element.getSimpleName().toString();
			classPackage = element.toString().replace("." + className, "");
		}
		System.out.println("className:" + className);
		System.out.println("classPackage:" + classPackage);


		for (TypeElement annotatedClass : ElementFilter.typesIn(roundEnvironment.getElementsAnnotatedWith(Version.class))) {

			boolean is = annotatedClass.getKind().isInterface();
			System.out.println("isInterface:" + is);

			for (ExecutableElement executableElement : ElementFilter.methodsIn(annotatedClass.getEnclosedElements())) {
				String returnValue = executableElement.getReturnType().toString();
				String methodName = executableElement.getSimpleName().toString();
				String annotation = null;
				for (AnnotationMirror mirror : executableElement.getAnnotationMirrors()) {
					annotation = mirror.getAnnotationType().asElement().getSimpleName().toString();
				}

				System.out.println("method:" + methodName);
				System.out.println("return:" + returnValue);
				System.out.println("org.warship.annotation:" + annotation);

				String content = new StringBuilder()
						.append("public static ")
						.append(executableElement.getReturnType().toString())
						.append(" ")
						.append(executableElement.toString())
						.append(" ")
						.append("{")
						.append("\n")
						.append("\t")
						.append("return ")
						.append(annotation)
						.append("\n")
						.append("}")
						.append("\n")
						.toString();

				System.out.println(content);

				try {

					String path = "./src/main/java/" + classPackage.replace(".", "/") + "/";
					System.out.println("path:"+path);
					System.out.println("className:"+className);

					GroovyUtil.invokeMethod("ProcessorHelper.groovy", "create"
							, path
							, className+".java"
							, content);

//					GroovyUtil.invokeMethod("ProcessorHelper.groovy", "create"
//							, "./src/main/java/org/warship/annotation/"
//							, "VersionClass.java"
//							, "aaaaa");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return false;
	}

}
