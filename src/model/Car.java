package model;

import static model.VehicleType.CAR;

public class Car extends Vehicle{

    public Car(String registrationNumber){
        super(registrationNumber,CAR);
    }

}
