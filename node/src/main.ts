function anno() {
    return (target: any, key: string, desc: PropertyDescriptor) => {
        if (typeof desc.value == 'function') {
            let impl = desc.value
            desc.value = function () {
                try {
                    return impl.apply(this, arguments)
                } catch (e) {
                    console.error(e)
                }
            }
        }
    }
}

class Test {

    @anno()
    a(msg: string) {
        console.log(msg)
        throw "A:Exception"
    }

}

let t = new Test()
t.a("hello")
