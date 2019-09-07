package zcf.mybatis.generator.plugins;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * @author zhangchongfei
 */
public class LombokPlugin extends PluginAdapter {
    private String javadoc;
    private FullyQualifiedJavaType repoAnnotation;
    private FullyQualifiedJavaType dataAnnotation;
    private FullyQualifiedJavaType builderAnnotation;
    private FullyQualifiedJavaType noArgsAnnotation;
    private FullyQualifiedJavaType allArgsAnnotation;

    public LombokPlugin() {
        repoAnnotation = new FullyQualifiedJavaType("org.springframework.stereotype.Repository");
        dataAnnotation = new FullyQualifiedJavaType("lombok.Data");
        builderAnnotation = new FullyQualifiedJavaType("lombok.Builder");
        noArgsAnnotation = new FullyQualifiedJavaType("lombok.NoArgsConstructor");
        allArgsAnnotation = new FullyQualifiedJavaType("lombok.AllArgsConstructor");
        javadoc = "/**\n" +
                  " * Created with Mybatis Generator Plugin\n" +
                  " *\n" +
                  " * @author zcf\n" +
                  " * Created on " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm")) + ".\n" +
                  " */";
    }
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        addDataAnnotation(topLevelClass);
        return true;
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {
        addDataAnnotation(topLevelClass);
        return true;
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addDataAnnotation(topLevelClass);
        return true;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method,
                                              TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn,
                                              IntrospectedTable introspectedTable,
                                              ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addRepository(interfaze);
        return true;
    }

    private void addRepository(Interface interfaze) {
        interfaze.addImportedType(repoAnnotation);
        interfaze.addAnnotation("@Repository");
        interfaze.addJavaDocLine(javadoc);
    }

    private void addDataAnnotation(TopLevelClass topLevelClass) {
        topLevelClass.addImportedType(dataAnnotation);
        topLevelClass.addImportedType(builderAnnotation);
        topLevelClass.addImportedType(noArgsAnnotation);
        topLevelClass.addImportedType(allArgsAnnotation);
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@Builder");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");
        topLevelClass.addJavaDocLine(javadoc);
    }

}
