package data;

import exception.IlligalDateException;

public class Date {
	private int day;
	private int month;
	private int year;
	
	private int hour;
	private int minute;
	private int second;
		
	public Date(int day, int month, int year, int hour, int minute, int second) throws IlligalDateException{
		if(checkDate(day, month, year, hour, minute, second)){
			this.day = day;
			this.month = month;
			this.year = year;
			this.hour = hour;
			this.minute = minute;
			this.second = second;
		}
		else{
			throw new IlligalDateException();
		}
		
	}
	
	public Date(Date other){
		this.day = other.day;
		this.month = other.month;
		this.year = other.year;
		this.hour = other.hour;
		this.minute = other.minute;
		this.second = other.second;
	}
	
	public boolean checkDate(int cday, int cmonth, int cyear, int chour, int cminute, int csecond){
		//				  Ja,Fe,Ma,Ap,Ma,Ju,Ju,Au,Se,Ok,No,De;
		int[] maxDay = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		if(cyear%400==0 || (cyear%4==0 && cyear%100!=0 ))maxDay[2]=29;
		
		if((cyear>=0) && (cmonth>=1 && cmonth<=12) && (cday>=1 && cday<=maxDay[cmonth]) && (chour>=0 && chour<=24) && (cminute>=0 && cminute<=60) && (csecond>=0 && cminute<=60)){
			return true;
		}
		return false;
	}
	
	public String toString(){
		return day+"."+month+"."+year+" "+hour+":"+minute+":"+second;
	}
	
	public boolean isBefore(Date other){
		boolean bfor = false;
		
		
		if(year < other.year){
			bfor = true;
		}
		else if(year == other.year){
			if(month < other.month){
				bfor = true;
			}
			else if(month == other.month){
				if(day < other.day){
					bfor = true;
				}
				else if(day == other.day){
					if(hour < other.hour){
						bfor = true;
					}
					else if(hour == other.hour){
						if(minute < other.minute){
							bfor = true;
						}
						else if(minute == other.minute){
							if(second < other.second){
								bfor = true;
							}
						}
					}
				}
			}
		}
		return bfor;
	}
	
}
