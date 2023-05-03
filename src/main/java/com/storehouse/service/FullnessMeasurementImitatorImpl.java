package com.storehouse.service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.storehouse.model.Container;
import com.storehouse.model.FullnessMeasurement;

@Service
public class FullnessMeasurementImitatorImpl implements FullnessMeasurementImitator {
	
	@Value("${app.amount.containers: 10}")
	int nContainers;
	@Value("${app.min.percent: 0}")
	int minPercent; 
	@Value("${app.max.percent: 100}")
	int maxPercent;
	ThreadLocalRandom tlr = ThreadLocalRandom.current();
	HashMap<Integer, Integer> containersStatus = new HashMap<>();
	
	@Override
	public FullnessMeasurement nextMeasurement() {
		Container container = getContainer(tlr.nextInt(1, nContainers));
		int status = getStatus(container.number);
		return new FullnessMeasurement(container, status);
	}

	private int getStatus(int number) {
		Integer value = containersStatus.get(number);
		return value == null ? tlr.nextInt(minPercent, maxPercent) : getNewStatus(number);
	}

	private int getNewStatus(int number) {
		return tlr.nextInt(minPercent, containersStatus.get(number));
	}

	private Container getContainer(int number) {
		return new Container(number, "Apple", "Kilo", 1000);
	}

}
