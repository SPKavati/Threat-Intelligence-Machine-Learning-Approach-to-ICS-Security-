
# coding: utf-8

# In[22]:

from OTXv2 import OTXv2, IndicatorTypes
import re
import os
import sys
import traceback
import argparse

HASH_BLACKLIST = [ 'e617348b8947f28e2a280dd93c75a6ad', '125da188e26bd119ce8cad7eeb1fc2dfa147ad47',
                  '06f7826c2862d184a49e3672c0aa6097b11e7771a4bf613ec37941236c1a8e20' ]

tagslist = ['energy', 'blackenergy', 'scada', 'industrial', 'power plant', 'plant', 'nuclear', 'defense', 
            'manufacturing', 'cyber', 'automation', 'power grid', 'ics', 'military', 'palo alto', 'Energy','paloalto']

def my_escape(string):
        return re.sub(r'([\-\(\)\.\[\]\{\}\\\+])',r'\\\1',string)

class OTXReceiver:
    # IOC strings
    hash_iocs = ""
    filename_iocs = ""
    c2_iocs = ""
    
    #output format
    separator = ';'
    use_csv_header = False
    extension = 'txt'
    hash_upper = False
    filename_regex_out = True
    
    # event container
    events =[]
    #debug, proxy
    def get_ics_pulses(self, api_key, siem_mode):
        #self.debug = debug
        self.otx = OTXv2(api_key)
        if siem_mode:
            self.separator = ","
            self.use_csv_header = True
            self.extension = "csv"
            self.hash_upper = True
            self.filename_regex_out = False
    
    def get_iocs_last(self):
        # mtime = (datetime.now() - timedelta(days=days_to_load)).isoformat()
        try:
            print("Starting OTX feed download ...")
            pulses = self.otx.getall()
            if len(pulses) > 0:
                for pulse in pulses:
                    pulsetags = pulse['tags']
                    for tag in pulsetags:
                        if tag in tagslist:
                            self.events.append(pulse)
                print("Download complete - %s events received" % len(self.events))
            else:
                print("Download failed - no events received (check your Internet connection)")
            # json_normalize(self.events)
        except Exception as e:
            traceback.print_exc()
    
    def write_iocs(self, ioc_folder):
        hash_ioc_file = os.path.join(ioc_folder, "otx-hash-iocs.{0}".format(self.extension))
        filename_ioc_file = os.path.join(ioc_folder, "otx-filename-iocs.{0}".format(self.extension))
        c2_ioc_file = os.path.join(ioc_folder, "otx-c2-iocs.{0}".format(self.extension))

        print("Processing indicators ...")
        for event in self.events:
            try:
                for indicator in event["indicators"]:
                    if indicator["type"] in ('FileHash-MD5', 'FileHash-SHA1', 'FileHash-SHA256') and                                     indicator["indicator"] not in HASH_BLACKLIST:

                        hash = indicator["indicator"]
                        if self.hash_upper:
                            hash = indicator["indicator"].upper()

                        self.hash_iocs += "{0}{3}{1} {2}\n".format(hash, event["name"].encode('unicode-escape')," / ".join(event["references"])[:80], self.separator)

                    if indicator["type"] == 'FilePath':
                        filename = indicator["indicator"]
                        if self.filename_regex_out:
                            filename = my_escape(indicator["indicator"])

                        self.filename_iocs += "{0}{3}{1} {2}\n".format(filename, event["name"].encode('unicode-escape')," / ".join(event["references"])[:80], self.separator)

                    if indicator["type"] in ('domain', 'hostname', 'IPv4', 'IPv6', 'CIDR'):

                        self.c2_iocs += "{0}{3}{1} {2}\n".format(indicator["indicator"], event["name"].encode('unicode-escape'), " / ".join(event["references"])[:80], self.separator)

            except Exception as e:
                traceback.print_exc()

        # Write to files
        with open(hash_ioc_file, "w") as hash_fh:
            if self.use_csv_header:
                hash_fh.write('hash{0}description\n'.format(self.separator))
            hash_fh.write(self.hash_iocs)
            print("{0} hash iocs written to {1}".format(self.hash_iocs.count('\n'), hash_ioc_file))
        with open(filename_ioc_file, "w") as fn_fh:
            if self.use_csv_header:
                fn_fh.write('filename{0}description\n'.format(self.separator))
            fn_fh.write(self.filename_iocs)
            print("{0} filename iocs written to {1}".format(self.filename_iocs.count('\n'), filename_ioc_file))
        with open(c2_ioc_file, "w") as c2_fh:
            if self.use_csv_header:
                c2_fh.write('host{0}description\n'.format(self.separator))
            c2_fh.write(self.c2_iocs)
            print("{0} c2 iocs written to {1}".format(self.c2_iocs.count('\n'), c2_ioc_file))
    


# In[23]:

OTX_KEY = "926c1e1c8338ff5be230d0bac854fd5e3f0206189310c743f501d75e599e5d28"
siem ='True'
# Create a receiver
otx_receiver = OTXReceiver()

#get ICS/SCADA pulse events
otx_receiver.get_ics_pulses(OTX_KEY, siem)

# Retrieve the events and store the ICS_IOCs
otx_receiver.get_iocs_last()

# Write ICS_IOC files
otx_receiver.write_iocs('Alienvault_OTX_ICS_IOCs')


# In[ ]:



