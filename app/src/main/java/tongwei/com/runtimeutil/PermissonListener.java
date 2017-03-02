package tongwei.com.runtimeutil;

import java.util.List;

/**
 * Created by CX on 2017/3/2.
 */
public interface PermissonListener {

    void onGranted();

    void onDenied(List<String> deniedPermissions);
}
