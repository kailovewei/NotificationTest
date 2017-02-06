package ykk.notification.com.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //取消对应ID的通知消息
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);
        button= (Button) findViewById(R.id.send_Id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
       // Notification notification=new Notification(R.mipmap.ic_launcher,"This id ticker text",System.currentTimeMillis());
        Intent intent=new Intent(this,NotificationActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        //下面方法google已经废弃了。用Builder方法导出Notification
       // notification.setLatestEventInfo(this,"this is content title","this is content text",pendingIntent);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("This is ticker text")
                .setContentTitle("通知标题")
                .setContentText("我是消息")
                .setDefaults(Notification.DEFAULT_LIGHTS| Notification.DEFAULT_SOUND)
                .setContentIntent(pendingIntent)
                .setContentInfo("Info");
        /*
        通知发出的时候播放一段音频
        Uri soundUri=Uri.fromFile(new File("/system/media/audio/ringtones"));
        builder.build().sound=soundUri;
        //控制手机的震动
        long [] vibrates={0,1000,1000,1000};
        builder.build().vibrate=vibrates;
        //控制LED灯的颜色
        builder.build().ledARGB= Color.GREEN;
        //控制LED闪烁的长短
        builder.build().ledOnMS=1000;
        //控制LED灭的长短
        builder.build().ledOffMS=1000;
        //显示LED
        builder.build().flags=Notification.FLAG_SHOW_LIGHTS;
        */
      builder.build().defaults=Notification.DEFAULT_ALL;
        manager.notify(1,builder.build());

    }
}
