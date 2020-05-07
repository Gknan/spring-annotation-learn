package cn.hust.spring.component;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class MyTypeFilter implements TypeFilter {

    /**
     *
     * @param metadataReader 读取当前扫描类的源信息
     * @param metadataReaderFactory 读取其他类的原信息的工厂
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类注解的信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前类的信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前类所在的资源信息(类的路径)
        Resource resource = metadataReader.getResource();

        System.out.println("annotationMetadata: " + annotationMetadata);
        System.out.println("classMetadata" + classMetadata);
        System.out.println("resource" + resource);

        System.out.println("=====================");

        // 放行类名字中包含 er 的类
//        if (classMetadata.getClassName().contains("er")) return true;

        return true;
    }
}
