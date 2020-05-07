package cn.hust.spring.bean;

public class Color {

    public String name;

    public Color(String name) {
        this.name = name;
    }

    // 如果注释掉无参构造，报错UnsatisfiedDependencyException
    // 说明 @Import 是根据无参构造器创建实例并加入容器的
    public Color() {
    }

    @Override
    public String toString() {
        return "Color{" +
                "name='" + name + '\'' +
                '}';
    }
}
