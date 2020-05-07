package cn.hust.spring.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {

    @Value("zhangsan")
    private String name;

    @Value("#{2 * 23}")
    private Integer age;

    @Value("${person.nickName}")// 从外部的配置文件中获取值并赋值
    private String nickName;


    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
