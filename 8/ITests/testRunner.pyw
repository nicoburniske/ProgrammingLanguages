import os
import string


def isInt(s):
    try:
        int(s)
        return True
    except ValueError:
        return False


shellDiff = "./xinterpret < {} | diff - {}"
xTranslateShell = "../xtranslate < {} > {}"
path = "../../../collected-tests/7/ITests"

translateTable = str.maketrans('', '', string.whitespace)
files = [os.path.join(path, fn) for fn in next(os.walk(path))[2]]
inFiles = filter(lambda f: "-in" in f, files)
outFiles = filter(lambda f: "-out" in f, files)

sortedIn = sorted(inFiles)
sortedOut = sorted(outFiles)

if len(sortedIn) != len(sortedOut):
    print("lengths are not equal")
# run translate
testCount = 0
for ii in range(len(sortedIn)):
    inF = sortedIn[ii]
    outF = sortedOut[ii]
    expected = open(outF).read()
    if not isInt(expected):
        continue
    # os.system("./xinterpret < {} > result.txt".format(inF))
    # Run xtranslate on all inputs, also copy output file to dir
    os.system(xTranslateShell.format(inF, "{}.in".format(testCount)))
    os.system("cp {} {}.out".format(outF, testCount))
    # actual = open("result.txt", 'r').read()
    testCount += 1
os.system("./make_exe")

# run executables and then diff
numberOfFailures = 0
for ii in range(testCount):
    os.system("./{} > result.txt".format(ii))
    translateResult = open("result.txt", 'r').read()
    expectedResult = open("{}.out".format(ii)).read()

    comparison = translateResult.translate(translateTable) == expectedResult.translate(translateTable)
    if not comparison:
        numberOfFailures += 1
        print("======================================================")
        print('FAILURE test #{}'.format(ii))
        print("Actual: {}".format(translateResult))
        print("Expected: {}".format(expectedResult))
        print("======================================================")
print(numberOfFailures)

