import namePic
import random

path = "E:\sources\cartoon_online\cover"

# print( namePic.changeName( path ) )

fileName = [ '0a9235ad5de54a9cb6c946e44.png', '19ce48a915394d64b5b3ff47b.png',
         '708b537cfb3140879739a1831.png', '9f0f4feed17f4e279cb121213.png',
         'a36b94aa82f7488782fac5aed.png', 'b90a5ae8359a42d8a3516ca56.png', 
         'd397768d7c3c444ca6115ec67.png', 'd58409e186094c3da1b61adef.png', 
         'e76480c151634c0483baf3954.png']
name = [
    '青春猪头少年不会梦到兔女郎学姐', '魔女之旅',
    '萤火之森', '葬送的芙莉莲',
    '你的名字', '秒速五厘米',
    'ReLIFE', '鬼灭之刃',
    '青春猪头少年不会梦到兔女郎学姐-剧场版'
]

if __name__ == "__main__":
    with open( "./insert.sql", 'w' ) as f:
        f.write("DELETE FROM cartoon;\n")
        for i in range( len( fileName ) ):
            ss = 'INSERT INTO cartoon VALUES(\"'
            ss += namePic.getName(7) + '\",\"'
            ss += name[i] + '\",\"'
            ss += "/cover/" + fileName[i] + '\",'
            ss += str(random.randint(0, 2))
            ss += ");\n\n";
            f.write( ss )
            
