package com.edwardjones.tfi.securitymaster.mapper.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.edwardjones.tfi.aggregator.common.model.CallEvent;
import com.edwardjones.tfi.aggregator.common.model.FixedIncomeSecurity;
import com.edwardjones.tfi.aggregator.common.model.MaterialEvent;

public class FixedIncomeSecurityMapperTest {

	FixedIncomeSecurityMapper mapper=null;

	@Before
	public void setUp() throws Exception {
		mapper=new FixedIncomeSecurityMapper();
	}


	@Test
	public void testmapToSecurityOfferingCusip() {


		String mqInMessage="ABCD123456789"
				+ "URKWjzqkOE6WcyPaLR804ChUkfO9vz3Bfj96O3lYYSSJoTjoO5W0UTjZjovq"
				+ "1234567891"
				+ "Y"
				+ "N"
				+ "wxyz5678"
				+ "Y"
				+ "C"
				+ "12"
				+ "123456"
				+ "123456"
				+ "123456"
				+ "123456"
				+ "123456"
				+ "N"
				+ "US"
				+ "USD"
				+ "000000000"
				+ "CreditEnhancement1CreditEnhancement2CreditEnhancement3Credit"
				+ "20201105"
				+ "c1"
				+ "DEB"
				+ "Y"
				+ "N"
				+ "Y"
				+ "20201105"
				+ "123456.78"
				+ "Fdic88"
				+ "20201105"
				+ "20201105"
				+ "12345"
				+ "R1"
				+ "12345"
				+ "R2"
				+ "12345"
				+ "R3"
				+ "12345"
				+ "R4"
				+ "12345"
				+ "R5"
				+ "12345"
				+ "R6"
				+ "Guarantor1Guarantor2Guarantor3Gu"
				+ "N"
				+ "INSRRR"
				+ "              "
				+ "20201105"
				+ "1234.1234"
				+ "123456789.1234"
				+ "Description1Description2DescriDescription1Description2Descri"
				+ "IssuerName1IssuerName2Issuer03"
				+ "123456789.12345"
				+ "20201105"
				+ "20201105"
				+ "Y"
				+ "1.2";

		FixedIncomeSecurity fixedIncomeSecurity =  mapper.mapToSecurityOfferingFields(mqInMessage );
		assertEquals("1","ABCD123456789", fixedIncomeSecurity.getCusip());
		assertEquals("2","URKWjzqkOE6WcyPaLR804ChUkfO9vz3Bfj96O3lYYSSJoTjoO5W0UTjZjovq", fixedIncomeSecurity.getIssueAdditionalDescription());
		assertEquals("3","1234567891", fixedIncomeSecurity.getSecType());
		assertEquals("4",true, fixedIncomeSecurity.isTaxExemptAlternativeMinimum());
		assertEquals("5",false, fixedIncomeSecurity.isBankQualified());
		assertEquals("6","wxyz5678", fixedIncomeSecurity.getCallProtectionUntil());
		assertEquals("7",true, fixedIncomeSecurity.isCall());
		assertEquals("8","C", fixedIncomeSecurity.getCallNoticeCode());
		assertEquals("9",12, fixedIncomeSecurity.getCallNoticeDays());
		assertEquals("10",BigDecimal.valueOf(123456), fixedIncomeSecurity.getPrimaryCPIRateForIssueDate());

		assertEquals("11",BigDecimal.valueOf(123456), fixedIncomeSecurity.getPrimaryCPIRateForSettleDate ());

		assertEquals("12",BigDecimal.valueOf(123456), fixedIncomeSecurity.getSecondaryCPIRateForIssueDate  ());

		assertEquals("13",BigDecimal.valueOf(123456), fixedIncomeSecurity.getSecondaryCPIRateForSettleDate  ());

		assertEquals("14",false, fixedIncomeSecurity.isConvertible());

		assertEquals("15","US", fixedIncomeSecurity.getCountry());

		assertEquals("16","USD", fixedIncomeSecurity.getCurrency());

		assertEquals("17",BigDecimal.valueOf(000000000), fixedIncomeSecurity.getCoupon  ());

		assertEquals("18","CreditEnhancement1CreditEnhancement2CreditEnhancement3Credit", fixedIncomeSecurity.getCreditEnhancement());

		assertEquals("19","20201105", fixedIncomeSecurity.getDatedDate());	

		assertEquals("20","c1", fixedIncomeSecurity.getDayTypeCalendar());

		assertEquals("21","DEB", fixedIncomeSecurity.getDebtType());

		assertEquals("22",true, fixedIncomeSecurity.isEscrowedToMaturity()); 

		assertEquals("23",false, fixedIncomeSecurity.isEstateFeature());

		assertEquals("24",true, fixedIncomeSecurity.isExtraordinary());

		assertEquals("25","20201105", fixedIncomeSecurity.getFactorDate());

		assertEquals("26",BigDecimal.valueOf(123456.78), fixedIncomeSecurity.getFactor());

		assertEquals("27","Fdic88", fixedIncomeSecurity.getFdicCert());

		assertEquals("28","20201105", fixedIncomeSecurity.getPaymentDateFirst());

		assertEquals("29","20201105", fixedIncomeSecurity.getFirstSettlementDate());

		assertEquals("30",12345 ,fixedIncomeSecurity.getRatingFitch());

		assertEquals("31", "R1", fixedIncomeSecurity.getRatingFitchVal());

		assertEquals("32",12345 ,fixedIncomeSecurity.getRatingFitchUnderlying());

		assertEquals("33", "R2", fixedIncomeSecurity.getRatingFitchUnderlyingVal());

		assertEquals("34",12345 ,fixedIncomeSecurity.getRatingMoody());

		assertEquals("35", "R3", fixedIncomeSecurity.getRatingMoodyVal());

		assertEquals("36",12345 ,fixedIncomeSecurity.getRatingMoodyUnderlying());

		assertEquals("37", "R4", fixedIncomeSecurity.getRatingMoodyUnderlyingVal());

		assertEquals("38",12345 ,fixedIncomeSecurity.getRatingSAndP());

		assertEquals("39", "R5", fixedIncomeSecurity.getRatingSAndPVal());

		assertEquals("40",12345 ,fixedIncomeSecurity.getRatingSAndPUnderlying());

		assertEquals("41", "R6", fixedIncomeSecurity.getRatingSAndPUnderlyingVal());

		assertEquals("42", "Guarantor1Guarantor2Guarantor3Gu", fixedIncomeSecurity.getGuarantor());

		assertEquals("43",false, fixedIncomeSecurity.isInsured());

		assertEquals("44", "INSRRR", fixedIncomeSecurity.getInsurer());

		assertEquals("45",0,fixedIncomeSecurity.getOriginalIssueQty());

		assertEquals("46", "20201105", fixedIncomeSecurity.getOriginalIssueDate());

		assertEquals("47",BigDecimal.valueOf(1234.1234), fixedIncomeSecurity.getOriginalIssuePrice ());

		assertEquals("48",BigDecimal.valueOf(123456789.1234), fixedIncomeSecurity.getOriginalIssueYield ());

		assertEquals("49", "Description1Description2DescriDescription1Description2Descri", fixedIncomeSecurity.getIssuerDescription());

		assertEquals("50", "IssuerName1IssuerName2Issuer03", fixedIncomeSecurity.getIssuerName());

		assertEquals("51",BigDecimal.valueOf(123456789.12345), fixedIncomeSecurity.getLapiPrice());

		assertEquals("52", "20201105", fixedIncomeSecurity.getLapiUpdateDate());

		assertEquals("53", "20201105", fixedIncomeSecurity.getLastCouponDate());

		assertEquals("54",true, fixedIncomeSecurity.isWholeCall());

		assertEquals("55",BigDecimal.valueOf(1.2), fixedIncomeSecurity.getWholeCallSpread());



	}


