package com.mapproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button clocation, bangalore, chennai;
		clocation = (Button) findViewById(R.id.clocation);
		bangalore = (Button) findViewById(R.id.bangalore);
		chennai = (Button) findViewById(R.id.chennai);

		clocation.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						MapActivity1.class);
				startActivity(intent);

			}
		});

		bangalore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						MapActivity2.class);
				startActivity(intent);

			}
		});

		chennai.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						MapActivity3.class);
				startActivity(intent);

			}
		});

	}

}
