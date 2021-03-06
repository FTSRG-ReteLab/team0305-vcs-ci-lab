package hu.bme.mit.train.controller;

import java.util.Date;
import java.util.Timer;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import hu.bme.mit.train.interfaces.TrainController;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	private Table<Date, Integer, Integer> tacho = HashBasedTable.create();
	
	@Override
	public void followSpeed() {

		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}

		enforceSpeedLimit();
	}

	public void startFollowSpeed() throws InterruptedException
	{
		while(true)
		{
			Thread.sleep(100);
			followSpeed();
		}
	}
	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.tacho.put(new Date(), joystickPosition, this.referenceSpeed);
		this.step = joystickPosition;		
	}
	@Override
		public Table<Date, Integer, Integer> getTacho() {
			return tacho;
		}

}
