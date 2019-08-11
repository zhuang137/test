package utils.helper.reflect;

import java.lang.reflect.Method;

public abstract class BaseMethodRefType {
    private Method mTarget;
    public abstract void targetMethod(Method target);
}
