/**
 * Car class for CS1501 Project 3
 * @author    Leo Tuckey
 */
package cs1501_p3;

public class Car implements Car_Inter {

    private String vin;
    private String carMake;
    private String carModel;
    private int price;
    private int mileage;
    private String color;

    public Car(String vin, String carMake, String carModel, int price, int mileage, String color) {
        this.vin = vin;
        this.carMake = carMake;
        this.carModel = carModel;
        this.price = price;
        this.mileage = mileage;
        this.color = color;
    }

    /**
     * Getter for the VIN attribute
     *
     * @return String The VIN
     */
    public String getVIN() {
        return this.vin;
    }

    /**
     * Getter for the make attribute
     *
     * @return String The make
     */
    public String getMake() {
        return this.carMake;
    }

    /**
     * Getter for the model attribute
     *
     * @return String The model
     */
    public String getModel() {
        return this.carModel;
    }

    /**
     * Getter for the price attribute
     *
     * @return String The price
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Getter for the mileage attribute
     *
     * @return String The mileage
     */
    public int getMileage() {
        return this.mileage;
    }

    /**
     * Getter for the color attribute
     *
     * @return String The color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Setter for the price attribute
     *
     * @param newPrice The new Price
     */
    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    /**
     * Setter for the mileage attribute
     *
     * @param newMileage The new Mileage
     */
    public void setMileage(int newMileage) {
        this.mileage = newMileage;
    }

    /**
     * Setter for the color attribute
     *
     * @param newColor The new color
     */
    public void setColor(String newColor) {
        this.color = newColor;
    }

}