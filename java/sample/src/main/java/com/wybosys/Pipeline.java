package com.wybosys;

// 基础管道工具
// 1，提供日志记录
// 2，分步记录栈数据

import java.util.HashMap;
import java.util.LinkedList;

public class Pipeline {

    private static class Stack {

        // 全局变量
        private final HashMap<String, Object> _globals = new HashMap<>();

        // 局部变量
        private final HashMap<String, Object> _locals = new HashMap<>();

        // 设置
        public void global(String name, Object val) {
            _globals.put(name, val);
        }

        public void local(String name, Object val) {
            _locals.put(name, val);
        }

        // 提取
        public Object global(String name) {
            return _globals.get(name);
        }

        public Object local(String name) {
            return _locals.get(name);
        }

        // 累加
        public void globalInc(String name, int delta) {
            try {
                Number now = (Number) _globals.getOrDefault(name, 0);
                _globals.put(name, now.intValue() + delta);
            } catch (Exception e) {
                // pass
            }
        }

        public void localInc(String name, int delta) {
            try {
                Number now = (Number) _locals.getOrDefault(name, 0);
                _locals.put(name, now.intValue() + delta);
            } catch (Exception e) {
                // pass
            }
        }

        public HashMap<String, Object> getGlobals() {
            return _globals;
        }

        public HashMap<String, Object> getLocals() {
            return _locals;
        }

        private String tag = "";

        public String getTag() {
            return tag;
        }

        public void setTag(String str) {
            tag = str;
        }

        public Stack clone() {
            Stack r = new Stack();
            r._globals.putAll(_globals);
            return r;
        }
    }

    private final LinkedList<String> _infos = new LinkedList<>();

    public void info(String str) {
        _infos.add(str);
    }

    public void info(String str, Object p0) {
        _infos.add(String.format(str, p0));
    }

    public void info(String str, Object p0, Object p1) {
        _infos.add(String.format(str, p0, p1));
    }

    public void info(String str, Object... ps) {
        _infos.add(String.format(str, ps));
    }

    public void info(String str, Throwable e) {
        _infos.add(String.format(str, e));
    }

    private final LinkedList<Stack> _stacks = new LinkedList<>();
    private Stack _cur;

    private Stack getCur() {
        if (_cur == null) {
            _cur = new Stack();
            _stacks.add(_cur);
        }
        return _cur;
    }

    public Pipeline next() {
        return next("");
    }

    public Pipeline next(String tag) {
        if (_cur == null) {
            _cur = new Stack();
        } else {
            _cur = _cur.clone();
        }
        _cur.setTag(tag);
        _stacks.add(_cur);
        return this;
    }

    public Pipeline global(String name, Object val) {
        getCur().global(name, val);
        return this;
    }

    public Object global(String name) {
        return getCur().global(name);
    }

    public Pipeline local(String name, Object val) {
        getCur().local(name, val);
        return this;
    }

    public Object local(String name) {
        return getCur().local(name);
    }

    public Pipeline globalInc(String name) {
        return globalInc(name, 1);
    }

    public Pipeline globalInc(String name, int delta) {
        getCur().globalInc(name, delta);
        return this;
    }

    public Pipeline localInc(String name) {
        return localInc(name, 1);
    }

    public Pipeline localInc(String name, int delta) {
        getCur().localInc(name, delta);
        return this;
    }

    @Override
    public String toString() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("PIPELINE: ");
            if (!_infos.isEmpty()) {
                sb.append("INFO:{");
                for (String each : _infos) {
                    sb.append(each).append(';');
                }
                sb.append("}");
            }
            if (!_stacks.isEmpty()) {
                sb.append("STACKS:{");
                for (Stack each : _stacks) {
                    sb.append(" ").append(each.getTag()).append(":{");
                    if (!each.getGlobals().isEmpty()) {
                        sb.append(" globals:{");
                        each.getGlobals().forEach((name, val) -> {
                            sb.append(name).append("=").append(val).append("|");
                        });
                        sb.append("}");
                    }
                    if (!each.getLocals().isEmpty()) {
                        sb.append(" locals:{");
                        each.getLocals().forEach((name, val) -> {
                            sb.append(name).append("=").append(val).append("|");
                        });
                        sb.append("}");
                    }
                    sb.append("}");
                }
                sb.append("}");
            }
            return sb.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
