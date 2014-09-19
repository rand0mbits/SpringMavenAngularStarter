package com.company.myproject.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.company.myproject.messaging.MessageCollection;


public class Helper {
	private static final Logger LOGGER = Logger.getLogger(Helper.class);
	private static final String CONTENT_TYPE="Content-Type";
	private static final String CONTENT_LENGTH="Content-Length";
	private static final String ACCEPT_RANGES="Accept-Ranges";

	
	@SuppressWarnings({ "unchecked" })
	public static <K, V> Map<K,V> listToLookupMap(List<V> list, String keyMethod, String valMethod) {
		Map<K, V> map;
		
		if(isEmpty(list)) {
			return Collections.<K,V>emptyMap();
		}
		
		map = new LinkedHashMap<K, V>(list.size());
		
		Class<? extends Object> cls = list.get(0).getClass();
		try {
			Method keyMeth = cls.getMethod(keyMethod);
			Method valMeth = cls.getMethod(valMethod);
			
			for(Object obj : list) {
				map.put((K)keyMeth.invoke(obj), (V)valMeth.invoke(obj));
			}
		} catch(Exception e) {
			LOGGER.error("Error transforming list to map.", e);
		}
		
		return map;
	}
	
	/**
	 * Transforms a list to a map of list values mapped by keyMethod
	 * @param list
	 * @param keyMethod
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static <K, V> Map<K,V> listToMap(List<V> list, String keyMethod) {
		Map<K, V> map;
		
		if(isEmpty(list)) {
			return Collections.<K,V>emptyMap();
		}
		
		map = new LinkedHashMap<K, V>(list.size());
		
		Class<? extends Object> cls = list.get(0).getClass();
		try {
			Method meth = cls.getMethod(keyMethod);
			
			for (Object obj : list) {
				map.put((K)meth.invoke(obj), (V) obj);
			}
			
			return map;
		} catch (Exception e) {
			LOGGER.error("Error transforming list to map.", e);
		} 
		
		return map;
	}
	
	/**
	 * Turns a list with objects of type O to a list of objects of type P, 
	 * with P being a property of object O returned by executing keyMethod on it.
	 * @param collection
	 * @param keyMethod
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public static <O,P> List<P> collectionOfObjectsToListOfProperties(Collection<O> collection, String keyMethod) {
        
        if(isEmpty(collection)) {
        	return Collections.<P>emptyList();
        }
        
        Class<? extends Object> cls = collection.iterator().next().getClass();
        try {
            Method meth = cls.getMethod(keyMethod);
            List<P> outList = new ArrayList<P>();
            
            for (Object obj : collection) {
                outList.add((P)meth.invoke(obj));
            }
            
            return outList;
        } catch (Exception e) {
            LOGGER.error("Error transforming list of objects to list of properties.", e);
        } 
        
        return Collections.emptyList();
    }
	
	/**
	 * Transforms a list to a map of lists of list values grouped by keyMethod
	 * @param list
	 * @param keyMethod
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static <K, V> Map<K,List<V>> listToMapOfLists(List<V> list, String keyMethod) {
		Map<K, List<V>> map;
		
		if(isEmpty(list)) {
			return Collections.<K, List<V>>emptyMap();
		}
		
		map = new LinkedHashMap<K, List<V>>(list.size());
		
		Class<? extends Object> cls = list.get(0).getClass();
		try {
			Method meth = cls.getMethod(keyMethod);
			
			for (Object obj : list) {
				K key = (K) meth.invoke(obj);
				if (!map.containsKey(key)) {
					map.put(key, new ArrayList<V>());
				}
				map.get(key).add((V) obj);
			}
			
			return map;
		} catch (Exception e) {
			LOGGER.error("Error transforming list to map of lists.", e);
		} 
		
		return map;
	}
	
	public static <K> K getFromCollectionById(Collection<K> list, String idMethod, Integer targetId){
	    if(isEmpty(list)) {
	    	return null;
	    }
	    Method getter = null;
	    try{
    	    for(K item : list){
    	       if(getter == null){
    	           Class<?> cls = item.getClass();
    	           getter = cls.getMethod(idMethod);
    	       }
    	       
    	       Integer id = (Integer) getter.invoke(item);
    	       if(targetId.equals(id)){
    	           return item;
    	       }
    	    }
	    } catch(Exception e){
            LOGGER.error(e);
        }
	    return null;
    }
	
	public static <K> Map<K,Integer> arrayToIndexMap(K[] array) {
	    Map<K,Integer> indexMap = new HashMap<K, Integer>();
	    for(int i = 0; i<array.length; i++){
	        indexMap.put(array[i], i);
	    }
	    return indexMap;
    }
	
	public static Method getMethodSafe(Class<?> cls, String methodName, Class<?> ... parameterTypes){
	    try{
            return cls.getMethod(methodName, parameterTypes);
        } catch(NoSuchMethodException e) {
            LOGGER.error(e);
        } catch(SecurityException e) {
            LOGGER.error(e);
        }
	    return null;
	}
	
	public static int[] createRange(int start, int end, int step){
	    if(end < start) {
	    	return new int[0];
	    }
	    
        int[] range = new int[(end-start)/step + 1];
        int index = 0;
        for(int i=start; i <= end; i+= step){
            range[index++] = i;
        }
        return range;
    }
	public static int[] createRange(int start, int end){
        return createRange(start, end, 1);
    }
	
	public static String[] createStringRange(int start, int end, int step){
	    if(end < start) {
	    	return new String[0];
	    }
	    
	    String[] range = new String[(end-start)/step + 1];
	    int index = 0;
        for(int i=start; i <= end; i+= step){
            range[index++] = Integer.toString(i);
        }
        return range;
    }
	public static String[] createStringRange(int start, int end){
	    return createStringRange(start, end, 1);
	}
	/**
     * Converts Integer to int, handling null values gracefully
     * @param value Integer to convert
     * @return if null 0, otherwise Integer.intValue()
     */
    public static int getSafe(Integer value){
        if(value == null) {
        	return 0;
        }
        return value.intValue();
    }
    /**
     * Converts Double to double, handling null values gracefully
     * @param value Double to convert
     * @return if null 0, otherwise Double.doubleValue()
     */
    public static double getSafe(Double value){
        if(value == null) {
        	return 0.0;
        }
        return value.doubleValue();
    }
    /**
     * Converts Short to int, handling null values gracefully
     * @param value Short to convert
     * @return if null 0, otherwise Short.intValue()
     */
    public static int getSafe(Short value){
        if(value == null) {
        	return 0;
        }
        return value.intValue();
    }
	/**
	 * @return An empty String if the input is null, otherwise the input
	 */
	public static String getSafe(String value){
	    if(value == null) {
	    	return "";
	    }
	    return value;
	}
	
