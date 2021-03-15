package com.mingzhi.demo.atomic;

import java.util.concurrent.atomic.*;

public class AtomicDemo {

    public static void main(String[] args) {
        //原子更新基本类型
        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.compareAndSet(1,2);
        System.out.println(atomicInteger.get());

        //原子更新数组
        int[] ints = new int[]{1,2,3};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(ints);
        atomicIntegerArray.compareAndSet(0,1,54);
        System.out.println(atomicIntegerArray.get(0));

        //原子更新属性
        Student student = new Student(1,"dmz");
        AtomicIntegerFieldUpdater<Student> integerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Student.class, "id");
        integerFieldUpdater.compareAndSet(student,1,45);
        System.out.println(student.getId());

        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Student.class,String.class,"name");
        atomicReferenceFieldUpdater.compareAndSet(student,"dmz","dmin");
        System.out.println(student.getName());

        //原子更新引用
        Student student1 = new Student(1,"dmz");
        Student student2 = new Student(2,"dmin123");
        AtomicReference atomicReference = new AtomicReference();
        atomicReference.set(student1);
        atomicReference.compareAndSet(student1,student2);
        Student student3 = (Student) atomicReference.get();
        System.out.println(student3.getName());
    }

}

class Student{
    public volatile int id;
    public volatile String name;

    public Student(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}