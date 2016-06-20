package classloader;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/13.
 */
public class HelloClassGeneratorTest {
    public static void main(String[] args) throws IOException {
        ClassWriter classWriter = new ClassWriter(0);

        classWriter.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC, "Hello", null, "java/lang/Object", null);

        // 构造方法
        MethodVisitor constructorMethod = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        constructorMethod.visitCode();
        constructorMethod.visitVarInsn(Opcodes.ALOAD, 0);
        constructorMethod.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        constructorMethod.visitInsn(Opcodes.RETURN);
        constructorMethod.visitMaxs(1, 1);
        constructorMethod.visitEnd();

        // sayHello方法
        MethodVisitor helloMethod = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "sayHello", "(Ljava/lang/String;)Ljava/lang/String;", null, null);
        helloMethod.visitCode();
        helloMethod.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        helloMethod.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
        helloMethod.visitInsn(Opcodes.DUP);
        helloMethod.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        helloMethod.visitVarInsn(Opcodes.ALOAD, 0);
        helloMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
        helloMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getName", "()Ljava/lang/String;", false);
        helloMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        helloMethod.visitLdcInsn(" -> ");
        helloMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        helloMethod.visitVarInsn(Opcodes.ALOAD, 1);
        helloMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        helloMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        helloMethod.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        helloMethod.visitVarInsn(Opcodes.ALOAD, 1);
        helloMethod.visitInsn(Opcodes.ARETURN);
        helloMethod.visitMaxs(3, 2);
        helloMethod.visitEnd();

        classWriter.visitEnd();

        byte[] data = classWriter.toByteArray();
        File file = new File("D://Hello.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(data);
        fileOutputStream.close();
    }
}
