package com.example;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;


/**
 * Created by ex-lizheng102 on 2017-06-29.
 */

public class JavaCodeBuilder {


    public static TypeSpec codeBuilder(){

        MethodSpec qwerty = MethodSpec.methodBuilder("qwerty")
                .addModifiers(PUBLIC)
                .addCode(" Log.d(\"asd\",\"是打发士大夫\");")
                .build();

        TypeSpec qwertyCode = TypeSpec.classBuilder("qwertyCode")
                .addModifiers(PUBLIC)
                .addMethod(qwerty)
                .build();

        return qwertyCode;

    }
}
