import os	
import string	
def isInt(s):	
    try:	
        int(s)	
        return True	
    except ValueError:	
        return False	
#shellDiff = "./xcps < {} | diff -w - {}"	
path = "/Users/nicolasburniske/Documents/collected-tests/9/ITests"	
translateTable = str.maketrans('', '', string.whitespace)	
files = [os.path.join(path, fn) for fn in next(os.walk(path))[2]]	
inFiles = filter(lambda f: "-in" in f, files)	
outFiles = filter(lambda f: "-out" in f, files)	
sortedIn = sorted(inFiles)	
sortedOut = sorted(outFiles)	
if len(sortedIn) != len(sortedOut):	
    print("lengths are not equal")	

numberOfFailures = os.system("rm result.txt")
for ii in range(len(sortedIn)):	
    inF = sortedIn[ii]	
    outF = sortedOut[ii]	
    os.system("./xcps {} {} >> result.txt".format(inF, outF))	

