package ru.cav.medici;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.ArrayList;

import ru.cav.medici.database.DataBaseConnector;
import ru.cav.medici.models.HeadChainModel;
import ru.cav.medici.models.SpecChainModel;

public class AddNewRecordActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1000;
    private static final String TAG = "BAR RECORD";
    private BarcodeDetector mBarcodeDetector;
    private CameraSource mCameraSource;
    private SurfaceView mCameraView;

    private String barStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);


        mCameraView = (SurfaceView) findViewById(R.id.surface_view);



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},PERMISSION_REQUEST_CODE);
            }
        } else {
            createCamera();
        }

        setTaskBar();
    }

    @SuppressWarnings("unchecked")
    private void createCamera(){
        mBarcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        mCameraSource = new CameraSource.Builder(this, mBarcodeDetector)
                .setAutoFocusEnabled(true).build();


        mBarcodeDetector.setProcessor(new Detector.Processor() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections detections) {
                final SparseArray barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    barStr = ((Barcode) barcodes.valueAt(0)).displayValue;
                    playMessage();
                    mBarcodeDetector.release();
                    Intent intent = new Intent(AddNewRecordActivity.this,ChangeActivity.class);
                    intent.putExtra(ConstantManager.CHANGE_FLG,ConstantManager.CHANGE_INSERT);
                    startActivityForResult(intent,ConstantManager.ADD_NEW_RECORD);
                    // mCameraSource.stop();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode!=PERMISSION_REQUEST_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }
        if (grantResults.length!=0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            createCamera();
            return;
        }

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("PC")
                .setMessage(R.string.no_camera_permission)
                .setPositiveButton(R.string.ok, listener)
                .show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCameraSource!=null){
            mCameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @SuppressWarnings("MissingPermission")
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {

                    try {
                        mCameraSource.start(mCameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    mCameraSource.stop();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCameraSource.release();
        mBarcodeDetector.release();
    }

    private void setTaskBar(){
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d(TAG,"HOME");
                onBackPressed();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void playMessage() {
        /*
        Ringtone ringtone;
        RingtoneManager rm = new RingtoneManager(this);
        rm.setType(RingtoneManager.TYPE_NOTIFICATION);
        ringtone = rm.getRingtone(0);
        ringtone.play();*/

        long mills = 300L;
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(mills);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case ConstantManager.ADD_NEW_RECORD:
                if (resultCode == RESULT_OK && data !=null) {
                    String title = data.getStringExtra(ConstantManager.REC_TITLE);
                    String desc = data.getStringExtra(ConstantManager.REC_DESC);
                    System.out.println(barStr);
                    ArrayList<SpecChainModel> chainModels = new ArrayList<>();
                    String item[] = barStr.split(" ");
                    for (int i=0;i<item.length;i++){
                        chainModels.add(new SpecChainModel(i,item[i],0));
                    }
                    DataBaseConnector db = new DataBaseConnector(this);
                    HeadChainModel model = new HeadChainModel(-1,title,desc,chainModels);
                    db.open();
                    db.insertChain(model);
                    db.close();
                    finish();
                }
                break;
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }
}
