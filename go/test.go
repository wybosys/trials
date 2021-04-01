package main

import (
	"bufio"
	"encoding/json"
	"fmt"
	"log"
	"os"
	"runtime"
	"sync"
	"time"
)

type TestJs struct {
	Id int
}

type AsyncLog struct {
	messages []string
	lck      sync.Mutex
	tmr      *time.Ticker
	fout     *os.File
}

const BUFFER_COUNT = 10000
const TIMER_FLUSH_DURATION = 2

var (
	logger AsyncLog
)

func (self *AsyncLog) Start() {
	self.tmr = time.NewTicker(time.Second * TIMER_FLUSH_DURATION)

	self.fout, _ = os.OpenFile("log.log", os.O_RDWR|os.O_CREATE, 0666)
	log.SetOutput(self.fout)

	go func() {
		for range self.tmr.C {
			self.lck.Lock()
			msgs := self.messages
			self.messages = make([]string, 0, BUFFER_COUNT)
			self.lck.Unlock()

			// 打印当前所有的
			for _, e := range msgs {
				// e = e
				log.Println(e)
			}
		}
	}()
}

func (self *AsyncLog) Add(msg string) {
	self.lck.Lock()
	self.messages = append(self.messages, msg)
	self.lck.Unlock()
}

func (self *AsyncLog) Addf(format string, v ...interface{}) {
	// fmt.Sprintf(format, v...)
	self.Add(fmt.Sprintf(format, v...))
}

func TestJson() {
	for j := 0; ; j++ {
		jsobj := &TestJs{1}
		jsstr, _ := json.Marshal(jsobj)
		ret := json.Unmarshal(jsstr, jsobj)
		if ret == nil {
			log.Printf("haha %d\n", j)
		} else {
			log.Println("wtf")
		}
		time.Sleep(time.Microsecond)
	}
}

func TestCommon() {
	for i := 0; i < 1000; i++ {
		go TestJson()
	}
}

func TestChannel() {
	c0 := make(chan int)
	// var c1 chan int
	c1 := make(chan int, BUFFER_COUNT)

	// 计数器
	var cnt int

	go func() {
		for {

			select {
			case v := <-c0:
				go logger.Addf("test channel c0: %d, %d, %d", v, cnt, len(c0))
			case v := <-c1:
				go logger.Addf("test channel c1[a]: %d, %d, %d", v, cnt, len(c1))
			case v := <-c1:
				go logger.Addf("test channel c1[b]: %d, %d, %d", v, cnt, len(c1))
			case v := <-c1:
				go logger.Addf("test channel c1[c]: %d, %d, %d", v, cnt, len(c1))
			case v := <-c1:
				go logger.Addf("test channel c1[d]: %d, %d, %d", v, cnt, len(c1))
			case v := <-c1:
				go logger.Addf("test channel c1[e]: %d, %d, %d", v, cnt, len(c1))
			case v := <-c1:
				go logger.Addf("test channel c1[f]: %d, %d, %d", v, cnt, len(c1))
			case v := <-c1:
				go logger.Addf("test channel c1[g]: %d, %d, %d", v, cnt, len(c1))
			case v := <-c1:
				go logger.Addf("test channel c1[h]: %d, %d, %d", v, cnt, len(c1))
			default:
				// log.Println("default")
			}
		}
	}()

	go func() {
		for {
			go func() {
				for i := 0; i < 100; i++ {

					// c0 <- cnt
					cnt = cnt + 1
					c1 <- cnt

				}
			}()

			time.Sleep(time.Millisecond)
		}
	}()
}

func TestDead() {
	var mtx sync.RWMutex

	go func() {
		time.Sleep(time.Second * 2)
		// panic("output")
	}()

	go func() {
		mtx.Unlock()		
	}()

	mtx.Lock()
	mtx.Lock()
	mtx.Unlock()

	fmt.Print("haha")

	/*
		go func() {
			mtx.Lock()
			mtx.Lock()
		}()
	*/
}

func main() {
	logger.Start()

	//Test()
	//TestChannel()
	TestDead()

	cin := bufio.NewReader(os.Stdin)
	inp, _ := cin.ReadString('\n')
	fmt.Println(inp)
}
