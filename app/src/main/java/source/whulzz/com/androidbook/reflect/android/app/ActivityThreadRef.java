package source.whulzz.com.androidbook.reflect.android.app;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ProviderInfo;

import java.util.List;

import utils.helper.reflect.FieldRef;
import utils.helper.reflect.FieldRefType;
import utils.helper.reflect.MethodRef;
import utils.helper.reflect.MethodRef2;
import utils.helper.reflect.MethodRefType;
import utils.helper.reflect.MethodRefType2;
import utils.helper.reflect.RefClassInit;
import utils.helper.reflect.RefTypeItem;

public class ActivityThreadRef {
    private static String CLASS_NAME = "android.app.ActivityThread";
    private static Class sClass;
    static {
        sClass = RefClassInit.init(CLASS_NAME, ActivityThreadRef.class);
    }

    @FieldRef(name="mInstrumentation")
    public static FieldRefType<Instrumentation> mInstrumentation;

    @FieldRef(name="mInitialApplication")
    public static FieldRefType<Application> mInitialApplication;

    @FieldRef(name="mBoundApplication")
    public static FieldRefType<Object> mBoundApplication;

    @FieldRef(name="mAllApplications")
    public static FieldRefType<List<Application>> mAllApplications;

    @MethodRef(name="currentActivityThread", value={})
    public static MethodRefType<Object> currentActivityThread;

    @MethodRef2(name="getPackageInfoNoCheck", value={
            @RefTypeItem(name="android.content.pm.ApplicationInfo"),
            @RefTypeItem(name="android.content.res.CompatibilityInfo")
    })
    public static MethodRefType2<Object> getPackageInfoNoCheck;

    @MethodRef(name="installContentProviders", value={
            Context.class, List.class
    })
    public static MethodRefType<Void> installContentProviders;

    public static class AppBindDataRef {
        private static final String CLASS_NAME = "android.app.ActivityThread$AppBindData";
        private static Class sClass;
        static {
            sClass = RefClassInit.init(CLASS_NAME, AppBindDataRef.class);
        }

        @FieldRef(name="providers")
        public static FieldRefType<List<ProviderInfo>> providers;

        @FieldRef(name="info")
        public static FieldRefType<Object> info;
    }
}
