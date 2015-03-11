package com.korkmaz.todolist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView myListView = (ListView)findViewById(R.id.myListView);
		final EditText myEditText = (EditText)findViewById(R.id.myEditText);
		
		//Yapılacaklar listesi için bir ArrayList dizisi tanımlama
		final ArrayList<ToDoItem> todoItems = new ArrayList<ToDoItem>();
		
		//Array'ı ListView'a bağlamak için bir ArrayAdapter oluşturma
		int resID = R.layout.todolis_item;
		final ArrayAdapter<ToDoItem> aA;
		aA = new ArrayAdapter<ToDoItem>(this, resID, todoItems);
		
		//ArrayAdapter'ı ListView'e bağayın
		myListView.setAdapter(aA);
		
		myEditText.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				
				if(event.getAction() == KeyEvent.ACTION_DOWN){
					if(keyCode == KeyEvent.KEYCODE_ENTER){
						ToDoItem newItem = new ToDoItem(myEditText.getText().toString());
						todoItems.add(0,newItem);
						aA.notifyDataSetChanged();
						myEditText.setText("");
						return true;
					}
				}
				return false;
			}
		});
		
		registerForContextMenu(myListView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
