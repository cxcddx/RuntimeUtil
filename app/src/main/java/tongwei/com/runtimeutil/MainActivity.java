package tongwei.com.runtimeutil;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        btn = (Button) this.findViewById(R.id.btn);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn:
//                getPermission();
                getPermissionByUtil();
                break;
            default:
                break;
        }
    }

    private void getPermissionByUtil() {
        new getRuntimeUtil().getRequest(new String[]{
            android.Manifest.permission.CALL_PHONE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE});
    }

    private void getPermission() {

        requestRuntimePermission(new String[] {
            android.Manifest.permission.CALL_PHONE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},new PermissonListener() {
            @Override
            public void onGranted() {
                Toast.makeText(MainActivity.this, "所有权限申请都通过了", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDenied(List<String> deniedPermissions) {

                for (String permission: deniedPermissions) {
                    Toast.makeText(MainActivity.this, permission+"权限被拒绝了,程序显示可能会不正常", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
