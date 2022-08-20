package com.mycompany.uz_mynavcntrl;
import android.view.*;
import android.os.*;

public class myFrag2
extends myBaseFragment
{
	//example only
	
	int i1=1;
	int i2=8;

	@Override
	public View onCreateView
	(LayoutInflater inflater
	 , ViewGroup container
	 , Bundle savedInstanceState)
	{
		// important :
		View tmpv=super.onCreateView
		(inflater, container, savedInstanceState); 

		i1=55;

		return tmpv; 
	}
}
