# Create a CommandSet for your remote control
# GPIO for the IR receiver: 23
# GPIO for the IR transmitter: 22
from ircodec.command import CommandSet
import paho.mqtt.client as mqtt
import time
from os import system
system("sudo pigpiod")


controller = CommandSet.load('led.json')

def on_message(client, userdata, message):
    msg = str(message.payload.decode("utf-8"))
    print(msg)

    if msg == "ON":
        controller.emit('on')
        time.sleep(0.1)
        controller.emit('%')
        time.sleep(0.1)
        for a in range(20):
            controller.emit('up')
            time.sleep(0.1)
        return

    elif msg=="OFF":
        controller.emit('off')
        return

    elif msg == "SLEEP":
        controller.emit('sleep')
        return
    elif msg == "UP":
        controller.emit('up')
        return
    elif msg == "DOWN":
        controller.emit('down')
        return
    elif msg == "%":
        controller.emit('%')
        return
    elif msg == "1M":
        controller.emit('1m')
        return
    elif msg == "10M":
        controller.emit('10m')
        return
    elif msg == "20M":
        controller.emit('20m')
        return
    elif msg == "30M":
        controller.emit('30m')
        return

        
    
broker_address = "localhost"
client1 = mqtt.Client() 
client1.connect(broker_address) 
client1.subscribe("led") 
client1.on_message = on_message
client1.loop_forever()



#controller.emit('volume_up')

# Remove the volume up command
# controller.remove('volume_up')

# Examine the contents of the CommandSet
# controller
# CommandSet(emitter=22, receiver=23, description="TV remote")
# {}

# Save to JSON
# controller.save_as('tv.json')
# while True:
#     data = input("버튼이름: ")
#     if data == "end":
#         break

#     controller.add(data)
# controller.save_as('led.json')
# Load from JSON
# controller = CommandSet.load('led.json')
# while True:
#     data = input("input : ")
#     if data=='end':
#         break
#     controller.emit(data)
