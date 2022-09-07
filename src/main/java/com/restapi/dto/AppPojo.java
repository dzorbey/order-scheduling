package com.restapi.dto;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import net.karneim.pojobuilder.GeneratePojoBuilder;

@GeneratePojoBuilder(withCopyMethod = true, withFactoryMethod = "create")
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
public @interface AppPojo {

}
