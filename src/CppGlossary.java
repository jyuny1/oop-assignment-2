
/**
 * This class implements a C++ glossary with functionality to 
 * support the search for terms. It represents a simple solution
 * to handle a number of different glossaries, it could be eliminated
 * if we use files to store the terms of the glossaries.
 * 
 * @author Pablo Romero
 * @version 2009.08.28
 */
public class CppGlossary extends Glossary
{
    /**
     * Constructor for objects of class CppGlossary
     */
    public CppGlossary() 
    {
        addTerm("access control","a C++ mechanism for prohibiting or granting access to individual members of a class. See public, private, protected, and visibility"); 
        addTerm("access declaration","a way of controlling access to a specified member of a base class when it is used in a derived class"); 
        addTerm("access specifier","a way of labelling members of a class to specify what access is permitted. See public, private, and protected"); 
        addTerm("aggregate","an array or object of a class with no constructors, no private or protected members, no base classes, and no virtual functions. See initializer and initialization"); 
        addTerm("allocation","the process of giving memory space to an object. See dynamic storage, static storage, and deallocation"); 
        addTerm("argument","when calling a function, refers to the actual values passed to the function. See parameter"); 
        addTerm("argument matching","the process of determining which of a set of functions of a specified name matches given arguments in a function call"); 
        addTerm("array","an ordered and indexable sequence of values. C++ supports arrays of a single dimension (a vector) or of multiple dimensions"); 
        addTerm("asm","C++ keyword used to specify assembly language in the middle of C++ code"); 
        addTerm("assignment","the process of giving a value to a pre-existing object. See copy constructor and initialization"); 
        addTerm("assignment operator","an operator for doing assignment. See also copy constructor"); 
        addTerm("auto","a C++ keyword used to declare a stack-based local variable in a function. This is the default and is normally not needed. See storage class"); 
        addTerm("base class","a class that serves as a base for a derived class to inherit members from. See inheritance"); 
        addTerm("bit field","a member of a class that represents small integral values"); 
        addTerm("bitwise copy","to copy an object without regard to its structure or members. See memberwise copy"); 
        addTerm("bool","C++ keyword used to declare a Boolean data type"); 
        addTerm("break","C++ keyword used to specify a statement that is used to break out of a for or while loop or out of a switch statement"); 
        addTerm("call by reference","passing a pointer to an argument to a function. The function can then change the argument value. See call by value"); 
        addTerm("call by value","passing a copy of an argument to a function. The function cannot then change the argument value. C and C++ use call by value argument passing. But also see pointer and reference, also call by reference"); 
        addTerm("calling conventions","refers to the system-specific details of just how the arguments to a function are passed. For example, the order in which they are passed on the stack or placed in machine registers"); 
        addTerm("case","a C++ keyword used to denote an individual element of a switch statement"); 
        addTerm("cast","a way of doing explicit type conversion via a cast operator. See new-style cast, old-style cast"); 
        addTerm("catch","a C++ keyword used to declare an exception handler"); 
        addTerm("cerr","in C++ stream I/O, the standard error stream"); 
        addTerm("cfront","a C++ front end that translates C++ source code to C code, which is then compiled via a C compiler. Originally developed by AT&T Bell Labs in the mid-1980s"); 
        addTerm("char","a C++ keyword used to declare an object of character type. Often considered the same as a byte, though it is possible to have multi-byte characters"); 
        addTerm("cin","in C++ stream I/O, the standard input stream"); 
        addTerm("class","a C++ keyword used to declare the fundamental building block of C++ programs. A class has a tag, members, access control mechanisms, and so on"); 
        addTerm("const","a C++ keyword used to declare an object as constant or used to declare a constant parameter"); 
        addTerm("constant","a literal or variable declared as const"); 
        addTerm("constant expression","a C++ expression that can be evaluated by the compiler. Used to declare bounds for an array among other things"); 
        addTerm("deallocation","the processing of freeing memory space previously used by an object. See allocation"); 
        addTerm("for","a C++ keyword used to specify an iteration or looping statement"); 
        addTerm("function","a C++ entity that is a sequence of statements. It has its own scope, accepts a set of argument values, and returns a value on completion"); 
        addTerm("garbage collection","a way of automatically managing dynamic storage such that explicit cleanup of storage is not required. C++ does not have garbage collection. See new operator and delete operator"); 
        addTerm("global name","a name declared at global scope"); 
        addTerm("global namespace","the implicit namespace where global variables reside"); 
        addTerm("global scope","see global namespace"); 
        addTerm("global variable","a variable that is accessible throughout the whole program, whose lifetime is that of the program"); 
        addTerm("header file","a file containing class declarations, preprocessor directives, and so on, and included in a translation unit. It is expanded by the preprocessor"); 
        addTerm("local class","a class declared local to a function"); 
        addTerm("local variable","a variable declared local to a function"); 
        addTerm("null pointer","a pointer value that evaluates to zero"); 
        addTerm("object","has several meanings. In C++, often refers to an instance of a class. Also more loosely refers to any named declaration of a variable or other entity that involves storage"); 
        addTerm("object file","in C or C++, typically the output of a compiler. An object file consists of machine language plus an external name list that is resolved by a linker"); 
        addTerm("object layout","refers to the ordering of data members within a class"); 
        addTerm("object-oriented","this term has various definitions, usually including the notions of derived classes and virtual functions. See data abstraction"); 
        addTerm("pointer","an address of an object"); 
        addTerm("pointer to data member","a pointer that points at a data member of a class"); 
        addTerm("pointer to function","an address of a function or a member function"); 
        addTerm("pointer to member","see pointer to data member, pointer to function"); 
        addTerm("private","a C++ keyword used to specify that a class member can only be accessed from member functions and friends of the class. See access control, protected, and public"); 
        addTerm("reference","another name for an object. Access to an object via a reference is like manipulating the object itself. References are typically implemented as pointers in the underlying generated code"); 
        addTerm("scope","the region of a program where a name has visibility"); 
        addTerm("type","a property of a name that determines how it can be used. For example, an object of a class type cannot be assigned to an integer variable"); 
        addTerm("variable","an object that can be assigned to");
    }
}
