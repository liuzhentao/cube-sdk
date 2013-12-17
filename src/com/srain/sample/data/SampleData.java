package com.srain.sample.data;

import com.srain.sdk.request.CacheableRequest;
import com.srain.sdk.request.CacheableRequestOnSuccHandler;
import com.srain.sdk.request.CacheableRequestPreHandler;
import com.srain.sdk.request.Request;
import com.srain.sdk.request.RequestOnSuccHandler;
import com.srain.sdk.request.RequestPreHandler;

/**
 * <p>
 * When requesting data from web API, it is a good practice to encapsulate all the request logic in a module.
 * </p>
 * <p>
 * This class has some methods to show how to encapsulate the web request.
 * 
 * </p>
 * 
 * <p>
 * Every method can take some parameters which are related to the specific business logic, and a
 * 
 * callback which will be applied after the request is finished.
 * </p>
 * 
 * @author huqiu.lhq
 */
public class SampleData {

	/**
	 * Show how to encapsulate the calling of a web api by Request
	 */
	public static void getRequestSampleData(final String msg, final RequestOnSuccHandler handler) {

		new Request(new RequestPreHandler() {

			@Override
			public void beforeRequest(Request request) {
				String url = "http://cube-server.liaohuqiu.net/api_demo/request.php";
				url += "?msg=" + msg;
				request.setRequestUrl(url);

				// TODO
				// more actions
				// RequestParams params = request.getParams();
				// params.put(key, value);
			}
		}, handler).send();
	}

	/**
	 * Show how to encapsulate the calling of a web api by CacheableRequest
	 */
	public static void getCacheableRequestSampleData(final String msg, final CacheableRequestOnSuccHandler handler) {

		new CacheableRequest(new CacheableRequestPreHandler() {

			@Override
			public void beforeRequest(Request request) {
				String url = "http://cube-server.liaohuqiu.net/api_demo/request.php";
				url += "?msg=" + msg;
				request.setRequestUrl(url);
			}

			@Override
			public String getSpecificCacheKey() {
				return "sample_data";
			}

			@Override
			public int getCacheTime() {
				return 100;
			}

			@Override
			public String getInitFileAssertPath() {
				return "request_init/sample_data.json";
			}
		}, handler).send();
	}
}