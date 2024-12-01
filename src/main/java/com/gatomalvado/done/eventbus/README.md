Problem Statement
Design an In-Memory Distributed Queue like Kafka.

Requirements
The queue should be in-memory and should not require access to the file system.
There can be multiple topics in the queue.
A (string) message can be published on a topic by a producer/publisher and consumers/subscribers can subscribe to the topic to receive the messages.
There can be multiple producers and consumers.
A producer can publish to multiple topics.
A consumer can listen to multiple topics.
The consumer should print "<consumer_id> received <message>" on receiving the message.
The queue system should be multi-threaded, i.e., messages can be produced or consumed in parallel by different producers/consumers.
Input/Output Format
You do not need to take input from the command-line.
Create 2 topics: topic1 and topic2
Create 2 producers: producer1, and producer2
Create 5 consumers: consumer1, consumer2, consumer3, consumer4, and consumer5
Make all 5 consumers subscribe to topic1
Make consumers 1, 3, and 4 subscribe to topic2
Make producer1 publish message "Message 1" to topic1
Make producer1 publish message "Message 2" to topic1
Make producer2 publish message "Message 3" to topic1
Make producer1 publish message "Message 4" to topic2
Make producer2 publish message "Message 5" to topic2