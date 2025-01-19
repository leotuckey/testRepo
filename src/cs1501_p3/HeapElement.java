/**
 * Class for the elements in the heap arrays for CS1501 Project 3
 * @author    Leo Tuckey
 */
package cs1501_p3;

public class HeapElement {

    public String vin;
    public int metric; // can be price or mileage

    public HeapElement(String vin, int metric) {
        this.vin = vin;
        this.metric = metric;
    }
}