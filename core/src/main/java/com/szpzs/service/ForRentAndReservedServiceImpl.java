package com.szpzs.service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szpzs.model.ForRent;
import com.szpzs.repository.ForRentAndReservedDAO;

@Service("ForRentAndReservedServiceImpl")
public class ForRentAndReservedServiceImpl implements ForRentAndReservedService {
	
	@Autowired
	private ForRentAndReservedDAO forRentAndReservedDAO;
	
	@Override
	public String saveForRent(ForRent forRent) throws ParseException{
		List<ForRent> forRents = this.getForRentsByProduct(forRent.getProductId());
		Boolean status = false;
		
		if(forRents.size()==0){
			forRentAndReservedDAO.saveForRent(forRent);
			return "Már van erre az intervallumra lefoglalhatóság.";
		}else{
			Date beforeOneDay = this.subtractDay(forRent.getFromDate());
		    
		    Date afterOneDay = this.addDay(forRent.getToDate());
		    
		    for (int i = 0; (i <forRents.size()-1) && !status; i++) {				
			    if((beforeOneDay.after(forRents.get(i).getToDate()) && afterOneDay.before(forRents.get(i+1).getFromDate())) || (afterOneDay.before(forRents.get(i).getFromDate()) && i==0) || (beforeOneDay.after(forRents.get(i+1).getToDate()) && (i+1)==(forRents.size()-1))){
					forRentAndReservedDAO.saveForRent(forRent);
					status = true;
				}
			}
			if(!status){
				ForRent newForRent = new ForRent();
				newForRent.setProductId(forRent.getProductId());
				
				for(int i=0; (i<forRents.size() && !status); i++){

					Boolean isInInterval = this.isInterval(forRent, forRents.get(i));
					Boolean isBeginOverlap = this.isBeginOverlap(forRent, forRents.get(i));
					Boolean isEndOverlap = this.isEndOverlap(forRent, forRents.get(i));
					
					if(isInInterval){
						status = true;
					}else{
						Date updatedFromDate = forRent.getFromDate();
						Date updatedToDate = forRent.getToDate();
						
						if(isBeginOverlap){
							updatedFromDate = this.BOcalculateNewFromDate(forRent, forRents, i);
							updatedToDate = this.BOcalculateNewToDate(forRent, forRents, i);
							
							newForRent.setFromDate(updatedFromDate);
							newForRent.setToDate(updatedToDate);
							status = true;
						}else{
							if(isEndOverlap){
								updatedFromDate = this.EOcalculateNewFromDate(forRent, forRents, i);
								updatedToDate = forRents.get(i).getToDate();
								
								newForRent.setFromDate(updatedFromDate);
								newForRent.setToDate(updatedToDate);
								status = true;
							}
						}
					}
				}
				if(!status){
					newForRent.setFromDate(forRent.getFromDate());
					newForRent.setToDate(forRent.getToDate());
				}
				
				if((newForRent.getFromDate()!=null && newForRent.getToDate()!=null)){
					status = false;
					for(int i = 0; i<forRents.size(); i++){
						if(this.isInterval(forRents.get(i),newForRent)){
							forRentAndReservedDAO.deleteForRent(forRents.get(i));
						}
					}
					forRentAndReservedDAO.saveForRent(newForRent);
					status = true;
				}
			}
		}
		
		if(status){			
			return "ok";
		}else{
			return "Hiba történt!";
		}
	}
	
	@Override
	public List<ForRent> getForRentsByProduct(BigInteger productId) {
		return forRentAndReservedDAO.getForRentsByProductId(productId);
	}
	