	/**
	 * @param list
	 * @return the first element of the list if the list is not null and not empty, otherwise null
	 */
	public static <T> T getSafeListFirstElement(List<T> list) {
	    if (!isEmpty(list)) {
	        return list.get(0);
	    }
	    return null;
	}
	
	/**
     * Converts Short to String, handling null values gracefully
     * @param value Short to convert
     * @return if null "", otherwise Short.intValue() as string
     */
    public static String toString(Short value){
        if(value == null) {
        	return "";
        }
        return Integer.toString(value.intValue());
    }
    /**
     * Converts Int to String, handling null values gracefully
     * @param value Short to convert
     * @return if null "", otherwise Short.intValue() as string
     */
    public static String toString(Integer value){
        if(value == null) {
        	return "";
        }
        return Integer.toString(value.intValue());
    }
	
    /**
     * Checks if a string is empty.
     * @param value
     * @return true if the string is null or its trimmed length is 0, false otherwise.
     */
	public static boolean isEmpty(String value){
	    return value == null || value.trim().length() == 0;
	}
	
	/**
     * Checks if a collection is empty.
     * @param collection
     * @return true if the collection is null or its length is 0, false otherwise.
     */
	public static boolean isEmpty(Collection<?> collection){
	    return collection == null || collection.isEmpty();
	}
	
	/**
	 * Safely check if a Boolean is true
	 * @param bool
	 * @return true if the Boolean is not null and is true, false otherwise.
	 */
	public static boolean isTrue(Boolean bool) {
		return bool != null && bool;
	}
	
	public static String join(String value1, String value2, String joiner){
	    if(value1 != null && value1.trim().length() > 0){
	        if(value2 != null && value2.trim().length() > 0){
	            return value1 + joiner + value2;
	        }
	        return value1;
	    }else if(value1 == null && value2 == null){
	        return "";
	    }
	    return value2;
	}
	
	public static String asHtml(String plainText){
	    if(plainText == null) {
	    	return null;
	    }
	    
	    StringBuilder html = new StringBuilder();
	    String[] paragraphs = plainText.split("\n");
	    for(String p : paragraphs){
	        html.append("<p>");
	        html.append(p);
	        html.append("</p>");
	    }
	    return html.toString();
	}
	
