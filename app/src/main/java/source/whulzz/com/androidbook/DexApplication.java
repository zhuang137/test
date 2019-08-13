package source.whulzz.com.androidbook;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.File;
import java.util.List;
import dalvik.system.DexClassLoader;
import source.whulzz.com.androidbook.reflect.android.app.ActivityThreadRef;
import source.whulzz.com.androidbook.reflect.android.app.LoadedApkRef;
import source.whulzz.com.androidbook.reflect.dalvik.system.PathClassLoaderRef;

public class DexApplication extends Application {

    private boolean isReplaceApp = false;
    private File mApkFile = null;
    private boolean isReplaceSuccess = false;
    private Application mRealApplication;
    public DexApplication() {

    }

    private void fixClassLoader() {
        ClassLoader currentClassLoader = getClassLoader();
        ClassLoader tmpClassLoader = currentClassLoader;
        while (tmpClassLoader != null && tmpClassLoader.getParent() instanceof DexClassLoader) {
            tmpClassLoader = tmpClassLoader.getParent();
        }
        if (tmpClassLoader != null) {
            ClassLoader newParent = PathClassLoaderRef.newInstance(mApkFile.getAbsolutePath(), tmpClassLoader.getParent());
            if (newParent != null) {
                PathClassLoaderRef.parent.set(tmpClassLoader, newParent);
                isReplaceSuccess = true;
            }
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            File obbDir = base.getObbDir();
            File apkFile = new File(obbDir, "base.apk");
            if (apkFile.exists()) {
                isReplaceApp = true;
                mApkFile = apkFile;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isReplaceApp) {
            return;
        }
        fixClassLoader();
        if (!isReplaceSuccess) {
            return;
        }
        PackageInfo pkgInfo = getPackageManager().getPackageArchiveInfo(mApkFile.getAbsolutePath(),
                PackageManager.GET_SHARED_LIBRARY_FILES
                        | PackageManager.GET_META_DATA);
        if (pkgInfo == null || pkgInfo.applicationInfo == null || TextUtils.isEmpty(pkgInfo.applicationInfo.name)) {
            return;
        }
        Object activityThrad = ActivityThreadRef.currentActivityThread.invoke(null);
        Instrumentation instrumentation = ActivityThreadRef.mInstrumentation.get(activityThrad);
        if (instrumentation == null) {
            return;
        }
        try {
            mRealApplication = instrumentation.newApplication(getClassLoader(), pkgInfo.applicationInfo.name, base);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mRealApplication != null) {
            Object activityThread = ActivityThreadRef.currentActivityThread.invoke(null);
            ActivityThreadRef.mInitialApplication.set(activityThread, mRealApplication);
            Object mBoundApplication = ActivityThreadRef.mBoundApplication.get(activityThread);
            Object loadedApk = ActivityThreadRef.AppBindDataRef.info.get(mBoundApplication);
            LoadedApkRef.mApplication.set(loadedApk, mRealApplication);
            List<Application> mAllApplications = ActivityThreadRef.mAllApplications.get(activityThread);
            if (mAllApplications != null) {
                for (Application application : mAllApplications) {
                    if (application == null || !(application instanceof DexApplication)) {
                        continue;
                    }
                    mAllApplications.remove(application);
                    mAllApplications.add(0, mRealApplication);
                }
            }
            ActivityThreadRef.mInstrumentation.get(activityThread).callApplicationOnCreate(mRealApplication);
        }
    }
}
