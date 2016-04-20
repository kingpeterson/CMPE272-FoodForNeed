from xml.dom.minidom import parse
import xml.dom.minidom

safewayadd='safewayadd.xml'
DOMTree = xml.dom.minidom.parse(safewayadd)
collection = DOMTree.documentElement
pois = collection.getElementsByTagName("poi")
csvfinal='safewaybayarea.csv'

fcsv = open(csvfinal,'w')

tableAttr=['MARKETID','MARKETNAME','CITY','PHONE','LATITUDE','LONGITUDE','ADDRESSLINE1','ADDRESSLINE2','STATE','COUNTRY','POSTALCODE']

for i in tableAttr:
	fcsv.write(i+',')

fcsv.write('\n')

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

