package com.ongroa.cocktails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
import android.widget.TextView;

public class QuizActivity extends Activity implements OnClickListener {

	private CocktailsAndQuiz mMain;
	private int quizIndex;
	private List<Integer> idxs;

	private Button buttonOk;
	private TextView textName;
	private TextView cocktailNumber;
	private Spinner cbAlapszesz;
	private Spinner cbPohar;
	private Spinner cbFajta;
	private Spinner cbMennyiseg1;
	private Spinner cbNev1;
	private Spinner cbMennyiseg2;
	private Spinner cbNev2;
	private Spinner cbMennyiseg3;
	private Spinner cbNev3;
	private Spinner cbMennyiseg4;
	private Spinner cbNev4;
	private Spinner cbMennyiseg5;
	private Spinner cbNev5;
	private Spinner cbMennyiseg6;
	private Spinner cbNev6;
	private Spinner cbMennyiseg7;
	private Spinner cbNev7;
	private Spinner cbMennyiseg8;
	private Spinner cbNev8;
	private Spinner cbMennyiseg9;
	private Spinner cbNev9;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mMain = (CocktailsAndQuiz) getIntent().getSerializableExtra("main");
		try {
			mMain.parseCocktails(new BufferedReader(
					new InputStreamReader(getAssets().open("cocktails.txt"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		buttonOk = (Button)findViewById(R.id.buttonQuizOk);
		buttonOk.setOnClickListener(this);
		textName = (TextView)findViewById(R.id.cocktailName);
		cocktailNumber = (TextView)findViewById(R.id.cocktailNumber);
		cbAlapszesz = (Spinner)findViewById(R.id.cbAlapszesz);
		cbPohar = (Spinner)findViewById(R.id.cbPohar);
		cbFajta = (Spinner)findViewById(R.id.cbfajta);
		cbMennyiseg1 = (Spinner)findViewById(R.id.cbMennyiseg1);
		cbNev1 = (Spinner)findViewById(R.id.cbNev1);
		cbMennyiseg2 = (Spinner)findViewById(R.id.cbMennyiseg2);
		cbNev2 = (Spinner)findViewById(R.id.cbNev2);
		cbMennyiseg3 = (Spinner)findViewById(R.id.cbMennyiseg3);
		cbNev3 = (Spinner)findViewById(R.id.cbNev3);
		cbMennyiseg4 = (Spinner)findViewById(R.id.cbMennyiseg4);
		cbNev4 = (Spinner)findViewById(R.id.cbNev4);
		cbMennyiseg5 = (Spinner)findViewById(R.id.cbMennyiseg5);
		cbNev5 = (Spinner)findViewById(R.id.cbNev5);
		cbMennyiseg6 = (Spinner)findViewById(R.id.cbMennyiseg6);
		cbNev6 = (Spinner)findViewById(R.id.cbNev6);
		cbMennyiseg7 = (Spinner)findViewById(R.id.cbMennyiseg7);
		cbNev7 = (Spinner)findViewById(R.id.cbNev7);
		cbMennyiseg8 = (Spinner)findViewById(R.id.cbMennyiseg8);
		cbNev8 = (Spinner)findViewById(R.id.cbNev8);
		cbMennyiseg9 = (Spinner)findViewById(R.id.cbMennyiseg9);
		cbNev9 = (Spinner)findViewById(R.id.cbNev9);

		addItems();
		idxs = mMain.pickRandomCocktails();
	}

	@Override
	public void onStart() {
		super.onStart();
		mMain.setNofGoodAnswers(0);
		mMain.setNofWrongAnswers(0);
		mMain.setNofGoodCocktails(0);
		mMain.clearQuizCocktails();
		quizIndex = 0;
		clearComboBoxes();
		textName.setText(mMain.getCocktails().get(idxs.get(quizIndex)).getName());
	}
	
	private void addItems() {
		ArrayAdapter<String> aaAlapszesz = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, mMain.getAlapszeszek());
		aaAlapszesz.insert("", 0);
		cbAlapszesz.setAdapter(aaAlapszesz);
		ArrayAdapter<String> aaPohar = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, mMain.getPoharak());
		aaPohar.insert("", 0);
		cbPohar.setAdapter(aaPohar);
		ArrayAdapter<String> aaFajta = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, mMain.getFajtak());
		aaFajta.insert("", 0);
		cbFajta.setAdapter(aaFajta);
		ArrayAdapter<String> aaMennyiseg = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, mMain.getMennyisegek());
		aaMennyiseg.insert("", 0);
		cbMennyiseg1.setAdapter(aaMennyiseg);
		ArrayAdapter<String> aanev = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, mMain.getOsszetevoNevek());
		aanev.insert("", 0);
		cbNev1.setAdapter(aanev);
		cbMennyiseg2.setAdapter(aaMennyiseg);
		cbNev2.setAdapter(aanev);
		cbMennyiseg3.setAdapter(aaMennyiseg);
		cbNev3.setAdapter(aanev);
		cbMennyiseg4.setAdapter(aaMennyiseg);
		cbNev4.setAdapter(aanev);
		cbMennyiseg5.setAdapter(aaMennyiseg);
		cbNev5.setAdapter(aanev);
		cbMennyiseg6.setAdapter(aaMennyiseg);
		cbNev6.setAdapter(aanev);
		cbMennyiseg7.setAdapter(aaMennyiseg);
		cbNev7.setAdapter(aanev);
		cbMennyiseg8.setAdapter(aaMennyiseg);
		cbNev8.setAdapter(aanev);
		cbMennyiseg9.setAdapter(aaMennyiseg);
		cbNev9.setAdapter(aanev);
	}

	private void addNewCocktailToQuiz() {
		Cocktail cocktail = new Cocktail();
		cocktail.setName(textName.getText().toString());
		cocktail.setAlapszesz(cbAlapszesz.getSelectedItem().toString());
		cocktail.setPohar(cbPohar.getSelectedItem().toString());
		cocktail.setFajta(cbFajta.getSelectedItem().toString());
		String mennyiseg = "";
		String nev = "";
		mennyiseg = cbMennyiseg1.getSelectedItem().toString();
		nev = cbNev1.getSelectedItem().toString();
		if (! mennyiseg.equals("") && ! nev.equals("")) {
			cocktail.addOsszetevo(new Osszetevo(mennyiseg, nev));
		}
		mennyiseg = cbMennyiseg2.getSelectedItem().toString();
		nev = cbNev2.getSelectedItem().toString();
		if (! mennyiseg.equals("") && ! nev.equals("")) {
			cocktail.addOsszetevo(new Osszetevo(mennyiseg, nev));
		}
		mennyiseg = cbMennyiseg3.getSelectedItem().toString();
		nev = cbNev3.getSelectedItem().toString();
		if (! mennyiseg.equals("") && ! nev.equals("")) {
			cocktail.addOsszetevo(new Osszetevo(mennyiseg, nev));
		}
		mennyiseg = cbMennyiseg4.getSelectedItem().toString();
		nev = cbNev4.getSelectedItem().toString();
		if (! mennyiseg.equals("") && ! nev.equals("")) {
			cocktail.addOsszetevo(new Osszetevo(mennyiseg, nev));
		}
		mennyiseg = cbMennyiseg5.getSelectedItem().toString();
		nev = cbNev5.getSelectedItem().toString();
		if (! mennyiseg.equals("") && ! nev.equals("")) {
			cocktail.addOsszetevo(new Osszetevo(mennyiseg, nev));
		}
		mennyiseg = cbMennyiseg6.getSelectedItem().toString();
		nev = cbNev6.getSelectedItem().toString();
		if (! mennyiseg.equals("") && ! nev.equals("")) {
			cocktail.addOsszetevo(new Osszetevo(mennyiseg, nev));
		}
		mennyiseg = cbMennyiseg7.getSelectedItem().toString();
		nev = cbNev7.getSelectedItem().toString();
		if (! mennyiseg.equals("") && ! nev.equals("")) {
			cocktail.addOsszetevo(new Osszetevo(mennyiseg, nev));
		}
		mennyiseg = cbMennyiseg8.getSelectedItem().toString();
		nev = cbNev8.getSelectedItem().toString();
		if (! mennyiseg.equals("") && ! nev.equals("")) {
			cocktail.addOsszetevo(new Osszetevo(mennyiseg, nev));
		}
		mennyiseg = cbMennyiseg9.getSelectedItem().toString();
		nev = cbNev9.getSelectedItem().toString();
		if (! mennyiseg.equals("") && ! nev.equals("")) {
			cocktail.addOsszetevo(new Osszetevo(mennyiseg, nev));
		}
		mMain.addCocktailToQuiz(cocktail);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_quiz, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.buttonQuizOk:
			addNewCocktailToQuiz();
			System.out.println(mMain.getQuizCocktails().size());
			if (mMain.getQuizCocktails().size() == 
					mMain.getNofQuizCocktails()) {
				mMain.evaluateQuiz();
				intent = new Intent(this, QuizResultActivity.class);
				intent.putExtra("main", mMain);
				startActivity(intent);
			} else {
				quizIndex++;
				clearComboBoxes();
				textName.setText(mMain.getCocktails().
						get(idxs.get(quizIndex)).getName());
			}

			break;
		}
	}

	private void clearComboBoxes() {
		cocktailNumber.setText(String.format("%d/%d koktél:", 
				quizIndex + 1, mMain.getNofQuizCocktails()));
		cbAlapszesz.setSelection(0);
		cbPohar.setSelection(0);
		cbFajta.setSelection(0);
		cbMennyiseg1.setSelection(0);
		cbNev1.setSelection(0);
		cbMennyiseg2.setSelection(0);
		cbNev2.setSelection(0);
		cbMennyiseg3.setSelection(0);
		cbNev3.setSelection(0);
		cbMennyiseg4.setSelection(0);
		cbNev4.setSelection(0);
		cbMennyiseg5.setSelection(0);
		cbNev5.setSelection(0);
		cbMennyiseg6.setSelection(0);
		cbNev6.setSelection(0);
		cbMennyiseg7.setSelection(0);
		cbNev7.setSelection(0);
		cbMennyiseg8.setSelection(0);
		cbNev8.setSelection(0);
		cbMennyiseg9.setSelection(0);
		cbNev9.setSelection(0);
	}


}
