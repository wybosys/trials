package main

import "github.com/wybosys/trials/go/test"

type Test struct {
	A string
}

func (t *Test) proc() string {
	t.A += " go"
	return t.A
}

func (t Test) proc2() string {
	t.A += " go"
	return t.A
}

func main() {
	t0 := Test{"go"}
	test.TestPrint(t0.proc())

	test.TestPrint(Test{"G"}.proc2())
	// test.TestPrint(Test{"G"}.proc()) 编译错误
	test.TestPrint((&Test{"G"}).proc())
}
