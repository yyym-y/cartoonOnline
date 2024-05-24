import os
import random
import namePic

oriDir = "E:/sources/cartoon/"
finalDir = "E:/sources/cartoon_online/video/"
keyMap = {}
order1 = ['ffmpeg -y -i \"', '', '\" -vcodec copy -acodec copy -bsf:v h264_mp4toannexb \"', '', '\"']
order2 = ['ffmpeg -i \"', '', '\" -c copy -map 0 -f segment -segment_list \"', '', '\" -segment_time 10 \"', '', '\"']

def init():
    keyMap["青春猪头少年不会梦到兔女郎学姐"] = "53c3270"
    keyMap["魔女之旅"] = "32c8841"
    keyMap["萤火之森"] = "5c01c97"
    keyMap["葬送的芙莉莲"] = "4dc2dd8"
    keyMap["你的名字"] = "af1de92"
    keyMap["秒速五厘米"] = "e28e44d"
    keyMap["ReLIFE"] = "0bdaac4"
    keyMap["鬼灭之刃"] = "940e098"
    keyMap["青春猪头少年不会梦到兔女郎学姐-剧场版"] = "7f646d5"

def getPlayNum():
    insert_str = lambda id, year, month, num : 'INSERT INTO play_infos VALUES(\"' + id + '\",' + year + ',' + month + ',' + num + ');\n'
    with open('./ttt.sql', 'w') as f:
        for (_, value) in keyMap.items():
            for i in range(12):
                f.write(insert_str(value, "2024", str(i + 1), str(random.randint(20, 200))))
    

def getRamdonPlayNum():
    insert_str = lambda id, year, month, num : 'INSERT INTO play_infos VALUES(\"' + id + '\",' + year + ',' + month + ',' + num + ');\n'
    with open('./ttt2.sql', 'w') as ff:
        for (_, value) in keyMap.items():
            for i in range(2019, 2024):
                for j in range(12):
                    ff.write(insert_str(value, str(i), str(j + 1), str(random.randint(20, 200))))



def getInsert( cartoonID, num, m3u8Url ):
    ss = 'INSERT INTO m3u8_info VALUES(\"' + cartoonID + '\",\"'
    ss += str(num) + '\",\"' + m3u8Url + '\");\n'
    return ss

def changeTo( cartoonID, ori_file, final_file_name, num ):
    pp = finalDir + cartoonID
    if not os.path.exists(pp):
        os.makedirs(pp)
    pp += '/' + str(num)
    if not os.path.exists(pp):
        os.makedirs(pp)
    total_ts_file_name = final_file_name + '.ts'
    order1[1] = ori_file
    order1[3] = pp + '/' + total_ts_file_name
    os.system( "".join(order1) )
    order2[1] = pp + '/' + total_ts_file_name
    order2[3] = pp + '/' + final_file_name + '.m3u8'
    order2[5] = pp + '/' + final_file_name + "_%3d.ts"
    print("".join(order2))
    os.system( "".join(order2) )
    os.remove( pp + '/' + total_ts_file_name )

def processDir( name ):
    if name not in keyMap:
        return
    cartoonID = keyMap[name]
    newPath = oriDir + name
    fileInfo = os.listdir( newPath )
    sorted(fileInfo)
    with open("./info.txt", 'w') as ff:
        for i in range( len( fileInfo ) ):
            final_file_name = namePic.getName()
            changeTo( cartoonID, newPath + '/' + fileInfo[i], final_file_name, i + 1 )
            ff.write( getInsert( cartoonID, i + 1, final_file_name + ".m3u8" ) )

def getAllInfo():
    fileInfo = os.listdir(finalDir)
    print(fileInfo)
    with open("./tt.txt", 'w') as f:
        for pr in fileInfo:
            numList = os.listdir(finalDir + '/' + pr)
            for i in numList:
                fileList = os.listdir(finalDir + '/' + pr + '/' + i)
                for tem in fileList:
                    if(tem.endswith(".m3u8")):
                        f.write(getInsert( pr, i, tem ))
                        break
                    

init()
getRamdonPlayNum()


        

