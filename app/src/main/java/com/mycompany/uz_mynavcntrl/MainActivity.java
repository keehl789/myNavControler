package com.mycompany.uz_mynavcntrl;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
//import com.mycompany.uz_myNavController.myFrag1;
//import com.mycompany.uz_myNavController.myFrag2;

public class MainActivity 
extends myBaseActivity 
{
	myNavControler mnc;

	Button btn1,btn2,btn3,btn4; 

	Class myFrag1Cls=myFrag1.class;
	Class myFrag2Cls=myFrag2.class;

	TextView tv;
	
	
    @Override
    protected void onCreate
	(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		
		_crea();
    }
	
	
	private void _crea()
	{
		mnc=new myNavControler(this);

		int btn1i=R.id.btn1;
		int btn2i=R.id.btn2;
		int btn3i=R.id.btn3;
		int btn4i=R.id.btn4;

		btn1=findViewById(R.id.btn1);
		btn2=findViewById(R.id.btn2);
		btn3=findViewById(R.id.btn3);
		btn4=findViewById(R.id.btn4);

		btn1.setOnClickListener(mnc._onclicklistener);
		btn2.setOnClickListener(mnc._onclicklistener);
		btn3.setOnClickListener(mnc._onclicklistener);
		btn4.setOnClickListener(mnc._onclicklistener);
		
		int layout1=R.layout.lyt01; 
		int layout2=R.layout.lyt02; 

		int trgt1=R.id.mainFrameLayout1;
		int trgt2=R.id.mainFrameLayout2;

		mnc._add_navigation(myFrag1Cls,layout1,trgt1,0);
		mnc._add_navigation(myFrag2Cls,layout2,trgt1,1);
		mnc._add_navigation(myFrag1Cls,layout1,trgt2,2);
		mnc._add_navigation(myFrag2Cls,layout2,trgt2,3); 

		mnc._add_sender_navigation(btn1i,0);
		mnc._add_sender_navigation(btn2i,1);
		mnc._add_sender_navigation(btn3i,2);
		mnc._add_sender_navigation(btn4i,3); 

		tv=findViewById(R.id.mtv);
	}
	
	
	
	@Override
	public void onBackPressed()
	{
		if(mnc==null)
		{super.onBackPressed();
			return;}
			
//if can't nav back , then finish actv
			
		if(!mnc._NavBack())
		  super.onBackPressed();
	} 
	
	

	@Override
	public void setTitle(CharSequence title)
	{
		if(tv==null)
		{super.setTitle(title);
			return;}
		if(title==null)
		{return;}

		tv.setText(title);
	}
	
}
