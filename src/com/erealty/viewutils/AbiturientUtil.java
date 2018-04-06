package com.erealty.viewutils;

import com.erealty.model.Abiturient;

public class AbiturientUtil {
	public static String getFullName(Abiturient abitur){
		String mName = abitur.getmName();
		return abitur.getlName()+" "+abitur.getfName()+(mName==null?"":" "+abitur.getmName());
	}
}
