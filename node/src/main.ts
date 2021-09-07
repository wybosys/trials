function anno(err: (e: any) => void = console.error) {
    return (target: any, key: string, desc: PropertyDescriptor) => {
        if (typeof desc.value == 'function') {
            let impl = desc.value
            desc.value = function () {
                try {
                    let r = impl.apply(this, arguments)
                    if (r instanceof Promise) {
                        r.catch(err)
                        return r
                    }
                    return r
                } catch (e) {
                    err(e)
                }
            }
        }
    }
}

class Time {

    static sleep(ms: number): Promise<void> {
        return new Promise<void>(resolve => {
            setTimeout(() => {
                resolve()
            }, ms)
        })
    }
}

class Test {

    @anno()
    a(msg: string) {
        console.log(msg)
        throw "A:Exception"
    }

    @anno()
    async test(msg: string) {
        console.log(msg)
        await Time.sleep(1000)
        throw `T:${msg}`
    }

}

let t = new Test()
t.a("hello")
t.test("async hello")
