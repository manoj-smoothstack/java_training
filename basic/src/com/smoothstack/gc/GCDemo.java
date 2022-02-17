package com.smoothstack.gc;

import java.util.ArrayList;
import java.util.List;

// try with various GC algorithms
/*
// -XX:+UseSerialGC
Serial Collector has the smallest footprint of any of the collectors. The amount of data
structures the footprint required for this Garbage collector to run is very minimal. This
collector uses a single thread for both minor and major collection. This collector is
generally best for applications that are run on a shared
CPU with very small amounts of memory.

Let’s imagine that we have a quad-core CPU and four applications are running on it. If
your Garbage collector was not single-threaded and it is multi-threaded and at some point of
time our Garbage collector will start all four threads on four cores of the CPU and will
utilize that entire CPU for its own garbage collection and that is when the other applications
running on the CPU will suffer. If there are multiple applications running on a single CPU and
we have to ensure that our garbage collector doesn’t affect other cores or applications then
we can use Serial Garbage Collector.

Parallel/Throughput Collector (-XX:+UseParallelGC , -XX:+UseParallelOldGC)
The next collector to understand is called Parallel Collector. We have Parallel Collector and
Parallel Old Collector. we generally use only  Parallel Old Collector which uses multiple
threads for both Minor GC as well as Major GC. This collector doesn’t run concurrently
with the application. It is named Parallel because it has multiple threads of the Garbage
collection itself and all of those threads run parallelly but when the Garbage collector
is running all the user threads are stopped and if our application is deployed on a multicore
or multiprocessor systems this collector will give us the greatest throughput.

In the shortest amount of time, it will be able to collect the highest amount of garbage
possible. It stops the entire application and it could stop it for some time and it is
the best collector only for batch applications. In the batch applications, we do not care
about users, response times because there is no user on the front end and its batch
application and running behind the scenes. For batch applications, the Parallel Collector
will be the best one to use.

Concurrent Mark and Sweep Collector (-XX:+UseConcMarkSweepGC, -XX:+UseParNewGC)
This is called Concurrent Mark and Sweep. This collector runs concurrently with the
application to mark all the live objects. The amount of time that the application has to
stop is less so the latency of the application is also less. In the actual collection, it

still has STW pauses. STW is also called Stop the World pauses which means it stops the
application for a very small amount of time to do its actual garbage collection. This CMS
collector requires more footprint than Parallel Collector and it has more data structures to
take care of. It has less throughput than the Parallel Collector but the advantage is it has
smaller pauses than the Parallel Collector. This collector is the best-used collector for
 all the general Java applications

G1 Collector (-XX:+UseG1GC)  (Garbage First)
The improvement over the CMS collector is called the G1 collector. Instead of having specific
young and old generations for Heap this collector uses its entire heap and divides it
into multiple regions. It has more footprint and the advantage of this collector is it has
 the most predictable latency and this is the best feature of this collector. When we
 start our application, we can pass on this variable that the maximum pause time
 (maxTargetPauseTime) that our application can withstand say 10ms for example. The G1 collector
 will try to ensure that the Garbage collection is done only for 10 ms and even if there is
 some garbage left it will take care in the next cycle. If we want predictable latencies
 and pause times the G1 collector will be the best collector to use. This is the most commonly
 used collector for all the performance testing needs.

Shenandoah Collector (-XX:+UseShenandoahGC)
There is one more collector called Shenandoah Collector. This collector is an improvement upon
G1 collector wherein it requires a little higher footprint so it takes more data structures
 behind the scenes but it has even lower latency than G1 collector.

Shenandoah is an ultra-low pause time garbage collector that reduces GC pause times by
performing more garbage collection work concurrently with the running Java program. CMS
and G1 both perform concurrent marking of live objects. Shenandoah adds concurrent compaction.

Epsilon Collector (-XX:+UseEpsilonGC): The JDK's Do-Nothing Collector
The Epsilon garbage collector introduced in JDK 11 as an experimental collector and only
allocates memory. It cannot release any allocated memory, so the application is very
likely to crash because of an OutOfMemoryError. The GC in Epsilon collector does not do any
GC cycles and therefore does not care about the object graph, object marking, object
copying, etc. Once the Java heap is exhausted, no allocation is possible, no memory
reclamation is possible, and therefore the test will fail.

The most significant advantage is no GC overhead and the JVM does not pause to clear
the memory because it does not even try to release any memory. The Epsilon GC has been
added as a benchmark to test applications for performance, memory usage, latency, and
throughput improvements. Epsilon Collector helps us to calculate how long it takes for the
Java Virtual Machine (JVM) to exhaust all its memory and shut down. The Epsilon GC helps
test raw application performance with no interference from GC and no GC barriers embedded in
the code. The Epsilon GC feature is disabled by default in JDK 11 and we must enable to
use this collector.

For ultra-latency-sensitive applications, to completely know about memory allocations,
memory footprint, and to know how much of your program’s performance is affected by garbage
 collection Epsilon collector is the best one to use.

Z Garbage Collector (-XX:+UseZGC)
Z Garbage Collector (ZGC) is scalable, with low latency. It is a completely new GC, written
from scratch. It can mark memory, copy and relocate it, all concurrently and it can work with
heap memory, ranging from KBs to a large TB memory. As a concurrent garbage collector, ZGC
guarantees not to exceed application latency by 10 milliseconds, even for bigger heap sizes.
The ZGC was initially released as an experimental GC in Java 11 (Linux) and more changes are
expected over time in JDK 11, 13, and 14.

The stop-the-world pauses are limited to root scanning in ZGC. It uses load barriers with
colored pointers to perform concurrent operations when the threads are running and they are
used to keep track of heap usage. Colored pointers are one of the core concepts of ZGC and
it enables ZGC to find, mark, locate, and remap the objects. Compared to G1, ZGC has better
ways to deal with very large object allocations which are highly performant when it comes
to reclaiming memory and reallocating it and it is a single-generation GC.

ZGC divides memory into regions, also called ZPages. These ZPages can be dynamically created
and destroyed and can also be dynamically sized. Unlike other GCs, the physical heap regions
of ZGC can map into a bigger heap address space (which can include virtual memory) which
can avoid memory fragmentation issues.

Conclusion
In general, the Serial collector is for small devices or when we want to ensure that GC doesn’t
affect other applications or CPU’s, the Parallel Collector is best for batch applications,
the CMS collector is used for general applications, G1 collector is best for predictable
latencies and Shenandoah collector is an improvement over G1 which we will be able to use as
default collector in few versions of Java (From Java 11). Epsilon and ZGC collectors are t
he new experimental collectors introduced from JDK 11 and they are still undergoing a lot
many changes from release to release.
 */

class Element {
    public static int cntr = 0;

    public Element() {
        cntr++;
    }

    // perhaps we should use shutdown hook
    public void finalize() {  // we are using a deprecated method
        cntr--;
    }
}

public class GCDemo {
    public static void main(String[] args) {
        new GCDemo();
    }

    public GCDemo() {
        List<Element> elementList = new ArrayList<>();
        elementList.add(new Element());
        elementList.add(new Element());
        assert Element.cntr == 2;
        elementList.set(1, null);
        assert Element.cntr == 2; // this could sometimes fail
        System.gc(); // a hint to GC
        System.runFinalization(); // synchronous can slow down programs!
        assert Element.cntr == 1;
    }
}
