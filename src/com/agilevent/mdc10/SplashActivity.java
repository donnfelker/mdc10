package com.agilevent.mdc10;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends Activity {
	
	private static final int MENU_ABOUT = 1; 
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splash); 
		
		setButtonClickListeners(); 
	}

	private void setButtonClickListeners() {
		Button websiteButton = (Button)findViewById(R.id.websiteButton); 
		websiteButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SplashActivity.this, WebActivity.class); 
				startActivity(i); 
			}
		});
		
		Button contactButon = (Button)findViewById(R.id.contactButton); 
		contactButon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SplashActivity.this, ContactActivity.class); 
				startActivity(i); 
			}
		});
		
		Button tweetsButton = (Button)findViewById(R.id.tweetsButton); 
		tweetsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SplashActivity.this, TwitterActivity.class); 
				startActivity(i);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = new MenuInflater(this); 
		mi.inflate(R.menu.main_menu, menu); 
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()) {
			case R.id.menu_about: 
				showDialog(MENU_ABOUT); 
				return true; 
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch(id) {
			case MENU_ABOUT: 
				return getAboutDialog();  
		}
		return super.onCreateDialog(id);
	}

	private Dialog getAboutDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this); 
		builder.setTitle(R.string.about_dialog_title)
			.setIcon(android.R.drawable.ic_dialog_info)
			.setMessage(R.string.about_dialog_message)
			.setPositiveButton(R.string.about_dialog_positive_dialog, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss(); 
				}
			}); 
		return builder.create(); 
	}
}
