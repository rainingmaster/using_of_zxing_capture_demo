package com.scandemo.Demo;

import com.example.scandemo.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button scan = (Button) findViewById(R.id.to_scan);
		scan.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
				String bsay = "Hello, this is B speaking"; 
				intent.putExtra("listenB", bsay);
				startActivityForResult(intent, 0);
			} 
		});
		Button create = (Button) findViewById(R.id.to_create);
		create.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, CreateImageActivity.class);
				startActivityForResult(intent, 1);
			} 
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode) { //resultCode为回传的标记，回传的是RESULT_OK
			case RESULT_OK:
				Bundle ref=data.getExtras();
				String str=ref.getString("URL");
				TextView ref_view = (TextView) findViewById(R.id.ref_url);
				ref_view.setText(str);
				break;
			default:
			    break;
		}
	}

}
