use ferris_says::say;
use std::{
    borrow::{Borrow, BorrowMut},
    cell::{Cell, RefCell},
    io::{stdout, BufWriter},
    rc::Rc,
    sync::Arc,
    thread,
};

trait IA {
    fn a(&self);
}

struct A {}

impl IA for A {
    fn a(&self) {
        println!("A");
    }
}

struct B {
    a: Box<A>,
    b: Cell<A>,
    c: RefCell<A>,
    d: Rc<A>,
    e: Arc<A>,
}

impl B {
    pub fn new() -> Self {
        B {
            a: Box::new(A {}),
            b: Cell::new(A {}),
            c: RefCell::new(A {}),
            d: Rc::new(A {}),
            e: Arc::new(A {}),
        }
    }
}

fn basetypes() {
    let mut b = B::new();

    let a = Box::new(A {});
    let mut b = Cell::new(A {});
    let mut c = RefCell::new(A {});
    let d = Rc::new(A {});
    let e = Arc::new(A {});

    // b.clone();
    // c.clone();

    let p = A {};
    // let pa = Box::new(p);
    // let pb = Cell::new(p);
    // let pbb = Cell::new(p);
    // let pc = RefCell::new(p);
    // let pcc = RefCell::new(p);
    // let pd = Rc::new(p);
    // let pdd = Rc::new(p);

    {
        let aa = &a;
        let aaa = &a;
        let bb = &b;
        let bbb = &b;
        bb.set(A {});
        bbb.set(A {});

        let cc = &c;
        let ccc = &c;
        let bbbb = b.borrow();
        // let cccc = c.borrow();
        // let cccc = c.borrow_mut();

        let bb = b.get_mut();
        *bb = A {};

        let bbbbb = b.get_mut();
        *bbbbb = A {};

        let ccccc = c.get_mut();
        *ccccc = A {};

        let cc = c.get_mut();
        *cc = A {};

        let bbbb = b.borrow();
        bbbb.set(A {});
        let mut cccc = c.borrow_mut();
        *cccc = A {};
    }

    b.set(A {});
    *c.get_mut() = A {};

    let ee = e.clone();
    let dd = d.clone();
    thread::spawn(move || {
        // let aa = a;
        // let dd = dd.clone();
        let ee = ee.clone();
    });

    // let aa = a;
    let bb = b.get_mut();
    let cc = c.get_mut();
    let dd = d.clone();
    let ee = e.clone();
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

    basetypes();
}
