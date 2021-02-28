package utils;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class AbstractSyntaxTreeReader {

    private AbstractSyntaxTreeReader() {
    }

    public static List<MethodDeclaration> getAllMethods(String filePath) {
        CompilationUnit compilationUnit = getCompilationUnit(filePath);
        return compilationUnit.findAll(MethodDeclaration.class);
    }

    public static List<ConstructorDeclaration> getAllConstructors(String filePath) {
        CompilationUnit compilationUnit = getCompilationUnit(filePath);
        return compilationUnit.findAll(ConstructorDeclaration.class);
    }

    private static CompilationUnit getCompilationUnit(String filePath) {
        StaticJavaParser.setConfiguration(getParserConfiguration());
        CompilationUnit compilationUnit = null;
        try {
            compilationUnit = StaticJavaParser.parse(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return compilationUnit;
    }

    private static ParserConfiguration getParserConfiguration() {
        return new ParserConfiguration().setAttributeComments(false);
    }

}

