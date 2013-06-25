package com.ongroa.cocktails;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

public class QuizResultActivity extends Activity 
implements OnClickListener {

	private String NO_ANSWER = "---";

	private CocktailsAndQuiz mMain;

	private ScrollView scrollView;
	private LinearLayout layoutMain;

	private Button buttonResultOk;
	private TextView textCocktailName;
	private TextView textAlapszesz1;
	private TextView textAlapszesz2;
	private TextView textPohar1;
	private TextView textPohar2;
	private TextView textFajta1;
	private TextView textFajta2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart() {
		super.onStart();
		scrollView = new ScrollView(this);
		setContentView(scrollView);
		scrollView.setBackgroundColor(getResources().
				getColor(android.R.color.darker_gray));
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mMain = (CocktailsAndQuiz) getIntent().getSerializableExtra("main");
		buttonResultOk = new Button(this);
		buttonResultOk.setOnClickListener(this);
		layoutMain = new LinearLayout(this);
		layoutMain.setOrientation(LinearLayout.VERTICAL);
		scrollView.addView(layoutMain);
		LinearLayout layout;

		List<Cocktail> quizCocktails = mMain.getQuizCocktails();
		String s = "";
		int color;
		for (int idx = 0; idx < quizCocktails.size(); idx++) {
			Cocktail quizCocktail = quizCocktails.get(idx);

			textCocktailName = new TextView(this);
			textAlapszesz1 = new TextView(this);
			textAlapszesz2 = new TextView(this);
			textPohar1 = new TextView(this);
			textPohar2 = new TextView(this);
			textFajta1 = new TextView(this);
			textFajta2 = new TextView(this);

			layoutMain.addView(textCocktailName);
			String name = quizCocktail.getName();
			Cocktail refCocktail = mMain.getCocktail(name);
			textCocktailName.setText(idx + 1 + ". " + name);

			layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.HORIZONTAL);
			layoutMain.addView(layout);
			TextView ref = new TextView(this);
			ref.setText(getString(R.string.referencia));
			layout.addView(ref);
			TextView answer = new TextView(this);
			answer.setText(getString(R.string.te_valaszod));
			answer.setLayoutParams(new TableLayout.LayoutParams(
					TableLayout.LayoutParams.FILL_PARENT,
					TableLayout.LayoutParams.WRAP_CONTENT));
			answer.setGravity(Gravity.RIGHT);
			layout.addView(answer);

			layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.HORIZONTAL);
			layoutMain.addView(layout);
			layout.addView(textAlapszesz1);
			layout.addView(textAlapszesz2);
			textAlapszesz2.setLayoutParams(new TableLayout.LayoutParams(
					TableLayout.LayoutParams.FILL_PARENT,
					TableLayout.LayoutParams.WRAP_CONTENT));
			textAlapszesz2.setGravity(Gravity.RIGHT);

			layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.HORIZONTAL);
			layoutMain.addView(layout);
			layout.addView(textPohar1);
			layout.addView(textPohar2);
			textPohar2.setLayoutParams(new TableLayout.LayoutParams(
					TableLayout.LayoutParams.FILL_PARENT,
					TableLayout.LayoutParams.WRAP_CONTENT));
			textPohar2.setGravity(Gravity.RIGHT);

			layout = new LinearLayout(this);
			layout.setOrientation(LinearLayout.HORIZONTAL);
			layoutMain.addView(layout);
			layout.addView(textFajta1);
			layout.addView(textFajta2);
			textFajta2.setLayoutParams(new TableLayout.LayoutParams(
					TableLayout.LayoutParams.FILL_PARENT,
					TableLayout.LayoutParams.WRAP_CONTENT));
			textFajta2.setGravity(Gravity.RIGHT);

			textAlapszesz1.setText(refCocktail.getAlapszesz());
			s = quizCocktail.getAlapszesz();
			if (s.equals("")) {
				s = NO_ANSWER;
			}
			if (s.equals(refCocktail.getAlapszesz())) {
				color = Color.GREEN;
			} else {
				color = Color.RED;
			}
			textAlapszesz2.setText(s);
			textAlapszesz2.setTextColor(color);

			textPohar1.setText(refCocktail.getPohar());
			s = quizCocktail.getPohar();
			if (s.equals("")) {
				s = NO_ANSWER;
			}
			if (s.equals(refCocktail.getPohar())) {
				color = Color.GREEN;
			} else {
				color = Color.RED;
			}
			textPohar2.setText(s);
			textPohar2.setTextColor(color);

			textFajta1.setText(refCocktail.getFajta());
			s = quizCocktail.getFajta();
			if (s.equals("")) {
				s = NO_ANSWER;
			}
			if (s.equals(refCocktail.getFajta())) {
				color = Color.GREEN;
			} else {
				color = Color.RED;
			}
			textFajta2.setText(s);
			textFajta2.setTextColor(color);

			layoutMain.addView(new TextView(this));

			quizCocktail.evaluateOsszetevok(refCocktail);

			List<Osszetevo> refOsszetevok = refCocktail.getOsszetevokSorted();
			List<Osszetevo> quizOsszetevok = quizCocktail.getOsszetevokSorted();
			for (int c = 0; c < Math.max(refCocktail.getOsszetevok().size(),
					quizCocktail.getOsszetevok().size()); c++) {
				ref = new TextView(this);
				answer = new TextView(this);

				if (refCocktail.getOsszetevok().size() > c) {
					s = String.format("%s %s",
							refOsszetevok.get(c).getMennyiseg(),
							refOsszetevok.get(c).getNev());
					ref.setText(s);
				} else {
					ref.setText(NO_ANSWER);
				}

				if (quizCocktail.getOsszetevok().size() > c) {
					s = String.format("%s %s",
							quizOsszetevok.get(c).getMennyiseg(),
							quizOsszetevok.get(c).getNev());
					answer.setText(s);
					if (quizOsszetevok.get(c).isValid()) {
						color = Color.GREEN;
						mMain.incNofGoodAnswers();
					} else {
						color = Color.RED;
						mMain.incNofWrongAnswers();
					}
				} else {
					answer.setText(NO_ANSWER);
					color = Color.RED;
					mMain.incNofWrongAnswers();
				}
				answer.setTextColor(color);

				layout = new LinearLayout(this);
				layout.setOrientation(LinearLayout.HORIZONTAL);
				layoutMain.addView(layout);
				layout.addView(ref);
				layout.addView(answer);
				answer.setLayoutParams(new TableLayout.LayoutParams(
						TableLayout.LayoutParams.FILL_PARENT,
						TableLayout.LayoutParams.WRAP_CONTENT));
				answer.setGravity(Gravity.RIGHT);
			}
			layoutMain.addView(new TextView(this));
		}

		TextView eredmeny = new TextView(this);
		eredmeny.setText(getString(R.string.eredmeny));
		TextView labelHibatlan = new TextView(this);
		labelHibatlan.setText(getString(R.string.hibatlan_koktelok));
		TextView labelHelyes = new TextView(this);
		labelHelyes.setText(getString(R.string.helyes_valaszok));
		TextView hibatlan = new TextView(this);
		hibatlan.setText(mMain.getGoodCocktailsResult());
		TextView helyes = new TextView(this);
		helyes.setText(mMain.getGoodAnswersResult());
		layoutMain.addView(eredmeny);
		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layoutMain.addView(layout);
		layout.addView(labelHibatlan);
		layout.addView(hibatlan);
		hibatlan.setLayoutParams(new TableLayout.LayoutParams(
				TableLayout.LayoutParams.FILL_PARENT,
				TableLayout.LayoutParams.WRAP_CONTENT));
		hibatlan.setGravity(Gravity.RIGHT);
		
		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layoutMain.addView(layout);
		layout.addView(labelHelyes);
		layout.addView(helyes);
		helyes.setLayoutParams(new TableLayout.LayoutParams(
				TableLayout.LayoutParams.FILL_PARENT,
				TableLayout.LayoutParams.WRAP_CONTENT));
		helyes.setGravity(Gravity.RIGHT);

		layoutMain.addView(buttonResultOk);
		buttonResultOk.setText("OK");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_quiz_result, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		if (v.equals(buttonResultOk)) {
			intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}

}
