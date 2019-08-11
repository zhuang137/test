package utils.helper.reflect;

import java.lang.reflect.Field;

public class FieldRefType<T> {
    Field mTarget;

    public void targetField(Field target) {
        mTarget = target;
        if (mTarget != null) {
            mTarget.setAccessible(true);
        }
    }

    public T get(Object instance) {
        if (mTarget != null) {
            try {
                return (T)mTarget.get(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public void set(Object instance, Object value) {
        if (mTarget != null) {
            try {
                mTarget.set(instance, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
