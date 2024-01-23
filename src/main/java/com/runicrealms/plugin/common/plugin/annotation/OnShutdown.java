package com.runicrealms.plugin.common.plugin.annotation;

import com.runicrealms.plugin.common.plugin.RunicPlugin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OnShutdown {

    boolean async() default false;

    Class<? extends RunicPlugin>[] runAfter() default {};

}

