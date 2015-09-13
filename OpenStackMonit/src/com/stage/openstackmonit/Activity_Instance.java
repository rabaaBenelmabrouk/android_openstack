package com.stage.openstackmonit;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;



import com.stage.openstackmonit.adapter.ImageAdapter;
import com.stage.openstackmonit.adapter.InstanceAdapter;
import com.stage.openstackmonit.data.Access;
import com.stage.openstackmonit.data.AdressServer;
import com.stage.openstackmonit.data.Image;
import com.stage.openstackmonit.data.Instance;
import com.stage.openstackmonit.http.HttpGet_;
import com.stage.openstackmonit.jsonconvert.Listimage;
import com.stage.openstackmonit.jsonconvert.Listinstance;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Activity_Instance extends DashboardActivity {
	
	List<Instance>lis_instance;
	 ListView list;
	 private InstanceAdapter adapter;
	 public Button hide;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listobject);
		 setTitleFromActivityLabel (R.id.title_text);
		 list = (ListView) findViewById(R.id.list);
		 InstanceTask ins = new InstanceTask();
			ins.execute();
		/*	Button hide = (Button) findViewById(R.id.login);
			hide.setOnClickListener(new Button.OnClickListener() {
				
				public void onClick(View v) {
					Intent intent = new Intent(Activity_Instance.this,
							InstanceModel.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
					startActivity(intent);
				}
			});
			*/
	}
	protected void onResume() {
		super.onResume();
		// The activity has become visible (it is now "resumed").
	}
	
	private class InstanceTask extends AsyncTask<Void,Void,JSONObject> {
	    
		//String resul="";
		//String adress=new AdressServer().getUrl()+":8774/v2/"+Access.getInstance().getIdTenant()+"/servers/detail";
		String adress="http://192.168.80.135:8774/v2/50caf3f1b1454913b8fdd29da723d578/servers/detail";
		@Override
		protected JSONObject doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONObject json = null;
			Log.i("tok",Access.getInstance().getToken());
			Log.i("ten",Access.getInstance().getIdTenant());
			String token ="MIIQKgYJKoZIhvcNAQcCoIIQGzCCEBcCAQExCTAHBgUrDgMCGjCCDoAGCSqGSIb3DQEHAaCCDnEEgg5teyJhY2Nlc3MiOiB7InRva2VuIjogeyJpc3N1ZWRfYXQiOiAiMjAxNC0xMi0xN1QxODo1NDowNS42MDY3MDAiLCAiZXhwaXJlcyI6ICIyMDE0LTEyLTE4VDE4OjU0OjA1WiIsICJpZCI6ICJwbGFjZWhvbGRlciIsICJ0ZW5hbnQiOiB7ImRlc2NyaXB0aW9uIjogImFkbWluIHRlbmFudCIsICJlbmFibGVkIjogdHJ1ZSwgImlkIjogIjUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4IiwgIm5hbWUiOiAiYWRtaW4ifX0sICJzZXJ2aWNlQ2F0YWxvZyI6IFt7ImVuZHBvaW50cyI6IFt7ImFkbWluVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo4Nzc0L3YyLzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4IiwgInJlZ2lvbiI6ICJSZWdpb25PbmUiLCAiaW50ZXJuYWxVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzQvdjIvNTBjYWYzZjFiMTQ1NDkxM2I4ZmRkMjlkYTcyM2Q1NzgiLCAiaWQiOiAiMTJkMzdmMWRkYTgzNDNjODliMGVmNTFiMzJjNTg5NGUiLCAicHVibGljVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo4Nzc0L3YyLzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4In1dLCAiZW5kcG9pbnRzX2xpbmtzIjogW10sICJ0eXBlIjogImNvbXB1dGUiLCAibmFtZSI6ICJub3ZhIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojk2OTYvIiwgInJlZ2lvbiI6ICJSZWdpb25PbmUiLCAiaW50ZXJuYWxVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojk2OTYvIiwgImlkIjogIjAyOTAwMjdiNjk3ZjQ4N2RhM2YxMGQyNDMzM2MzZWVkIiwgInB1YmxpY1VSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6OTY5Ni8ifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAibmV0d29yayIsICJuYW1lIjogIm5ldXRyb24ifSwgeyJlbmRwb2ludHMiOiBbeyJhZG1pblVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3Ni92Mi81MGNhZjNmMWIxNDU0OTEzYjhmZGQyOWRhNzIzZDU3OCIsICJyZWdpb24iOiAiUmVnaW9uT25lIiwgImludGVybmFsVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo4Nzc2L3YyLzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4IiwgImlkIjogIjE0OGY5M2NkODc0YjQ2ZDc4ZTg5MWQzZWI1ZDE1MWZkIiwgInB1YmxpY1VSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3Ni92Mi81MGNhZjNmMWIxNDU0OTEzYjhmZGQyOWRhNzIzZDU3OCJ9XSwgImVuZHBvaW50c19saW5rcyI6IFtdLCAidHlwZSI6ICJ2b2x1bWV2MiIsICJuYW1lIjogImNpbmRlcnYyIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1OjgwODAiLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODA4MCIsICJpZCI6ICI5YmYwNjdhYTkzOGY0NzZjOTM5Yjc1MzVhYWE2NGU1NCIsICJwdWJsaWNVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1OjgwODAifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAiczMiLCAibmFtZSI6ICJzd2lmdF9zMyJ9LCB7ImVuZHBvaW50cyI6IFt7ImFkbWluVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo5MjkyIiwgInJlZ2lvbiI6ICJSZWdpb25PbmUiLCAiaW50ZXJuYWxVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1OjkyOTIiLCAiaWQiOiAiM2NjZDM2ZmJkNDEzNDA3ZThlNDExMmFhOTJhZmRkNGYiLCAicHVibGljVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo5MjkyIn1dLCAiZW5kcG9pbnRzX2xpbmtzIjogW10sICJ0eXBlIjogImltYWdlIiwgIm5hbWUiOiAiZ2xhbmNlIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzciLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3NyIsICJpZCI6ICIxZDU3MzcxNGU5ZmE0MzBhOWU4ZmRkNTA5ODk2YWI4ZCIsICJwdWJsaWNVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzcifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAibWV0ZXJpbmciLCAibmFtZSI6ICJjZWlsb21ldGVyIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzYvdjEvNTBjYWYzZjFiMTQ1NDkxM2I4ZmRkMjlkYTcyM2Q1NzgiLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3Ni92MS81MGNhZjNmMWIxNDU0OTEzYjhmZGQyOWRhNzIzZDU3OCIsICJpZCI6ICI5MGY0ZWNjN2NkMzQ0ODk4YjJiZjc1OWIyZmVlYmY1MiIsICJwdWJsaWNVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzYvdjEvNTBjYWYzZjFiMTQ1NDkxM2I4ZmRkMjlkYTcyM2Q1NzgifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAidm9sdW1lIiwgIm5hbWUiOiAiY2luZGVyIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzMvc2VydmljZXMvQWRtaW4iLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3My9zZXJ2aWNlcy9DbG91ZCIsICJpZCI6ICI2YzFhMTkzN2RiYWQ0MzM0OGJiODUyNGI5Y2EzZTgzMyIsICJwdWJsaWNVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzMvc2VydmljZXMvQ2xvdWQifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAiZWMyIiwgIm5hbWUiOiAibm92YV9lYzIifSwgeyJlbmRwb2ludHMiOiBbeyJhZG1pblVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODA4MC8iLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODA4MC92MS9BVVRIXzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4IiwgImlkIjogIjBhNjQ2M2MyNzNmYzQ1Nzk5ZmZkNmUyYmFmYjFkNGJiIiwgInB1YmxpY1VSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODA4MC92MS9BVVRIXzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4In1dLCAiZW5kcG9pbnRzX2xpbmtzIjogW10sICJ0eXBlIjogIm9iamVjdC1zdG9yZSIsICJuYW1lIjogInN3aWZ0In0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1OjM1MzU3L3YyLjAiLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6NTAwMC92Mi4wIiwgImlkIjogIjE0ZmZiNDAzN2Q3MTQ2NWU4YWJhNjAzYmVjNjVkNzdlIiwgInB1YmxpY1VSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6NTAwMC92Mi4wIn1dLCAiZW5kcG9pbnRzX2xpbmtzIjogW10sICJ0eXBlIjogImlkZW50aXR5IiwgIm5hbWUiOiAia2V5c3RvbmUifV0sICJ1c2VyIjogeyJ1c2VybmFtZSI6ICJhZG1pbiIsICJyb2xlc19saW5rcyI6IFtdLCAiaWQiOiAiYWJhZjQ4YWE0ZGJmNGQzNjgwN2QyZjJlNDM5MjJiMjQiLCAicm9sZXMiOiBbeyJuYW1lIjogImFkbWluIn1dLCAibmFtZSI6ICJhZG1pbiJ9LCAibWV0YWRhdGEiOiB7ImlzX2FkbWluIjogMCwgInJvbGVzIjogWyJhYWM1N2FmZDAxMDY0ZWFlOGQ0NjU5NGE0Yjc0N2YyMiJdfX19MYIBgTCCAX0CAQEwXDBXMQswCQYDVQQGEwJVUzEOMAwGA1UECAwFVW5zZXQxDjAMBgNVBAcMBVVuc2V0MQ4wDAYDVQQKDAVVbnNldDEYMBYGA1UEAwwPd3d3LmV4YW1wbGUuY29tAgEBMAcGBSsOAwIaMA0GCSqGSIb3DQEBAQUABIIBACIUFWFc-TwlA5ow1pkSYVixFWlBjlFMglSWWEy27k4pMO3amrbtr7emMtyBloET5M2-POaQhTnJZnu0ow6dH1U48S7LzKmkzreWrZTyy9ZKdH5FgK6CWw2bWAkgwE9MKxKYeDEoFKNI2zXu7qxh2+zzARl3XKDXEcJgJ+kyasNTPz1pN-QS3JY7HmO1SP5cOYMC86w32r+yMaady+KpuxJHWK1i7GiQvjJj3k-jsQaOcJjZ4V-WYSwHgAB-WWFL3Znq5xfUhcsz8RNQ5yWBCgJXLaydxYGXpOB6PGoyXbL1MEKyqUWM-xns8kuFRv-j8oO0CkJGgL2aFFnB1TagV0Q=";
			HttpGet_ imageHttp=new HttpGet_(adress,"MIIQKgYJKoZIhvcNAQcCoIIQGzCCEBcCAQExCTAHBgUrDgMCGjCCDoAGCSqGSIb3DQEHAaCCDnEEgg5teyJhY2Nlc3MiOiB7InRva2VuIjogeyJpc3N1ZWRfYXQiOiAiMjAxNC0xMi0xN1QxODo1NDowNS42MDY3MDAiLCAiZXhwaXJlcyI6ICIyMDE0LTEyLTE4VDE4OjU0OjA1WiIsICJpZCI6ICJwbGFjZWhvbGRlciIsICJ0ZW5hbnQiOiB7ImRlc2NyaXB0aW9uIjogImFkbWluIHRlbmFudCIsICJlbmFibGVkIjogdHJ1ZSwgImlkIjogIjUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4IiwgIm5hbWUiOiAiYWRtaW4ifX0sICJzZXJ2aWNlQ2F0YWxvZyI6IFt7ImVuZHBvaW50cyI6IFt7ImFkbWluVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo4Nzc0L3YyLzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4IiwgInJlZ2lvbiI6ICJSZWdpb25PbmUiLCAiaW50ZXJuYWxVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzQvdjIvNTBjYWYzZjFiMTQ1NDkxM2I4ZmRkMjlkYTcyM2Q1NzgiLCAiaWQiOiAiMTJkMzdmMWRkYTgzNDNjODliMGVmNTFiMzJjNTg5NGUiLCAicHVibGljVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo4Nzc0L3YyLzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4In1dLCAiZW5kcG9pbnRzX2xpbmtzIjogW10sICJ0eXBlIjogImNvbXB1dGUiLCAibmFtZSI6ICJub3ZhIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojk2OTYvIiwgInJlZ2lvbiI6ICJSZWdpb25PbmUiLCAiaW50ZXJuYWxVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojk2OTYvIiwgImlkIjogIjAyOTAwMjdiNjk3ZjQ4N2RhM2YxMGQyNDMzM2MzZWVkIiwgInB1YmxpY1VSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6OTY5Ni8ifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAibmV0d29yayIsICJuYW1lIjogIm5ldXRyb24ifSwgeyJlbmRwb2ludHMiOiBbeyJhZG1pblVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3Ni92Mi81MGNhZjNmMWIxNDU0OTEzYjhmZGQyOWRhNzIzZDU3OCIsICJyZWdpb24iOiAiUmVnaW9uT25lIiwgImludGVybmFsVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo4Nzc2L3YyLzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4IiwgImlkIjogIjE0OGY5M2NkODc0YjQ2ZDc4ZTg5MWQzZWI1ZDE1MWZkIiwgInB1YmxpY1VSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3Ni92Mi81MGNhZjNmMWIxNDU0OTEzYjhmZGQyOWRhNzIzZDU3OCJ9XSwgImVuZHBvaW50c19saW5rcyI6IFtdLCAidHlwZSI6ICJ2b2x1bWV2MiIsICJuYW1lIjogImNpbmRlcnYyIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1OjgwODAiLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODA4MCIsICJpZCI6ICI5YmYwNjdhYTkzOGY0NzZjOTM5Yjc1MzVhYWE2NGU1NCIsICJwdWJsaWNVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1OjgwODAifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAiczMiLCAibmFtZSI6ICJzd2lmdF9zMyJ9LCB7ImVuZHBvaW50cyI6IFt7ImFkbWluVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo5MjkyIiwgInJlZ2lvbiI6ICJSZWdpb25PbmUiLCAiaW50ZXJuYWxVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1OjkyOTIiLCAiaWQiOiAiM2NjZDM2ZmJkNDEzNDA3ZThlNDExMmFhOTJhZmRkNGYiLCAicHVibGljVVJMIjogImh0dHA6Ly8xOTIuMTY4LjgwLjEzNTo5MjkyIn1dLCAiZW5kcG9pbnRzX2xpbmtzIjogW10sICJ0eXBlIjogImltYWdlIiwgIm5hbWUiOiAiZ2xhbmNlIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzciLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3NyIsICJpZCI6ICIxZDU3MzcxNGU5ZmE0MzBhOWU4ZmRkNTA5ODk2YWI4ZCIsICJwdWJsaWNVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzcifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAibWV0ZXJpbmciLCAibmFtZSI6ICJjZWlsb21ldGVyIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzYvdjEvNTBjYWYzZjFiMTQ1NDkxM2I4ZmRkMjlkYTcyM2Q1NzgiLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3Ni92MS81MGNhZjNmMWIxNDU0OTEzYjhmZGQyOWRhNzIzZDU3OCIsICJpZCI6ICI5MGY0ZWNjN2NkMzQ0ODk4YjJiZjc1OWIyZmVlYmY1MiIsICJwdWJsaWNVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzYvdjEvNTBjYWYzZjFiMTQ1NDkxM2I4ZmRkMjlkYTcyM2Q1NzgifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAidm9sdW1lIiwgIm5hbWUiOiAiY2luZGVyIn0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzMvc2VydmljZXMvQWRtaW4iLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODc3My9zZXJ2aWNlcy9DbG91ZCIsICJpZCI6ICI2YzFhMTkzN2RiYWQ0MzM0OGJiODUyNGI5Y2EzZTgzMyIsICJwdWJsaWNVUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1Ojg3NzMvc2VydmljZXMvQ2xvdWQifV0sICJlbmRwb2ludHNfbGlua3MiOiBbXSwgInR5cGUiOiAiZWMyIiwgIm5hbWUiOiAibm92YV9lYzIifSwgeyJlbmRwb2ludHMiOiBbeyJhZG1pblVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODA4MC8iLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODA4MC92MS9BVVRIXzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4IiwgImlkIjogIjBhNjQ2M2MyNzNmYzQ1Nzk5ZmZkNmUyYmFmYjFkNGJiIiwgInB1YmxpY1VSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6ODA4MC92MS9BVVRIXzUwY2FmM2YxYjE0NTQ5MTNiOGZkZDI5ZGE3MjNkNTc4In1dLCAiZW5kcG9pbnRzX2xpbmtzIjogW10sICJ0eXBlIjogIm9iamVjdC1zdG9yZSIsICJuYW1lIjogInN3aWZ0In0sIHsiZW5kcG9pbnRzIjogW3siYWRtaW5VUkwiOiAiaHR0cDovLzE5Mi4xNjguODAuMTM1OjM1MzU3L3YyLjAiLCAicmVnaW9uIjogIlJlZ2lvbk9uZSIsICJpbnRlcm5hbFVSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6NTAwMC92Mi4wIiwgImlkIjogIjE0ZmZiNDAzN2Q3MTQ2NWU4YWJhNjAzYmVjNjVkNzdlIiwgInB1YmxpY1VSTCI6ICJodHRwOi8vMTkyLjE2OC44MC4xMzU6NTAwMC92Mi4wIn1dLCAiZW5kcG9pbnRzX2xpbmtzIjogW10sICJ0eXBlIjogImlkZW50aXR5IiwgIm5hbWUiOiAia2V5c3RvbmUifV0sICJ1c2VyIjogeyJ1c2VybmFtZSI6ICJhZG1pbiIsICJyb2xlc19saW5rcyI6IFtdLCAiaWQiOiAiYWJhZjQ4YWE0ZGJmNGQzNjgwN2QyZjJlNDM5MjJiMjQiLCAicm9sZXMiOiBbeyJuYW1lIjogImFkbWluIn1dLCAibmFtZSI6ICJhZG1pbiJ9LCAibWV0YWRhdGEiOiB7ImlzX2FkbWluIjogMCwgInJvbGVzIjogWyJhYWM1N2FmZDAxMDY0ZWFlOGQ0NjU5NGE0Yjc0N2YyMiJdfX19MYIBgTCCAX0CAQEwXDBXMQswCQYDVQQGEwJVUzEOMAwGA1UECAwFVW5zZXQxDjAMBgNVBAcMBVVuc2V0MQ4wDAYDVQQKDAVVbnNldDEYMBYGA1UEAwwPd3d3LmV4YW1wbGUuY29tAgEBMAcGBSsOAwIaMA0GCSqGSIb3DQEBAQUABIIBACIUFWFc-TwlA5ow1pkSYVixFWlBjlFMglSWWEy27k4pMO3amrbtr7emMtyBloET5M2-POaQhTnJZnu0ow6dH1U48S7LzKmkzreWrZTyy9ZKdH5FgK6CWw2bWAkgwE9MKxKYeDEoFKNI2zXu7qxh2+zzARl3XKDXEcJgJ+kyasNTPz1pN-QS3JY7HmO1SP5cOYMC86w32r+yMaady+KpuxJHWK1i7GiQvjJj3k-jsQaOcJjZ4V-WYSwHgAB-WWFL3Znq5xfUhcsz8RNQ5yWBCgJXLaydxYGXpOB6PGoyXbL1MEKyqUWM-xns8kuFRv-j8oO0CkJGgL2aFFnB1TagV0Q=");
		//	HttpGet_ imageHttp=new HttpGet_(adress,Access.getInstance().getToken());
			try {
				
				if(imageHttp.CodeConnection()==200)
				json= imageHttp.getMethod();
				
				else
					{
					 json= new JSONObject().put("fail",imageHttp.display(imageHttp.CodeConnection()));
					}
				Log.i("reslt",json.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return json;
		}
	 @Override
        protected void onPostExecute(JSONObject json) {
		 try {
         	if (json.has("fail"))
         	{
         		Toast.makeText(getApplicationContext(),json.getString("fail") , 10).show();
         		
         	}
         	else
         	{
         		lis_instance= new Listinstance(json).getListInstance();
        		 adapter = new InstanceAdapter(getApplicationContext(),lis_instance);
                list.setAdapter(adapter);
         	}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
            
		 }
		 
	}
	
}
