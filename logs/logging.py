import time
from send_email import send

f = open('spring.log', 'r')
while True:
    line = ''
    while len(line) == 0 or line[-1] != '\n':
        tail = f.readline()
        if tail == '':
            time.sleep(0.1)
            continue
        line += tail
        if (tail.find('ERROR') != -1):
            send(line)