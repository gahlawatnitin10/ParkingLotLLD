package model;

public class Slot {

    private Vehicle parkedVehicle;
    private int slotNumber;

    private String date;
    private int time;
    public Slot(int slotNumber){
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isSlotFree(){
        return parkedVehicle == null;
    }

    public void assignVehicle(Vehicle vehicle){
        this.parkedVehicle = vehicle;
    }

    public void unassignVehicle(){
        this.parkedVehicle = null;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
