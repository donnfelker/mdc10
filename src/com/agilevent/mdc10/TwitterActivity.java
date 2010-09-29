package com.agilevent.mdc10;

import java.util.List;


import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;



import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TwitterActivity extends Activity {
	
	private static final int LOADING_DIALOG = 1;
	List<Status> mResults;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.twitter); 
		
		loadTweets(); 
	}
	
	private void loadTweets() {
		showDialog(LOADING_DIALOG); 
		new TweetAsyncTask().execute(); 
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch(id) {
			case LOADING_DIALOG: 
			return createLoadingDialog(); 
		}
		return super.onCreateDialog(id);
	}
	
	private Dialog createLoadingDialog() {
		ProgressDialog pd =  new ProgressDialog(TwitterActivity.this); 
		pd.setIndeterminate(true); 
		pd.setTitle("Please Wait"); 
		pd.setMessage("Loading...");
		return pd; 
	}

	class TweetAsyncTask extends AsyncTask<Void, Void, List<winterwell.jtwitter.Twitter.Status>>
	{

		@Override
		protected List<winterwell.jtwitter.Twitter.Status> doInBackground(Void... params) {
			Twitter client = new Twitter();
			return client.search("#mdc10");
		}
		
		@Override
		protected void onPostExecute(List<winterwell.jtwitter.Twitter.Status> result) {
			
			mResults = result; 
			ListView tweetList = (ListView)findViewById(R.id.tweetList);
			tweetList.setAdapter(new TweetAdapter()); 
			
			dismissDialog(LOADING_DIALOG); 
		}

		
		
	}
	
	class TweetAdapter extends ArrayAdapter<Status> {

		public TweetAdapter() {
			super(TwitterActivity.this, android.R.layout.simple_list_item_1, mResults); 
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = getLayoutInflater(); 
			View row = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
			TextView tv = (TextView)row.findViewById(android.R.id.text1);
			tv.setAutoLinkMask(Linkify.ALL); 
			
			Status status = mResults.get(position); 
			String displayText = status.getUser().screenName + ": " + status.getText(); 
			
			tv.setText(displayText); 
			tv.setTextSize(13);
			return row; 
		}
		
	}
}
