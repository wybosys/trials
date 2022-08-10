use ferris_says::say;
use std::io::{stdout, BufWriter};

trait IA {
    fn a(&self);
}

struct A {}

impl IA for A {
    fn a(&self) {
        println!("A");
    }
}

fn main() {
    println!("Hello, world!");

    let out = stdout();
    let msg = String::from("一二三");
    let len = msg.chars().count();

    let mut tgt = BufWriter::new(out.lock());
    say(msg.as_bytes(), len, &mut tgt).unwrap();

    let ptr: Option<A>;

    {
        let a = A {};
        a.a();
        ptr = Some(a);
    }

    ptr.unwrap().a();
}
