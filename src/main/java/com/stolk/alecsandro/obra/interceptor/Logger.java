package com.stolk.alecsandro.obra.interceptor;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

@InterceptorBinding
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Logger {

}