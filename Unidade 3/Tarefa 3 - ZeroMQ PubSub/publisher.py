import time
import zmq

def main():
    # Prepare our context and publisher
    context = zmq.Context()
    publisher = context.socket(zmq.PUB)
    publisher.bind("tcp://*:4000")

    while True:
        # Write two messages, each with an envelope and content
        num = '10'
        publisher.send_multipart([b"1", b"islaine@operador@1000"])
        time.sleep(2)

        publisher.send_multipart([b"2", b"islaine@feminino@21"])
        time.sleep(2)

        publisher.send_multipart([b"3", b"8@5@5"])
        time.sleep(2)

        publisher.send_multipart([b"4", b"1.62@feminino"])
        time.sleep(2)

        publisher.send_multipart([b"5", b"25"])
        time.sleep(2)

        publisher.send_multipart([b"6", b"islaine@A@1500@2"])
        time.sleep(2)

        publisher.send_multipart([b"7", b"35@20"])
        time.sleep(2)

        publisher.send_multipart([b"8", b"650"])
        time.sleep(2)

    # We never get here but clean up anyhow
    publisher.close()
    context.term()

if __name__ == "__main__":
    main()