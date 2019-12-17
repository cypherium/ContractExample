This is an example of flight delay insurance..

Set the insurance premium to 10

When the flight is delayed

1.Delay 2.5-3 hours compensation 100

2.Delay 3-4 hours compensation 200

3.Delay over 4 hours compensation 400

This smart contract instance opens the following interface:

1.Set insurance fee
  public static String feeSet(long fee ) 

2.User buys insurance with token
  public static String buy(long number ) 

3.User taking a flight
  public static String flight(String flightNo, long useNumber ) 
  
4.Automatically compensate all users according to flight delays (send the amount of tokens to the user)
  public static String flightResult(String flightNo, long delay )
