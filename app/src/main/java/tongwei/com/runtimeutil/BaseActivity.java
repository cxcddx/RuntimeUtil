package tongwei.com.runtimeutil;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CX on 2017/3/2.
 */
public class BaseActivity extends AppCompatActivity {

    private static PermissonListener m_listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("********************");
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    public static void requestRuntimePermission(String[] permissions, PermissonListener listener) {

        Activity topActivity = ActivityCollector.getTopActivity();
        if(topActivity == null) {
            return;
        } else {
            m_listener = listener;

            List<String> permissionList = new ArrayList<>();

            for(String permission : permissions) {
                if(ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permission);
                }
            }

            if(!permissionList.isEmpty()) {
                ActivityCompat.requestPermissions(topActivity, permissionList.toArray(new String[permissionList.size()]), 1);
            } else {
                m_listener.onGranted();
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case 1:
                if(grantResults.length>0) {
                    List<String> deniedPermissions = new ArrayList<>();

                    for(int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if(grantResult != PackageManager.PERMISSION_GRANTED) {

                            deniedPermissions.add(permission);
                        }
                    }

                    if(!deniedPermissions.isEmpty()) {
                        m_listener.onDenied(deniedPermissions);
                    } else {
                        m_listener.onGranted();
                    }
                }
                break;
            default:
                break;
        }
    }

}
