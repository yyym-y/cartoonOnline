import uuid
import os

def getName( name_len = 25 ):
    return ("".join(str(uuid.uuid4()).split("-")))[: name_len]

def changeName( path ):
    fileDir = os.listdir( path )
    for pr in fileDir:
        new_name = getName() + pr[pr.rfind(".") :]
        os.rename( path + "/" + pr, path + "/" + new_name )
    return os.listdir( path )

# print(getName(7))
