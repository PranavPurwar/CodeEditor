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

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		initialize(savedInstanceState);

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

			
		        try {

			        FileUtil.deleteFile(getApplicationContext().getExternalFilesDir(null).getAbsolutePath().concat("/core-lambdas-stubs.jar"));

			        FileUtil.deleteFile(getApplicationContext().getExternalFilesDir(null).getAbsolutePath().concat("/android.jar"));

		        } catch(Exception e) {

			        Toast.makeText(getApplicationContext(), "Failed to delete previous files", Toast.LENGTH_SHORT).show();

                        }

		        new async().execute("extractTools");
		}

	}

	

	private void initialize(Bundle savedInstanceState) {

 

	}



	private class async extends AsyncTask<String, Integer, String> {


		@Override

		protected String doInBackground(String... params) {

			String param = params[0];

			switch(param) {

				case "extractTools": {

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

		protected void onPostExecute(String _result) {

			startActivity(new Intent(MainActivity.this, EditorActivity.class)); Animatoo.animateShrink(MainActivity.this);

		}

	}

}
