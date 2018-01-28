package com.andbase.im.service;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Presence;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.ab.task.AbTask;
import com.ab.task.AbTaskItem;
import com.ab.task.AbTaskListener;
import com.ab.util.AbAppUtil;
import com.andbase.im.global.IMConstant;
import com.andbase.im.util.IMUtil;

/**
 * 重连接服务.
 */
public class ReConnectService extends Service {
	private Context context;

	@Override
	public void onCreate() {
		context = this;
		Log.i("TAG", "[服务]重连服务：启动");
		//意图过滤器
		IntentFilter mFilter = new IntentFilter();
		mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		//监听网络状态变化的 广播接收器
		registerReceiver(reConnectionBroadcastReceiver, mFilter);
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
	    Log.i("TAG", "[服务]重连服务：关闭");
		unregisterReceiver(reConnectionBroadcastReceiver);
		super.onDestroy();
	}
//这个是广播
	BroadcastReceiver reConnectionBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
				Log.d("TAG", "----监听到网络状态改变----");
				XMPPConnection connection = IMUtil.getXMPPConnection();
				//网络是否可以连接到网络
				boolean isAvailable = AbAppUtil.isNetworkAvailable(context);
				//分别是网络可用和不可用时的状态
				if (isAvailable) {
					if (!connection.isConnected()) {
						reConnect(connection);
					} else {
						sendInentAndPre(IMConstant.RECONNECT_STATE_SUCCESS);
						Toast.makeText(context, "用户已上线!", Toast.LENGTH_LONG).show();
					}
				} else {
					sendInentAndPre(IMConstant.RECONNECT_STATE_FAIL);
					Toast.makeText(context, "网络断开,用户已离线!", Toast.LENGTH_LONG).show();
				}
			}

		}

	};

	/**
	 * 
	 * 递归重连，直连上为止.
	 * 
	 */
	public void reConnect(final XMPPConnection connection) {
	       AbTask task = new AbTask();
	       final AbTaskItem item = new AbTaskItem();
	       item.setListener(new AbTaskListener(){

                @Override
                public void get(){
                    try{
                        connection.connect();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
    
                @Override
                public void update(){
                    if (connection.isConnected()) {
                        Presence presence = new Presence(Presence.Type.available);
                        connection.sendPacket(presence);
                        Toast.makeText(context, "用户已上线!", Toast.LENGTH_LONG).show();
                     }else{
                         reConnect(connection);
                     }
                }
	           
	       });
	       
	       task.execute(item);
	}

	private void sendInentAndPre(boolean isSuccess) {
		Intent intent = new Intent();
		SharedPreferences preference = getSharedPreferences(IMConstant.IMSHARE,0);
		// 保存在线连接信息
		preference.edit().putBoolean(IMConstant.IS_ONLINE, isSuccess).commit();
		intent.setAction(IMConstant.ACTION_RECONNECT_STATE);
		intent.putExtra(IMConstant.RECONNECT_STATE, isSuccess);
		sendBroadcast(intent);
	}

}
