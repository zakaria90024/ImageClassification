package com.example.imageclassification;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

public class MainActivity2 extends AppCompatActivity {
    public Button select_image_button;
    public Button make_prediction;
    public ImageView img_view;
    public TextView text_view;
    public Bitmap bitmap;
    public Button camerabtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public final void checkandGetpermissions() {
        if (this.checkSelfPermission("android.permission.CAMERA") == PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{"android.permission.CAMERA"}, 100);
        } else {
           // Toast.makeText((Context)this, (CharSequence)"Camera permission granted", 0).show();
        }

    }

    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkParameterIsNotNull(permissions, "permissions");
        Intrinsics.checkParameterIsNotNull(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == 0) {
                //Toast.makeText((Context)this, (CharSequence)"Camera permission granted", 0).show();
            } else {
                //Toast.makeText((Context)this, (CharSequence)"Permission Denied", 0).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ImageView var10000;
        Bitmap var10001;
        if (requestCode == 250) {
            var10000 = this.img_view;
            if (var10000 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("img_view");
            }

            var10000.setImageURI(data != null ? data.getData() : null);
            Uri uri = data != null ? data.getData() : null;
            try {
                var10001 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Intrinsics.checkExpressionValueIsNotNull(var10001, "MediaStore.Images.Media.…his.contentResolver, uri)");
            //this.bitmap = var10001;

        } else if (requestCode == 200 && resultCode == -1) {
            Object var6;
            label43: {
                if (data != null) {
                    Bundle var5 = data.getExtras();
                    if (var5 != null) {
                        var6 = var5.get("data");
                        break label43;
                    }
                }

                var6 = null;
            }

            if (var6 == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.graphics.Bitmap");
            }

            this.bitmap = (Bitmap)var6;
            var10000 = this.img_view;
            if (var10000 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("img_view");
            }

            var10001 = this.bitmap;
            if (var10001 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bitmap");
            }

            var10000.setImageBitmap(var10001);
        }

    }

    public final int getMax(@NotNull float[] arr) {
        Intrinsics.checkParameterIsNotNull(arr, "arr");
        int ind = 0;
        float min = 0.0F;
        int i = 0;

        for(short var5 = 1000; i <= var5; ++i) {
            if (arr[i] > min) {
                min = arr[i];
                ind = i;
            }
        }

        return ind;
    }
}