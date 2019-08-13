package source.whulzz.com.androidbook.reflect.android.app;

import utils.helper.reflect.FieldRef;
import utils.helper.reflect.FieldRefType;
import utils.helper.reflect.RefClassInit;

public class ResourcesManagerRef {
    private static String CLASS_NAME = "android.app.ResourcesManager";
    private static Class sClass;
    static {
        sClass = RefClassInit.init(CLASS_NAME, ResourcesManagerRef.class);
    }

    @FieldRef(name="mResCompatibilityInfo")
    public static FieldRefType<Object> mResCompatibilityInfo;

    public static Class getRefClass() {
        return sClass;
    }
}
