#!/bin/bash

sudo -S  apt-get install sysstat -y


if [ ! -d "/usr/lib/ganglia/python_modules/" ]; then
     sudo -S mkdir /usr/lib/ganglia/python_modules/
fi

if [ ! -d "/etc/ganglia/conf.d/" ]; then
     sudo -S mkdir /etc/ganglia/conf.d/
fi


sudo -S cp ./*.py /usr/lib/ganglia/python_modules/
sudo -S cp ./daasPlugIns.pyconf /etc/ganglia/conf.d/daasPlugIns.pyconf
sudo -S cp ./modpython.conf /etc/ganglia/conf.d/modpython.conf
sudo -S service ganglia-monitor restart

