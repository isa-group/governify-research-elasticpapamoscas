import urllib, urllib2, sys, httplib
import subprocess

from xml.dom.minidom import parseString

HOST_IP = 'localhost:8080'
url = '/DaaS/api'
httplib.HTTPConnection.debuglevel = 0

def _getData():
    return int(1)

def temp_handler(name):
    return _getData();

def metric_init(params):
    global descriptors

    d1 = {'name': 'publicIP',
        'call_back': temp_handler,
        'time_max': 5,
        'value_type': 'int',
        'units': '#',
        'slope': 'both',
        'format': '%d',
        'description': 'Number of Public IPs used',
        'groups': 'resource'}

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
 




