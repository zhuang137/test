package utils.helper.reflect;

import java.lang.reflect.Method;

public class MethodRefType<T> extends BaseMethodRefType {
    Method mTarget;

    public void targetMethod(Method target) {
        mTarget = target;
        if (mTarget != null) {
            mTarget.setAccessible(true);
        }
    }

    public T invoke(Object instance) {
        if (mTarget != null) {
            try {
                return (T)mTarget.invoke(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public T invoke(Object instance, Object[] params) {
        if (mTarget != null) {
            try {
                return (T)mTarget.invoke(instance, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
