package com.yrj520.pfapp.ymjg.UI.photo;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.yrj520.pfapp.ymjg.UI.view.base.BaseActivity;
import com.yrj520.pfapp.ymjg.UI.utils.SDCardUtils;
import com.yrj520.pfapp.ymjg.UI.view.base.MyBarView;
import com.yrj520.pfapp.ymjg.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * 调用媒体选择库
 * 需要在inten中传递2个参数
 * 1. 选择模式 chose_mode  0  //单选 1多选
 * 2. 选择张数 max_chose_count  多选模式默认 9 张
 */
public class MediaChoseActivity extends BaseActivity {

    public static final int CHOSE_MODE_SINGLE = 0;
    public static final int CHOSE_MODE_MULTIPLE = 1;
    public int max_chose_count = 1;
    public LinkedHashMap imasgemap = new LinkedHashMap();
    public LinkedHashSet imagesChose = new LinkedHashSet();
    PhotoGalleryFragment photoGalleryFragment;
    int chosemode = CHOSE_MODE_MULTIPLE;

    boolean isneedCrop = false;
    int crop_image_w, crop_image_h;
    private MyBarView myBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_chose);
        myBarView = (MyBarView) findViewById(R.id.mb_account_bar);
        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        chosemode = getIntent().getIntExtra("chose_mode", CHOSE_MODE_MULTIPLE);
        if (chosemode == 1) {
            max_chose_count = getIntent().getIntExtra("max_chose_count", 9);
        }
        //是否需要剪裁
        isneedCrop = getIntent().getBooleanExtra("crop", false);
        if (isneedCrop) {
            chosemode = CHOSE_MODE_SINGLE;
            max_chose_count = 1;
            crop_image_w = getIntent().getIntExtra("crop_image_w", 720);
            crop_image_h = getIntent().getIntExtra("crop_image_h", 720);
        } else {
            myBarView.setRightText("确认");
            myBarView.setRightColor(Color.parseColor("#44A598"));
        }
        photoGalleryFragment = PhotoGalleryFragment.newInstance(chosemode, max_chose_count);
        fragmentTransaction.add(R.id.container, photoGalleryFragment, PhotoGalleryFragment.class.getSimpleName());
        fragmentTransaction.commit();

        myBarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imasgemap.isEmpty()) {
                    setResult(RESULT_CANCELED);
                    finish();
                } else {
                    sendImages();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            String FRAGMENTS_TAG = "android:support:fragments";
            outState.remove(FRAGMENTS_TAG);
        }
    }

    boolean isPriview = false;

    public void starPriview(LinkedHashMap map, String currentimage) {
        if (isneedCrop && !isCropOver) {
            sendStarCrop(currentimage);
        } else {
            Set<String> keys = map.keySet();
            ArrayList<String> ims = new ArrayList<String>();
            int pos = 0;
            int i = 0;
            for (String s : keys) {
                ims.add((String) map.get(s));
                if (map.get(s).equals(currentimage)) {
                    pos = i;
                }
                i++;
            }
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, ImagePreviewFragemnt.newInstance(ims, pos), ImagePreviewFragemnt.class.getSimpleName());
            fragmentTransaction.addToBackStack("con");
            fragmentTransaction.commit();
            isPriview = true;
            invalidateOptionsMenu();
        }
    }

    public Fragment getCurrentFragment(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public LinkedHashMap getImageChoseMap() {
        return imasgemap;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.photo_gallery_menu, menu);
//        if (isPriview && (chosemode == CHOSE_MODE_MULTIPLE)) {
//            menu.findItem(R.id.menu_photo_delete).setVisible(true);
//        } else {
//            menu.findItem(R.id.menu_photo_delete).setVisible(false);
//        }
//        if (imasgemap.size() < 1) {
//            menu.findItem(R.id.menu_photo_count).setEnabled(false);
//            menu.findItem(R.id.menu_photo_count).setVisible(false);
//        } else {
//            menu.findItem(R.id.menu_photo_count).setEnabled(true);
//            menu.findItem(R.id.menu_photo_count).setVisible(true);
//            if (chosemode == CHOSE_MODE_MULTIPLE) {
//                menu.findItem(R.id.menu_photo_count).setTitle("发送(" + imasgemap.size() + "/" + max_chose_count + ")");
//            } else {
//                menu.findItem(R.id.menu_photo_count).setTitle("发送(1)");
//            }
//        }
//        return super.onCreateOptionsMenu(menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            popFragment();
//        } else if (item.getItemId() == R.id.menu_photo_delete) {
//            ImagePreviewFragemnt fragemnt = (ImagePreviewFragemnt) getCurrentFragment(ImagePreviewFragemnt.class.getSimpleName());
//            if (fragemnt != null) {
//                String img = fragemnt.delete();
//                Iterator iterator = imasgemap.keySet().iterator();
//                while (iterator.hasNext()) {
//                    String key = (String) iterator.next();
//                    if (imasgemap.get(key).equals(img)) {
//                        iterator.remove();
//                    }
//                }
//                invalidateOptionsMenu();
//            }
//        } else if (item.getItemId() == R.id.menu_photo_count) {
//            sendImages();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        FragmentManager fm = getSupportFragmentManager();
        if (keyCode == KeyEvent.KEYCODE_BACK && fm.getBackStackEntryCount() > 0) {
            popFragment();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void log(String msg) {
        Log.i("gallery", msg);
    }

    public void popFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStackImmediate();
        isPriview = false;
        invalidateOptionsMenu();
        if (photoGalleryFragment != null && chosemode == CHOSE_MODE_MULTIPLE) {
            photoGalleryFragment.notifyDataSetChanged();
        }
    }

    boolean isCropOver = false;

    public void sendImages() {
        if (isneedCrop && !isCropOver) {
            Iterator iterator = imasgemap.keySet().iterator();
            File file = new File(iterator.next().toString());
            if (!file.exists()) {
                Toast.makeText(this, "获取文件失败", Toast.LENGTH_SHORT).show();
            }
            sendStarCrop(file.getAbsolutePath());
        } else {
            Intent intent = new Intent();
            ArrayList<String> img = new ArrayList<String>();
            Iterator iterator = imasgemap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                img.add((String) imasgemap.get(key));
            }
            intent.putExtra("data", img);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CROP && (chosemode == CHOSE_MODE_SINGLE)) {
            Intent intent = new Intent();
            Uri crop_path = data.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
            isCropOver = true;
            if (crop_path != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, crop_path);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "截取图片失败", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CAMERA && (chosemode == CHOSE_MODE_SINGLE)) {
            if (currentfile != null && currentfile.exists() && currentfile.length() > 10) {
                if (isneedCrop && !isCropOver) {
                    sendStarCrop(currentfile.getAbsolutePath());
                } else {
                    Intent intent = new Intent();
                    ArrayList<String> img = new ArrayList<String>();
                    img.add(currentfile.getAbsolutePath());
                    intent.putExtra("data", img);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                insertImage(currentfile.getAbsolutePath());
            } else {
                Toast.makeText(MediaChoseActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CAMERA && (chosemode == CHOSE_MODE_MULTIPLE)) {

            if (currentfile != null && currentfile.exists() && currentfile.length() > 10) {
                getImageChoseMap().put(currentfile.getAbsolutePath(), currentfile.getAbsolutePath());
                invalidateOptionsMenu();
                insertImage(currentfile.getAbsolutePath());
            } else {
                Toast.makeText(MediaChoseActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void insertImage(String fileName) {
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(),
                    fileName, new File(fileName).getName(),
                    new File(fileName).getName());
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(new File(fileName));
            intent.setData(uri);
            sendBroadcast(intent);
            MediaScannerConnection.scanFile(this, new String[]{fileName}, new String[]{"image/jpeg"}, new MediaScannerConnection.MediaScannerConnectionClient() {
                @Override
                public void onMediaScannerConnected() {
                }

                @Override
                public void onScanCompleted(String path, Uri uri) {
                    photoGalleryFragment.addCaptureFile(path);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static final int REQUEST_CODE_CAMERA = 2001;
    public static final int REQUEST_CODE_CROP = 6709;
    File currentfile;

    public void sendStarCamera() {
        if (!SDCardUtils.isSDCardEnable()) {
            Toast.makeText(this, "外部存储设备不可用,照相功能暂不能使用", Toast.LENGTH_SHORT).show();
            return;
        }
        currentfile = getTempFile();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentfile));
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    public void sendStarCrop(String path) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped.png"));
        Intent intent = new Intent(this, CropImageActivity.class);
        intent.setData(Uri.fromFile(new File(path)));
        intent.putExtra("max_x", crop_image_w);
        intent.putExtra("max_y", crop_image_h);
        intent.putExtra("aspect_x", crop_image_w);
        intent.putExtra("aspect_y", crop_image_h);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, destination);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, getCropFile().getAbsolutePath());
        startActivityForResult(intent, REQUEST_CODE_CROP);
    }


    public File getTempFile() {
        String str = null;
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        date = new Date(System.currentTimeMillis());
        str = format.format(date);
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "IMG_" + str + ".jpg");
    }

    public File getCropFile() {
        return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), ".crop.jpg");
    }

}