	public static double dateDiff(Date earlierDate, Date laterDate){
    	if(earlierDate != null && laterDate != null) {
            return (double) ((laterDate.getTime() - earlierDate.getTime())/ 1000 / 3600 / 24 / 365.2425);
    	} else {
    		return 0;
    	}
    }
	
	public static int getYearsBetween(Date earlierDate, Date laterDate) {
	    return Years.yearsBetween(new LocalDate(earlierDate), new LocalDate(laterDate)).getYears();
	}
	
	public static boolean isAutosaved(HttpServletRequest request){
		return "true".equalsIgnoreCase(request.getParameter("autosave"));
	}
	
	public static int parseInt(String input, int defaultValue){
	    if(input == null) {
	    	return defaultValue;
	    }
	    try{
            return Integer.parseInt(input);
        }catch(NumberFormatException e){
            return defaultValue;
        }
    }
    public static int parseInt(String input){
        return parseInt(input, 0);
    }
	public static double parseDouble(String input, double defaultValue){
	    if (input == null) {
	    	return defaultValue;
	    }
	    try{
	        return Double.parseDouble(input);
	    } catch(NumberFormatException e){
	        return defaultValue;
	    }
	}
	public static double parseDouble(String input){
	    return parseDouble(input, 0.0);
	}
	
	public static String toProperCase(String word){
	    return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
	}
	public static String underscoreToCamelCase(String underscore){
	    String[] words = underscore.split("_");
	    StringBuilder camel = new StringBuilder(words[0].toLowerCase());
	    for(int i=1; i<words.length;i++){
	        camel.append(toProperCase(words[i]));
	    }
	    return camel.toString();
	}
	
    public static int roundToNearestMultiple(int num, int multipleOf){
        int diff = num % multipleOf;
        if(diff < 3){
            return num - diff;
        } else {
            return num + 5 - diff;
        }
    }
    
    public static String joinWithAnd(String[] values){
        if(values == null || values.length == 0){
            return "";
        }
        int max = values.length - 1;
        StringBuilder out = new StringBuilder(values[0]);
        for(int i=1;i<=max;i++){
            if(i == max){
                out.append(" and ");
            }else{
                out.append(", ");
            }
            out.append(values[i]);
        }
        
        return out.toString();
    }
    
    public static boolean areEqual(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        } else {
            return obj1.equals(obj2);
        }
    }
    
    public static boolean doublesEqual(double a, double b, double epsilon){
        return a >= (b - epsilon) && a <= (b + epsilon);
    }
    public static boolean doublesEqual(double a, double b){
        return doublesEqual(a, b, 0.0000001);
    }
    
    public static Context getEnvironmentContext() {
        try{
            return (Context) (new InitialContext()).lookup("java:comp/env");
        } catch(NamingException e){
            LOGGER.error("Unable to retrieve environment context", e);
            return null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> arraysToList(T[]... arrays) {
        List<T> list = new ArrayList<T>();
        for (T[] array : arrays) {
            for (T el : array) {
                list.add(el);
            }
        }
        return list;
    }
    
    /**
     * Sets appropriate response headers based on the messages contained in MessageCollection
     * @param messages
     * @param response
     */
    public static void setResponseMessageHeaders(MessageCollection messages, HttpServletResponse response) {
    	if (messages.hasErrors()) {
			response.addHeader("action-status", "error");
			response.addHeader("halt-success", "true");
		} else if (messages.hasPrompts()) {
    		response.addHeader("action-status", "prompt");
			response.addHeader("halt-success", "true");
    	}
    }
    
    public static boolean isConfirmedRequest(HttpServletRequest request) {
    	return "true".equals(request.getParameter("confirm"));
    }
       
    public static void setHeadersForFileDownload(String mimeType, String fileName, Integer fileLength, HttpServletResponse response){
		response.setHeader(CONTENT_TYPE, mimeType);
        response.setHeader("Content-Disposition", "inline; filename=\""+fileName+"\"");
        if(fileLength != null){
            response.setHeader(CONTENT_LENGTH, Integer.toString(fileLength));
            response.setHeader(ACCEPT_RANGES, "bytes");
        }
	}
	
	/**
	 * Returns trimmed string if not null, null otherwise.
	 * @param string
	 * @return
	 */
	public static String safeTrim(String string) {
		return string == null ? null : string.trim();
	}

}

