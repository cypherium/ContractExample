package main

import (
	"fmt"
	"os"
	"io/ioutil"  
	"github.com/urfave/cli"
)

func main() {
	
	app := cli.NewApp()
	app.Name = "file2str"
	app.Usage = "file2str yourfile"
	app.Version = "1.0.0"
	app.Action = func(c *cli.Context) error {
		args := c.Args()
		if c.NArg() < 1 {
			fmt.Println("pleast input your filename.")
			return nil
		}
		sfile  := args.Get(0)
		readMe(sfile)
		return nil
	}
	app.Run(os.Args)
}


func readMe(path string){  
    fi,err := os.Open(path)  
    if err != nil{
		panic(err)
	}  
    defer fi.Close()  
	
	fmt.Printf("0x");
    fd,err := ioutil.ReadAll(fi)  
	s := string(fd)
	for i:=0;i<len(s);i=i+1 {
		fmt.Printf("%02x",s[i] )
	}
    //return string(fd)  
}  

