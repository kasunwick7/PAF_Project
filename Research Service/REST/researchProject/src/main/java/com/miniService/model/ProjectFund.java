package com.miniService.model;

public class ProjectFund {
	
	public int fundPaymentID;
	public int researchID;
	public int fundingBodyID;
	public Float fundingAmount;
	
	
	public ProjectFund() {
	}

	
	public ProjectFund(int fundPaymentID, int researchID, int fundingBodyID, Float fundingAmount) {
		super();
		this.fundPaymentID = fundPaymentID;
		this.researchID = researchID;
		this.fundingBodyID = fundingBodyID;
		this.fundingAmount = fundingAmount;
	}


	public int getFundPaymentID() {
		return fundPaymentID;
	}


	public void setFundPaymentID(int fundPaymentID) {
		this.fundPaymentID = fundPaymentID;
	}


	public int getResearchID() {
		return researchID;
	}


	public void setResearchID(int researchID) {
		this.researchID = researchID;
	}


	public int getFundingBodyID() {
		return fundingBodyID;
	}


	public void setFundingBodyID(int fundingBodyID) {
		this.fundingBodyID = fundingBodyID;
	}


	public Float getFundingAmount() {
		return fundingAmount;
	}


	public void setFundingAmount(Float fundingAmount) {
		this.fundingAmount = fundingAmount;
	}
	
	
	
	

}
