package com.example;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Completion;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class ContentViewProcessor extends AbstractProcessor{

    public ContentViewProcessor() {
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return super.getSupportedAnnotationTypes();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation, ExecutableElement member, String userText) {
        return super.getCompletions(element, annotation, member, userText);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("asdasdasdasd");
        MethodSpec qwerty = MethodSpec.methodBuilder("qwerty")
                .addModifiers(Modifier.PUBLIC)
                .addCode(" Log.d(\"asd\",\"是打发士大夫\");")
                .build();

        TypeSpec typeSpec = TypeSpec.classBuilder("qwertyCode")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(qwerty)
                .build();
//        TypeSpec typeSpec = JavaCodeBuilder.codeBuilder();
        JavaFile javaFile = JavaFile.builder("com.example", typeSpec).build();
        try {
            javaFile.writeTo(new File("com.example.qaz"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
