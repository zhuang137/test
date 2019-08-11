package source.whulzz.com.androidbook.reflect.android.view;

import utils.helper.reflect.MethodRef;
import utils.helper.reflect.MethodRefType;
import utils.helper.reflect.RefClassInit;

public class WindowManagerGlobalRef {
    private static String CLASS_NAME = "android.view.WindowManagerGlobal";
    private static Class sClass;
    static {
        sClass = RefClassInit.init(CLASS_NAME, WindowManagerGlobalRef.class);
    }
    @MethodRef(name="getInstance", value={})
    private static MethodRefType<Object> getInstance;

    public static Object getInstance(Object obj) {
        return getInstance != null ? getInstance.invoke(obj) : null;
    }

    public static Class getRefClass() {
        return sClass;
    }
}
