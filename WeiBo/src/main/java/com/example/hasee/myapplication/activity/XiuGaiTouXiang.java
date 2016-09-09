package com.example.hasee.myapplication.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.hasee.myapplication.R;
import com.example.hasee.myapplication.sqlite.WeiBoDataBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XiuGaiTouXiang extends Activity {

    private GridView gridView;
    private WeiBoDataBase weiBoDataBase=WeiBoDataBase.getInstance(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiuigaitouxiang_view);

        gridView= (GridView) findViewById(R.id.gridview);

        final int imageId[]={R.mipmap.a000,R.mipmap.a001,R.mipmap.a002,R.mipmap.a003,R.mipmap.a004,R.mipmap.a005,R.mipmap.a006,R.mipmap.a007,R.mipmap.a008,R.mipmap.a009,R.mipmap.a010,
                R.mipmap.a011,R.mipmap.a012,R.mipmap.a013,R.mipmap.a014,R.mipmap.a015,R.mipmap.a016,R.mipmap.a017,R.mipmap.a018,R.mipmap.a019,R.mipmap.a020,R.mipmap.a021,R.mipmap.a022,
                R.mipmap.a023,R.mipmap.a024,R.mipmap.a025,R.mipmap.a026,R.mipmap.a027,R.mipmap.a028,R.mipmap.a029,R.mipmap.a030,R.mipmap.a031,R.mipmap.a032,R.mipmap.a033,R.mipmap.a034,
                R.mipmap.a035,R.mipmap.a036,R.mipmap.a037,R.mipmap.a038,R.mipmap.a039,R.mipmap.a040,R.mipmap.a041,R.mipmap.a042,R.mipmap.a043,R.mipmap.a044,R.mipmap.a045,R.mipmap.a046,
                R.mipmap.a047,R.mipmap.a048,R.mipmap.a049,R.mipmap.a050,R.mipmap.a051,R.mipmap.a052,R.mipmap.a053,R.mipmap.a054,R.mipmap.a055,R.mipmap.a056,R.mipmap.a057,R.mipmap.a058,
                R.mipmap.a059,R.mipmap.a060,R.mipmap.a061,R.mipmap.a062,R.mipmap.a063,R.mipmap.a064,R.mipmap.a065,R.mipmap.a066,R.mipmap.a067,R.mipmap.a068,R.mipmap.a069,R.mipmap.a070,
                R.mipmap.a071,R.mipmap.a072,R.mipmap.a073,R.mipmap.a074,R.mipmap.a075,R.mipmap.a076,R.mipmap.a077,R.mipmap.a078,R.mipmap.a079,R.mipmap.a080,R.mipmap.a081,R.mipmap.a082,
                R.mipmap.a083,R.mipmap.a084,R.mipmap.a085,R.mipmap.a086,R.mipmap.a087,R.mipmap.a088,R.mipmap.a089,R.mipmap.a090,R.mipmap.a091,R.mipmap.a092,R.mipmap.a093,R.mipmap.a094,
                R.mipmap.a095,R.mipmap.a096,R.mipmap.a097,R.mipmap.a098,R.mipmap.a099};
        List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();

        for(int i=0;i<imageId.length;i++){
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("tupian",imageId[i]);
            list.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,list,R.layout.gridview_view,new String[]{"tupian"},new int[]{R.id.tupian});
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                weiBoDataBase.xiugaitouxiang(imageId[i]);
                Intent intent2=new Intent(XiuGaiTouXiang.this,MainActivity.class);
                intent2.putExtra("yonghuming",MainActivity.getYonghuming());
                startActivity(intent2);
            }
        });
    }



    /*private ImageView tupian;

    private WeiBoDataBase weiBoDataBase=WeiBoDataBase.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiuigaitouxiang_view);

        tupian= (ImageView) findViewById(R.id.tupian);

        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image*//*");
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK) {
                    if(Build.VERSION.SDK_INT>=19){
                        handleImageOnKitKat(data);
                    }else{
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this, uri)){
             //如果是document类型的uri，则通过document id来处理
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id=docId.split("1")[1];
                String selection=MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath=getImagePath(uri,null);
        }
        saveImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        saveImage(imagePath);
    }

    private String getImagePath(Uri uri,String selcetion){
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,selcetion,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    @SuppressLint("NewApi")
    private void saveImage(String imagePath){
        if(imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            tupian.setImageBitmap(bitmap);
            weiBoDataBase.xiugaitouxiang(bitmap.getGenerationId());
        }else{
            Log.i("tupian","failed to get image");
        }
    }*/
}
