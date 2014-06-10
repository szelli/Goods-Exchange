package com.szpzs.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.ForRent;
import com.szpzs.model.Reserved;
import com.szpzs.repository.ForRentAndReservedDAO;

@Service("ForRentAndReservedServiceImpl")
public class ForRentAndReservedServiceImpl implements ForRentAndReservedService{
	@Autowired
	private ForRentAndReservedDAO forRentAndReservedDAO;

	@Override
	@Transactional
	public void saveForRent(ForRent forRent){
		//meg kell nézni h olyan időintervallumon szerepel már egy sor vagy nem
		/*if(!forRentAndReservedDAO.existsForRent(forRent.getProductId())){*/
			forRentAndReservedDAO.saveForRent(forRent);
		//}
	}

	/*@Override
	@Transactional
	public void updateForRent(ForRent forRent) {
		forRentAndReservedDAO.updateForRent(forRent);
	}*/
	
	@Override
	@Transactional
	public List<ForRent> getForRentsByProduct(int productId) {
		return forRentAndReservedDAO.getForRentsByProductId(BigInteger.valueOf(productId));
	}
	
	@Override
	@Transactional
	public void changedToReserved(Reserved reserved){
		ForRent fr = forRentAndReservedDAO.getForRentByDateInterval(reserved.getProductId(), reserved.getFromDate(), reserved.getToDate());
		if(fr!=null){
			this.cutReservedIntervFromForRentInterv(fr, reserved);
			forRentAndReservedDAO.saveReserved(reserved);
			forRentAndReservedDAO.deleteForRent((long) fr.getId());
		}
	}
	
	public void cutReservedIntervFromForRentInterv(ForRent fr, Reserved reserved){
		GregorianCalendar calendar = new GregorianCalendar();
	    
		ForRent newfr1 = new ForRent();
		newfr1.setProductId(fr.getProductId());
		ForRent newfr2 = new ForRent();
		newfr2.setProductId(fr.getProductId());
		
		calendar.setTime(reserved.getFromDate());
	    calendar.add(calendar.DAY_OF_MONTH,-1);
	    Boolean biggerCriterion = ((Date) calendar.getTime()).after(fr.getFromDate());
	    Boolean equalsCriterion = ((Date) calendar.getTime()).equals(fr.getFromDate());
	    if(biggerCriterion || equalsCriterion){
		    newfr1.setFromDate(fr.getFromDate());
		    newfr1.setToDate(calendar.getTime());
		    forRentAndReservedDAO.saveForRent(newfr1);
	    }
	    
	    calendar.setTime(reserved.getToDate());
	    calendar.add(calendar.DAY_OF_MONTH,+1);
	    Boolean smallerCriterion = ((Date) calendar.getTime()).before(fr.getToDate());
	    equalsCriterion = ((Date) calendar.getTime()).equals(fr.getToDate());
	    
	    if(smallerCriterion || equalsCriterion){
		    newfr2.setFromDate(calendar.getTime());
		    newfr2.setToDate(fr.getToDate());
		    forRentAndReservedDAO.saveForRent(newfr2);
	    }
	}
}