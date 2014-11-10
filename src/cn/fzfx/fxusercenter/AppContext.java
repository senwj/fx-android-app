package cn.fzfx.fxusercenter;

import java.util.HashMap;
import java.util.Map;

import android.app.Application;
import cn.fzfx.android.tools.WebTool;
import cn.fzfx.android.tools.security.FxSecurity;
import cn.fzfx.fxusercenter.bean.URLs;

public class AppContext extends Application{
	WebTool  mWebTool;
	
	public void onCreate() {
		super.onCreate();
		mWebTool = new WebTool(AppContext.this);
	}

//	public String registerUser(String username,String password ) throws Exception {
//		ArrayList<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
//		params.add(new BasicNameValuePair("saccount",new FxSecurity().encrypt2("["+username+"]")));
//		params.add(new BasicNameValuePair("spassword", new FxSecurity().encrypt2(password)));
//		params.add(new BasicNameValuePair("loginTypeAccount","true"));
//		String result=ApiClient._post(this, URLs.FX_REGITER,params,null);
//		return result;
//	}
	
	public String registerUser(String username,String password ) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("saccount",new FxSecurity().encrypt2("["+username+"]"));
		map.put("spassword",new FxSecurity().encrypt2(password));
		String result =mWebTool.doPostString(URLs.FX_REGITER, map);
		return result;
	}
	
	public String registerUser(String phoneNum ) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("sphone",new FxSecurity().encrypt2(phoneNum));
		String result =mWebTool.doPostString(URLs.FX_PHONE_CODE, map);
		return result;
	}
}
