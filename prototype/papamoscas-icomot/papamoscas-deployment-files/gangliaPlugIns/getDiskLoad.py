import urllib, urllib2, sys, httplib
import subprocess
import StringIO

from xml.dom.minidom import parseString

HOST_IP = 'localhost:8080'
url = '/DaaS/api'
httplib.HTTPConnection.debuglevel = 0

def _getData():
    cmd = 'iostat -d'
    ps = subprocess.Popen(cmd,shell=True,stdout=subprocess.PIPE,stderr=subprocess.STDOUT)
    output = ps.communicate()[0].split('\n')
    #first 3 rows are useless text like headers
    output = output[3:len(output)-1]
    totalDiskAccessVolume = 0
    for d in output:
       columns = d.split()
       if(len(columns) == 6):
          # 5'th element (index 4) is total read, and next one on 5'th index is total write on the element
          totalDiskAccessVolume = totalDiskAccessVolume +  int(columns[4]) + int(columns[5] )

    return int(totalDiskAccessVolume)

def temp_handler(name):
    return _getData();

def metric_init(params):
    global descriptors

    d1 = {'name': 'diskDataLoad',
        'call_back': temp_handler,
        'time_max': 5,
        'value_type': 'int',
        'units': 'KB',
        'slope': 'both',
        'format': '%u',
        'description': 'Data written and read from the disck during this session',
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

