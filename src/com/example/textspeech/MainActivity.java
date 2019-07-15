package com.example.textspeech;

import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnInitListener, OnClickListener {
EditText input;
Button button_clear,button_speak;
TextToSpeech tts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		input = (EditText)findViewById(R.id.input);
		button_clear = (Button)findViewById(R.id.button_clear);
		button_speak = (Button)findViewById(R.id.button_speak);
		button_clear.setOnClickListener(this);
		button_speak.setOnClickListener(this);
		
		tts = new TextToSpeech(this, this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_clear:
			input.setText("");
			break;
			
		case R.id.button_speak:
			String text = input.getText().toString();
			if (text.isEmpty()) {
				Toast.makeText(MainActivity.this, "Text is Empty", Toast.LENGTH_SHORT).show();
			}else{
				tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
			}
			break;

		default:
			break;
		}
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onInit(int status) {
		if (status==TextToSpeech.SUCCESS) {
			Locale bahasa =  tts.getLanguage();
			int result = tts.setLanguage(bahasa);
			if (result==TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
Log.e("TTS", "This langungae  in ora support");				
			}else{
				
			}
			
		}else{
			Log.e("TTS", "initialization failed");
		}
		// TODO Auto-generated method stub
		
	}

}
