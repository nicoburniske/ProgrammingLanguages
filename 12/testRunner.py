import os
import string

path = "/Users/nicolasburniske/Documents/collected-tests/11/ITests"
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
    expected = open(outF, 'r').read()
    os.system("./xrun < {} > result.txt".format(inF))
    actual = open("result.txt", 'r').read()
    comparison = actual.translate(translateTable) == expected.translate(translateTable)
    if not comparison:
        numberOfFailures += 1
        print("======================================================")
        print('FAILURE test #{}, FILE: {}'.format(ii, inF))
        print("Actual: {}".format(actual))
        print("Expected: {}".format(expected))
        print("======================================================")
print(numberOfFailures)
