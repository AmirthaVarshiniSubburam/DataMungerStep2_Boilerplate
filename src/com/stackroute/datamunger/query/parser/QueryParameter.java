package com.stackroute.datamunger.query.parser;

import java.util.ArrayList;
import java.util.List;

/* 
 * This class will contain the elements of the parsed Query String such as conditions,
 * logical operators,aggregate functions, file name, fields group by fields, order by
 * fields, Query Type
 * */

public class QueryParameter {

	private String baseQuery, fileName;
	private List<String> orderByFields = new ArrayList<String>();
	private List<String> groupByFields = new ArrayList<String>();
	private List<String> fields = new ArrayList<String>();
	private List<String> logicalOperators  = new ArrayList<String>();
	private List<AggregateFunction> aggregateFunctions = new ArrayList<AggregateFunction>();
	private List<Restriction> restrictions = new  ArrayList<Restriction>();
	
	
	public String getBaseQuery() {
		return baseQuery;
	}
	
	public void setBaseQuery(String baseQuery) {
		this.baseQuery = baseQuery;
	}
		
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public List<String> getOrderByFields() {
		return orderByFields;
	}
	
	public void setOrderByFields(List<String> orderByFields) {
		this.orderByFields = orderByFields;
	}
	
	public List<String> getGroupByFields() {
		return groupByFields;
	}

	public void setGroupByFields(List<String> groupByFields) {
		this.groupByFields = groupByFields;
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
	}

	public List<String> getLogicalOperators() {
		return logicalOperators;
	}

	public void setLogicalOperators(List<String> logicalOperators) {
		this.logicalOperators = logicalOperators;
	}

	public List<AggregateFunction> getAggregateFunctions() {
		return aggregateFunctions;
	}

	public void setAggregateFunctions(List<AggregateFunction> aggregateFunctions) {
		this.aggregateFunctions = aggregateFunctions;
	}
	
	public List<Restriction> getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(List<Restriction> restrictions) {
		this.restrictions = restrictions;
	}
	
	
}