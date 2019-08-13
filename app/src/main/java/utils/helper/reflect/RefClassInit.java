package utils.helper.reflect;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import utils.helper.constant.Features;

public class RefClassInit {
    private static final String TAG = RefClassInit.class.getSimpleName();
    private static final boolean debug = Features.debug;

    public static void init(Class targetClass, Class reflector) {
        //inject fields
        Field[] fields = reflector.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                field.setAccessible(true);
                if ((field.getModifiers() & Modifier.STATIC) == 0) {
                    continue;
                }
                FieldRef fieldAnnotation = field.getAnnotation(FieldRef.class);
                MethodRef methodAnnotation = field.getAnnotation(MethodRef.class);
                MethodRef2 methodAnnotation2 = field.getAnnotation(MethodRef2.class);

                if (fieldAnnotation != null) {
                    String name = fieldAnnotation.name();
                    if (TextUtils.isEmpty(name)) continue;
                    Field targetField = null;
                    Class tmpClass = targetClass;
                    do {
                        try {
                            targetField = tmpClass.getDeclaredField(name);
                            if (targetField != null) break;
                        } catch (NoSuchFieldException e) {
                            tmpClass = tmpClass.getSuperclass();
                        }

                    } while (tmpClass != null);

                    if (targetField != null) {
                        try {
                            Object refObj = field.getType().newInstance();
                            ((FieldRefType) refObj).targetField(targetField);
                            field.set(null, refObj);
                        } catch (Exception e) {
                        }

                    }
                }
                if (methodAnnotation == null && methodAnnotation2 == null) {
                    continue;
                }
                String name = "";
                Class[] paramsTypes = null;
                if (methodAnnotation != null) {
                    name = methodAnnotation.name();
                    paramsTypes = methodAnnotation.value();
                } else {
                    name = methodAnnotation2.name();
                    ClassLoader cl = targetClass.getClassLoader();
                    RefTypeItem[] typeItems = methodAnnotation2.value();
                    int size = typeItems.length;
                    paramsTypes = new Class[size];
                    try {
                        for (int i = 0; i < size; i++) {
                            paramsTypes[i] = cl.loadClass(typeItems[i].name());
                        }
                    } catch (ClassNotFoundException e) {
                        paramsTypes = null;
                    }
                }
                if (TextUtils.isEmpty(name) || paramsTypes == null) {
                    continue;
                }

                Method targetMethod = null;
                Class tmpClass = targetClass;
                do {
                    try {
                        targetMethod = tmpClass.getDeclaredMethod(name, paramsTypes);
                        if (targetMethod != null) break;
                    } catch (NoSuchMethodException e) {
                        tmpClass = tmpClass.getSuperclass();
                    }

                } while (tmpClass != null);

                if (targetMethod != null) {
                    try {
                        Object refObj = field.getType().newInstance();
                        ((BaseMethodRefType) refObj).targetMethod(targetMethod);
                        field.set(null, refObj);
                    } catch (Exception e) {
                    }

                }
            }
        }
    }
    public static Class init(String className, Class reflector) {
        if (TextUtils.isEmpty(className) || reflector == null) {
            return null;
        }
        Class targetClass = null;
        try {
            targetClass = reflector.getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            if (debug) e.printStackTrace();
            return null;
        }
        init(targetClass, reflector);
        return targetClass;
    }
    public static Class init(ClassLoader targetClassLoader, String className, Class reflector) {
        if (TextUtils.isEmpty(className) || reflector == null) {
            return null;
        }
        Class targetClass = null;
        try {
            targetClass = targetClassLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            if (debug) e.printStackTrace();
            return null;
        }
        init(targetClass, reflector);
        return targetClass;
    }
}
