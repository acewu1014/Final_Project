public class Keyword {
	public String name;
	public int count;
	public double weight;
	
	public Keyword(String name, double weight){
		this.name = name;
		this.weight = weight;
	}
	public void setCount(int count) {
		this.count=count;
	}
	
	public String toString(){
		return "[" + name +","+ count + "]" ;
	}
	public String getName() {
		return this.name;
	}


}