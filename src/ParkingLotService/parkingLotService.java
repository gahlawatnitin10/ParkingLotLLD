package ParkingLotService;

import model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static model.VehicleType.CAR;
import static model.VehicleType.TWOWHEELER;

public class parkingLotService {

    private Map<Integer, ParkingLot> parkingLots;

    public parkingLotService(){
        parkingLots = new HashMap<>();
    }

    public void addParkingLot(int parkingLotNumber,int TWCapacity, int CarCapacity,int TWRate, int CarRate){
        if(parkingLots.containsKey(parkingLotNumber)){
            //exception
            System.out.println("Parking lot "+parkingLotNumber+" already exists");
            return;
        }
        ParkingLot newParkingLot = new ParkingLot(TWCapacity,CarCapacity,TWRate,CarRate);
        parkingLots.put(parkingLotNumber,newParkingLot);
    }

    public int park(int parkingLotNumber, String registrationNumber, VehicleType type, int startTime,String date){
        if(!parkingLots.containsKey(parkingLotNumber)){
            //exception
            System.out.println("Invalid parking lot number");
            return -1;
        }
        Vehicle vehicle = new Vehicle(registrationNumber,type);
        ParkingLot parkingLot = parkingLots.get(parkingLotNumber);
        Slot slot;
        if(vehicle.getType()==CAR){
            slot = parkingLot.getCarSlot();
        }else{
            slot = parkingLot.getTWSlot();
        }
        slot.setTime(startTime);
        slot.setDate(date);
        parkingLot.park(vehicle,slot);
        System.out.println("Parked successfully");
        return slot.getSlotNumber();
    }

    public void unPark(int parkingLotNumber,int slotNumber,int curTime){
        if(!parkingLots.containsKey(parkingLotNumber)){
            //exception
            System.out.println("Invalid parking lot number");
            return;
        }
        ParkingLot parkingLot = parkingLots.get(parkingLotNumber);
        Slot slot = parkingLot.getSlot(slotNumber);

        int amount = curTime-slot.getTime();
        if(slot.getParkedVehicle().getType()==CAR){
            amount*=parkingLot.getCarRate();
        }else{
            amount*=parkingLot.getTWRate();
        }

        Account account = new Account(slot.getParkedVehicle(),slot.getDate(),slot.getTime(),curTime,amount);
        parkingLot.addAccount(account);
        parkingLot.unPark(slot.getParkedVehicle(),slot);
        System.out.println("unparked successfully --- amount : "+amount);

    }

    public void printHistory(int parkingLotNumber, String date){
        if(!parkingLots.containsKey(parkingLotNumber)){
            //exception
            System.out.println("Invalid parking lot number");
            return;
        }
        ParkingLot parkingLot = parkingLots.get(parkingLotNumber);
        List<Account> list = parkingLot.getHistory(date);
        for(Account account : list){
            System.out.println("vehicle regNum : "+account.getVehicle().getRegistrationNumber()+" startTime : "+account.getStartTime() +"  endTime : "+account.getEndTime()+"  amount : "+account.getAmount());
        }
    }


}
