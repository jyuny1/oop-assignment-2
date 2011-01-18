package CYL;

public class Movie {
	private String title;
	private String category;
	private String year;
	private String rating;
	private String plot;
	
	public Movie(String title,
				 String category,
				 String year,
				 String rating,
				 String plot){
		this.title = title;
		this.category=category;
		this.year=year;
		this.plot=plot;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getYear() {
		return year;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getRating() {
		return rating;
	}
	public void setPlot(String plot) {
		this.plot = plot;
	}
	public String getPlot() {
		return plot;
	}
	
	
}
