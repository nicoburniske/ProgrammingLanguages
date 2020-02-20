import os
import subprocess

files = os.listdir("../../collected-tests/6/CTests")

for file in files :
    print(file)
    if file.find("-in.json") != -1 :
        outputfile = file.split("-in.json")[0] + "-out.json"
        subprocess.call("./xcheck < {} | diff -w - {}".format(file, outputfile).split())
        print("in conditional")
