package source.whulzz.com.androidbook.reflect.android.app;

import android.app.Application;
import android.app.Instrumentation;

import utils.helper.reflect.FieldRef;
import utils.helper.reflect.FieldRefType;
import utils.helper.reflect.MethodRef;
import utils.helper.reflect.MethodRefType;
import utils.helper.reflect.RefClassInit;

public class LoadedApkRef {

    private static final String CLASS_NAME = "android.app.LoadedApk";
    private static Class sClass;

    static {
        sClass = RefClassInit.init(CLASS_NAME, LoadedApkRef.class);
    }

    @MethodRef(name="makeApplication", value={boolean.class, Instrumentation.class})
    public static MethodRefType<Application> makeApplication;
}
