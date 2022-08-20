package com.mycompany.uz_mynavcntrl;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import java.util.ArrayList;

import java.lang.Runnable;
import android.os.Handler;

import android.widget.Toast;

import android.view.View;
import android.view.View.OnClickListener;

import android.view.*;
import android.app.*;
import android.os.*;


//c main class:

public class myNavControler
{
	//declarations 

	public myBaseFragment _fragment_ref = null;
	public int _nav_id = 0;
	public int _target_id = 0;
	public View _sender_ref = null;
	public myBaseActivity a;
	public FragmentManager _v_fragmentManager = null;
	public Runnable runnable;
	public Handler h; 

	public ArrayList<_navigation_descriptor> 
	_navigations = new ArrayList<_navigation_descriptor>();
	public ArrayList<myBaseFragment> 
	_fragments = new ArrayList<myBaseFragment>();
	public ArrayList<_last_frag_descriptor> 
	_last_fragments_ = new ArrayList<_last_frag_descriptor>();
	public ArrayList<_sender_navigation_descriptor>
	_senders_navs = new ArrayList<_sender_navigation_descriptor>();
	//...

	public OnClickListener _onclicklistener 
	= new OnClickListener()
	{
		public void onClick(View v)
		{try{

				//show new fragment
				_navigate_by_sender_id(v.getId());  

				//exec postShow script
				_set_timer_(v);

				//...

		}catch(Throwable g)
		{a.setTitle(g.getMessage());}}
	};

	//end declarations 


	//constructor
	
	public myNavControler
	  (myBaseActivity ma){
		  
		a=ma;
		h=new Handler(); 

		_v_fragmentManager
			=a.getFragmentManager();
	}


	//methods : 
	
	
	public void _add_navigation 
	(Class _lc_clss_name, 
	 int _lc_xml, 
	 int _lc_target_id, 
	 int _lc_nav_id)
	{ 

		try  
		{
			myBaseFragment _tm_frag = 
				myBaseFragment._instantiate_fragment_
			(_lc_clss_name
			 , _lc_xml
			 , this.a);

			_navigation_descriptor _tm_nav = 
				new _navigation_descriptor();
			_tm_nav._fragment_ref = _tm_frag;
			_tm_nav._target_id = _lc_target_id;
			_tm_nav._nav_id = _lc_nav_id;


			this._fragments.add(_tm_frag);
			this._navigations.add(_tm_nav); 

		}

		catch (Throwable e) 
		{
			toast(
				"Failed instantiate object: "
				+ e.getMessage());
        } 
	} 


	private _navigation_descriptor 
	_get_nav_by_id(int _lc_id)
	{
		_navigation_descriptor _tm_nav = null; 

		int i = 0;
		Boolean _found = false;

		while ( ( i < _navigations.size() ) && ( ! _found ) )
		{
			if (_navigations.get(i)._nav_id == _lc_id)
			{
				_tm_nav = _navigations.get(i);
				_found = true;
			}

			i++;
		}  

		return _tm_nav;
	}


	private void 
	_add_last_frag(myBaseFragment _lc_frag, int _lc_trgt_id)
	{
		//t!
		if (_lc_frag == null)
		{
			_lc_frag = new myBaseFragment();
			//n! _lc_frag.a = this.a;
			//...
		} 

		_last_frag_descriptor _tm_last_frag = 
			new _last_frag_descriptor();
		_tm_last_frag._fragment_ref = _lc_frag;
		_tm_last_frag._target_id = _lc_trgt_id;
		this._last_fragments_.add(_tm_last_frag);
		
		toast(""+_last_fragments_.size());
	}


	private myBaseFragment 
	_get_last_frag_by_trgt_id(int _lc_trgt_id)
	{
		myBaseFragment _tm_frag = null;

		int i = 0;
		Boolean _found = false;

		while ( ( i < _last_fragments_.size() ) && ( ! _found ) )
		{
			if (_last_fragments_.get(i)._target_id == _lc_trgt_id)
			{
				_tm_frag = _last_fragments_.get(i)._fragment_ref;
				_found = true;
			}

			i++;
		} 

		return _tm_frag;
	}


	/* private void 
	_set_last_frag_where_trgt_id
	(int _lc_trgt_id, myBaseFragment _lc_frag)
	{
		int i = 0;
		Boolean _found = false;

		while ( ( i < _last_fragments_.size() ) && ( ! _found ) )
		{
			if (_last_fragments_.get(i)._target_id == _lc_trgt_id)
			{
				_last_fragments_.get(i)._fragment_ref = _lc_frag;
				_found = true;
			}

			i++;
		} 

		if (!_found) 
		{
			_add_last_frag(_lc_frag, _lc_trgt_id);

		} 
	}*/


	public void 
	_add_sender_navigation(int _lc_sender_id, int _lc_nav_id)
	{
		_sender_navigation_descriptor _tm_sndr_nav_descr = 
			new _sender_navigation_descriptor();

		_tm_sndr_nav_descr._sndr_id = _lc_sender_id;
		_tm_sndr_nav_descr._nav_id = _lc_nav_id;

		this._senders_navs.add(_tm_sndr_nav_descr);
		//..
	}


