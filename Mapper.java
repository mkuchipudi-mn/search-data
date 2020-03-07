package com.edwardjones.tfi.securitymaster.mapper.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.edwardjones.tfi.aggregator.common.model.CallEvent;
import com.edwardjones.tfi.aggregator.common.model.FixedIncomeSecurity;
import com.edwardjones.tfi.aggregator.common.model.MaterialEvent;
import com.edwardjones.tfi.aggregator.common.model.SinkingFundEvent;
import com.edwardjones.tfi.aggregator.common.model.StepUpEvent;

public class FixedIncomeSecurityMapper {

	private static final Logger log = LoggerFactory.getLogger(FixedIncomeSecurityMapper.class);

	/**
	 * 
	 * @param mqInMessage
	 * @return
	 */
	public FixedIncomeSecurity mapToSecurityOfferingFields(String mqInMessage) {
		log.info("mapping the SecurityOfferingFields {}", mqInMessage);
		FixedIncomeSecurity fixedIncomeSecurity = null;
		if (!mqInMessage.isEmpty()) {
			fixedIncomeSecurity = new FixedIncomeSecurity();
			CopybookGet cb = new CopybookGet(mqInMessage);
			setIncomeSecurityBasic(cb, fixedIncomeSecurity);
			setMatEvents(cb, fixedIncomeSecurity);
			setMinQty(cb, fixedIncomeSecurity);
			setPrrOptions(cb, fixedIncomeSecurity);
			setNextOpt(cb, fixedIncomeSecurity);
			setStateList(cb, fixedIncomeSecurity);
			setObligor(cb,fixedIncomeSecurity);
			setPaperParams(cb, fixedIncomeSecurity);
			setSinkingData(cb, fixedIncomeSecurity);
			setSorParams(cb, fixedIncomeSecurity);
			setStepEvents(cb, fixedIncomeSecurity);
			setExemptEvents(cb, fixedIncomeSecurity);
		}
		return fixedIncomeSecurity;
	}

