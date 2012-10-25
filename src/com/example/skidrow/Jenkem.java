package com.example.skidrow;

public class Jenkem extends Good{
	
	public Jenkem()
	{
		super(5000, 6, 4, 7, -150, 100);
	}

	@Override
	int getBasePrice(String event) 
	{
		 if (event.equals("BOREDOM"))
		 {
			 return basePrice * randomGen.nextInt(5)+1;
		 }
		 else{
			 return basePrice;
		 }
	}

}