	@Test
	public void testsetMatEvents() {
		String mqInMessage="ABCD123456789URKWjzqkOE6WcyPaLR804ChUkfO9vz3Bfj96O3lYYSSJoTjoO5W0UTjZjovq1234567891YNwxyz5678YC12123456123456123456123456123456NUSUSD000000000CreditEnhancement1CreditEnhancement2CreditEnhancement3Credit20201105c1DEBYNY20201105123456.78Fdic88202011052020110512345R112345R212345R312345R412345R512345R6Guarantor1Guarantor2Guarantor3GuNINSRRR              202011051234.1234123456789.1234Description1Description2DescriDescription1Description2DescriIssuerName1IssuerName2Issuer03123456789.123452020110520201105Y1.2"
				+ "20";
		for(int i=1;i<=20;i++) {
			if(i==3) {
				mqInMessage+="120200813ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkks";
			}else {
				mqInMessage+="120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";
			}
		}
		FixedIncomeSecurity fixedIncomeSecurity =  mapper.mapToSecurityOfferingFields(mqInMessage );	
		List<MaterialEvent> materialEvents = fixedIncomeSecurity.getMaterialEvents();
		for(int i=1;i<=20;i++) {
			MaterialEvent event = materialEvents.get(i-1);             
			if(i==3) {
				assertEquals("56","20200813",event.getDate());
				assertEquals("57","ecod",event.getEventCode());
				assertEquals("58","kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkks",event.getEventText());
			} else {
				assertEquals("59","20200812",event.getDate());
				assertEquals("60","ecod",event.getEventCode());
				assertEquals("61","kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk",event.getEventText());
			}
		}
		assertEquals("62",20,materialEvents.size());
	}


