package com.stackroute.datamunger.query.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*There are total 4 DataMungerTest file:
 * 
 * 1)DataMungerTestTask1.java file is for testing following 4 methods
 * a)getBaseQuery()  b)getFileName()  c)getOrderByClause()  d)getGroupByFields()
 * 
 * Once you implement the above 4 methods,run DataMungerTestTask1.java
 * 
 * 2)DataMungerTestTask2.java file is for testing following 2 methods
 * a)getFields() b) getAggregateFunctions()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask2.java
 * 
 * 3)DataMungerTestTask3.java file is for testing following 2 methods
 * a)getRestrictions()  b)getLogicalOperators()
 * 
 * Once you implement the above 2 methods,run DataMungerTestTask3.java
 * 
 * Once you implement all the methods run DataMungerTest.java.This test case consist of all
 * the test cases together.
 */

public class QueryParser {

	private QueryParameter queryParameter = new QueryParameter();

	/*
	 * This method will parse the queryString and will return the object of
	 * QueryParameter class
	 */
	public QueryParameter parseQuery(String queryString) {
		queryParameter.setBaseQuery(getBaseQuery(queryString));
		queryParameter.setFileName(getFileName(queryString));
		queryParameter.setOrderByFields(getOrderByFields(queryString));
		queryParameter.setGroupByFields(getGroupByFields(queryString));
		queryParameter.setFields(getFields(queryString));
		queryParameter.setLogicalOperators(getLogicalOperators(queryString));
		queryParameter.setAggregateFunctions(getAggregateFunctions(queryString));
		queryParameter.setRestrictions(getRestrictions(queryString));
		return queryParameter;
	}
	
	/*
	 * Extract the name of the file from the query. File name can be found after the
	 * "from" clause.
	 */
	
	public String getFileName(String queryString) {
		return queryString.substring( queryString.indexOf("from") + 5, queryString.indexOf("csv") + 3  );	
	}
	

	/*
	 * 
	 * Extract the baseQuery from the query.This method is used to extract the
	 * baseQuery from the query string. BaseQuery contains from the beginning of the
	 * query till the where clause
	 */
	
	public String getBaseQuery(String queryString) {
		return queryString.substring( 0, queryString.indexOf("csv") + 3  );
	}

	/*
	 * extract the order by fields from the query string. Please note that we will
	 * need to extract the field(s) after "order by" clause in the query, if at all
	 * the order by clause exists. For eg: select city,winner,team1,team2 from
	 * data/ipl.csv order by city from the query mentioned above, we need to extract
	 * "city". Please note that we can have more than one order by fields.
	 */
	
	public List<String> getOrderByFields(String queryString) {

		if(queryString.contains("order by"))
		{
//			return Arrays.asList(queryString.substring(queryString.indexOf("order by ") + 9).split(" "));
			List<String> list = Arrays.asList(queryString.substring(queryString.indexOf("order by ") + 9).split(" "));
			List <String> arrayList = new ArrayList<>(list);
			return arrayList;	
		}
		return null;
		
//		List<String>  orderByFields = new ArrayList<String>();
//		if (queryString.contains("order by")) {
//			orderByFields = Arrays.asList(queryString.split(" order by ")[1].split(" "));
//			}
//			return orderByFields;
		
		
	}
	
	/*
	 * Extract the group by fields from the query string. Please note that we will
	 * need to extract the field(s) after "group by" clause in the query, if at all
	 * the group by clause exists. For eg: select city,max(win_by_runs) from
	 * data/ipl.csv group by city from the query mentioned above, we need to extract
	 * "city". Please note that we can have more than one group by fields.
	 */
	
	public List<String> getGroupByFields(String queryString) {

//		if(queryString.contains("group by"))
//		{
//			return Arrays.asList(queryString.substring(queryString.indexOf("group by ") + 9).split(" "));
//		}
//		else if(queryString.contains("order by")) {
//			return Arrays.asList(queryString.substring(queryString.indexOf("group by ") + 9, queryString.indexOf("order by")).split(" "));
//		}
//		
//		
//		else {
//		return null;
//		}
		
		if (queryString.contains("group by")) {
		return Arrays.asList(queryString.split(" group by ")[1].split(" order by ")[0].split(" "));
		}
		return null;
//		return null;
	}
	

	/*
	 * Extract the selected fields from the query string. Please note that we will
	 * need to extract the field(s) after "select" clause followed by a space from
	 * the query string. For eg: select city,win_by_runs from data/ipl.csv from the
	 * query mentioned above, we need to extract "city" and "win_by_runs". Please
	 * note that we might have a field containing name "from_date" or "from_hrs".
	 * Hence, consider this while parsing.
	 */
	
