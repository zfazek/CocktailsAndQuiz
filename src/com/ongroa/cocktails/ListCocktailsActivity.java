package com.ongroa.cocktails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListCocktailsActivity extends ListActivity {

	private CocktailsAndQuiz mMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		TextView text = (TextView)findViewById(R.id.cocktail_number);
		mMain = (CocktailsAndQuiz) getIntent().getSerializableExtra("main");
		try {
			mMain.parseCocktails(new BufferedReader(
					new InputStreamReader(getAssets().open("cocktails.txt"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		text.setText(""+mMain.getCocktails().size()+" db koktél");
		List<String> array = new ArrayList<String>();
		for (int i = 0; i < mMain.getCocktails().size(); i++) { 
			array.add(i + 1 + ". "+ mMain.getCocktails().get(i));
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, array);
		setListAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_list, menu);
		return true;
	}

}
