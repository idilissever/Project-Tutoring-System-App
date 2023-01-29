package course;

/**
 * Stores the name, price and the profit percentage of the equipment.
 * 
 * @author idilissever
 *
 */
public class Equipment {

	private String name;
	private double price;
	private double profitPercantage;

	public Equipment(String name, double price, double profitPercantage) {
		this.name = name;
		this.price = price;
		this.profitPercantage = profitPercantage;
	}

	public double getProfit() {
		return profitPercantage * price * 0.01;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getProfitPercantage() {
		return profitPercantage;
	}

	public void setProfitPercantage(double profitPercantage) {
		this.profitPercantage = profitPercantage;
	}

}
