import os
import string
def isInt(s):
    try:
        int(s)
        return True
    except ValueError:
        return False
shellDiff = "./xinterpret < {} | diff -w - {}"
path = "/Users/nicolasburniske/Documents/collected-tests/5/ITests"
translateTable = str.maketrans('', '', string.whitespace)
files = [os.path.join(path, fn) for fn in next(os.walk(path))[2]]
inFiles = filter(lambda f: "-in" in f, files)
outFiles = filter(lambda f: "-out" in f, files)
sortedIn = sorted(inFiles)
sortedOut = sorted(outFiles)
if len(sortedIn) != len(sortedOut):
    print("lengths are not equal")
numberOfFailures = 0
for ii in range(len(sortedIn)):
    inF = sortedIn[ii]
    outF = sortedOut[ii]
    actual = open("result.txt", 'r').read()
    expected = open(outF, 'r').read()
    if not isInt(expected):
        continue
    os.system("./xinterpreter < {} > result.txt".format(inF))
    comparison = actual.translate(translateTable) == expected.translate(translateTable)
    if not comparison and not ("\"type error: domain doesn't match\"" == expected):
        numberOfFailures += 1
        print("======================================================")
        print('FAILURE test #{}, FILE: {}'.format(ii, inF))
        print("Actual: {}".format(actual))
        print("Expected: {}".format(expected))
        print("======================================================")
print(numberOfFailures)