	/**
	 * 
	 * @param mqInMessage
	 * @param fixedIncomeSecurity
	 */
	public void setIncomeSecurityBasic(CopybookGet cb, FixedIncomeSecurity fixedIncomeSecurity) {



		fixedIncomeSecurity.setCusip(cb.getString(CopybookConstants.CUSIP_NO).trim());

		fixedIncomeSecurity.setIssueAdditionalDescription(cb.getString(CopybookConstants.ADD_DESC).trim());

		fixedIncomeSecurity.setSecType(cb.getString(CopybookConstants.SEC_TYPE).trim());

		fixedIncomeSecurity.setTaxExemptAlternativeMinimum(parseBoolean(cb.getString(CopybookConstants.MUNI_AMT).trim()));

		fixedIncomeSecurity.setBankQualified(parseBoolean(cb.getString(CopybookConstants.BANK_QLFY).trim()));

		fixedIncomeSecurity.setCallProtectionUntil(cb.getString(CopybookConstants.CALL_PROT_UNTIL).trim());

		fixedIncomeSecurity.setCall(parseBoolean(cb.getString(CopybookConstants.CALLABLLE).trim()));

		fixedIncomeSecurity.setCallNoticeCode(cb.getString(CopybookConstants.CALL_BUS_CAL_DAY).trim());

		fixedIncomeSecurity.setCallNoticeDays(cb.getInt(CopybookConstants.CALL_NOTICE_DAYS));
	
		
		//TODO CPI_ISS_DA_RT
		cb.getString(CopybookConstants.CPI_ISS_DA_RT);
		
		fixedIncomeSecurity.setPrimaryCPIRateForIssueDate(parseBigDecimal(cb.getString(CopybookConstants.CPI_2MO_ISS_RT).trim()));

		fixedIncomeSecurity.setPrimaryCPIRateForSettleDate(parseBigDecimal(cb.getString(CopybookConstants.CPI_2MO_SETL_RT).trim()));

		fixedIncomeSecurity.setSecondaryCPIRateForIssueDate(parseBigDecimal(cb.getString(CopybookConstants.CPI_3MO_ISS_RT).trim()));

		fixedIncomeSecurity.setSecondaryCPIRateForSettleDate(parseBigDecimal(cb.getString(CopybookConstants.CPI_3MO_SETL_RT).trim()));

		fixedIncomeSecurity.setConvertible(parseBoolean(cb.getString(CopybookConstants.CONVERTIBLE).trim()));

		fixedIncomeSecurity.setCountry(cb.getString(CopybookConstants.COUNTRY_CD).trim());

		fixedIncomeSecurity.setCurrency(cb.getString(CopybookConstants.CURRENCY_CD).trim());

		fixedIncomeSecurity.setCoupon(parseBigDecimal(cb.getString(CopybookConstants.COUPON_RATE).trim()));

		fixedIncomeSecurity.setCreditEnhancement(cb.getString(CopybookConstants.CREDIT_ENH).trim());

		fixedIncomeSecurity.setDatedDate(cb.getString(CopybookConstants.DATED_DATE).trim());

		fixedIncomeSecurity.setDayTypeCalendar(cb.getString(CopybookConstants.DAY_TYPE_CAL).trim());

		fixedIncomeSecurity.setDebtType(cb.getString(CopybookConstants.DEBT_TYPE).trim());

		fixedIncomeSecurity.setEscrowedToMaturity(parseBoolean(cb.getString(CopybookConstants.ESCROW_TO_MAT).trim()));

		fixedIncomeSecurity.setEstateFeature(parseBoolean(cb.getString(CopybookConstants.ESTATE_FEATURE).trim()));

		fixedIncomeSecurity.setExtraordinary(parseBoolean(cb.getString(CopybookConstants.EXTORD_CALL).trim()));

		fixedIncomeSecurity.setFactorDate(cb.getString(CopybookConstants.FACTOR_DATE).trim());

		fixedIncomeSecurity.setFactor(parseBigDecimal(cb.getString(CopybookConstants.FACTOR_RATE).trim()));

		fixedIncomeSecurity.setFdicCert(cb.getString(CopybookConstants.FDIC_CERT_NO).trim());

		fixedIncomeSecurity.setPaymentDateFirst(cb.getString(CopybookConstants.FIRST_PAYMENT_DA).trim());

		fixedIncomeSecurity.setFirstSettlementDate(cb.getString(CopybookConstants.FIRST_SETTL_DA).trim());

		fixedIncomeSecurity.setRatingFitch((cb.getInt(CopybookConstants.FITCH_RATING)));

		fixedIncomeSecurity.setRatingFitchVal(cb.getString(CopybookConstants.FITCH_RATING_VAL).trim());

		fixedIncomeSecurity.setRatingFitchUnderlying((cb.getInt(CopybookConstants.FITCH_UND_RATING)));

		fixedIncomeSecurity.setRatingFitchUnderlyingVal(cb.getString(CopybookConstants.FITCH_UND_RAT_V).trim());

		fixedIncomeSecurity.setRatingMoody((cb.getInt(CopybookConstants.MOODY_RATING)));

		fixedIncomeSecurity.setRatingMoodyVal(cb.getString(CopybookConstants.MOODY_RATING_VAL).trim());

		fixedIncomeSecurity.setRatingMoodyUnderlying((cb.getInt(CopybookConstants.MOODY_UND_RATING)));

		fixedIncomeSecurity.setRatingMoodyUnderlyingVal(cb.getString(CopybookConstants.MOODY_UND_RAT_V).trim());

		fixedIncomeSecurity.setRatingSAndP((cb.getInt(CopybookConstants.SNP_RATING)));

		fixedIncomeSecurity.setRatingSAndPVal(cb.getString(CopybookConstants.SNP_RATING_VAL).trim());

		fixedIncomeSecurity.setRatingSAndPUnderlying((cb.getInt(CopybookConstants.SNP_UND_RATING)));

		fixedIncomeSecurity.setRatingSAndPUnderlyingVal(cb.getString(CopybookConstants.SNP_UND_RAT_V).trim());

		fixedIncomeSecurity.setGuarantor(cb.getString(CopybookConstants.GUARANTOR ).trim());

		fixedIncomeSecurity.setInsured(parseBoolean(cb.getString(CopybookConstants.INSURED).trim()));

		fixedIncomeSecurity.setInsurer(cb.getString(CopybookConstants.INSURER).trim());

		fixedIncomeSecurity.setOriginalIssueQty((cb.getInt(CopybookConstants.ISSUE_AMT)));

		fixedIncomeSecurity.setOriginalIssueDate(cb.getString(CopybookConstants.ISSUE_DATE).trim());

		fixedIncomeSecurity.setOriginalIssuePrice(parseBigDecimal(cb.getString(CopybookConstants.ISSUE_PRICE).trim()));

		fixedIncomeSecurity.setOriginalIssueYield(parseBigDecimal(cb.getString(CopybookConstants.ISSUE_YLD).trim()));

		fixedIncomeSecurity.setIssuerDescription(cb.getString(CopybookConstants.ISSUER_DESC).trim());

		fixedIncomeSecurity.setIssuerName(cb.getString(CopybookConstants.ISSUER_NAME).trim());

		fixedIncomeSecurity.setLapiPrice(parseBigDecimal(cb.getString(CopybookConstants.VENDOR_PRICE).trim()));

		fixedIncomeSecurity.setLapiUpdateDate(cb.getString(CopybookConstants.VENDOR_UPDT_DA).trim());

		fixedIncomeSecurity.setLastCouponDate(cb.getString(CopybookConstants.LAST_COUPON_DA).trim());

		fixedIncomeSecurity.setWholeCall(parseBoolean(cb.getString(CopybookConstants.MAKE_WHOLE_CALL ).trim()));

		fixedIncomeSecurity.setWholeCallSpread(parseBigDecimal(cb.getString(CopybookConstants.MAKE_WHOLE_SPRD ).trim()));

	}

