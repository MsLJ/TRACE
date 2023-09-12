# -*- coding:utf-8 -*-
from pymongo.mongo_client import MongoClient


con=MongoClient("195.168.9.65")
db=con.xe
f = open("C:/lee/work space/Project/tracenewstxts/TRACENEWSWORLD.txt", "a", encoding="utf-8")

result=db.Trace_News_world.find()
for n in result:
    f.write("%s"%n["title"])
    f.write(n["description"].replace("&quot;", ""))


print("끝")
f.close()
con.close()