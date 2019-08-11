package source.whulzz.com.androidbook.reflect.android.app;

import utils.helper.reflect.FieldRef;
import utils.helper.reflect.FieldRefType;
import utils.helper.reflect.MethodRef;
import utils.helper.reflect.MethodRefType;
import utils.helper.reflect.RefClassInit;

public class ResourceManagerRef {
    private static String CLASS_NAME = "android.app.ResourceManager";
    private static Class sClass;
    static {
        sClass = RefClassInit.init(CLASS_NAME, ResourceManagerRef.class);
    }

    @FieldRef(name="mResCompatibilityInfo")
    public static FieldRefType<Object> mResCompatibilityInfo;

    public static Class getRefClass() {
        return sClass;
    }
}
