package com.opensource.ide.rebuild;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.core.content.ContextCompat;

import com.blogspot.atifsoftwares.animatoolib.*;

import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import android.Manifest;

import android.os.AsyncTask;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

	
	private String result = "";

	

 

	

	

	@Override

	protected void onCreate(Bundle _savedInstanceState) {

		super.onCreate(_savedInstanceState);

		setContentView(R.layout.main);

		initialize(_savedInstanceState);

		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED

		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);

		}

		else {

			initializeLogic();

		}

	}

	

	@Override

	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		if (requestCode == 1000) {

			initializeLogic();

		}

	}

	

	private void initialize(Bundle _savedInstanceState) {

 

	}

	

	private void initializeLogic() {

		try {

			FileUtil.deleteFile(getApplicationContext().getExternalFilesDir(null).getAbsolutePath().concat("/core-lambdas-stubs.jar"));

			FileUtil.deleteFile(getApplicationContext().getExternalFilesDir(null).getAbsolutePath().concat("/android.jar"));

		} catch(Exception e) {

			SketchwareUtil.showMessage(getApplicationContext(), "Failed to delete old files");

		}

		new async().execute("copyTools");

	}

 

	

	private class async extends AsyncTask<String, Integer, String> {

		@Override

		protected void onPreExecute() {

			

		}

		

		@Override

		protected String doInBackground(String... params) {

			String _param = params[0];

			switch(_param) {

				case "copyTools": {

					try {

						BuildUtil.extract("android.jar", getApplicationContext());

						BuildUtil.extract("core-lambda-stubs.jar", getApplicationContext());

					} catch(Exception e) {

						result = "Failed";

					}

					break;

				}

			}

			return (result);

		}

		

		@Override

		protected void onProgressUpdate(Integer... values) {

			int _value = values[0];

			

		}

		

		@Override

		protected void onPostExecute(String _result) {

			SketchwareUtil.showMessage(getApplicationContext(), _result);

			startActivity(new Intent(MainActivity.this, EditorActivity.class)); Animatoo.animateShrink(MainActivity.this);

		}

	}

}
