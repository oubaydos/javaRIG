import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// may require the full path of the annotation
@SupportedAnnotationTypes("RandomInstanceGenerator")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class RandomInstanceGeneratorProcessor extends AbstractProcessor {


    @Override
    /**
     * for now will be processing only classes that explicitily uses setters ( not a SOURCE Retention Policy like lombok Setter 😞
     */
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedClasses = roundEnv.getElementsAnnotatedWith(RandomInstanceGenerator.class);
        for (Element annotatedClass : annotatedClasses) {
            List<? extends Element> setters = annotatedClass.getEnclosedElements().stream().filter(element -> element.getSimpleName().toString().startsWith("set")).toList();
            Map<String, String> setterMap = setters.stream().collect(Collectors.toMap(setter -> setter.getSimpleName().toString(), setter -> ((ExecutableType) setter.asType()).getParameterTypes().get(0).toString()));
            String className = ((TypeElement) setters.get(0).getEnclosingElement()).getQualifiedName().toString();

            try {
                writeBuilderFile(className, setterMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        for (Element element : roundEnv.getElementsAnnotatedWith(Buildable.class)) {
//            String packageName = elements.getPackageOf(element).getQualifiedName().toString();
//            ClassName builderClass =
//                    ClassName.get(packageName, String.format("%sBuilder", element.getSimpleName()));
//            List<FieldSpec> fieldSpecs = new ArrayList<>();
//            List<MethodSpec> setterSpecs = new ArrayList<>();
//
//            Class<BuilderField> builderFieldClass = BuilderField.class;
//            for (Element el : element.getEnclosedElements()) {
//                if (el.getAnnotation(builderFieldClass) != null) {
//                    if (el.getModifiers().contains(Modifier.PRIVATE)) {
//                        messager.printMessage(Diagnostic.Kind.ERROR,
//                                BuilderField.class.getName() + " cannot be added to a private field");
//                    }
//
//                    TypeName typeName = TypeName.get(el.asType());
//
//                    String name = el.getSimpleName().toString();
//
//                    fieldSpecs.add(FieldSpec.builder(typeName, name, Modifier.PRIVATE).build());
//
//                    setterSpecs.add(MethodSpec.methodBuilder(name)
//                            .addModifiers(Modifier.PUBLIC)
//                            .returns(builderClass)
//                            .addParameter(typeName, name)
//                            .addStatement("this.$N = $N", name, name)
//                            .addStatement("return this")
//                            .build());
//                }
//            }
//
//            TypeName targetType = TypeName.get(element.asType());
//            String targetName = "target";
//            MethodSpec.Builder builderMethodBuilder = MethodSpec.methodBuilder("build")
//                    .addModifiers(Modifier.PUBLIC)
//                    .returns(targetType)
//                    .addStatement("$1T $2N = new $1T()", targetType, targetName);
//            for (FieldSpec fieldSpec : fieldSpecs) {
//                builderMethodBuilder.addStatement("$1N.$2N = this.$2N", targetName, fieldSpec);
//            }
//            builderMethodBuilder.addStatement("return $N", targetName);
//            MethodSpec buildMethod = builderMethodBuilder.build();
//
//            TypeSpec classSpec = TypeSpec.classBuilder(builderClass.simpleName())
//                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
//                    .addFields(fieldSpecs)
//                    .addMethods(setterSpecs)
//                    .addMethod(buildMethod)
//                    .build();
//
//            JavaFile file = JavaFile.builder(builderClass.packageName(), classSpec).build();
//
//            try {
//                file.writeTo(filer);
//            } catch (IOException e) {
//                messager.printMessage(Diagnostic.Kind.ERROR,
//                        "Failed to create file " + builderClass.packageName());
//            }
//        }
        return true;
    }


    private void writeBuilderFile(String className, Map<String, String> setterMap) throws IOException {

        String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
        }

        String simpleClassName = className.substring(lastDot + 1);
        String builderClassName = "Random" + className + "Generator";
        String builderSimpleClassName = builderClassName.substring(lastDot + 1);
        String varName = "random" + simpleClassName.substring(0, 1).toLowerCase() + simpleClassName.substring(1);
        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(builderClassName);
        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

            if (packageName != null) {
                out.print("package ");
                out.print(packageName);
                out.println(";");
                out.println();
            }

            out.print("public class ");
            out.print(builderSimpleClassName);
            out.println(" {");
            out.println();

            out.print("    public static ");
            out.print(simpleClassName);
            out.print(" ");
            out.print(varName);
            out.println(";");

            out.print("    public ");
            out.print(builderSimpleClassName);
            out.println("() {");
            out.println("        try {");
            out.print("               this.");
            out.print(varName);
            out.print(" = new RandomGenerator().generateRandomObject(");
            out.print(simpleClassName);
            out.println(".class);");
            out.println("            }");
            out.println("        catch (Exception e) {}");
            //todo
            out.println("");
//            out.println("    }");
            out.println("    }");
            out.println();
            out.println("}");

        }
    }

}
