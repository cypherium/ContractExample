# Cypherium Java Smart Contract Tutorial
```html
span style =“color：green”>go< / span>
```

## Run your cypher Node accord to the README file begin,update the txBlockNumber and keyBlockNumber to the latest number.
[CypherTestBin](https://github.com/cypherium/CypherTestBin/blob/master/README.md) Tutorial for run cypher Node

## Environmental configuration

Cypherium’s Java Smart Contract system need JDK1.8 support.So Please install JDK1.8. To learn how to configure the environment of JDK1.8, refer to Java’s online literature.

After you have configured your JDK environment, execute javac --version in the Console environment to confirm whcpher it is 1.8.xxx.Below install commands is for ubuntu:

```
sudo apt install openjdk-8-jre-headless -y
sudo apt install openjdk-8-jdk-headless
javac -version

```
Download repository

 ```
  git clone https://github.com/cypherium/ContractExample.git

 ```

## Write and Compile smart contract
Before you want to deploy smart contract,you should confirm your account'cosin is enough (1000000000000000000 around),
if no,please take part in pow work to become a committee member to get reward or contact us to transfer some to you.
 ```
 cph.getBalance(cph.accounts[0])//cph.getBalance("0x461f9d24b10edca41c1d9296f971c5c028e6c64c")
 ```
### Ok,as all the ready word has been done,we can create one simple  smart contract as follows:

```
import javax.cypher.Cypnet;
import mjson.Json;

public class _cypher_contract {

	static Json defj = Json.object();

    public static void main(String[] args) {
        long totalSupply = 10000;
	    System.out.println("main");
		//Set token information
		Cypnet.setTokenInfo("HR", "HR Coin", totalSupply, "" );
       // Cypnet.changeBalance("caller", totalSupply);

	}
	......
```
Caution:please do not edit the `_cypher_contract` class name
### Compile _cypher_contract.java and execute the following command:

```
 cd ContractExample/src/cases/HR/
 javac -cp ../../jdk/classes _cypher_contract.java
```

A _cypher_contract.class will be generated in the current directory.

## Deploy your smart contract

In order to deploy your newly generated `_cypher_contract.class` on the Cypherium blockchain network, you need to convert the binary file into string format. We provide one file2str tool to complete this step (similar tools on the network are also available):

We provide executable files for Linux, Mac platforms, and users can select the target type according to your OS systems.

### Generate string format
Suppose you local at `src` folder and you OS are linux,Below is the usage:

```
 ../../../file2str/linux/file2str _cypher_contract.class
```

![](./smart_contract_file2str.png)

### Deploy smart contract by cypher console

 #### Eidt abi and Defines a contract class
```
abi=[{"inputs":[{"name":"initialSupply","type":"uint256"},{"name":"tokenName","type":"string"},{"name":"tokenSymbol","type":"string"}],"stateMutability":"nonpayable","typ":"constructor"}	]
sample=cph.contract(abi)
```
#### Set the java  smart contract bin to the SampleHEX variable
```
SampleHEX="0xcafebabe0000003400a70a0032005405000000000000271009005500560800390a0057005808005908005a08005b0a005c005d0a005e005f0a005e00600700610a000d00540800620a000d00630a000d006408006508006608006708006808006908006a0a0031006b0a006c006d0a003200640a005c006e08006f0800700800710800720a005c00730a005c007408007508007608007708007808007908007a08007b0a005e007c0a006c007d0a006c007e07007f09003100800a003200810a006c00820a006c00830700840700850100046465666a01000c4c6d6a736f6e2f4a736f6e3b0100063c696e69743e010003282956010004436f646501000f4c696e654e756d6265725461626c650100046d61696e010016285b4c6a6176612f6c616e672f537472696e673b29560100087365744163746f7201004a284c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f537472696e673b294c6a6176612f6c616e672f537472696e673b01000d537461636b4d61705461626c65070086010008726567697374657201006e284c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f537472696e673b294c6a6176612f6c616e672f537472696e673b010009656475636174696f6e01005c284c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f537472696e673b294c6a6176612f6c616e672f537472696e673b0100057072697a65010005656e7472790100056c656176650100097265636f6d6d656e64010038284c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f537472696e673b294c6a6176612f6c616e672f537472696e673b010006726573756d6501000c67657453746174654a736f6e010020284c6a6176612f6c616e672f537472696e673b294c6d6a736f6e2f4a736f6e3b07008707007f01000d6765744a736f6e56616c756553010032284c6d6a736f6e2f4a736f6e3b4c6a6176612f6c616e672f537472696e673b294c6a6176612f6c616e672f537472696e673b01000867657456616c7565010026284c6a6176612f6c616e672f537472696e673b294c6a6176612f6c616e672f537472696e673b0100083c636c696e69743e01000a536f7572636546696c650100155f6379706865725f636f6e74726163742e6a6176610c003500360700880c0089008a07008b0c008c008d0100024852010007485220436f696e01000007008e0c008f00900700860c009100920c009300940100176a6176612f6c616e672f537472696e674275696c646572010005616464723a0c009500960c00970094010007636f6d70616e7901000b4048525f636f6d70616e790100077465616368657201000b4048525f746561636865720100067072697a657201000a4048525f7072697a65720c0049004a0700870c009800990c009a009b0100026f6b0100064062617369630100014001000840636f6e746163740c009c00500c009d005001001a6e6f7420666f756e64207468652073747564656e74277320696401000a40656475636174696f6e0100106e6f7420666f756e6420746865206964010006407072697a6501000640656e747279010006406c6561766501000a407265636f6d6d656e640c009e009f0c00a000a10c00a2004a0100136a6176612f6c616e672f457863657074696f6e0c003300340c00a300a40c00a5004a0c00a600940100105f6379706865725f636f6e74726163740100106a6176612f6c616e672f4f626a6563740100106a6176612f6c616e672f537472696e6701000a6d6a736f6e2f4a736f6e0100106a6176612f6c616e672f53797374656d0100036f75740100154c6a6176612f696f2f5072696e7453747265616d3b0100136a6176612f696f2f5072696e7453747265616d0100077072696e746c6e010015284c6a6176612f6c616e672f537472696e673b29560100136a617661782f6379706865722f4379706e657401000c736574546f6b656e496e666f01003a284c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f537472696e673b4a4c6a6176612f6c616e672f537472696e673b295a010009737562737472696e670100152849294c6a6176612f6c616e672f537472696e673b01000b746f55707065724361736501001428294c6a6176612f6c616e672f537472696e673b010006617070656e6401002d284c6a6176612f6c616e672f537472696e673b294c6a6176612f6c616e672f537472696e674275696c6465723b010008746f537472696e67010003736574010032284c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f4f626a6563743b294c6d6a736f6e2f4a736f6e3b0100087365745374617465010027284c6a6176612f6c616e672f537472696e673b4c6a6176612f6c616e672f537472696e673b295a01000a676574416464726573730100086765745374617465010007696e6465784f66010015284c6a6176612f6c616e672f537472696e673b29490100066f626a65637401000e28294c6d6a736f6e2f4a736f6e3b01000472656164010006657175616c73010015284c6a6176612f6c616e672f4f626a6563743b295a01000261740100086173537472696e67002100310032000000010008003300340000000e000100350036000100370000001d00010001000000052ab70001b10000000100380000000600010000000400090039003a000100370000003c000500030000001814000240b200041205b60006120712081f1209b8000a57b1000000010038000000120004000000090004000a000c000c0017000f0009003b003c00010037000000c500030005000000682a1024b6000bb6000c4bb20004bb000d59b7000e120fb600102ab60010b60011b6000612094e2b1212a6000912134ea7001e2b1214a6000912154ea700122b1216a6000912174ea700061209b02db800183a0419042a2cb60019572d1904b6001ab8001b57121cb00000000200380000003a000e00000015000a00160023001800260019002c001a0032001b0038001c003e001d0044001e004a0020004d002200530023005b002400650025003d0000000b0004fc003207003e0b0b020009003f0040000100370000009100030006000000652a1024b6000bb6000c4bbb000d59b7000e2ab60010121db60010b600113a051905bb000d59b7000e2bb60010121eb600102cb60010121eb600102db60010b60011b8001b57bb000d59b7000e2ab60010121fb60010b600113a0519051904b8001b57121cb00000000100380000001a00060000002a000a002b001f002c0045002d005a002e0062002f00090041004200010037000000ed00040008000000951209b800203a0419041024b6000bb6000c3a042a1024b6000bb6000c4bbb000d59b7000e2ab60010121db60010b600113a051905b800213a0619061209a600061222b0bb000d59b7000e2ab600101223b60010b600113a051905b800183a0719071904bb000d59b7000e2bb60010121eb600102cb60010121eb600102db60010b60011b600195719051907b6001ab8001b57121cb000000002003800000032000c000000330007003800130039001d003a0032003b0039003c0040003d0043003f00580040005f00410087004200920044003d0000000e0001fe004307003e07003e07003e00090043003c00010037000000e000040007000000881209b800204e2d1024b6000bb6000c4e2a1024b6000bb6000c4bbb000d59b7000e2ab60010121db60010b600113a041904b800213a0519051209a600061224b0bb000d59b7000e2ab600101225b60010b600113a041904b800183a0619062dbb000d59b7000e2bb60010121eb600102cb60010b60011b600195719041906b6001ab8001b57121cb000000002003800000032000c000000480006004d0010004e001a004f002f005000360051003d00520040005400550055005c0056007a005700850059003d0000000e0001fe004007003e07003e07003e00090044003c00010037000000e000040007000000881209b800204e2d1024b6000bb6000c4e2a1024b6000bb6000c4bbb000d59b7000e2ab60010121db60010b600113a041904b800213a0519051209a600061224b0bb000d59b7000e2ab600101226b60010b600113a041904b800183a0619062dbb000d59b7000e2bb60010121eb600102cb60010b60011b600195719041906b6001ab8001b57121cb000000002003800000032000c0000005d0006006200100063001a0064002f006500360066003d0067004000690055006a005c006b007a006c0085006e003d0000000e0001fe004007003e07003e07003e00090045003c000100370000009600040006000000621209b800204e2d1024b6000bb6000c4e2a1024b6000bb6000c4bbb000d59b7000e2ab600101227b60010b600113a041904b800183a0519052dbb000d59b7000e2bb60010121eb600102cb60010b60011b600195719041905b6001ab8001b57121cb0000000010038000000220008000000720006007700100078001a0079002f007a0036007b0054007c005f007d00090046004700010037000000ea000200050000008c1209b800204d2c1024b6000bb6000c4d2a1024b6000bb6000c4bbb000d59b7000e2ab60010121db60010b600114e2db800213a0419041209a600061224b0bb000d59b7000e2ab600101228b60010b600114e2db800213a0419041209a5000c19042bb600299c0024bb000d59b7000e1904b60010121eb600102bb60010b600113a042d1904b8001b57121cb000000002003800000036000d000000810006008200100083001a0084002e008500340086003b0087003e00890052008a0058008b0068008c0082008d0089008f003d000000100003fe003e07003e07003e07003e2920000900480047000100370000004100020002000000252a1024b6000bb6000c4bbb000d59b7000e2ab60010121eb600102bb60010b60011b80021b00000000100380000000a000200000093000a009400080049004a0001003700000072000200040000001e2ab800214cb8002a4d2bc600122b1209a5000c2bb8002b4da700044e2cb0000100130018001b002c000200380000001e000700000098000500990009009a0013009d0018009f001b009e001c00a1003d000000160002ff001b000307003e07003e07004b000107004c000008004d004e000100370000005a000200030000001f2ab2002db6002e9900061209b02a2bb6002f4d2cc700061209b02cb60030b00000000200380000001a0006000000a5000a00a6000d00a7001300a8001700a9001a00aa003d0000000900020dfc000c07004b0009004f0050000100370000002300010002000000072ab800214c2bb00000000100380000000a0002000000ad000500ae000800510036000100370000001f0001000000000007b8002ab3002db10000000100380000000600010000000600010052000000020053"

```
#### Unlock your account
```
personal.unlockAccount(cph.accounts[0])//personal.unlockAccount("0x80e4d44d9d24fa90dc7fb07d360f920953cb52dc")

```
#### Publish it to the cypher blockchain
```
theSample=sample.new(1,{from:cph.accounts[0],data:SampleHEX,gas:3000000})

{
  abi: [{
      inputs: [{...}, {...}, {...}],
      stateMutability: "nonpayable",
      typ: "constructor"
  }],
  address: undefined,
  transactionHash: "0x3ecde60e82d301c8ccb7846d01ed8eae900fc25b8363e638fc3f63a246d19118"
}
```


## Operate or edit smart contract 
Open the HR.htm by your chrome by desktop OS
After deploying the  Smart Contract according to the above diagram, you can click `Get contract info`, `From's Balance`, `To's Balance`, `Transfer` to perform related smart contract executions.
### Get contract address
 ```
 cph.getTransactionReceipt("0x3ecde60e82d301c8ccb7846d01ed8eae900fc25b8363e638fc3f63a246d19118")
  ```
{
  blockHash: "0x143554d1cfb1cf3be26209f636ed61def145e1450fc193a1c3064cc586cdd354",
  blockNumber: 328,
  contractAddress: "0xf76fcdfafab908dcbd8e7d662d715236538fe79e",
  cumulativeGasUsed: 854136,
  from: "0x80e4d44d9d24fa90dc7fb07d360f920953cb52dc",
  gasUsed: 854136,
  logs: [],
  root: "0x21c2d3ddeb1fb1cfb9e3cf2d0aae15db625b95bd4efb0d784cde322c9163996d",
  to: null,
  transactionHash: "0x3ecde60e82d301c8ccb7846d01ed8eae900fc25b8363e638fc3f63a246d19118",
  transactionIndex: 0
}

### Set target rpcnode Rpc node and Contract address
RpcNode:http://34.210.170.38:8000
Contract address:0xf76fcdfafab908dcbd8e7d662d715236538fe79e

### Fix your account address for your Role
Student:0x80e4d44d9d24fa90dc7fb07d360f920953cb22dc

### Fix personal info data
name:jaxon
sex:man
age:26
contact:18642437977

### Resume check the Fix personal info data
Fix the Student id(Student account):0x80e4d44d9d24fa90dc7fb07d360f920953cb22dc
Finally click the Resume button you will see the personal info data

## javax.cypher.Cypnet library
In addition, we also provide the following functions in the javax.cypher.Cypnet library.

### Get the account balance
```
public​ ​static​ ​native​ ​long​ ​BalanceOf​(String paramString);
```

### Change the account balance
```
public​ ​static​ ​native​ ​boolean​ ​ChangeBalance​(String paramString, ​long​ paramLong);
```

### Get key-value
```
 public​ ​static​ ​native​ ​boolean​ GetState(​String​ paramString1, ​String​ paramString2, byte​[] paramArrayOfByte);
```

### Set key-value
```
 public​ ​static​ ​native​ ​boolean​ SetState(​String​ paramString1, ​String​ paramString2, byte​[] paramArrayOfByte);
```

We provide support for most JDK1.8 libraries in jdk/classes, and you may design smart contracts in accordance with traditional Java programs.