	/**
	 * 
	 * @param mqInMessage
	 * @param fixedIncomeSecurity
	 */
	public void setMatEvents(CopybookGet cb, FixedIncomeSecurity fixedIncomeSecurity) {

		List<MaterialEvent> materialEvents = new ArrayList<MaterialEvent>();

        int noOfMatEvents = cb.getInt(CopybookConstants.NO_OF_MAT_EVENTS);
		//MAT-EVENTS(1) OCCURS 20 TIMES

		for (int i = 1; i <= noOfMatEvents; i++) {

			cb.getString(CopybookConstants.MAT_EVENT_STAT);
			String date = cb.getString(CopybookConstants.MAT_EVENT_DATE);
			String eventCode = cb.getString(CopybookConstants.MAT_EVENT_CODE);
			String	eventText = cb.getString(CopybookConstants.MAT_EVENT_DESCRIPTION);
			if(!StringUtils.isEmpty(date) && !StringUtils.isEmpty(eventCode) && !StringUtils.isEmpty(eventText)) {
				materialEvents.add(new MaterialEvent(date, eventCode, eventText));
			} 

		}
		fixedIncomeSecurity.setMaterialEvents(materialEvents);

	}


	public void setMinQty(CopybookGet cb, FixedIncomeSecurity fixedIncomeSecurity) {
		
		fixedIncomeSecurity.setMaturityDate(cb.getString(CopybookConstants.MATURITY_DA));
		fixedIncomeSecurity.setMinDenom(cb.getInt(CopybookConstants.MIN_INIT_QTY));
		fixedIncomeSecurity.setMultiple(cb.getInt(CopybookConstants.MIN_SBSQT_QTY));
	}


	/**
	 * 
	 * @param mqInMessage
	 * @param fixedIncomeSecurity
	 */
	public void setPrrOptions(CopybookGet cb, FixedIncomeSecurity fixedIncomeSecurity) {

		List<CallEvent> callEvents = new ArrayList<CallEvent>();

		int noOfOptions = cb.getInt(CopybookConstants.NO_OF_OPTIONS);
		// CALL-PUT-PRR-OPTIONS OCCURS 99 TIMES
		for(int i=1;i<=noOfOptions;i++) {

			cb.getString(CopybookConstants.KIND_OF_OPTION);
			String type = cb.getString(CopybookConstants.OPTION_TYPE);
			String date = cb.getString(CopybookConstants.OPTION_DATE);
			BigDecimal price = parseBigDecimal(cb.getString(CopybookConstants.OPTION_PRICE)); //BigDecimal

			if(!StringUtils.isEmpty(date) && !StringUtils.isEmpty(price) && !StringUtils.isEmpty(type)) {

				callEvents.add(new CallEvent(date,price,type)); 
			}

		}
		fixedIncomeSecurity.setCallSchedule(callEvents);
	}
	
	
	public void setNextOpt(CopybookGet cb, FixedIncomeSecurity fixedIncomeSecurity) {
		fixedIncomeSecurity.setNextOptionKind(cb.getString(CopybookConstants.NEXT_OPTION_KIND));
		fixedIncomeSecurity.setNextOptionDate(cb.getString(CopybookConstants.NEXT_OPT_DATE));
		fixedIncomeSecurity.setNextOptionPrice(parseBigDecimal(cb.getString(CopybookConstants.NEXT_OPT_PRICE)));
		fixedIncomeSecurity.setPaymentDateNext(cb.getString(CopybookConstants.NEXT_PAYMENT_DA));
	}
	/**
	 * 
	 * @param cb
	 * @param fixedIncomeSecurity
	 */
	public void setStateList(CopybookGet cb, FixedIncomeSecurity fixedIncomeSecurity) {
		int noOfStates = cb.getInt(CopybookConstants.NO_STATES);
		List<String> notSaleInList=new ArrayList<String>();
		for(int i=1;i<=noOfStates;i++) {
			notSaleInList.add(cb.getString(CopybookConstants.STATE_ABBR_CD));
		}
		fixedIncomeSecurity.setNotForSaleIn(notSaleInList);
	}

