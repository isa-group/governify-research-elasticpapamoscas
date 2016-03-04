import urllib, urllib2, sys, httplib
import subprocess

from xml.dom.minidom import parseString

HOST_IP = 'localhost:8080'
url = '/DaaS/api'
httplib.HTTPConnection.debuglevel = 0

def _getData():
    cmd = 'ifconfig eth0 | grep "RX bytes:[0-9]* (" -o | grep "[0-9]*" -o'
    ps = subprocess.Popen(cmd,shell=True,stdout=subprocess.PIPE,stderr=subprocess.STDOUT)
    output = ps.communicate()[0]
    return int(output)

def temp_handler(name):
    return _getData();

def metric_init(params):
    global descriptors

    d1 = {'name': 'dataIn',
        'call_back': temp_handler,
        'time_max': 5,
        'value_type': 'int',
        'units': 'byte',
        'slope': 'both',
        'format': '%u',
        'description': 'Data received from outside of the cloud this session',
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
 




