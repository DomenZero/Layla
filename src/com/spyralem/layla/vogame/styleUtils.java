package com.spyralem.layla.vogame;

import android.app.Activity;
import android.content.Intent;

public class styleUtils {
	private static int theme;
	
	//constant name used theme
	public final static int THEME_STANDART=1;
	public final static int THEME_DARK=0;
	public final static int THEME_LIGHT=2;
	
	//set theme & restart activity 
	public static void changedTheme(Activity activity, int stheme)
	{
		theme=stheme;
		activity.finish();
		activity.startActivity(new Intent(activity, activity.getClass()));
	}
	
	//set selected theme
	public static void onActivitySetTheme(Activity activity)
	{
		switch (theme) {
		default:
		case THEME_STANDART:
			activity.setTheme(R.style.StandartTheme);
			break;
		case THEME_DARK:
			activity.setTheme(R.style.DarkTheme);
			break;
		case THEME_LIGHT:
			activity.setTheme(R.style.LightTheme);
			break;
		}
	}

}
