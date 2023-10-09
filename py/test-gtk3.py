#!/usr/bin/env python3

import gi

gi.require_version("Gtk", "3.0")

from gi.repository import GLib, Gtk
import sys


class MyApplication(Gtk.Application):
    def __init__(self):
        super().__init__(application_id="com.example.MyGtkApplication")
        GLib.set_application_name("My Gtk Application")

    def do_activate(self):
        window = Gtk.ApplicationWindow(application=self, title="Hello World")
        window.present()


app = MyApplication()
exit_status = app.run(sys.argv)
sys.exit(exit_status)
