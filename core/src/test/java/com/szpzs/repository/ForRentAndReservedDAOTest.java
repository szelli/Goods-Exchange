package com.szpzs.repository;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.szpzs.model.ForRent;
import com.szpzs.model.Reserved;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ForRentAndReservedDAOTest {

	@Autowired
	private ForRentAndReservedDAO frarDAO;

	static Logger log = Logger.getLogger(ForRentAndReservedDAO.class.getName());

	@Test
	@Transactional
	public void test1listForRents(){
		int productId = 2;
		List<ForRent> frlist = frarDAO.getForRentsByProductId(BigInteger.valueOf(productId));
		assertEquals(1,frlist.size());
	}

	@Test
	@Transactional
	public void test2saveForRent() throws ParseException{
		int productId = 18;
		List<ForRent> frlist = frarDAO.getForRentsByProductId(BigInteger.valueOf(productId));
		assertEquals(0,frlist.size());
		
		ForRent forRent = new ForRent();
		forRent.setProductId(BigInteger.valueOf(productId));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		Date fromDate = sdf.parse("2014-08-05");
		Date toDate = sdf.parse("2014-08-30");
		forRent.setFromDate(fromDate);
		forRent.setToDate(toDate);
		frarDAO.saveForRent(forRent);
		
		frlist = frarDAO.getForRentsByProductId(BigInteger.valueOf(productId));
		assertNotEquals(0,frlist.size());
	}
	
	@Test
	@Transactional
	public void test3GetForRent() throws ParseException{
		int productId = 16;
		List<ForRent> frlist = frarDAO.getForRentsByProductId(BigInteger.valueOf(productId));
		assertEquals(0,frlist.size());
		
		ForRent forRent = new ForRent();
		forRent.setProductId(BigInteger.valueOf(productId));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		Date fromDate = sdf.parse("2014-09-07");
		Date toDate = sdf.parse("2014-10-30");
		forRent.setFromDate(fromDate);
		forRent.setToDate(toDate);
		assertNull(frarDAO.getForRentByDateInterval(forRent.getProductId(), forRent.getFromDate(), forRent.getToDate()));
		
		frarDAO.saveForRent(forRent);
		
		ForRent savedForRent = frarDAO.getForRentByDateInterval(forRent.getProductId(), forRent.getFromDate(), forRent.getToDate());
		assertEquals(forRent.getProductId(), savedForRent.getProductId());
		assertEquals(forRent.getFromDate(), savedForRent.getFromDate());
		assertEquals(forRent.getToDate(), savedForRent.getToDate());		
	}
	
	@Test
	@Transactional
	public void test4deleteForRentById() throws ParseException{
		int productId = 19;
		ForRent forRent = new ForRent();
		forRent.setProductId(BigInteger.valueOf(productId));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		Date fromDate = sdf.parse("2014-09-07");
		Date toDate = sdf.parse("2014-10-30");
		forRent.setFromDate(fromDate);
		forRent.setToDate(toDate);
		assertNull(frarDAO.getForRentByDateInterval(forRent.getProductId(), forRent.getFromDate(), forRent.getToDate()));
		
		frarDAO.saveForRent(forRent);
		List<ForRent> frlist = frarDAO.getForRentsByProductId(BigInteger.valueOf(productId));
		assertEquals(1,frlist.size());
		
		frarDAO.deleteForRent((long)(frlist.get(0)).getId());
		
		frlist = frarDAO.getForRentsByProductId(BigInteger.valueOf(productId));
		assertEquals(0,frlist.size());
	}
	
	@Test
	@Transactional
	public void test5saveReserved() throws ParseException{
		int userId = 10;
		Reserved reserved = new Reserved();		
		reserved.setProductId(BigInteger.valueOf(2));
		
		String strDateFrom = "2014-06-05";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = formatter.parse(strDateFrom);
        String endDateFrom = "2014-06-20";
        Date toDate = formatter.parse(endDateFrom);
        reserved.setFromDate(fromDate);
		reserved.setToDate(toDate);
        
		reserved.setReserverId(BigInteger.valueOf(userId));
		
		List<ForRent> frlist = frarDAO.getReservedByProductId(reserved.getProductId());
		assertEquals(0,frlist.size());
		frarDAO.saveReserved(reserved);
		frlist = frarDAO.getReservedByProductId(reserved.getProductId());
		assertEquals(1,frlist.size());	
	}
}