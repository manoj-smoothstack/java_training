# Assignment: Combine retry with a skip

## Implement a service.

Build a service that will generate random numbers between 1 and 1000 and return them to the client
upon request through the endpoint that resembles:

```
http://<host>:<port>/random/
```

## Implement a client.

The client should keep retrying until it gets a prime number.

Use a retry limit of 10 with a skip limit of 2.
