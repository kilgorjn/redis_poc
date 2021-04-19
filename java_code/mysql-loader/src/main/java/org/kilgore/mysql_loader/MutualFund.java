package org.kilgore.mysql_loader;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/*
 * CREATE TABLE `redispoc`.`mutual_funds` (
  `fund_symbol` VARCHAR(5) NOT NULL,
  `fund_extended_name` VARCHAR(255) NULL,
  `fund_family` VARCHAR(45) NULL,
  `inception_date` DATE NULL,
  `category` VARCHAR(45) NULL,
  `rating` INT NULL,
  `return_rating` INT NULL,
  `risk_rating` INT NULL,
  `investment_strategy` VARCHAR(255) NULL,
  `investment_type` VARCHAR(45) NULL,
  `size_type` VARCHAR(45) NULL,
  `currency` VARCHAR(45) NULL,
  `fund_net_annual_expense_ratio` DECIMAL(5) NULL,
  `category_net_annual_expese_ratio` DECIMAL(5) NULL,
  `asset_cash` DECIMAL(5) NULL,
  `asset_stocks` DECIMAL(5) NULL,
  `asset_bonds` DECIMAL(5) NULL,
  `asset_others` DECIMAL(5) NULL,
  `asset_preferred` DECIMAL(5) NULL,
  `asset_convertable` DECIMAL(5) NULL,
  PRIMARY KEY (`fund_symbol`));
 */


public class MutualFund {
	
	public MutualFund() {

		
	}
	
	private String fundSymbol;
	private String fundExtendedName;
	private String fundFamily;
    @JsonFormat(pattern = "m/dd/yyyy")
	private Date inceptionDate;
	private String category;
	private Integer rating;
	private Integer returnRating;
	private Integer riskRating;
	private String investmentStrategy;
	private String investmentType;
	private String sizeType;
	private String currency;
	private Float fundNetAnnualExpenseRation;
	private Float categoryNetAnnualExpenseRatio;
	private Float assetCash;
	private Float assetStocks;
	private Float assetBonds;
	private Float assetOthers;
	private Float assetPreferred;
	private Float assetConvertable;
	public String getFundSymbol() {
		return fundSymbol;
	}
	public void setFundSymbol(String fundSymbol) {
		this.fundSymbol = fundSymbol;
	}
	public String getFundExtendedName() {
		return fundExtendedName;
	}
	public void setFundExtendedName(String fundExtendedName) {
		this.fundExtendedName = fundExtendedName;
	}
	public String getFundFamily() {
		return fundFamily;
	}
	public void setFundFamily(String fundFamily) {
		this.fundFamily = fundFamily;
	}
	public Date getInceptionDate() {
		return inceptionDate;
	}
	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public Integer getReturnRating() {
		return returnRating;
	}
	public void setReturnRating(Integer returnRating) {
		this.returnRating = returnRating;
	}
	public Integer getRiskRating() {
		return riskRating;
	}
	public void setRiskRating(Integer riskRating) {
		this.riskRating = riskRating;
	}
	public String getInvestmentStrategy() {
		return investmentStrategy;
	}
	public void setInvestmentStrategy(String investmentStrategy) {
		this.investmentStrategy = investmentStrategy;
	}
	public String getInvestmentType() {
		return investmentType;
	}
	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType;
	}
	public String getSizeType() {
		return sizeType;
	}
	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Float getFundNetAnnualExpenseRation() {
		return fundNetAnnualExpenseRation;
	}
	public void setFundNetAnnualExpenseRation(Float fundNetAnnualExpenseRation) {
		this.fundNetAnnualExpenseRation = fundNetAnnualExpenseRation;
	}
	public Float getCategoryNetAnnualExpenseRatio() {
		return categoryNetAnnualExpenseRatio;
	}
	public void setCategoryNetAnnualExpenseRatio(Float categoryNetAnnualExpenseRatio) {
		this.categoryNetAnnualExpenseRatio = categoryNetAnnualExpenseRatio;
	}
	public Float getAssetCash() {
		return assetCash;
	}
	public void setAssetCash(Float assetCash) {
		this.assetCash = assetCash;
	}
	public Float getAssetStocks() {
		return assetStocks;
	}
	public void setAssetStocks(Float assetStocks) {
		this.assetStocks = assetStocks;
	}
	public Float getAssetBonds() {
		return assetBonds;
	}
	public void setAssetBonds(Float assetBonds) {
		this.assetBonds = assetBonds;
	}
	public Float getAssetOthers() {
		return assetOthers;
	}
	public void setAssetOthers(Float assetOthers) {
		this.assetOthers = assetOthers;
	}
	public Float getAssetPreferred() {
		return assetPreferred;
	}
	public void setAssetPreferred(Float assetPreferred) {
		this.assetPreferred = assetPreferred;
	}
	public Float getAssetConvertable() {
		return assetConvertable;
	}
	public void setAssetConvertable(Float assetConvertable) {
		this.assetConvertable = assetConvertable;
	}

}
