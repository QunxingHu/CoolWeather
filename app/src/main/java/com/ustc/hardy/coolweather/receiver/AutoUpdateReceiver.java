package com.ustc.hardy.coolweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ustc.hardy.coolweather.service.AutoUpdateService;

/**
 * Author: Created by QuincyHu on 2016/5/17 0017 12:53.
 * Email:  zhihuqunxing@163.com
 */
public class AutoUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AutoUpdateService.class);
        context.startService(i);
    }
}
