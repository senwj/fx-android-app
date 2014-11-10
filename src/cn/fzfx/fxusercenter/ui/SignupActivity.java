package cn.fzfx.fxusercenter.ui;

import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import cn.fzfx.fxusercenter.AppContext;
import cn.fzfx.fxusercenter.R;
import cn.fzfx.fxusercenter.ui.SignupGetCodeFragment.OnGetCodeClickListener;

public class SignupActivity extends FragmentActivity {
	private RadioGroup steps;
	private SignupGetCodeFragment getcode;
	private FragmentTransaction t;
	private AppContext ac;
	
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.fragment_signup_down);
		ac = (AppContext) getApplication();
		initFreamView();
	}

	private void initFreamView(){
		t = this.getSupportFragmentManager().beginTransaction();
		
		steps = (RadioGroup) this.findViewById(R.id.steps);
		getcode = new SignupGetCodeFragment();
		
		t.replace(R.id.container, getcode);
		t.commit();
		
		getcode.setOnGetCodeClickListener(new OnGetCodeClickListener(){
			public void OnClick(String phoneNum) {
				getCode(phoneNum);
			}
		});
	}
	

	private void getCode(final String phoneNum){
		final Handler mHandler = new Handler(){
			public void handleMessage(Message msg) {
				
			}
		};
		new Thread(){
			public void run() {
				Message msg = new Message();
				try {
					String result = ac.registerUser(phoneNum);
					JSONObject js = new JSONObject(result);
					if(js.getBoolean("succsse")){
						JSONObject data = new JSONObject(js.getString("data"));
						msg.arg1=1;
					}else{
						msg.arg1 = js.getInt("errorcode");
					}
					mHandler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}.start();
	}
}






















