package com.storehouse.service;

import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;
import com.storehouse.model.Container;
import com.storehouse.model.FullnessMeasurement;

@Service
public class FullnessMeasurementImitatorImpl implements FullnessMeasurementImitator {
	
	@Override
	public FullnessMeasurement nextMeasurement() {
		ThreadLocalRandom tlr = ThreadLocalRandom.current();
		Container container = new Container(tlr.nextInt(1, 5), "Apple", "Kilo", 1000);
		return new FullnessMeasurement(container, tlr.nextInt(1, 100));
	}

}
