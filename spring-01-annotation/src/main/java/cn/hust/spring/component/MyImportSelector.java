package cn.hust.spring.component;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    // 将返回的全类名的数组按名称注册到 IOC 中
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {


        return new String[]{"cn.hust.spring.bean.Dog"};
    }
}