	@Test 
	public void testsetMin() {
		String mqInMessage="ABCD123456789URKWjzqkOE6WcyPaLR804ChUkfO9vz3Bfj96O3lYYSSJoTjoO5W0UTjZjovq1234567891YNwxyz5678YC12123456123456123456123456123456NUSUSD000000000CreditEnhancement1CreditEnhancement2CreditEnhancement3Credit20201105c1DEBYNY20201105123456.78Fdic88202011052020110512345R112345R212345R312345R412345R512345R6Guarantor1Guarantor2Guarantor3GuNINSRRR              202011051234.1234123456789.1234Description1Description2DescriDescription1Description2DescriIssuerName1IssuerName2Issuer03123456789.123452020110520201105Y1.220120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200813ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkks120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";
		String MATURITY_DA="12345678";
		String MIN_INIT_QTY="1234567";
		String MIN_SBSQT_QTY="1234567";
		mqInMessage+=MATURITY_DA + MIN_INIT_QTY + MIN_SBSQT_QTY;
		FixedIncomeSecurity fixedIncomeSecurity = mapper.mapToSecurityOfferingFields(mqInMessage);
		assertEquals("63","12345678",fixedIncomeSecurity.getMaturityDate());
		assertEquals("64",1234567,fixedIncomeSecurity.getMinDenom());
		assertEquals("65",1234567,fixedIncomeSecurity.getMultiple());
	}




	@Test 
	public void testsetPrrOptions() { 
		String mqInMessage="ABCD123456789URKWjzqkOE6WcyPaLR804ChUkfO9vz3Bfj96O3lYYSSJoTjoO5W0UTjZjovq1234567891YNwxyz5678YC12123456123456123456123456123456NUSUSD000000000CreditEnhancement1CreditEnhancement2CreditEnhancement3Credit20201105c1DEBYNY20201105123456.78Fdic88202011052020110512345R112345R212345R312345R412345R512345R6Guarantor1Guarantor2Guarantor3GuNINSRRR              202011051234.1234123456789.1234Description1Description2DescriDescription1Description2DescriIssuerName1IssuerName2Issuer03123456789.123452020110520201105Y1.220120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200813ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkks120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk1234567812345671234567"; 		
		String NO_OF_OPTIONS="99";
		String KIND_OF_OPTION="1";
		String OPTION_TYPE="1";
		String OPTION_DATE="12345678";
		String OPTION_PRICE="12345678";
		mqInMessage+=NO_OF_OPTIONS ;
		for(int i=1;i<=99;i++) {
			mqInMessage+= KIND_OF_OPTION + OPTION_TYPE + OPTION_DATE + OPTION_PRICE;
		}

		FixedIncomeSecurity fixedIncomeSecurity = mapper.mapToSecurityOfferingFields(mqInMessage ); 
		List<CallEvent> callEvents = fixedIncomeSecurity.getCallSchedule();
		assertEquals("66",99,callEvents.size());
		for(int i=1;i<=99;i++) {
			CallEvent event = callEvents.get(i-1);             
			assertEquals("56","12345678",event.getDate());
			assertEquals("57",12345678,event.getPrice().intValue());
		}
	}


	@Test 
	public void testNextOpt() { 
		String mqInMessage="ABCD123456789URKWjzqkOE6WcyPaLR804ChUkfO9vz3Bfj96O3lYYSSJoTjoO5W0UTjZjovq1234567891YNwxyz5678YC12123456123456123456123456123456NUSUSD000000000CreditEnhancement1CreditEnhancement2CreditEnhancement3Credit20201105c1DEBYNY20201105123456.78Fdic88202011052020110512345R112345R212345R312345R412345R512345R6Guarantor1Guarantor2Guarantor3GuNINSRRR              202011051234.1234123456789.1234Description1Description2DescriDescription1Description2DescriIssuerName1IssuerName2Issuer03123456789.123452020110520201105Y1.220120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200813ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkks120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk120200812ecodkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk123456781234567123456799111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678111234567812345678";	
		String NEXT_OPTION_KIND="1";
		String NEXT_OPT_DATE="20200813";
		String NEXT_OPT_PRICE="12345668";
		String NEXT_PAYMENT_DA="12345648";
		mqInMessage+= NEXT_OPTION_KIND + NEXT_OPT_DATE + NEXT_OPT_PRICE + NEXT_PAYMENT_DA;
		FixedIncomeSecurity fixedIncomeSecurity = mapper.mapToSecurityOfferingFields(mqInMessage ); 
		assertEquals("67","1",fixedIncomeSecurity.getNextOptionKind());
		assertEquals("68","20200813",fixedIncomeSecurity.getNextOptionDate());
		assertEquals("69",12345668,fixedIncomeSecurity.getNextOptionPrice().intValue());
		assertEquals("70","12345648",fixedIncomeSecurity.getPaymentDateNext());
	}


	

}
