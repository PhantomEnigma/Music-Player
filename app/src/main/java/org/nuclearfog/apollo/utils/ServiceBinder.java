package org.nuclearfog.apollo.utils;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import org.nuclearfog.apollo.IApolloService;

import java.lang.ref.WeakReference;

/**
 * @author nuclearfog
 */
public class ServiceBinder implements ServiceConnection {

	private WeakReference<ServiceBinderCallback> callback;
	private IApolloService service;


	public ServiceBinder(ServiceBinderCallback callback) {
		this.callback = new WeakReference<>(callback);
	}


	@Override
	public void onServiceConnected(ComponentName name, IBinder iBinder) {
		service = IApolloService.Stub.asInterface(iBinder);
		if (callback.get() != null) {
			callback.get().onServiceConnected();
		}
	}


	@Override
	public void onServiceDisconnected(ComponentName name) {
		service = null;
	}


	public IApolloService getService() {
		return service;
	}

	/**
	 * callback interface used to informa activity when a service is connected/disconnected
	 */
	public interface ServiceBinderCallback {

		/**
		 * called when the service was connected successfully
		 */
		void onServiceConnected();
	}
}
