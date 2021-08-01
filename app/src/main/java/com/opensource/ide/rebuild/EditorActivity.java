package com.opensource.ide.rebuild;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.text.Editable;
import android.text.TextWatcher;
import com.blogspot.atifsoftwares.animatoolib.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import com.android.dx.command.Main;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;

public class EditorActivity extends AppCompatActivity {
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private double historySize = 0;
	
	private ArrayList<String> textHistory = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ImageView back;
	private TextView textview1;
	private ImageView undo;
	private ImageView redo;
	private LinearLayout linear5;
	private LinearLayout linear4;
	private EditText edittext1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.editor);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		_coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		back = (ImageView) findViewById(R.id.back);
		textview1 = (TextView) findViewById(R.id.textview1);
		undo = (ImageView) findViewById(R.id.undo);
		redo = (ImageView) findViewById(R.id.redo);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					// dx
					Main.main(new String[]{
						"--dex",
						"/storage/emulated/0/.sketchware/libs/local_libs/ecj/classes.jar",
						"/storage/emulated/0/.sketchware/libs/local_libs/ecj/classes.dex"
					});
				} catch(Exception e) {
					SketchwareUtil.showMessage(getApplicationContext(), "e.toString()");
				}
			}
		});
		
		undo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!textHistory.isEmpty()) {
					historySize--;
					edittext1.setText(textHistory.get((int)(historySize)));
				}
				else {
					SketchwareUtil.CustomToast(getApplicationContext(), "Nothing to undo", 0xFF651FFF, 25, Color.TRANSPARENT, 0, SketchwareUtil.BOTTOM);
				}
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (!textHistory.isEmpty()) {
					if (!edittext1.getText().toString().equals(textHistory.get((int)(textHistory.size())))) {
						textHistory.add(edittext1.getText().toString());
						historySize++;
					}
				}
				else {
					historySize = 0;
					textHistory.add(edittext1.getText().toString());
					historySize++;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
	}
	
	private void initializeLogic() {
		getSupportActionBar().hide();
		SimpleHighlighter highlight = new SimpleHighlighter(edittext1);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
	}
}