package com.amagana.settingsservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StreamTest {

	List<String> names = new ArrayList<>();
	List<Integer> numbers = new ArrayList<>();
	@BeforeEach
	public void setUp() {
		names = Arrays.asList("donald", "laurice", "evann", null, "chilé", null);
		numbers = Arrays.asList(20, 30, 20, 40, 56, 38);
	}
	
	@Test
	public void remove_null_value() {
		List<String> namesResult = names.stream()
				.flatMap(Stream::ofNullable)
				.toList();
		System.out.println(namesResult);
		Stream.iterate(0, n->n+2)
		     .limit(10)
		     .forEach(System.out::println);
		assertEquals(4, namesResult.size());
		assertEquals("donald", namesResult.get(0));
	}
	
	@Test
	public void shouldReturnAvgForListofNumber() {
		Optional<Integer> nb = numbers.stream()
				.collect(Collectors.maxBy(Integer::compareTo));
		System.out.println(nb);
		assertEquals(56, nb.get());
	}
	
	@Test
	public void givenStringShouldReturnCharOccurence() {
	  Map<String, Long> javatechie= countEachOccurence("ilovejavatechie");
	  Map<Character, Integer> result = new HashMap<>();
	  //result.put("i", 2);
	  assertEquals(result, javatechie);
	}
	
	public Map<String, Long> countEachOccurence(String s) {
	   Map<String, Long> str =	Arrays.stream(s.split(""))
		  .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(str);
		
		return str;
	}
	
	@Test
	public void givenStringShouldReturnCharDuplicate() {
	 List<String> javatechie= duplicateElementString("ilovejavatechie");
	 List<String> result = Arrays.asList("i", "o", "e", "a");
	  //result.put("i", 2);
	  assertEquals(result, javatechie);
	}
	
	public List<String> duplicateElementString(String stg) {
		List<String> str =	Arrays.stream(stg.split(""))
		     .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
		     .entrySet().stream()
		     .filter(x -> x.getValue() >1)
		     .map(Map.Entry::getKey)
		     .toList();
		return str;
	}
	@Test
	public void givenStringShouldReturnCharFirstNonDuplicate() {
	 List<String> javatechie= nonDuplicatFirsteElementString("ilovejavatechie");
	 List<String> result = Arrays.asList("i", "o", "e", "a");
	  //result.put("i", 2);
	  assertEquals(result, javatechie);
	}
	public List<String> nonDuplicatFirsteElementString(String stg) {
		List<String> str =	Arrays.stream(stg.split(""))
		     .collect(Collectors.groupingBy(Function.identity(),LinkedHashMap::new, Collectors.counting()))
		     .entrySet().stream()
		     .filter(x -> x.getValue() ==1) 
		     /*.findFirst()
		     .get()
		     .getKey()*/
		     .map(Map.Entry::getKey)
		     .toList();
		return str;
	}
	
	public List<String> givenStrinArraythenReturnLongest(List<String> stg) {
	List<String> str =	Stream.ofNullable(stg)
				.reduce((word1, word2)-> word1.size() > word2.size() ? word1 : word2)
				.get();
		return str;
	}
}
