/**
 * CarsPQ class for CS1501 Project 3
 * @author    Leo Tuckey
 */
package cs1501_p3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;

public class CarsPQ implements CarsPQ_Inter {

    private PQ priceHeap;
    private PQ mileageHeap;
    private IndirectionDLB indirectionDataStructure;
    private SpecificMakeModelDLB stringConcatenationMap;

    public CarsPQ(String fileName) {
        this.priceHeap = new PQ();
        this.mileageHeap = new PQ();
        this.indirectionDataStructure = new IndirectionDLB();
        this.stringConcatenationMap = new SpecificMakeModelDLB();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    line = br.readLine();
                    continue;
                }
                String[] parameters = line.split(":");
                Car car = new Car(parameters[0], parameters[1], parameters[2], Integer.parseInt(parameters[3]),
                        Integer.parseInt(parameters[4]), parameters[5]);
                //debug
                //System.out.println(car.getVIN());
                // if(car.getVIN().equals("SXAWE12X5ZRT8W85R")) {
                //     System.out.println("bruh");
                // }
                //debug
                this.add(car);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new Car to the data structure
     * Should throw an `IllegalStateException` if there is already car with the
     * same VIN in the datastructure.
     *
     * @param c Car to be added to the data structure
     */
    public void add(Car c) throws IllegalStateException {
        if (this.indirectionDataStructure.getNode(c.getVIN()) != null) {
            throw new IllegalStateException("There already exists a car with this VIN.");
        } else {
            int priceHeapIndex = this.priceHeap.add(new HeapElement(c.getVIN(), c.getPrice()),
                    this.indirectionDataStructure, 1);
            int mileageHeapIndex = this.mileageHeap
                    .add(new HeapElement(c.getVIN(), c.getMileage()), this.indirectionDataStructure, 2);
            SpecificMakeModelDLBNode node = this.stringConcatenationMap.getNode(c.getMake() + c.getModel());
            int specificPriceHeapIndex = node.specificPriceHeap.add(new HeapElement(c.getVIN(), c.getPrice()),
                    this.indirectionDataStructure, 3);
            int specificMileageHeapIndex = node.specificMileageHeap.add(new HeapElement(c.getVIN(), c.getMileage()),
                    this.indirectionDataStructure, 4);
            this.indirectionDataStructure.add(c, priceHeapIndex, mileageHeapIndex, specificPriceHeapIndex,
                    specificMileageHeapIndex);
        }
    }

    /**
     * Retrieve a new Car from the data structure
     * Should throw a `NoSuchElementException` if there is no car with the
     * specified VIN in the datastructure.
     *
     * @param vin VIN number of the car to be updated
     */
    public Car get(String vin) throws NoSuchElementException {
        if (this.indirectionDataStructure.getNode(vin) == null) {
            throw new NoSuchElementException("There is no such car with this VIN.");
        } else {
            return this.indirectionDataStructure.getNode(vin).car;
        }
    }

    /**
     * Update the price attribute of a given car
     * Should throw a `NoSuchElementException` if there is no car with the
     * specified VIN in the datastructure.
     *
     * @param vin      VIN number of the car to be updated
     * @param newPrice The updated price value
     */
    public void updatePrice(String vin, int newPrice) throws NoSuchElementException {
        if (this.indirectionDataStructure.getNode(vin) == null) {
            throw new NoSuchElementException("There is no such car with this VIN.");
        } else {
            IndirectionDLBNode indirectionNode = this.indirectionDataStructure.getNode(vin);
            SpecificMakeModelDLBNode specificNode = this.stringConcatenationMap
                    .getNode(indirectionNode.car.getMake() + indirectionNode.car.getModel());
            specificNode.specificPriceHeap.update(indirectionNode.specificPriceHeapIndex, newPrice,
                    this.indirectionDataStructure, 3);
            this.priceHeap.update(indirectionNode.priceHeapIndex, newPrice, this.indirectionDataStructure, 1);
            indirectionNode.car.setPrice(newPrice);
        }
    }

