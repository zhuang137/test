package source.whulzz.com.androidbook.reflect.android.app;

import android.content.Context;

import utils.helper.reflect.FieldRef;
import utils.helper.reflect.FieldRefType;
import utils.helper.reflect.RefClassInit;

public class ApplicationRef {
    private static final String CLASS_NAME = "android.app.Application";
    private static Class sClass;

    static {
        sClass = RefClassInit.init(CLASS_NAME, ApplicationRef.class);
    }

    @FieldRef(name="mBase")
    public static FieldRefType<Context> mBase;
}
