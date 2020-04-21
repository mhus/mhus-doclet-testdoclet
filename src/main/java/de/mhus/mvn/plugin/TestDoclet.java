package de.mhus.mvn.plugin;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

public class TestDoclet implements Doclet {

	@Override
	public void init(Locale locale, Reporter reporter) {
		System.out.println("INIT");
	}

	@Override
	public String getName() {
		return "TestDoclet";
	}

	@Override
	public Set<? extends Option> getSupportedOptions() {
        Set<Option> options = new HashSet<>();
        options.add(new XOption("-bottom,-charset,-d,-docencoding,-doctitle,-use,-windowtitle",1,"") {
			@Override
			public boolean process(String option, List<String> arguments) {
				System.out.println("Option: " + option + "=" + arguments);
				return true;
			}
        });
        options.add(new XOption("-version,-author",0,"") {
			@Override
			public boolean process(String option, List<String> arguments) {
				System.out.println("Option: " + option + "=" + arguments);
				return true;
			}
        });
        return options;
	}

	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.RELEASE_11;
	}

	@Override
	public boolean run(DocletEnvironment environment) {
		System.out.println("RUN");
		
		dump("",environment.getSpecifiedElements());
		
		return true;
	}

	private void dump(String path, Collection<? extends Element> elements) {
		System.out.println(path);
		for (Element sub : elements) {
			dump(path + "/" + sub, sub.getEnclosedElements());
		}
	}

}