    /**
     * Update the mileage attribute of a given car
     * Should throw a `NoSuchElementException` if there is not car with the
     * specified VIN in the datastructure.
     *
     * @param vin        VIN number of the car to be updated
     * @param newMileage The updated mileage value
     */
    public void updateMileage(String vin, int newMileage) throws NoSuchElementException {
        if (this.indirectionDataStructure.getNode(vin) == null) {
            throw new NoSuchElementException("There is no such car with this VIN.");
        } else {
            IndirectionDLBNode indirectionNode = this.indirectionDataStructure.getNode(vin);
            SpecificMakeModelDLBNode specificNode = this.stringConcatenationMap
                    .getNode(indirectionNode.car.getMake() + indirectionNode.car.getModel());
            specificNode.specificMileageHeap.update(indirectionNode.specificMileageHeapIndex, newMileage,
                    this.indirectionDataStructure, 4);
            this.mileageHeap.update(indirectionNode.mileageHeapIndex, newMileage, this.indirectionDataStructure, 2);
            indirectionNode.car.setMileage(newMileage);
        }
    }

    /**
     * Update the color attribute of a given car
     * Should throw a `NoSuchElementException` if there is not car with the
     * specified VIN in the datastructure.
     *
     * @param vin      VIN number of the car to be updated
     * @param newColor The updated color value
     */
    public void updateColor(String vin, String newColor) throws NoSuchElementException {
        if (this.indirectionDataStructure.getNode(vin) == null) {
            throw new NoSuchElementException("There is no such car with this VIN.");
        } else {
            this.indirectionDataStructure.getNode(vin).car.setColor(newColor);
        }
    }

    /**
     * Remove a car from the data structure
     * Should throw a `NoSuchElementException` if there is not car with the
     * specified VIN in the datastructure.
     *
     * @param vin VIN number of the car to be removed
     */
    public void remove(String vin) throws NoSuchElementException {
        if (this.indirectionDataStructure.getNode(vin) == null) {
            throw new NoSuchElementException("There is no such car with this VIN.");
        } else {
            IndirectionDLBNode indirectionNode = this.indirectionDataStructure.getNode(vin);
            this.priceHeap.delete(indirectionNode.priceHeapIndex, this.indirectionDataStructure, 1);
            this.mileageHeap.delete(indirectionNode.mileageHeapIndex, this.indirectionDataStructure, 2);
            SpecificMakeModelDLBNode specificNode = this.stringConcatenationMap
                    .getNode(indirectionNode.car.getMake() + indirectionNode.car.getModel());
            specificNode.specificPriceHeap.delete(indirectionNode.specificPriceHeapIndex, this.indirectionDataStructure,
                    3);
            specificNode.specificMileageHeap.delete(indirectionNode.specificMileageHeapIndex,
                    this.indirectionDataStructure, 4);
            this.indirectionDataStructure.delete(vin);
        }
    }

    /**
     * Get the lowest priced car (across all makes and models)
     * Should return `null` if the data structure is empty
     *
     * @return Car object representing the lowest priced car
     */
    public Car getLowPrice() {
        if(this.priceHeap.heapArr[0]==null) {
            return null;
        }
        return (this.indirectionDataStructure.getNode(this.priceHeap.heapArr[0].vin)).car;
    }

    /**
     * Get the lowest priced car of a given make and model
     * Should return `null` if the data structure is empty
     *
     * @param make  The specified make
     * @param model The specified model
     * 
     * @return Car object representing the lowest priced car
     */
    public Car getLowPrice(String make, String model) {
        SpecificMakeModelDLBNode specificNode = this.stringConcatenationMap.getNode(make + model);
        if(specificNode.specificPriceHeap.heapArr[0]==null) {
            return null;
        }
        return (this.indirectionDataStructure.getNode(specificNode.specificPriceHeap.heapArr[0].vin)).car;
    }

    /**
     * Get the car with the lowest mileage (across all makes and models)
     * Should return `null` if the data structure is empty
     *
     * @return Car object representing the lowest mileage car
     */
    public Car getLowMileage() {
        if(this.mileageHeap.heapArr[0]==null) {
            return null;
        }
        return (this.indirectionDataStructure.getNode(this.mileageHeap.heapArr[0].vin)).car;
    }

    /**
     * Get the car with the lowest mileage of a given make and model
     * Should return `null` if the data structure is empty
     *
     * @param make  The specified make
     * @param model The specified model
     *
     * @return Car object representing the lowest mileage car
     */
    public Car getLowMileage(String make, String model) {
        SpecificMakeModelDLBNode specificNode = this.stringConcatenationMap.getNode(make + model);
        if(specificNode.specificMileageHeap.heapArr[0]==null) {
            return null;
        }
        return (this.indirectionDataStructure.getNode(specificNode.specificMileageHeap.heapArr[0].vin)).car;
    }
}