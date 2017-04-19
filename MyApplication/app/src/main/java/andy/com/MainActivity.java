package andy.com;

import android.Manifest.permission;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import static andy.com.R.id.btn;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CALL_PHONE = 1;

    private static final String PHONE_NUM = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(btn);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }

    private void makePhoneCall() {
        if (ContextCompat.checkSelfPermission(this, permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            call(PHONE_NUM);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission.CALL_PHONE}, REQUEST_CODE_CALL_PHONE);
        }
    }

    private void call(String mobile) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.CALL");
        intent.setData(Uri.parse("tel:" + mobile));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_CALL_PHONE && permissions.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call(PHONE_NUM);
            } else {
                Toast.makeText(MainActivity.this, "申请拨打电话权限被拒绝", Toast.LENGTH_LONG).show();
            }
        }
    }
}
