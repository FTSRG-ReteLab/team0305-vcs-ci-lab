package hu.bme.mit.train.interfaces;

import java.util.Date;

import com.google.common.collect.Table;

public interface TrainController {

	void followSpeed();

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);
	
	Table<Date, Integer, Integer> getTacho();

}
