package model;

import java.util.*;

import static model.VehicleType.CAR;

public class ParkingLot {

    private int TWCapacity;
    private  int CarCapacity;
    private Map<Integer,Slot> slots;

    private PriorityQueue<Slot> TWFreeSlots;

    private PriorityQueue<Slot> CarFreeSlots;

    private int TWRate;
    private int CarRate;

    private Map<String, List<Account>> history;

    public ParkingLot(int TWCapacity,int CarCapacity, int TWRate,int CarRate){
        this.TWCapacity = TWCapacity;
        this.CarCapacity = CarCapacity;
        this.TWRate = TWRate;
        this.CarRate = CarRate;
        this.slots = new HashMap<>();
        this.history = new HashMap<>();
        this.CarFreeSlots = new PriorityQueue<>((a,b)->a.getSlotNumber()-b.getSlotNumber());
        this.TWFreeSlots = new PriorityQueue<>((a,b)->a.getSlotNumber()-b.getSlotNumber());
        makeSlots();
    }

    private void makeSlots(){
        for(int i = 1;i<=TWCapacity;i++){
            Slot slot = new Slot(i);
            TWFreeSlots.offer(slot);
            slots.put(i,slot);
            //System.out.println("making TW slots "+i);
        }
        for(int i = TWCapacity+1;i<=CarCapacity+TWCapacity;i++){
            Slot slot = new Slot(i);
            CarFreeSlots.offer(slot);
            slots.put(i,slot);
            //System.out.println("making CAR slots "+i);
        }
    }

    public Slot park(Vehicle vehicle,Slot slot){
        slot.assignVehicle(vehicle);
        return slot;
    }

    public void unPark(Vehicle vehicle,Slot slot){
        if(vehicle.getType()==CAR){
            CarFreeSlots.offer(slot);
        }else{
            TWFreeSlots.offer(slot);
        }
        slot.unassignVehicle();
    }

    public void makeSlotFree(Slot slot, VehicleType type){
        slot.unassignVehicle();
        if(type==CAR){
            CarFreeSlots.offer(slot);
        } else {
            TWFreeSlots.offer(slot);
        }
    }

    public Slot getTWSlot(){
        if(TWFreeSlots.isEmpty()){
            return getCarSlot();
        }
        return TWFreeSlots.poll();
    }

    public Slot getCarSlot(){
        if(CarFreeSlots.isEmpty()){
            //exception
            System.out.println("parking lot full");
        }
        Slot slot = CarFreeSlots.poll();
        System.out.println("slot number"+slot.getSlotNumber());
        return slot;
    }

    public Slot getSlot(int slotNumber){
        if(!slots.containsKey(slotNumber)){
            //exception
        }
        Slot slot = slots.get(slotNumber);
        System.out.println("slot number"+slot.getSlotNumber());
        return slot;
    }

    public int getTWRate() {
        return TWRate;
    }

    public int getCarRate() {
        return CarRate;
    }

    public void addAccount(Account account){
        if(!history.containsKey(account.getDate())){
            history.put(account.getDate(),new ArrayList<>());
        }
        List<Account> list = history.get(account.getDate());
        list.add(account);
        history.put(account.getDate(),list);
    }

    public List<Account> getHistory(String date){
        return history.get(date);
    }
}
