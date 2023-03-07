package com.orcl.thread;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: CLH 锁，参考：https://mp.weixin.qq.com/s/jEx-4XhNGOFdCo4Nou5tqg
 * @author: orcl
 * @since: 2023-03-07 14:19
 * @history: 2023-03-07 14:19 created by orcl
 */
public class CLH {

    private final ThreadLocal<Node> node = ThreadLocal.withInitial(Node::new);

    public final AtomicReference<Node> tail = new AtomicReference<>(new Node());

    private static class Node {
        private volatile boolean locked;
    }

    private void lock() {
        Node node = this.node.get();
        node.locked = true;
        Node pre = this.tail.getAndSet(node);
        while (pre.locked) ;
    }

    public void unlock() {
        final Node node = this.node.get();
        node.locked = false;
        this.node.set(new Node());
    }

}
