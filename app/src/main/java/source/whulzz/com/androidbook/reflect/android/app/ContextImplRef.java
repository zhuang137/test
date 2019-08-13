package source.whulzz.com.androidbook.reflect.android.app;

import android.content.Context;

import utils.helper.reflect.FieldRef;
import utils.helper.reflect.FieldRefType;
import utils.helper.reflect.RefClassInit;

public class ContextImplRef {
    private static final String CLASS_NAME = "android.app.ContextImpl";
    private static Class sClass;
    static {
        sClass = RefClassInit.init(CLASS_NAME, ContextImplRef.class);
    }

    @FieldRef(name="mOuterContext")
    public static FieldRefType<Context> mOuterContext;
}