	//TODO will Map appropriate mongo object later
	public void setObligor(CopybookGet cb, FixedIncomeSecurity fixedIncomeSecurity) {
		int noOfStates = cb.getInt(CopybookConstants.NO_OBLIGORS);
		//List<String> notSaleInList=new ArrayList<String>();
		for(int i=1;i<=noOfStates;i++) {
			cb.getString(CopybookConstants.OBLIGOR);
		}
		//fixedIncomeSecurity.setNotForSaleIn(notSaleInList);
	}


	public void setPaperParams(CopybookGet cb, FixedIncomeSecurity fixedIncomeSecurity) {
		
		fixedIncomeSecurity.setPaperType(cb.getString(CopybookConstants.PAPER_TYPE));
		fixedIncomeSecurity.setPaymentEOM(parseBoolean(cb.getString(CopybookConstants.PAY_EOM)));
		
		// TODO PaymentFrequencyCode or PaymentFrequencyText
		fixedIncomeSecurity.setPaymentFrequencyCode(cb.getString(CopybookConstants.PAY_FREQ)); 
		
		// No matching field in mongo
		//fixedIncomeSecurity.setNextOptionDate(cb.getString(CopybookConstants.COMP_FREQ));
		
		fixedIncomeSecurity.setPool(cb.getString(CopybookConstants.POOL_NO));
		fixedIncomeSecurity.setPrerefunded(parseBoolean(cb.getString(CopybookConstants.PREREFUNDED)));
		fixedIncomeSecurity.setPrerefundedDate(cb.getString(CopybookConstants.PREREFUND_DA));
		fixedIncomeSecurity.setProductTypeCode(cb.getString(CopybookConstants.PRODUCT_TY_CD));
		fixedIncomeSecurity.setProductTradeCode(cb.getString(CopybookConstants.PRODUCT_TY_DESC));
		fixedIncomeSecurity.setPutType(cb.getString(CopybookConstants.PUT_TYPE));
		fixedIncomeSecurity.setPut(parseBoolean(cb.getString(CopybookConstants.PUTABLE)));
		fixedIncomeSecurity.setRedemptionType(cb.getString(CopybookConstants.REDEMPTION_TYPE));
		fixedIncomeSecurity.setRedemptionValue(cb.getString(CopybookConstants.REDEMPTION_VALUE));
		fixedIncomeSecurity.setSecStat(cb.getString(CopybookConstants.SEC_STAT_CD));
		fixedIncomeSecurity.setSector (cb.getString(CopybookConstants.SECTOR));
		fixedIncomeSecurity.setSinkingFund(parseBoolean(cb.getString(CopybookConstants.SINKING_FUND)));

	}
	
	
	    public void setSinkingData(CopybookGet cb, FixedIncomeSecurity fixedIncomeSecurity) {
		int noOfSinks = cb.getInt(CopybookConstants.NO_OF_SINK);
		List<SinkingFundEvent> sinkingList=new ArrayList<SinkingFundEvent>();
		SinkingFundEvent fundEvent = null;
		String date = null;
		BigDecimal amount = null;
		BigDecimal rate = null;
		for(int i=1;i<=noOfSinks;i++) {
			date = cb.getString(CopybookConstants.SINKDATE);
			amount = parseBigDecimal(cb.getString(CopybookConstants.SINK_AMT));
			rate = parseBigDecimal(cb.getString(CopybookConstants.SINK_RT));
			fundEvent = new SinkingFundEvent(date,amount,rate);
			sinkingList.add(fundEvent);
		}
		fixedIncomeSecurity.setSinkingFundSchedule(sinkingList);
	}
	
	
	    public void setSorParams(CopybookGet cb, FixedIncomeSecurity fixedIncomeSecurity) {
		fixedIncomeSecurity.setSecurityOfferingReform(parseBoolean(cb.getString(CopybookConstants.SOR)));
		fixedIncomeSecurity.setSpecialIrregularPayAdjust(parseBigDecimal(cb.getString(CopybookConstants.SPEC_IRR_PAY)));
		fixedIncomeSecurity.setState(cb.getString(CopybookConstants.STATE_CD));
		fixedIncomeSecurity.setStepUp(parseBoolean(cb.getString(CopybookConstants.STEP_UP)));
	}


