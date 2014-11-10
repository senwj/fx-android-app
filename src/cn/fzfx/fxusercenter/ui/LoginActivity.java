package cn.fzfx.fxusercenter.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.fzfx.fxusercenter.AppContext;
import cn.fzfx.fxusercenter.R;


public class LoginActivity extends Activity{
	private EditText edit_username;
	private EditText edit_password;
	private Button btn_login;
	private ProgressBar login_progress;
	private AppContext ac;
	private TextView register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ac = (AppContext) this.getApplication();
		
		initFreamView();
	}
	
	private void initFreamView(){
		edit_username = (EditText) this.findViewById(R.id.edit_username);
		edit_password = (EditText) this.findViewById(R.id.edit_password);
		btn_login = (Button) this.findViewById(R.id.btn_login);
		register = (TextView) this.findViewById(R.id.register);
		login_progress = (ProgressBar) this.findViewById(R.id.login_progress);
		btn_login.setOnClickListener(setViewOnClickListener(btn_login.getId()));
		register.setOnClickListener(setViewOnClickListener(register.getId()));
	}
	
	private View.OnClickListener setViewOnClickListener(final int ViewId){
		return new View.OnClickListener(){
			public void onClick(View v) {
				switch(ViewId){
				case R.id.btn_login:
					userRegister();
				break;
				case R.id.register:
					Intent i = new Intent(LoginActivity.this,SignupActivity.class);
					startActivity(i);
				break;
				}
			}
		};
	}
	
	private void userRegister(){
		login_progress.setVisibility(View.VISIBLE);
		btn_login.setEnabled(false);
		final String username = edit_username.getText().toString();
		final String password = edit_password.getText().toString();
		final Handler mHandler = new Handler(){
			public void handleMessage(Message msg) {
				login_progress.setVisibility(View.GONE);
				btn_login.setEnabled(true);
				
			}
		};
		new Thread(){
			public void run() {
				try {
					ac.registerUser(username, password );
					mHandler.sendEmptyMessage(0);
				} catch (Exception e) {
				}
			}
		}.start();
	}
}














