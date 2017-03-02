package tongwei.com.runtimeutil;

import android.Manifest;
import android.widget.Toast;

import java.util.List;

/**
 * Created by CX on 2017/3/2.
 */
public class getRuntimeUtil {

    public void getRequest(String[] permissions) {
        BaseActivity.requestRuntimePermission(permissions, new PermissonListener() {
            @Override
            public void onGranted() {
                Toast.makeText(ActivityCollector.getTopActivity(), "所有权限申请都通过了", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDenied(List<String> deniedPermissions) {

                for (String permission: deniedPermissions) {
                    Toast.makeText(ActivityCollector.getTopActivity(), permission+"权限被拒绝了,程序显示可能会不正常", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
