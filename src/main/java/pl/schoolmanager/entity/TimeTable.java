package pl.schoolmanager.entity;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TimeTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private LocalTime start;
	
	private Map<Integer, Integer> breaks = new HashMap<>();
	
	
}