	public List<String> getFields(String queryString) {

		return Arrays.asList(queryString.substring(queryString.indexOf("select ") + 7, queryString.indexOf(" from")).split(","));
	}

	/*
	 * Extract the conditions from the query string(if exists). for each condition,
	 * we need to capture the following: 1. Name of field 2. condition 3. value
	 * 
	 * For eg: select city,winner,team1,team2,player_of_match from data/ipl.csv
	 * where season >= 2008 or toss_decision != bat
	 * 
	 * here, for the first condition, "season>=2008" we need to capture: 1. Name of
	 * field: season 2. condition: >= 3. value: 2008
	 * 
	 * the query might contain multiple conditions separated by OR/AND operators.
	 * Please consider this while parsing the conditions.
	 * 
	 */
	
	public List<String> getLogicalOperators(String queryString) {

		
		queryString = queryString.toLowerCase();
		String[] array = queryString.split(" ");
		
		String logical = "";
		
		if(queryString.contains("where"))
		{
		for (int i = 0; i < array.length; i++) {
			
			if(array[i].matches("and|or"))
			{
				logical = logical + array[i] + " ";
			}	
		}
		List<String> list = Arrays.asList(logical.split(" "));
		List <String> arrayList = new ArrayList<>(list);
		return arrayList;
		}
		else {
			return null;
		}
	}

	/*
	 * Extract the logical operators(AND/OR) from the query, if at all it is
	 * present. For eg: select city,winner,team1,team2,player_of_match from
	 * data/ipl.csv where season >= 2008 or toss_decision != bat and city =
	 * bangalore
	 * 
	 * The query mentioned above in the example should return a List of Strings
	 * containing [or,and]
	 */
	
	public ArrayList<Restriction> getRestrictions(String queryString) {
        String query = queryString;
        String[] conditions = null;
        ArrayList<Restriction> restrictionList = null;
        if(query.contains("where")) {
            String conditionQuery = query.split("where|group by|order by")[1].trim();
            conditions = conditionQuery.split(" and | or ");
        
            restrictionList = new ArrayList<Restriction>();
            
            for(int i = 0; i < conditions.length; i++) {
                if(conditions[i].contains("'")) {
                    String var = conditions[i].split(" ")[0];
                    String restriction[] = conditions[i].split("'");
                    Restriction r = new Restriction(var.trim(),restriction[1].trim(),restriction[0].trim().split(" ")[1]);
                    restrictionList.add(r);
                }
                else {
                    String restriction[] = conditions[i].split(" ");
                    Restriction r = new Restriction(restriction[0].trim(),restriction[2].trim(),restriction[1].trim());
                    restrictionList.add(r);
                }
            }
        }
        
        return restrictionList;
    }

	/*
	 * Extract the aggregate functions from the query. The presence of the aggregate
	 * functions can determined if we have either "min" or "max" or "sum" or "count"
	 * or "avg" followed by opening braces"(" after "select" clause in the query
	 * string. in case it is present, then we will have to extract the same. For
	 * each aggregate functions, we need to know the following: 1. type of aggregate
	 * function(min/max/count/sum/avg) 2. field on which the aggregate function is
	 * being applied.
	 * 
	 * Please note that more than one aggregate function can be present in a query.
	 * 
	 * 
	 */
	public List<AggregateFunction> getAggregateFunctions(String queryString) {
		List<AggregateFunction>  aggregateFunction = new ArrayList<AggregateFunction>();
		
		if(queryString.contains("sum(") || queryString.contains("max(") || queryString.contains("min(") || queryString.contains("avg(") || queryString.contains("count("))
		{
			List<String> aggregrateFunction = Arrays.asList(queryString.split("select ")[1].split(" from ")[0].split("\\s*,\\s*"));
			for(String sr : aggregrateFunction)
			{
				if(sr.contains("sum(") || sr.contains("avg(") || sr.contains("count(") || sr.contains("max(") || sr.contains("min("))
				{
					String fieldName = sr.split("\\(")[1].split("\\)")[0];
					String FunctionName = sr.split("\\(")[0];
					AggregateFunction af = new AggregateFunction(fieldName,FunctionName);
					aggregateFunction.add(af);

				}
			}
		}
		else
		{
			AggregateFunction af = new AggregateFunction(null,null);
			aggregateFunction.add(af);

		}
        return aggregateFunction;
    }

	


}