	private Date addDay(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar calendar = new GregorianCalendar();
		
		calendar.setTime(date);
	    calendar.add(calendar.DAY_OF_MONTH, 1);
	    
	    String afterOneDayString = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());			   
	    Date afterOneDay = sdf.parse(afterOneDayString);
		return afterOneDay;
	}

	private Date subtractDay(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar calendar = new GregorianCalendar();
		
		calendar.setTime(date);
	    calendar.add(calendar.DAY_OF_MONTH,-1);
	    
	    String beforeOneDayString = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());			   
	    Date beforeOneDay = sdf.parse(beforeOneDayString);
		return beforeOneDay;
	}

	private Date EOcalculateNewFromDate(ForRent insertable, List<ForRent> intervals, int index) {
		int j = index-1;
		while((j>1) && (!insertable.getFromDate().after(intervals.get(j).getToDate()) || insertable.getFromDate().equals(intervals.get(j).getToDate()))){
			j--;
		}						
		if(insertable.getFromDate().after(intervals.get(j+1).getFromDate())){
			return intervals.get(j+1).getFromDate();
		}else{
			return insertable.getFromDate();
		}		
	}

	private Date BOcalculateNewFromDate(ForRent insertable, List<ForRent>intervals, int index) {
		long FromDateDiffFromDate = ((intervals.get(index).getFromDate().getTime() - insertable.getFromDate().getTime()) / 1000L / 60L / 60L / 24L);;
		if(FromDateDiffFromDate == 1){
			if(index-1>0){
				long DiffBeforeToDate = ((intervals.get(index-1).getToDate().getTime() - insertable.getFromDate().getTime()) / 1000L / 60L / 60L / 24L);
				if(Math.abs(DiffBeforeToDate) == 1){
					return intervals.get(index-1).getFromDate();
				}else{
					return insertable.getFromDate();
				}
			} else{
				return insertable.getFromDate();
			}
		}else{
			return intervals.get(index).getFromDate();
		}
	}

	private Date BOcalculateNewToDate(ForRent insertable, List<ForRent> intervals,int index) {
		int j = index+1;
		long DateDiff = ((intervals.get(j).getFromDate().getTime() - insertable.getToDate().getTime()) / 1000L / 60L / 60L / 24L);
		while((j<intervals.size()-1) && ((!insertable.getToDate().before(intervals.get(j).getFromDate()) || insertable.getToDate().equals(intervals.get(j).getFromDate())) || ((DateDiff<=1) && DateDiff>0))){
			j++;
			DateDiff = ((intervals.get(j).getFromDate().getTime() - insertable.getToDate().getTime()) / 1000L / 60L / 60L / 24L);
		}			
		if(insertable.getToDate().before(intervals.get(j-1).getToDate())){
			return intervals.get(j-1).getToDate();
		}else{
			return insertable.getToDate();
		}
	}
	
	private Boolean isEndOverlap(ForRent insertable, ForRent interval) {
		long ToDateDiffFromDate = ((interval.getFromDate().getTime() - insertable.getToDate().getTime()) / 1000L / 60L / 60L / 24L);
		
		return (
				(insertable.getToDate().after(interval.getFromDate()) 
						|| insertable.getToDate().equals(interval.getFromDate())
						|| Math.abs(ToDateDiffFromDate)==1) 
				&& (insertable.getToDate().before(interval.getToDate()) 
						|| insertable.getToDate().equals(interval.getToDate()))
		);
	}
	
	private Boolean isBeginOverlap(ForRent insertable, ForRent interval) {
		long FromDateDiffFromDate = ((interval.getFromDate().getTime() - insertable.getFromDate().getTime()) / 1000L / 60L / 60L / 24L);
		long FromDateDiffToDate = ((interval.getToDate().getTime() - insertable.getFromDate().getTime()) / 1000L / 60L / 60L / 24L);
		
		return (
				(insertable.getFromDate().after(interval.getFromDate()) 
						|| insertable.getFromDate().equals(interval.getFromDate()) 
						|| Math.abs(FromDateDiffFromDate)==1) 
				&& (insertable.getFromDate().before(interval.getToDate()) 
						|| insertable.getFromDate().equals(interval.getToDate()) 
						|| Math.abs(FromDateDiffToDate)==1)
		);
	}
	
	private Boolean isInterval(ForRent insertable, ForRent interval) {
		return (
				(insertable.getFromDate().after(interval.getFromDate()) 
						|| insertable.getFromDate().equals(interval.getFromDate()))
				&& (insertable.getToDate().before(interval.getToDate())					 
						|| insertable.getToDate().equals(interval.getToDate()))
		);
	}
}
