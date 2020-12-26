package org.warship.annotation.processors;

import org.warship.annotation.interfaces.Author;
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

		for(Element element : roundEnvironment.getRootElements()) {
			System.out.println("class path:"+element.toString());
			System.out.println("class name:"+element.getSimpleName().toString());
		}


		for(TypeElement annotatedClass : ElementFilter.typesIn(roundEnvironment.getElementsAnnotatedWith(Version.class))) {

			boolean is = annotatedClass.getKind().isInterface();
			System.out.println("isInterface:"+is);


			for(ExecutableElement executableElement : ElementFilter.methodsIn(annotatedClass.getEnclosedElements())) {
				String returnValue = executableElement.getReturnType().toString();
				String methodName = executableElement.getSimpleName().toString();
				String annotation = null;
				for(AnnotationMirror mirror : executableElement.getAnnotationMirrors()) {
					annotation = mirror.getAnnotationType().asElement().getSimpleName().toString();
				}

				System.out.println("method:"+methodName);
				System.out.println("return:"+returnValue);
				System.out.println("annotation:"+annotation);

				String build = new StringBuilder()
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

				System.out.println(build);
			}

		}

		return false;
	}

}
