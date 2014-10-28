package com.example.nfchelloworld1;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private NfcAdapter		mNfcAdapter;
	private IntentFilter[]	mWriteTagFilters;
	private PendingIntent	mNfcPendingIntent;
	private Context			context;
	private TextView		view;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.view = (TextView) findViewById(R.id.textView1);
		context = getApplicationContext();

		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

		mNfcPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
				getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
				| Intent.FLAG_ACTIVITY_CLEAR_TOP), 0);

		IntentFilter discovery = new IntentFilter(
				NfcAdapter.ACTION_TAG_DISCOVERED);
		// IntentFilter ndefDetected = new
		// IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
		// IntentFilter techDetected = new
		// IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);

		// Intent filters for writing to a tag
		mWriteTagFilters = new IntentFilter[] { discovery };
	}

	protected void onResume()
	{
		super.onResume();
		
		mNfcAdapter.enableForegroundDispatch(this, mNfcPendingIntent, mWriteTagFilters,null);
	}
	
	 protected void onNewIntent(Intent intent) 
	 {
		 view.setVisibility(View.VISIBLE);
		 Toast.makeText(this.context, "Git da go duha", Toast.LENGTH_LONG).show();
	 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
