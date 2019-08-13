package source.whulzz.com.androidbook.reflect.android.app;

import android.app.Application;
import android.app.Instrumentation;
import android.content.pm.ApplicationInfo;

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

    @FieldRef(name="mApplicationInfo")
    public static FieldRefType<ApplicationInfo> mApplicationInfo;

    @FieldRef(name="mApplication")
    public static FieldRefType<Application> mApplication;
}
