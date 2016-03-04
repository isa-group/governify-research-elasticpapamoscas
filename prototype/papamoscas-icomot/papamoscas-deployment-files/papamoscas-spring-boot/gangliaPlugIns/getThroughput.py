import urllib, urllib2, sys, httplib, json

from xml.dom.minidom import parseString

HOST_IP = 'localhost'
url = '/metrics'
httplib.HTTPConnection.debuglevel = 0

def _getData():
            connection =  httplib.HTTPConnection(HOST_IP, 8080,timeout=6)
            headers={
	          'Content-Type':'application/json; charset=utf-8',
                  'Accept':'application/json'
	    }
 
            try:
               connection.request('GET', url+'/throughput',headers=headers,)
               result = connection.getresponse()
               result_json = result.read()
               #print result_xml
               throughput = json.loads(result_json)
               #print int(xmlTag[0].firstChild.nodeValue)
               #returns only avg reponse time
               return int(throughput['throughput'])
            except:
               return -1


def temp_handler(name):
    return _getData();

def metric_init(params):
    global descriptors

    d1 = {'name': 'throughput',
        'call_back': temp_handler,
        'time_max': 1,
        'value_type': 'int',
 'units': '#',
        'slope': 'both',
        'format': '%d',
        'description': 'Average throughput per 1 seconds',
        'groups': 'performance'}

    descriptors = [d1]

    return descriptors

def metric_cleanup():
    '''Clean up the metric module.'''
    pass

#This code is for debugging and unit testing
if __name__ == '__main__':
    metric_init({})
    for d in descriptors:
        v = d['call_back'](d['name'])
        print 'value for %s is %s' % (d['name'],  v)
 

