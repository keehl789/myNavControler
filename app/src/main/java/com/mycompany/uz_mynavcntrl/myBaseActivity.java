package com.mycompany.uz_mynavcntrl;
import android.widget.*;
import android.app.*;

public class myBaseActivity
extends Activity
{
	
	// helper funct 
	public void toast(String _text_lclvar)
	{
		Toast.makeText(this, _text_lclvar
		 , Toast.LENGTH_SHORT).show(); 
	} 
	
}
