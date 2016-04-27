from xml.dom.minidom import parse
import xml.dom.minidom
import csv
import random
import time


def strTimeProp(start, end, format, prop):
    """Get a time at a proportion of a range of two formatted times.

    start and end should be strings specifying times formated in the
    given format (strftime-style), giving an interval [start, end].
    prop specifies how a proportion of the interval to be taken after
    start.  The returned time will be in the specified format.
    """

    stime = time.mktime(time.strptime(start, format))
    etime = time.mktime(time.strptime(end, format))

    ptime = stime + prop * (etime - stime)

    return time.strftime(format, time.localtime(ptime))


def randomDate(start, end, prop):
    return strTimeProp(start, end, '%m/%d/%Y %I:%M %p', prop)




safewayadd='safewaybayarea.csv'

#with 
fsway= open(safewayadd, 'r')
#DOMTree = xml.dom.minidom.parse(safewayadd)
#collection = DOMTree.documentElement
#pois = collection.getElementsByTagName("poi")
csvfinal='products.csv'

fcsv=open(csvfinal,'w')

reader=csv.DictReader(fsway)

fcsv = open(csvfinal,'w')

tableAttr=['FRUIT','MARKETID','MEAT','ORDERDATE','OTHERS','PRODUCTID','SEAFOOD','SRCLATITUDE','SRCLONGITUDE','VEGETABLE']

for i in tableAttr:
	fcsv.write(i+',')

fcsv.write('\n')
date=''
i=5
for row in reader:
	fcsv.write(str(random.randrange(20, 90, 5))+','+row['MARKETID']+','+str(random.randrange(20, 90, 5))+','+str(randomDate("5/1/2016 1:30 PM", "6/30/2016 9:30 PM", random.random()))+','+str(random.randrange(20, 90, 5))+','+str(i)+','+str(random.randrange(20, 90, 5))+','+row['LATITUDE']+','+row['LONGITUDE']+','+str(random.randrange(20, 90, 5)))
	fcsv.write('\n')
	i+=1
fcsv.close()
"""
uidlist=[]
i = 4
for poi in pois:

	address1 = poi.getElementsByTagName('address1')[0].childNodes[0].data

	if len(poi.getElementsByTagName('address2')[0].childNodes) != 0:#[0].data != 0:
		address2 = poi.getElementsByTagName('address2')[0].childNodes[0].data
	else:
		address2=''

	city = poi.getElementsByTagName('city')[0].childNodes[0].data
	country = poi.getElementsByTagName('country')[0].childNodes[0].data
	latitude = poi.getElementsByTagName('latitude')[0].childNodes[0].data
	longitude = poi.getElementsByTagName('longitude')[0].childNodes[0].data
	name = poi.getElementsByTagName('name')[0].childNodes[0].data
	if len(poi.getElementsByTagName('phone')[0].childNodes) !=0:
		phone = poi.getElementsByTagName('phone')[0].childNodes[0].data
	else:
		phone = ''
	postalcode = poi.getElementsByTagName('postalcode')[0].childNodes[0].data
	state = poi.getElementsByTagName('state')[0].childNodes[0].data
	uid = poi.getElementsByTagName('uid')[0].childNodes[0].data
	if uid not in uidlist:
		fcsv.write(str(i)+','+name+','+city+','+phone+','+latitude+','+longitude+','+address1+','+address2+','+state+','+country+','+postalcode)
		fcsv.write('\n')
		uidlist.append(uid)
		i+=1
fcsv.close()
#poi.getElementsByTagName('city')


#for poi in pois:


	#fcsv.write(contentIndex.decode('utf8'))
	#fcsv.close()
"""
