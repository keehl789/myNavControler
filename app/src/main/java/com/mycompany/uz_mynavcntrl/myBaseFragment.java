package com.mycompany.uz_mynavcntrl;

import android.app.*;
import android.view.*;
import android.os.*;

public class myBaseFragment
extends Fragment
{
	//declaration 
	
	public int _inflator_xml_file = 0;
	public myBaseActivity a;
	
	//constrcutor 
	public myBaseFragment()
	{
		
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
		 ViewGroup container,
		 Bundle savedInstanceState) 
	{ 
		super.onCreateView(inflater, container, 
			   savedInstanceState);
		//...

		// Inflate the layout for this fragment
		return inflater.inflate(_inflator_xml_file, container, false);
	} 
	

	@Override
	public void onAttach(Activity ma)
	{
		a = (myBaseActivity)ma; 
		super.onAttach(ma);
	} 



	// intanciate fragment :
	public static myBaseFragment 
	_instantiate_fragment_
	(Class _lc_obj_clss 
	 , int _lc_inflator_xml_file
	 , myBaseActivity ma) 
	//n! throws InstantiationException
	{
		myBaseFragment _tm_obj = null;

        try 
		{
            _tm_obj = (myBaseFragment)
				_lc_obj_clss.newInstance();

			//	_tm_obj.setActivity() = 
			//	 	this._main;

			_tm_obj.a=ma; 

			_tm_obj._inflator_xml_file = _lc_inflator_xml_file;
        } 

		catch (Throwable e) 
		{
			//toast("err" + e.getMessage()
			//  . replace("java.lang.ClassCastException","")
			//	  );
        } 

		return _tm_obj; 
	} // end instanciate fragment
	
}
