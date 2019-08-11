package source.whulzz.com.androidbook;

import androidx.appcompat.app.AppCompatActivity;
import source.whulzz.com.androidbook.reflect.android.view.WindowManagerGlobalRef;
import utils.helper.Slog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void btnClick(View v) {
        if (true) {
            Object windowManagerGlobal = WindowManagerGlobalRef.getInstance(null);
            Slog.printLog(TAG, "windowManagerGlobal %s", windowManagerGlobal);
        }
    }
}
