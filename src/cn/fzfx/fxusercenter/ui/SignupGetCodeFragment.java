package cn.fzfx.fxusercenter.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import cn.fzfx.fxusercenter.R;

public class SignupGetCodeFragment extends Fragment{
	private EditText phone_edit;
	private Button get_code_button;
	private Activity mActivity;
	
	
	private OnGetCodeClickListener mGetCodeClick;
	
	public interface OnGetCodeClickListener{
		void OnClick(String phoneNum);
	}
	
	public void setOnGetCodeClickListener(OnGetCodeClickListener click){
		mGetCodeClick = click;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mActivity =  getActivity();
		
		
		View view  = inflater.inflate(R.layout.fragment_signup_getcode,null);
		phone_edit = (EditText) view.findViewById(R.id.phone_edit);
		get_code_button = (Button) view.findViewById(R.id.get_code_button);
		
		get_code_button.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				mGetCodeClick.OnClick(phone_edit.getText().toString());
			}
		});
		phone_edit.addTextChangedListener(new TextWatcher(){

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {}
			public void afterTextChanged(Editable s) {
				String str = s.toString();
				if(str.length()==11){
					get_code_button.setEnabled(true);
				}else{
					get_code_button.setEnabled(false);
				}
			}
		});
		return view;
	}

}
