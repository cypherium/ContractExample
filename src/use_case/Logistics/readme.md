This is an example of logistics financing. 

The functions are: 

entering the warehouse, setting the token price, pledge financing (melting the coin), lifting the pledge, leaving the warehouse, and some query functions.

This example is more in line with the actual situation of the logistics industry, and uses the characteristics of the blockchain, involving multi-party collaboration and account mutual restraint, the use of goods to melt coins, only the pledge account can have the authority to release the pledge, tracking the number of goods, lock the situation , transportation route, etc.

Although the function is not perfect, it can continue to generate complex practical applications in the logistics industry.

This smart contract instance opens the following interface:

1. Goods into the warehouse
   String in(String name, long number )   

2. Setting Goods's token price
String setTokenPrice(String name, long tokenPrice ) 

3. Pledged goods and get some token
String pledge(String name, long number, String  addr/*the Funder*/  ) 

4. Funder confirmation
String confirmPledge(String name, String addr/*the supplier*/, long number ) 

5. Release pledge and return token
String releasePledge(String name, long number ) 

6. Goods out of the warehouse
String out(String name, long number ) 

7. Get Goods list Info
String getGoodsInfo(String name ) 
 