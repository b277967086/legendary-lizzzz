package com.good.diaodiaode.tebiediao;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SDCardUtils {

    public static void readSDCard() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            File sdcardDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            StatFs sf = new StatFs(sdcardDir.getAbsolutePath());
            long blockSize = sf.getBlockSizeLong();
            long blockCount = sf.getBlockCountLong();
            long availCount = sf.getAvailableBlocksLong();
            Log.d("SDCardUtils_"+sdcardDir, "block大小:" + blockSize + ",block数目:" + blockCount + ",总大小:" + blockSize * blockCount / 1024/1024 + "MB");
            Log.d("SDCardUtils", "可用的block数目：:" + availCount + ",剩余空间:" + availCount * blockSize / 1024 /1024+ "MB");
        }
    }

    public static void readSystem(Context ctx) {
//        File root = ctx.getDataDir();
//        StatFs sf = new StatFs(root.getAbsolutePath());
//        long blockSize = sf.getBlockSizeLong();
//        long blockCount = sf.getBlockCountLong();
//        long availCount = sf.getAvailableBlocksLong();
//        Log.d("SDCardUtils_"+root, "block大小:" + blockSize + ",block数目:" + blockCount + ",总大小:" + blockSize * blockCount / 1024/1024 + "MB");
//        Log.d("SDCardUtils", "可用的block数目：:" + availCount + ",可用大小:" + availCount * blockSize / 1024/1024 + "MB");
    }

    public static void writeSystem(Context ctx) {
//        File root = ctx.getDataDir();
//        try {
//            FileOutputStream fileOutputStream = ctx.openFileOutput(root.getPath(), Context.MODE_PRIVATE);
//
//            fileOutputStream.write();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public static void readPathRoot() {
//        String path = "mnt" + File.separator + "sdcard" + File.separator + "qk";
//        try {
//            File root =  new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/qk");
//            StatFs sf = new StatFs(root.getPath());
//            long blockSize = sf.getBlockSizeLong();
//            long blockCount = sf.getBlockCountLong();
//            long availCount = sf.getAvailableBlocksLong();
//            Log.d("SDCardUtils_"+root, "block大小:" + blockSize + ",block数目:" + blockCount + ",总大小:" + blockSize * blockCount / 1024/1024 + "MB");
//            Log.d("SDCardUtils", "可用的block数目：:" + availCount + ",可用大小:" + availCount * blockSize / 1024/1024 + "MB");
//
//        } catch (Exception e) {
//        }
    }


    // 往SD卡的自定义目录下保存文件
    public static boolean saveFileToSDCardCustomDir(byte[] data, String dir,
                                                    String fileName) {
        BufferedOutputStream bos = null;
        if (isSDCardMounted()) {
            File file = new File(getSDCardBaseDir() + File.separator + dir);
            if (!file.exists()) {
                file.mkdirs();// 递归创建自定义目录
            }
            try {
                bos = new BufferedOutputStream(new FileOutputStream(new File(
                        file, fileName)));
                bos.write(data);
                bos.flush();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    // 判断SD卡是否被挂载
    public static boolean isSDCardMounted() {
        // return Environment.getExternalStorageState().equals("mounted");
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    // 获取SD卡的根目录
    public static String getSDCardBaseDir() {
        if (isSDCardMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }
}

