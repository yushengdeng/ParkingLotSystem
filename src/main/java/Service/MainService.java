package Service;

import Module.Car;
import Module.ParkingLot;
import Module.Slot;

import java.util.ArrayList;

import static Constant.MainConstant.*;

public class MainService {

    private static ParkingLot parkingLot = null;

    public static ParkingLot createParkingLot(String slotsNumber){
        parkingLot = new ParkingLot(Integer.parseInt(slotsNumber));
        return parkingLot;
    }

    public static Integer parkCar(String registrationNumber, String color) throws Exception{
        if(parkingLot == null) {
            throw new Exception("There is no parking lot created.");
        }

        ArrayList<Slot> slots = parkingLot.getSlots();
        for(Slot slot : slots){ // Foreach loop will get slot by the order we put in the list.
            if(slot.getCar() == null){ // Check if the slot is still available.
                Car car = new Car(registrationNumber, Car.Color.valueOf(color));
                slot.setCar(car);
                return slot.getNumber();
            }
        }
        return null;
    }

    public static void leaveCar(String slotNumber) throws Exception{
        if(parkingLot == null) {
            throw new Exception("There is no parking lot created.");
        }

        ArrayList<Slot> slots = parkingLot.getSlots();
        Slot slot = slots.get(Integer.parseInt(slotNumber));
        slot.setCar(null);
    }

    public static String checkStatus() throws Exception{
        if(parkingLot == null) {
            throw new Exception("There is no parking lot created.");
        }

        StringBuilder status = new StringBuilder("Slot No.");
        status.append(SHORT_SPACE).append("Registration No.").append(SHORT_SPACE).append("Colour").append(NEW_LINE);

        ArrayList<Slot> slots = parkingLot.getSlots();
        for(Slot slot : slots){
            if(slot.getCar() != null){
                int slotNumber = slot.getNumber().intValue();
                Car car = (Car) slot.getCar();
                String registrationNumber = car.getRegistrationNumber();
                Car.Color color = car.getColor();
                status.append(slotNumber).append(LONG_SPACE).append(registrationNumber).append(SHORT_SPACE).append(color).append(NEW_LINE);
            }
        }
        return status.toString();
    }

    public static String[] getRegistrationNumbersByColor(String color) throws Exception{
        if(parkingLot == null) {
            throw new Exception("There is no parking lot created.");
        }

        Car.Color enumColor = Car.Color.valueOf(color);
        ArrayList<Slot> slots = parkingLot.getSlots();
        ArrayList<String> registrationNumbers = new ArrayList<String>();

        for(Slot slot : slots){
            Car car = (Car) slot.getCar();
            if(enumColor.equals(car.getColor())){
                registrationNumbers.add(car.getRegistrationNumber());
            }
        }
        return registrationNumbers.toArray(new String[]{});
    }

    public static Integer[] getSlotNumbersByColor(String color) throws Exception{
        if(parkingLot == null) {
            throw new Exception("There is no parking lot created.");
        }

        Car.Color enumColor = Car.Color.valueOf(color);
        ArrayList<Slot> slots = parkingLot.getSlots();
        ArrayList<Integer> slotNumbers = new ArrayList<Integer>();

        for(Slot slot : slots){
            Car car = (Car) slot.getCar();
            if(enumColor.equals(car.getColor())){
                slotNumbers.add(slot.getNumber());
            }
        }
        return slotNumbers.toArray(new Integer[]{});
    }

    public static Integer getSlotNumberByRegistrationNumber(String registrationNumber) throws Exception{
        if(parkingLot == null) {
            throw new Exception("There is no parking lot created.");
        }

        ArrayList<Slot> slots = parkingLot.getSlots();

        for(Slot slot : slots){
            Car car = (Car) slot.getCar();
            if(registrationNumber.equals(car.getRegistrationNumber())){
                return slot.getNumber();
            }
        }
        return null;
    }

}
