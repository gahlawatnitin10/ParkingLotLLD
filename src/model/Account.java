package model;

public class Account {

    private Vehicle vehicle;
    private String date;
    private int startTime;
    private int endTime;
    private int amount;

    public Account(Vehicle vehicle,String date, int startTime, int endTime, int amount){
        this.vehicle = vehicle;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amount = amount;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getDate() {
        return date;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getAmount() {
        return amount;
    }
}
