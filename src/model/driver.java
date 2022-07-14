package model;

import ParkingLotService.parkingLotService;

import static model.VehicleType.CAR;
import static model.VehicleType.TWOWHEELER;

public class driver {
    public static void main(String[] args) {
        parkingLotService PS = new parkingLotService();
        PS.addParkingLot(1,6,5,10,20);
        PS.addParkingLot(2,2,3,5,15);
        PS.addParkingLot(2,2,3,5,15);
        //System.out.println();
        System.out.println(PS.park(1,"123",CAR,1,"2022"));
        System.out.println(PS.park(2,"456",TWOWHEELER,1,"2022"));
        System.out.println(PS.park(1,"789",CAR,1,"2022"));

        PS.unPark(1,7,4);

        System.out.println(PS.park(1,"123",CAR,1,"2022"));

        PS.unPark(1,8,4);
        PS.unPark(2,1,5);
        PS.printHistory(1,"2022");
    }
}
