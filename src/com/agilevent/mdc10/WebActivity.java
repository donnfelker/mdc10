package com.agilevent.mdc10;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends Activity {
	
	final Activity mActivity = this; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		
		setContentView(R.layout.web); 
		 
		loadWebView(); 
		
	}

	private void loadWebView() {
		WebView wv = (WebView)findViewById(R.id.hdcWebsiteView); 
		WebSettings settings = wv.getSettings(); 
		
		settings.setJavaScriptEnabled(true);
		
		 wv.setWebChromeClient(new WebChromeClient() {
			 @Override
			public void onProgressChanged(WebView view, int newProgress) {
				mActivity.setTitle("Loading");
				
				mActivity.setProgress(newProgress * 100);
				 
                if(newProgress == 100)
                	mActivity.setTitle(R.string.app_name);
			}
			
		 });
		
		 wv.setWebViewClient(new WebViewClient() {
			 
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true; 
			}
			
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(WebActivity.this, description, Toast.LENGTH_LONG).show(); 
			}
		 });
		 
		

		
		wv.loadUrl("http://mdc.ilmservice.com");
		
	}
}
