// #[cfg(target_os = "linux")]
// type Abc = Cde;

fn main() {
    let machine_kind = if cfg!(unix) {
        "unix"
    } else if cfg!(windows) {
        "windows"
    } else {
        "unknown"
    };

    println!("I'm running on a {} machine!", machine_kind);
}
