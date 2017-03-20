package com.example.reactor.demo;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.Environment;
import reactor.rx.Streams;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Enan on 17/3/20.
 */
public class ReactorPublisher {

    public static void main(String[] args) {
        Environment.initialize();

        Publisher<String> fileStream = new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> subscriber) {
                final File file = new File(this.getClass().getClassLoader().getResource("test.json").getFile());
//                final File file = new File("123.txt");
                try {
                    final BufferedReader is = new BufferedReader(new FileReader(file));

                    subscriber.onSubscribe(new Subscription() {
                        final AtomicBoolean terminated = new AtomicBoolean(false);

                        @Override
                        public void request(long n) {
                            long requestCursor = 0L;
                            try {
                                String line;
                                while ((requestCursor++ < n || n == Long.MAX_VALUE) && !terminated.get()) {
                                    line = is.readLine();
                                    if (line != null) {
                                        subscriber.onNext(line);
                                    } else {
                                        if (terminate()) {
                                            subscriber.onComplete();
                                        }
                                        return;
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {

                            }
                        }

                        @Override
                        public void cancel() {
                            terminate();
                        }

                        private boolean terminate() {
                            if (terminated.compareAndSet(false, true)) {
                                try {
                                    is.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            }
                            return false;
                        }
                    });
                } catch (FileNotFoundException e) {
                    Streams.<String, FileNotFoundException> fail(e).subscribe(subscriber);
                } finally {
                }
            }
        };

        Streams.wrap(fileStream)
                .capacity(4L)
                .consumeOn(
                        Environment.sharedDispatcher(),
                        System.out::println,
                        Throwable::printStackTrace,
                        nothing -> System.out.println("## EOF ##")
                );

//        Publisher<String> fileStream1 = PublisherFactory.create(
//                (n, sub) -> {
//                    String line;
//                    final BufferedReader inputStream = sub.context();
//                }
//        )
    }


}
