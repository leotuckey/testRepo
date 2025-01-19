/**
 * A driver for CS1501 Project 3
 * @author    Dr. Farnan
 */
package cs1501_p3;

public class App {
	public static void main(String[] args) {
		CarsPQ cpq = new CarsPQ("cars.txt");
		Car c = new Car("5", "Ford", "Fiesta", 20, 200000, "White");
		cpq.add(c);
        cpq.updatePrice("X1U2PEJSC361L10MZ", 22);
        cpq.updatePrice("678PL45NTNWRED0RJ", 21);;
        cpq.updateMileage("5", 999);
        cpq.remove("678PL45NTNWRED0RJ");
        cpq.remove("X1U2PEJSC361L10MZ");

        cpq.remove("PUAF85WU5R6L6H1P9");
        cpq.remove("5");
        cpq.updateMileage("KLXKBRHFYFFM0M1VR", 4999);
        Car car = cpq.getLowMileage();
        System.out.println(car.getVIN() + " " + car.getMake() + " " + car.getModel() +  " " + car.getPrice() +  " " + car.getMileage() +  " " + car.getColor());

	}
}
