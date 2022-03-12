import paho.mqtt.client as mqtt
from py_irsend import irsend

def on_message(client, userdata, message):
    msg = str(message.payload.decode("utf-8"))
    print(msg)

    if msg=="OFF":
        irsend.send_once('led',['OFF'])
    elif msg == "ON":
        irsend.send_once('led',['ON'])
        irsend.send_once('led',['%'])
        for a in range(20):
            irsend.send_once('led',['UP'])
    elif msg == "SLEEP":
        irsend.send_once('led',['SLEEP'])
    elif msg == "UP":
        irsend.send_once('led',['UP'])
    elif msg == "DOWN":
        irsend.send_once('led',['DOWN'])
    elif msg == "%":
        irsend.send_once('led',['%'])
    elif msg == "1M":
        irsend.send_once('led',['1M'])
    elif msg == "10M":
        irsend.send_once('led',['10M'])
    elif msg == "20M":
        irsend.send_once('led',['20M'])
    elif msg == "30M":
        irsend.send_once('led',['30M'])

        
    
broker_address = "localhost"
client1 = mqtt.Client() 
client1.connect(broker_address) 
client1.subscribe("led") 
client1.on_message = on_message
client1.loop_forever()