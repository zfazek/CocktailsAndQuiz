package com.ongroa.cocktails;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnClickListener {

	private Button buttonList;
	private Button buttonQuiz;
	private CocktailsAndQuiz mMain;
	private Spinner cbQuestion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		buttonList = (Button)findViewById(R.id.buttonList);
		buttonQuiz = (Button)findViewById(R.id.buttonQuiz);
		cbQuestion = (Spinner)findViewById(R.id.cbQuestion);
		buttonList.setOnClickListener(this);
		buttonQuiz.setOnClickListener(this);
		mMain = new CocktailsAndQuiz();
		mMain.init();
		ArrayAdapter<Integer> aaQuestion = new ArrayAdapter<Integer>(this, 
				android.R.layout.simple_spinner_item, mMain.getQuestions());
		cbQuestion.setAdapter(aaQuestion);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.buttonList:
			intent = new Intent(this, ListCocktailsActivity.class);
			intent.putExtra("main", mMain);
			startActivity(intent);
			break;
		case R.id.buttonQuiz:
			mMain.setNofQuizCocktails(Integer.parseInt(cbQuestion.getSelectedItem().toString()));
			intent = new Intent(this, QuizActivity.class);
			intent.putExtra("main", mMain);
			startActivity(intent);
			break;
		}
	}
}