	public void setStepEvents(CopybookGet cb, FixedIncomeSecurity fixedIncomeSecurity) {
		int noOfStates = cb.getInt(CopybookConstants.NO_OF_STEPS);
		List<StepUpEvent> stepEvents=new ArrayList<StepUpEvent>();
		StepUpEvent stepEvent = null;
		String date = null;

		BigDecimal rate = null;
		for(int i=1;i<=noOfStates;i++) {
			rate = parseBigDecimal(cb.getString(CopybookConstants.STEP_UP_RATE));
			date = cb.getString(CopybookConstants.STEP_UP_DATE);
			stepEvent = new StepUpEvent(date,rate);
			stepEvents.add(stepEvent);
		}
		fixedIncomeSecurity.setStepUpSchedule(stepEvents);
	}

	
	    public void setExemptEvents(CopybookGet cb, FixedIncomeSecurity fixedIncomeSecurity) {
		fixedIncomeSecurity.setTaxExemptFederal(parseBoolean(cb.getString(CopybookConstants.FED_TAX_EXEMPT)));
		fixedIncomeSecurity.setTaxExemptState(parseBoolean(cb.getString(CopybookConstants.IN_ST_EXEMPT)));
		fixedIncomeSecurity.setTaxExemptAllStates(parseBoolean(cb.getString(CopybookConstants.ALL_ST_EXEMPT)));
		fixedIncomeSecurity.setTaxExemptLocal(parseBoolean(cb.getString(CopybookConstants.LOC_TAX_EXEMPT)));
		
		// No matching field in mongo
		fixedIncomeSecurity.setState(cb.getString(CopybookConstants.NO_OF_TRLR_CDS));
		
		fixedIncomeSecurity.setTrailerCode(cb.getString(CopybookConstants.TRLR_CD));
		fixedIncomeSecurity.setTrailerCodeExpirationDate(cb.getString(CopybookConstants.TRLR_CD_EXP_DA));
		fixedIncomeSecurity.setVariableRateSecurityCode(cb.getString(CopybookConstants.VAR_RATE_CD));
		fixedIncomeSecurity.setWhenIssued(parseBoolean(cb.getString(CopybookConstants.WHEN_ISSUED)));
		fixedIncomeSecurity.setZeroCoupon(parseBoolean(cb.getString(CopybookConstants.ZERO_COUPON)));
	}


    private String getValue(String input, int beginIndex, int endIndex) {
		if (!input.isEmpty() && input.length() >= endIndex) {
			return input.substring(beginIndex, endIndex);
		}
		return null;
	}

