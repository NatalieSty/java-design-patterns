package com.iluwatar.singleton;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * This class provides several test case that test singleton construction.
 *
 * The first proves that multiple calls to the singleton getInstance object are the same when called in the SAME thread.
 * The second proves that multiple calls to the singleton getInstance object are the same when called in the DIFFERENT thread.
 * The third proves that there is a hole if the singleton is created reflectively
 *
 */
public class LazyLoadedSingletonThreadSafetyTest {

    private static final int NUM_THREADS = 5;
    private List<ThreadSafeLazyLoadedIvoryTower> threadObjects = new ArrayList<>();

    //NullObject class so Callable has to return something
    private class NullObject{private NullObject(){}}

    @Test
    public void test_MultipleCallsReturnTheSameObjectInSameThread() {
        //Create several instances in the same calling thread
        ThreadSafeLazyLoadedIvoryTower instance1 = ThreadSafeLazyLoadedIvoryTower.getInstance();
        ThreadSafeLazyLoadedIvoryTower instance2 = ThreadSafeLazyLoadedIvoryTower.getInstance();
        ThreadSafeLazyLoadedIvoryTower instance3 = ThreadSafeLazyLoadedIvoryTower.getInstance();
        //now check they are equal
        assertEquals(instance1, instance1);
        assertEquals(instance1, instance2);
        assertEquals(instance2, instance3);
        assertEquals(instance1, instance3);
    }

    @Test
    public void test_MultipleCallsReturnTheSameObjectInDifferentThreads() throws InterruptedException, ExecutionException {
        {//create several threads and inside each callable instantiate the singleton class
            ExecutorService executorService = Executors.newSingleThreadExecutor();

            List<Callable<NullObject>> threadList = new ArrayList<>();
            for (int i = 0; i < NUM_THREADS; i++) {
                threadList.add(new SingletonCreatingThread());
            }

            ExecutorService service = Executors.newCachedThreadPool();
            List<Future<NullObject>> results = service.invokeAll(threadList);

            //wait for all of the threads to complete
            for (Future res : results) {
                res.get();
            }

            //tidy up the executor
            executorService.shutdown();
        }
        {//now check the contents that were added to threadObjects by each thread
            assertEquals(NUM_THREADS, threadObjects.size());
            assertEquals(threadObjects.get(0), threadObjects.get(1));
            assertEquals(threadObjects.get(1), threadObjects.get(2));
            assertEquals(threadObjects.get(2), threadObjects.get(3));
            assertEquals(threadObjects.get(3), threadObjects.get(4));
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void test_HoleInSingletonCreationIfUsingReflection() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field[] f = ThreadSafeLazyLoadedIvoryTower.class.getDeclaredFields();
        assertEquals("One field only in ThreadSafeLazyLoadedIvoryTower", 1, f.length);
        f[0].setAccessible(true);

        {//reflectively create an object - the singleton field is null
            Class lazyIvoryTowerClazz = Class.forName("com.iluwatar.singleton.ThreadSafeLazyLoadedIvoryTower");
            Constructor<ThreadSafeLazyLoadedIvoryTower> constructor = lazyIvoryTowerClazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            ThreadSafeLazyLoadedIvoryTower instance = constructor.newInstance();
            assertNull(f[0].get(instance));
        }

        //instantiate the singleton but when we do the below code we are creating a new object where it is set to null still
        IvoryTower.getInstance();

        {//reflectively create an object - the singleton field is null as a new object is created
            Class lazyIvoryTowerClazz = Class.forName("com.iluwatar.singleton.ThreadSafeLazyLoadedIvoryTower");
            Constructor<ThreadSafeLazyLoadedIvoryTower> constructor = lazyIvoryTowerClazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            ThreadSafeLazyLoadedIvoryTower instance = constructor.newInstance();
            assertNull(f[0].get(instance));
        }
    }

    private class SingletonCreatingThread implements Callable<NullObject> {
        @Override
        public NullObject call() {
            //instantiate the thread safety class and add to list to test afterwards
            ThreadSafeLazyLoadedIvoryTower instance = ThreadSafeLazyLoadedIvoryTower.getInstance();
            threadObjects.add(instance);
            return new NullObject();//return null object (cannot return Void)
        }
    }
}
