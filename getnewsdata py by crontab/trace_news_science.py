# -*- coding:utf-8 -*-
from http.client import HTTPSConnection

from xml.etree.ElementTree import fromstring
from urllib.parse import quote
from pymongo.mongo_client import MongoClient
from test.libregrtest.cmdline import DESCRIPTION
from _datetime import datetime



def clean(s):
    s = s.replace("&apos;", "").replace("&amp;", "")
    s = s.replace("<b>", "").replace("</b>", "")
    s = s.replace("&quot;","")
    s = s.replace("&lt;","").replace("&gt;","")
    return s


con=MongoClient("195.168.9.65")
db=con.xe#con.db명



huc = HTTPSConnection("openapi.naver.com")
query = "과학" 
query = quote(query)
dsp=100;
dsp=str(dsp)

h = {"X-Naver-Client-Id":"{id}",
   "X-Naver-Client-Secret":"{api key}"}
for start in range(1,1000,99):
    start=str(start)
    huc.request("GET", "/v1/search/news.xml?query=" + query+"&display="+dsp+"&start="+start,headers=h)
    resBody = huc.getresponse().read()
    
    news = fromstring(resBody).getiterator("item")





    for n in news:
        try:
            tt = clean(n.find("title").text)
         
            dsc = clean(n.find("description").text)
            pbd=n.find("pubDate").text
            parsed_date = datetime.strptime(pbd, "%a, %d %b %Y %H:%M:%S %z")
            formatted_date = parsed_date.strftime("%Y-%m-%d %H:%M:%S")
            combined_datetime = parsed_date.replace(second=0, microsecond=0)
            l=n.find("link").text
            d = {"_id":l,"title": tt, "description": dsc,"pubdate":combined_datetime}
                
            result=db.Trace_News_it.insert_one(d)  
            
            if result.acknowledged:
                print("성공")
        except Exception as e:
            continue
    
huc.close()   
    
con.close()