	private Integer parseInt(String value) {
		if (value != null) {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return null;
	}

	private boolean parseBoolean(String value) {
		if (value != null) {
			return "Y".equalsIgnoreCase(value) ? true : false;
		}
		return false;
	}

	private BigDecimal parseBigDecimal(String value) {
		if (value != null) {
			try {
				return new BigDecimal(value);
			} catch (NumberFormatException e) {
				return BigDecimal.ZERO;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		/*
		 * String mqInMessage = RandomStringUtils.random(7595, true, true);
		 * FixedIncomeSecurityMapper helperMapper = new FixedIncomeSecurityMapper();
		 * FixedIncomeSecurity security =
		 * helperMapper.mapToSecurityOfferingFields(mqInMessage);
		 * System.out.println(security);
		 */

		String buffer="1234";
		CopybookGet cb=new CopybookGet(buffer);

		System.out.println(cb.getInt(4));
	}

}































/*
 * fixedIncomeSecurity.setNextOptionKind(getValue(mqInMessage,19803,19803);
 * fixedIncomeSecurity.setNextOptionDate(getValue(mqInMessage,19804,19811));
 * fixedIncomeSecurity.setNextOptionPrice(getValue(mqInMessage,19812,19819));
 * //Bigdecimal
 * fixedIncomeSecurity.setPaymentDateNext(getValue(mqInMessage,19820,19827);
 */

// NO-STATES ( 19828,19831)

//occurs 54

//occurs 10

/*
 * fixedIncomeSecurity.setPaperType(getValue(mqInMessage,20004,20005));
 * fixedIncomeSecurity.setPaymentEOM(Boolean.parseBoolean(getValue(mqInMessage,
 * 20006,20006)));
 * fixedIncomeSecurity.setPaymentFrequencyCode(getValue(mqInMessage,20007,20007)
 * ); fixedIncomeSecurity.setPool(getValue(mqInMessage,20008,20017));
 * fixedIncomeSecurity.setPreRefunded(Boolean.parseBoolean(getValue(mqInMessage,
 * 20018,20018)));
 * fixedIncomeSecurity.setPreRefundedDate(getValue(mqInMessage,20019,20026));
 * fixedIncomeSecurity.setProductTypeCode(getValue(mqInMessage,20027,20032));
 * fixedIncomeSecurity.setProductTradeCode(getValue(mqInMessage,20033,20035));
 * fixedIncomeSecurity.setPutType(getValue(mqInMessage,20036,20037));
 * fixedIncomeSecurity.setPut(Boolean.parseBoolean(getValue(mqInMessage,20038,
 * 20038)));
 * fixedIncomeSecurity.setRedemptionType(getValue(mqInMessage,20039,20039));
 * fixedIncomeSecurity.setRedemptionValue(getValue(mqInMessage,20040,20040));
 * fixedIncomeSecurity.setSecStat(getValue(mqInMessage,20041,20041));
 * fixedIncomeSecurity.setSector(getValue(mqInMessage,20042,20042));
 * fixedIncomeSecurity.setSinkingFund(Boolean.parseBoolean(getValue(mqInMessage,
 * 20043,20043)));
 */

//occurs 100

/*
 * fixedIncomeSecurity.setSecurityOfferingReform(Boolean.parseBoolean(getValue(
 * mqInMessage,23448,23448)));
 * fixedIncomeSecurity.setSpecialIrregularPayAdjust(getValue(mqInMessage,23449,
 * 23450)); //Bigdecimal
 * fixedIncomeSecurity.setState(getValue(mqInMessage,23451,23452));
 * fixedIncomeSecurity.setStepUp(Boolean.parseBoolean(getValue(mqInMessage,23453
 * ,23453))); // NO-OF-STEPS (23454,23457)
 */

//occurs 20 

/*
 * fixedIncomeSecurity.setTaxExemptFederal(Boolean.parseBoolean(getValue(
 * mqInMessage,23798,23798)));
 * fixedIncomeSecurity.setTaxExemptState(Boolean.parseBoolean(getValue(
 * mqInMessage,23799,23799)));
 * fixedIncomeSecurity.setTaxExemptAllStates(Boolean.parseBoolean(getValue(
 * mqInMessage,23800,23800)));
 * fixedIncomeSecurity.setTaxExemptLocal(Boolean.parseBoolean(getValue(
 * mqInMessage,23801,23801))); // NO-OF-TRLR-CDS (23802,23803)
 * fixedIncomeSecurity.setTrailerCode(getValue(mqInMessage,23804,23806));
 * fixedIncomeSecurity.setTrailerCodeExpirationDate(getValue(mqInMessage,23807,
 * 23814));
 * fixedIncomeSecurity.setVariableRateSecurityCode(getValue(mqInMessage,23815,
 * 23815));
 * fixedIncomeSecurity.setWhenIssued(Boolean.parseBoolean(getValue(mqInMessage,
 * 23816,23816)));
 * fixedIncomeSecurity.setZeroCoupon(Boolean.parseBoolean(getValue(mqInMessage,
 * 23817,23817)));
 */
