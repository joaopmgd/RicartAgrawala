# RicartAgrawala
Ricart Agrawala distributed algorithm.

"Ricart and Agrawala's algorithm requires that there be a total ordering of all events in the system. 
That is, for any pair of events, such as messages, it must be unambiguous which one actually happened first. 
Lamport's algorithm presented in Sec. 6.2.1 is one way to achieve this ordering and can be used to provide time- stamps for distributed mutual exclusion.
The algorithm works as follows. 
When a process wants to access a shared re- source, it builds a message containing the name of the resource, its process num- ber, and the current (logical) time. 
It then sends the message to all other proc- esses, conceptually including itself. 
The sending of messages is assumed to be reliable; that is, no message is lost.
When a process receives a request message from another process, the action it takes depends on its own state with respect to the resource named in the message.
Three different cases have to be clearly distinguished:

1. If the receiver is not accessing the resource and does not want to ac-
cess it, it sends back an OK message to the sender.
2. If the receiver already has access to the resource, it simply does not
reply. Instead, it queues the request.
3. If the receiver wants to access the resource as well but has not yet done so, it compares the timestamp of the incoming message with me. one contained in the message that it has sent everyone. 
The lowest one wins. 
If the incoming message has a lower timestamp, the re- ceiver sends back an OK message. 
If its own message has a lower timestamp, the receiver queues the incoming request and sends noth- ing."

Tanenbaum, A. S., Steen M. A. (October 12, 2006) Distributed Systems: Principles and Paradigms. Pearson, 2 edition
