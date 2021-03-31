use ferris_says::say;
use std::io::{stdout, BufWriter};

fn main() {
    println!("Hello, world!");

    let out = stdout();
    let msg = String::from("一二三");
    let len = msg.chars().count();

    let mut tgt = BufWriter::new(out.lock());
    say(msg.as_bytes(), len, &mut tgt).unwrap();
}
