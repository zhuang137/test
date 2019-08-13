package source.whulzz.com.androidbook.reflect.dalvik.system;

import java.lang.reflect.Constructor;

import utils.helper.reflect.FieldRef;
import utils.helper.reflect.FieldRefType;
import utils.helper.reflect.RefClassInit;

public class PathClassLoaderRef {
    private static final String CLASS_NAME = "dalvik.system.PathClassLoader";
    private static Class sClass;
    static {
        sClass = RefClassInit.init(CLASS_NAME, PathClassLoaderRef.class);
    }

    @FieldRef(name="parent")
    public static FieldRefType<ClassLoader> parent;

    public static ClassLoader newInstance(String dexPath, ClassLoader parent) {
        if (sClass != null) {
            try {
                Constructor constructor = sClass.getDeclaredConstructor(String.class, ClassLoader.class);
                constructor.setAccessible(true);
                ClassLoader loader = (ClassLoader) constructor.newInstance(dexPath, parent);
                loader.loadClass("xx.yy.zz");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}