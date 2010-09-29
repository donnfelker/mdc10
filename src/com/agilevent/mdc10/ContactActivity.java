package com.agilevent.mdc10;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.contact); 
			
			setButtonClickListener(); 
	}

	private void setButtonClickListener() {
		Button sendButton = (Button)findViewById(R.id.sendButton); 
		sendButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText subject = (EditText)findViewById(R.id.subject); 
				EditText message = (EditText)findViewById(R.id.message);
				
				
				// Start activity to send email. 
				Intent i = new Intent(Intent.ACTION_SEND); 
				i.setType("plain/text");
				i.putExtra(Intent.EXTRA_EMAIL, "dfelker@agilevent.com");
				i.putExtra(Intent.EXTRA_SUBJECT, "From Demo App: " + subject.getText());
				i.putExtra(Intent.EXTRA_TEXT, "Sent From the HDC Lab App. I loved your class! \r\n" + message.getText());
				
				// Start with a choose just in case user DOES not 
				// have anything to handle the intent, we will not 
				// get an exception, or allow which app the user wants 
				// to send the email with. 
				startActivity(Intent.createChooser(i, "Send with ...")); 
				
			}
		});
		
	}
}
