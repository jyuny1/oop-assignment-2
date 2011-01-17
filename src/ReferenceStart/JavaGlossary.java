
/**
 * This class implements a Java glossary with functionality to 
 * support the search for terms. It represents a simple solution
 * to handle a number of different glossaries, it could be eliminated
 * if we use files to store the terms of the glossaries.
 * 
 * @author Pablo Romero
 * @version 2009.08.28
 */
public class JavaGlossary extends Glossary
{
    /**
     * Constructor for objects of class JavaGlossary
     */
    public JavaGlossary() 
    {
        addTerm("abstract class","A class that contains one or more abstract methods, and therefore can never be instantiated. Abstract classes are defined so that other classes can extend them and make them concrete by implementing the abstract methods");
        addTerm("abstract method","A method that has no implementation");
        addTerm("Abstract Window Toolkit (AWT)","A collection of graphical user interface (GUI) components that were implemented using native-platform versions of the components. These components provide that subset of functionality which is common to all native platforms. Largely supplanted by the Project Swing component set. See also Swing");
        addTerm("api","Application Programming Interface. The specification of how a programmer writing an application accesses the behavior and state of classes and objects");
        addTerm("arithmetic exception","A division by 0 has been perfomed. If it occurred in a paint method, likely this occurred as a result of creating a Font with zero height");
        addTerm("argument","A data item specified in a method call. An argument can be a literal value, a variable, or an expression");
        addTerm("array","A collection of data items, all of the same type, in which each item's position is uniquely designated by an integer");
        addTerm("array index out of bounds exception","You have used an array index outside the allowable range. There may be several array references x[i] in the same line. Don't leap to conclusions about which one caused the trouble. Arrays are indexed from 0 to x.length-1, not 1 to x.length");
        addTerm("boolean","Refers to an expression or variable that can have only a true or false value. The Java programming language provides the boolean type and the literal values true and false");
        addTerm("casting","Explicit conversion from one data type to another");
        addTerm("class","In the Java programming language, a type that defines the implementation of a particular kind of object. A class definition defines instance and class variables and methods, as well as specifying the interfaces the class implements and the immediate superclass of the class. If the superclass is not explicitly specified, the superclass will implicitly be Object");
        addTerm("class not found exception","This occurs only at run time. Usually it means a class that was present during compilation has disappeared, or the classpath has changed so it is no longer accessible. It could also happen if you dynamically load a class and the class is not on the classpath");
        addTerm("classpath","An environmental variable which tells the Java virtual machine1 and Java technology-based applications where to find the class libraries, including user-defined class libraries");
        addTerm("compiler","A program to translate source code into code to be executed by a computer. The Java compiler translates source code written in the Java programming language into bytecode for the Java virtual machine1. See also interpreter");
        addTerm("constructor","A pseudo-method that creates an object. In the Java programming language, constructors are instance methods with the same name as their class. Constructors are invoked using the new keyword");
        addTerm("deprecation","Refers to a class, interface, constructor, method or field that is no longer recommended, and may cease to exist in a future version");
        addTerm("encapsulation","The localization of knowledge within a module. Because objects encapsulate data and implementation, the user of an object can view the object as a black box that provides services. Instance variables and methods can be added, deleted, or changed, but as long as the services provided by the object remain the same, code that uses the object can continue to use it without being rewritten. See also instance variable,  instance method");
        addTerm("exception","An event during program execution that prevents the program from continuing normally; generally, an error. The Java programming language supports exceptions with the try, catch, and throw keywords");
        addTerm("extends","Class X extends class Y to add functionality, either by adding fields or methods to class Y, or by overriding methods of class Y. An interface extends another interface by adding methods. Class X is said to be a subclass of class Y");
        addTerm("field","A data member of a class. Unless specified otherwise, a field is not static");
        addTerm("final","A Java keyword. You define an entity once and cannot change it or derive from it later. More specifically: a final class cannot be subclassed, a final method cannot be overridden and a final variable cannot change from its initialized value");
        addTerm("for","A Java keyword used to declare a loop that reiterates statements. The programmer can specify the statements to be executed, exit conditions, and initialization variables for the loop");
        addTerm("formal parameter list","The parameters specified in the definition of a particular method");
        addTerm("if","A Java keyword used to conduct a conditional test and execute a block of statements if the test evaluates to true");
        addTerm("inheritance","The concept of classes automatically containing the variables and methods defined in their supertypes");
        addTerm("instance","An object of a particular class. In programs written in the Java programming language, an instance of a class is created using the new operator followed by the class name");
        addTerm("instance method","Any method that is invoked with respect to an instance of a class");
        addTerm("instance variable","Any item of data that is associated with a particular object. Each instance of a class has its own copy of the instance variables defined in the class. Also called a field");
        addTerm("local variable","A data item known within a block, but inaccessible to code outside the block. For example, any variable defined within a method is a local variable and can't be used outside the method");
        addTerm("method","A function defined in a class. See also instance method, class method. Unless specified otherwise, a method is not static");
        addTerm("object","The principal building blocks of object-oriented programs. Each object is a programming unit consisting of data (instance variables) and functionality (instance methods)");
        addTerm("object-oriented design","A software design method that models the characteristics of abstract or real objects using classes and objects");
        addTerm("overriding","Providing a different implementation of a method in a subclass of the class that originally defined the method");
        addTerm("out of bounds exception","see array index out of bounds exception");
        addTerm("primitive type","A variable data type in which the variable's value is of the appropriate size and format for its type: a number, a character, or a boolean value");
        addTerm("public","A Java keyword used in a method or variable declaration. It signifies that the method or variable can be accessed by elements residing in other classes");
        addTerm("scope","A characteristic of an identifier that determines where the identifier can be used. Most identifiers in the Java programming environment have either class or local scope. Instance and class variables and methods have class scope; they can be used outside the class and its subclasses only by prefixing them with an instance of the class or (for class variables and methods) with the class name. All other variables are declared within methods and have local scope; they can be used only within the enclosing block");
        addTerm("subarray","An array that is inside another array");
        addTerm("subclass","A class that is derived from a particular class, perhaps with one or more classes in between. See also superclass");
        addTerm("subtype","If type X extends or implements type Y, then X is a subtype of Y");
        addTerm("superclass","A class from which a particular class is derived, perhaps with one or more classes in between. See also subclass");
        addTerm("super","A Java keyword used to access members of a class inherited by the class in which it appears");
        addTerm("supertype","The supertypes of a type are all the interfaces and classes that are extended or implemented by that type");
        addTerm("swing","A collection of graphical user interface (GUI) components that runs uniformly on any native platform which supports the Java virtual machine*. Because they are written entirely in the Java programming language, these components may provide functionality above and beyond that provided by native-platform equivalents. (Contrast with AWT)");
    }
}
