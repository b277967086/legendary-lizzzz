# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Smile/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# 保留行号
-keepattributes SourceFile,LineNumberTable

#保护匿名、异常、范型不被混淆
-keepattributes Exceptions,InnerClasses,Signature,EnclosingMethod

-keep @android.support.annotation.Keep class *

-keep class * implements android.os.Parcelable
-keepclassmembers class * implements android.os.Parcelable {
 public <fields>;
 private <fields>;
}

# jifen
#-keep ,allowoptimization,allowobfuscation class com.jifen.**{*;}
-keep ,allowoptimization,allowobfuscation class com.jifen.qkbase.**{*;}
-keep ,allowoptimization,allowobfuscation class com.jifen.framework.**{*;}
-keep ,allowoptimization,allowobfuscation class com.jifen.qukan.push.**{*;}

#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keep class * extends android.content.ContentProvider { *; }

-keep class android.support.v4.app.NotificationCompat**{    public *;}
-keepclassmembers class * extends android.webkit.WebChromeClient{
   	public void openFileChooser(...);
}

-keepattributes *Annotation*

-ignorewarning



