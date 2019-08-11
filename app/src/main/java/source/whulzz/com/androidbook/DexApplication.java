package source.whulzz.com.androidbook;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import java.io.File;
import java.util.List;
import source.whulzz.com.androidbook.reflect.android.app.ActivityThreadRef;
import source.whulzz.com.androidbook.reflect.android.app.LoadedApkRef;
import source.whulzz.com.androidbook.reflect.android.app.ResourceManagerRef;

public class DexApplication extends Application {

    private boolean isReplaceApp = false;
    private File mApkFile = null;
    public DexApplication() {
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            File obbDir = getApplicationContext().getObbDir();
            File apkFile = new File(obbDir, "base.apk");
            if (apkFile.exists()) {
                isReplaceApp = true;
                mApkFile = apkFile;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isReplaceApp) {
            PackageInfo pkgInfo = getPackageManager().getPackageArchiveInfo(mApkFile.getAbsolutePath(),
                    PackageManager.GET_SHARED_LIBRARY_FILES
                            | PackageManager.GET_META_DATA);
            if (pkgInfo == null || pkgInfo.applicationInfo == null) {
                return;
            }
            ApplicationInfo info = pkgInfo.applicationInfo;
            Object activityThread = ActivityThreadRef.currentActivityThread.invoke(null);
            Object resourceManager = ActivityThreadRef.mResourcesManager.get(activityThread);
            Object compatInfo = ResourceManagerRef.mResCompatibilityInfo.get(resourceManager);
            Object loadedApk = ActivityThreadRef.getPackageInfoNoCheck.invoke(
                    activityThread, new Object[] {
                            info, compatInfo
                    });

            Application app = LoadedApkRef.makeApplication.invoke(loadedApk, new Object[]{false, null});
            ActivityThreadRef.mInitialApplication.set(activityThread, app);

            Object boundApplication = ActivityThreadRef.mBoundApplication.get(activityThread);
            List<ProviderInfo> providers = ActivityThreadRef.AppBindDataRef.providers.get(boundApplication);
            ActivityThreadRef.installContentProviders.invoke(activityThread, new Object[]{app, providers});
            ActivityThreadRef.mInstrumentation.get(activityThread).callApplicationOnCreate(app);
        }
    }
}