	private int 
	_get_nav_id_by_sender_id(int _lc_sender_id)
	{
		int _tm_nav_id = 0;

		int i = 0;
		Boolean _found = false;

		while ( ( i < _senders_navs.size() ) && ( ! _found ) )
		{
			if (_senders_navs.get(i)._sndr_id == _lc_sender_id)
			{
				_tm_nav_id = _senders_navs.get(i)._nav_id;
				_found = true;
			}

			i++;
		} 

//dbg! _toast("nvid= " + Integer.toString(_tm_nav_id));

		return _tm_nav_id;
		//...
	}


	public void 
	_navigate_by_sender_id(int _lc_sender_id)
	{
		int _tm_nav_id = 0;

		_tm_nav_id = _get_nav_id_by_sender_id(_lc_sender_id);
//dbg! _toast("nvid_" + Integer.toString(_tm_nav_id));

		_navigate_by_nav_id(_tm_nav_id);
		//...
	}


	public Boolean 
	_navigate_by_nav_id(int _lc_nav_id) 
	{

		_navigation_descriptor _tm_nav = null;
		myBaseFragment _tm_frag = null;
		int _tm_trgt = 0;

		_tm_nav = _get_nav_by_id(_lc_nav_id);

		/*if (_tm_nav == null) {_toast("nill");}
		 else {_toast("okkk");}*/

		_tm_frag = _tm_nav._fragment_ref;
		_tm_trgt = _tm_nav._target_id; 

		if(_navigate_by_frag_and_target
		   (_tm_frag,_tm_trgt,true))
		{ return true; }

		else 
		{ return false; } 

	} //end nav_by_id!



	public boolean _navigate_by_frag_and_target
	(myBaseFragment _tm_frag
	 , int _tm_trgt
	 , boolean m_if_forward)
	{
		if ((_tm_frag != null) )
		//t! && (_get_last_frag_by_trgt_id(_tm_trgt) != _tm_frag))
		{
			Fragment _last_frag = 
				_get_last_frag_by_trgt_id (_tm_trgt);

			Fragment _tmp 
				= new Fragment();

			FragmentTransaction fragmentTransaction 
				= _v_fragmentManager.beginTransaction();

			//? if (_last_frag != null) 
			//? 	{fragmentTransaction.detach( _last_frag );}

			fragmentTransaction.replace(_tm_trgt, _tm_frag);
			//n! fragmentTransaction.replace(_tm_trgt, _tm_frag); 
        	//w! fragmentTransaction.addToBackStack("none");
        	fragmentTransaction.commit(); 

			if(m_if_forward){
			  //_set_last_frag_where_trgt_id
			  //  (_tm_trgt, _tm_frag);
			  _add_last_frag
			    (_tm_frag,_tm_trgt);
			}

			return true;

		}

		else 
		{ return false; }
	}
	
	
	// no need , deprecated
	/* 
	private void 
	_anc_populate(int[] _lc_senders_ids,
		int[] _lc_targets_ids)
	{

		int j = 0;
		while (j < _lc_targets_ids.length)
		{
			_add_last_frag(null, _lc_targets_ids[j] );
			j++;
		}
		//...

		int i = 0;
		View _tm_v = null;
		while (i < _lc_senders_ids.length)
		{
			_tm_v = a.findViewById(_lc_senders_ids[i]);
			_tm_v.setOnClickListener(_onclicklistener);
			i++;
		}

		//t! _toast("i="+Integer.toString(i));
	}
	*/ 


	private void 
	_set_timer_(final View v)
	{
		runnable = new Runnable() 
		{
			@Override 
			public void run ()
			{
				_exec_timer_(v);
			}
		};

		h.postDelayed(runnable, 300); 
	}


	private void 
	_exec_timer_(View v)
	{
		//_execute_post_scripts(v.getId()); 
	} 
	
	
	/*td! 
	there still is some 
	work to do in back nav process 
	*/
	
	public boolean _NavBack()
	{
		try{
			
		if(_last_fragments_.size()>0)
		{
			int last_frag_ix
				=_last_fragments_.size()
				- 1;
			_last_fragments_.remove(last_frag_ix);
			
			last_frag_ix
				=_last_fragments_.size()
				- 1;

			if(_last_fragments_.size()>0) 
			{
			  _last_frag_descriptor last_frag_
				=_last_fragments_.get(last_frag_ix);

			  _navigate_by_frag_and_target
			  ( last_frag_._fragment_ref
			   , last_frag_._target_id
			   , false);
			   
			   return true;
			}
			
			else 
			  return false;
		}
		
		else 
		  return false;
		
		}catch (Throwable e) 
		{toast(
		  "err, nav back: "
		  + e.getMessage());
		  return true;} 
		  
	  //return false;
	}


	/* n! no need
	
	public boolean _canNavBack()
	{
		return (_last_fragments_.size()
			>0);
	}*/
	
	
	// helper funct  
	public void toast(String _text_lclvar)
	{
		a.toast(_text_lclvar); 
	} 
	

} // end main class 


//============================


//helper classes : 

class _navigation_descriptor
{
	public int _nav_id;
	public int _target_id;
	public myBaseFragment _fragment_ref;
	
	//constructor
	public _navigation_descriptor()
	{
		//...
	}
}


class _sender_navigation_descriptor
{
	public int _nav_id = 0;
	public int _sndr_id = 0;

	//constructor
	public _sender_navigation_descriptor()
	{
		//...
	}
	
} //endclss 


//this can be used to implement
//onbackpressed nav back 


class _last_frag_descriptor
{
	public myBaseFragment _fragment_ref 
	  = null;
	public int _target_id = 0;

	//constructor
	public _last_frag_descriptor()
	{
		//...
	}
} //endclss


//============================
 
