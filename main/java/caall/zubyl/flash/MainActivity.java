package caall.zubyl.flash;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout layout;
    String cameraId;
    Switch SwitchFlashlight;
    CameraManager cameraManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout=findViewById(R.id.layout);
        SwitchFlashlight =findViewById(R.id.switchflashlight);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
         if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
             Toast.makeText(MainActivity.this, "This device have Camera", Toast.LENGTH_SHORT).show();
             SwitchFlashlight.setEnabled(true);

         }else{
             Toast.makeText(MainActivity.this, "This device no Camera", Toast.LENGTH_SHORT).show();
         }
        }else{
            Toast.makeText(MainActivity.this,"This device no Camera",Toast.LENGTH_LONG).show();
        }

        SwitchFlashlight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    try {

                        cameraId=cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId,true);

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                    SwitchFlashlight.setText("Flash On");
                    layout.setBackgroundResource(R.drawable.on);

                }else{
                    try {
                        cameraId=cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId,false);

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                    SwitchFlashlight.setText("Flash Off");
                    layout.setBackgroundResource(R.drawable.off);

                }
            }
        });
    }
}